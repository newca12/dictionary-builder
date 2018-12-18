package org.edla.dico

import java.io._
import java.nio.file.Paths
import java.util.zip.GZIPOutputStream

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream._
import akka.stream.alpakka.xml.scaladsl.XmlParsing
import akka.stream.alpakka.xml.{EndElement, StartElement, TextEvent}
import akka.stream.scaladsl.{FileIO, Flow, GraphDSL, Partition, RunnableGraph, Sink, Source, StreamConverters}
import akka.util.ByteString
import com.typesafe.config.ConfigFactory
import org.edla.dico.utils.BZip2MultiStreamCompressorInputStream

import scala.collection.immutable
import scala.concurrent.Future

object DicoBuilder extends App {
  println("Your dictionary is being built. Please wait.")

  val conf = ConfigFactory.load().getConfig("dictionary-builder")

  val root              = conf.getString("root")
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
    if (word.contains("/") || word.contains(":")) return false
    if (!expression && word.contains(" ")) return false
    if ((!languageFilter && !definition.isEmpty)
        || definition.contains("==" + language + "==")   //needed for English wiktionary
        || definition.contains("== " + language + " ==") //neeeded for Nepali wiktionary
        //needed for French wiktionary
        || definition
          .contains("== {{langue|" + languageShort + "}} ==")
        || definition.contains("== {{langue|" + languageShort + "}}==")
        || definition.contains("=={{langue|" + languageShort + "}}==")
        || definition.contains("=={{langue|" + languageShort + "}} =="))
      true
    else false
  }

  def locate(word: String): String = {
    val rootDirectory = "/tmp/dico"
    if (word.length == 1) rootDirectory + "/" + word
    else rootDirectory + "/" + word.charAt(0) + "/" + word.charAt(1)
  }

  private def buildDefinitionFiles(word: String, definition: String): Unit = {
    val directory = locate(word)
    try {
      new File(directory).mkdirs
      val writer = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(directory + "/" + word + ".gz")))
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
      case e: IOException ⇒
        System.out.println(word + " " + directory)
        e.printStackTrace()
    }
  }

  val xmlParserFlow: Flow[ByteString, (String, String), NotUsed] =
    XmlParsing.parser
      .statefulMapConcat(() ⇒ {
        // state
        var insidePage  = false
        var insideTitle = false
        var insideText  = false
        val titleBuffer = StringBuilder.newBuilder
        val textBuffer  = StringBuilder.newBuilder
        // aggregation function
        _ match {
          case StartElement("page", _, _, _, _) ⇒
            insidePage = true
            immutable.Seq.empty
          case StartElement("title", _, _, _, _) ⇒
            insideTitle = true
            titleBuffer.clear()
            immutable.Seq.empty
          case StartElement("text", _, _, _, _) ⇒
            insideText = true
            textBuffer.clear()
            immutable.Seq.empty
          case EndElement("page") ⇒
            val pageText  = textBuffer.toString
            val titleText = titleBuffer.toString
            insidePage = false
            immutable.Seq((titleText, pageText))
          case EndElement("title") ⇒
            insideTitle = false
            immutable.Seq.empty
          case EndElement("text") ⇒
            insideText = false
            immutable.Seq.empty
          case t: TextEvent ⇒
            if (insideTitle)
              titleBuffer.append(t.text)
            if (insidePage && insideText)
              textBuffer.append(t.text)
            immutable.Seq.empty
          case _ ⇒
            immutable.Seq.empty
        }
      })

  val g = RunnableGraph.fromGraph(GraphDSL.create() { implicit builder: GraphDSL.Builder[NotUsed] ⇒
    import akka.stream.scaladsl.GraphDSL.Implicits._

    val uncompressedOsmFileSource: Source[ByteString, Future[IOResult]] =
      StreamConverters.fromInputStream(() ⇒ new BZip2MultiStreamCompressorInputStream(new FileInputStream(xmlDump)))
    val partition: UniformFanOutShape[(String, String), (String, String)] =
      builder.add(Partition[(String, String)](2, e ⇒ if (isValidWord(e)) 1 else 0))
    val excludedWordFlow: Flow[(String, String), ByteString, NotUsed] =
      Flow[(String, String)].map(t ⇒ ByteString(t._1 + "\n"))
    val validWordSink: Sink[ByteString, Future[IOResult]] = FileIO.toPath(wordsFile)
    val validWordFlow: Flow[(String, String), ByteString, NotUsed] =
      Flow[(String, String)].map { t ⇒
        buildDefinitionFiles(t._1, t._2)
        ByteString(t._1 + "\n")
      }
    val excludedWordSink: Sink[ByteString, Future[IOResult]] =
      FileIO.toPath(excludedWordsFile)
    uncompressedOsmFileSource ~> xmlParserFlow ~> partition.in
    partition.out(0) ~> excludedWordFlow ~> excludedWordSink
    partition.out(1) ~> validWordFlow ~> validWordSink
    ClosedShape
  })

  g.run()
}
