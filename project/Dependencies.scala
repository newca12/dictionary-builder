import sbt._

object Version {
  val akka = "2.5.19"
  val akkaStreamsKafka = "0.22"
  val akkaStreamAlpakka = "1.0-M1"
  val akkaStreamContrib = "0.9"
  val scopt = "3.7.0"
  val kamon = "1.1.3"
  val akkaStreamCheckpointKamon = "0.0.3"
  val kamonStatd = "1.0.0"
  val apacheCommonCompress = "1.18"
}

object Dependencies {
  val akkaActor = "com.typesafe.akka" %% "akka-actor" % Version.akka
  val akkaStream = "com.typesafe.akka" %% "akka-stream" % Version.akka
  val akkaStreamAlpakkaFile = "com.lightbend.akka" %% "akka-stream-alpakka-file" % Version.akkaStreamAlpakka
  val akkaStreamAlpakkaXml = "com.lightbend.akka" %% "akka-stream-alpakka-xml" % Version.akkaStreamAlpakka
  val apacheCommonCompress = "org.apache.commons" % "commons-compress" % Version.apacheCommonCompress
  val akkaStreamContrib = "com.typesafe.akka" %% "akka-stream-contrib" % Version.akkaStreamContrib
  val akkaSlf4j = "com.typesafe.akka" %% "akka-slf4j" % Version.akka
  val akkaTestkit = "com.typesafe.akka" %% "akka-testkit" % Version.akka
  val kamonStatd = "io.kamon" %% "kamon-statsd" % Version.kamonStatd
  val akkaStreamCheckpointKamon = "com.github.svezfaz" %% "akka-stream-checkpoint-kamon" % Version.akkaStreamCheckpointKamon
  val scopt = "com.github.scopt" %% "scopt" % Version.scopt
}
