package org.edla.dico

import java.io._
import java.nio.file.Paths
import java.util.zip.GZIPOutputStream

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream._
import akka.stream.alpakka.xml.scaladsl.XmlParsing
import akka.stream.alpakka.xml.{EndElement, StartElement, TextEvent}
import akka.stream.scaladsl.{
  Broadcast,
  FileIO,
  Flow,
  GraphDSL,
  Partition,
  RunnableGraph,
  Sink,
  Source,
  StreamConverters
}
import akka.util.ByteString
import com.typesafe.config.ConfigFactory
import org.edla.dico.utils.BZip2MultiStreamCompressorInputStream

import scala.collection.immutable
import scala.concurrent.{ExecutionContext, Future}

object DicoBuilder extends App {
  println("Your dictionary is being built. Please wait.")

  implicit val ec = ExecutionContext.global

  val conf = ConfigFactory.load().getConfig("dictionary-builder")

  val rootDirectory     = conf.getString("root")
  val wordsFile         = Paths.get(conf.getString("wordsFile"))
  val excludedWordsFile = Paths.get(conf.getString("excludedWordsFile"))
  val xmlDump           = conf.getString("xmlDump")
  val expression        = conf.getBoolean("expression")
  val languageFilter    = conf.getBoolean("languageFilter")
  val language          = conf.getString("language")
  val languageShort     = conf.getString("languageShort")

  implicit val system: ActorSystem = ActorSystem("dictionary-builder")
  implicit val mat: Materializer   = ActorMaterializer()

  private def isValidWord(element: (String, String)): Boolean = {
    val (word, definition) = element
    //println("===========================")
    //println(definition)
    !List("/", ":").exists(word.contains) && (expression || !word.contains(" ")) &&
    definition.nonEmpty && (!languageFilter || List(
      "==" + language + "==",
      "== " + language + " ==",
      "== {{langue|" + languageShort + "}} ==",
      "== {{langue|" + languageShort + "}}==",
      "=={{langue|" + languageShort + "}}==",
      "=={{langue|" + languageShort + "}} =="
    ).exists(definition.contains))
  }

  def locate(word: String): String = {
    if (word.length == 1) rootDirectory + File.separator + word
    else rootDirectory + File.separator + word.charAt(0) + File.separator + word.charAt(1)
  }

  private def buildDefinitionFiles(word: String, definition: String): Unit = {
    val directory = locate(word)
    try {
      new File(directory).mkdirs
      val writer = new BufferedOutputStream(
        new GZIPOutputStream(new FileOutputStream(directory + File.separator + word + ".gz"))
      )
      /*
       * ZipOutputStream writer = new ZipOutputStream( new
       * BufferedOutputStream(new FileOutputStream(directory + "/" + word
       * + ".zip")));
       */
      writer.write(definition.getBytes)
      // FileWriter writer = new FileWriter(directory+"/" + word);
      // writer.write(definition);
      writer.close()
    } catch {
      case e: IOException =>
        System.out.println(word + " " + directory)
        e.printStackTrace()
    }
  }

  val xmlParserFlow: Flow[ByteString, (String, String), NotUsed] =
    XmlParsing.parser
      .statefulMapConcat(() => {
        // state
        var insidePage  = false
        var insideTitle = false
        var insideText  = false
        val titleBuffer = StringBuilder.newBuilder
        val textBuffer  = StringBuilder.newBuilder
        // aggregation function
        _ match {
          case StartElement("page", _, _, _, _) =>
            insidePage = true
            immutable.Seq.empty
          case StartElement("title", _, _, _, _) =>
            insideTitle = true
            titleBuffer.clear()
            immutable.Seq.empty
          case StartElement("text", _, _, _, _) =>
            insideText = true
            textBuffer.clear()
            immutable.Seq.empty
          case EndElement("page") =>
            val pageText  = textBuffer.toString
            val titleText = titleBuffer.toString
            insidePage = false
            immutable.Seq((titleText, pageText))
          case EndElement("title") =>
            insideTitle = false
            immutable.Seq.empty
          case EndElement("text") =>
            insideText = false
            immutable.Seq.empty
          case t: TextEvent =>
            if (insideTitle)
              titleBuffer.append(t.text)
            if (insidePage && insideText)
              textBuffer.append(t.text)
            immutable.Seq.empty
          case _ =>
            immutable.Seq.empty
        }
      })

  val validWordSink: Sink[ByteString, Future[IOResult]]    = FileIO.toPath(wordsFile)
  val excludedWordSink: Sink[ByteString, Future[IOResult]] = FileIO.toPath(excludedWordsFile)
  val countValidWordSink: Sink[ByteString, Future[Int]]    = Sink.fold[Int, ByteString](0)((acc, _) => acc + 1)
  val countExcludedWordSink: Sink[ByteString, Future[Int]] = Sink.fold[Int, ByteString](0)((acc, _) => acc + 1)

  val g: RunnableGraph[(Future[IOResult], Future[IOResult], Future[Int], Future[Int])] =
    RunnableGraph.fromGraph(
      GraphDSL.create(validWordSink, excludedWordSink, countValidWordSink, countExcludedWordSink)((_, _, _, _)) {
        implicit builder: GraphDSL.Builder[(Future[IOResult], Future[IOResult], Future[Int], Future[Int])] =>
          (valid, excluded, countValid, countExcluded) =>
            import akka.stream.scaladsl.GraphDSL.Implicits._
            //https://stackoverflow.com/questions/49225365/count-number-of-elements-in-akka-streams
            val broadcastValid    = builder.add(Broadcast[ByteString](2))
            val broadcastExcluded = builder.add(Broadcast[ByteString](2))
            val uncompressedXmlDumpSource: Source[ByteString, Future[IOResult]] =
              StreamConverters.fromInputStream(
                () => new BZip2MultiStreamCompressorInputStream(new FileInputStream(xmlDump))
              )
            val partition: UniformFanOutShape[(String, String), (String, String)] =
              builder.add(Partition[(String, String)](2, e => if (isValidWord(e)) 1 else 0))
            val excludedWordFlow: Flow[(String, String), ByteString, NotUsed] =
              Flow[(String, String)].mapAsync(64)(t => Future(ByteString(t._1 + "\n")))
            val validWordFlow: Flow[(String, String), ByteString, NotUsed] =
              Flow[(String, String)].mapAsync(64) { t =>
                buildDefinitionFiles(t._1, t._2)
                Future(ByteString(t._1 + "\n"))
              }
            uncompressedXmlDumpSource
              .buffer(50, OverflowStrategy.backpressure)
              .async ~> xmlParserFlow.async ~> partition.in
            partition.out(0) ~> excludedWordFlow.async ~> broadcastExcluded.in
            broadcastExcluded.out(0) ~> excluded
            broadcastExcluded.out(1) ~> countExcluded
            partition.out(1) ~> validWordFlow.async ~> broadcastValid.in
            broadcastValid.out(0) ~> valid
            broadcastValid.out(1) ~> countValid
            ClosedShape
      }
    )

  val done: (Future[IOResult], Future[IOResult], Future[Int], Future[Int]) = g.async.run()

  val aggFut: Future[(IOResult, IOResult, Int, Int)] = for {
    validResults        <- done._1
    excludedResults     <- done._2
    countValidResult    <- done._3
    countExcludedResult <- done._4
  } yield (validResults, excludedResults, countValidResult, countExcludedResult)

  aggFut.map { r =>
    println(s"total number of entries: ${r._3}")
    println(s"total number of removed entries: ${r._4}")
    system.terminate()
  }

  aggFut.failed.map { _ =>
    println("Opus something went wrong")
    println(s"Please, create the root folder $rootDirectory")
    println("and/or check/fix your config file application.conf and try again.")
    system.terminate()
  }

}
