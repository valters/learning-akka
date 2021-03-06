name := "learning-akka"

version := "1.0"

scalaVersion := "2.12.8"

val akkaVersion = "2.5.21"
val akkaHttpVersion = "10.1.7"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
  "com.typesafe.akka" %% "akka-persistence" % akkaVersion,
  "com.typesafe.akka" %% "akka-persistence-typed" % akkaVersion,
  "com.typesafe.akka" %% "akka-cluster" % akkaVersion,
  "com.typesafe.akka" %% "akka-cluster-typed" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream-typed" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % Test,
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion,
  //"com.ning" % "async-http-client" % "1.9.40",
  //"org.jsoup" % "jsoup" % "1.11.3",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "org.scalacheck" %% "scalacheck" % "1.14.0",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test",
  "junit" % "junit" % "4.12" % "test"
)


libraryDependencies += {
  "com.lihaoyi" % "ammonite" % "1.6.3" % "test" cross CrossVersion.full
}

sourceGenerators in Test += Def.task {
  val file = (sourceManaged in Test).value / "amm.scala"
  IO.write(file, """object amm extends App { ammonite.Main.main(args) }""")
  Seq(file)
}.taskValue

/*
// Optional, required for the `source` command to work
(fullClasspath in Test) ++= {
  (updateClassifiers in Test).value
    .configurations
    .find(_.configuration == Test.name)
    .get
    .modules
    .flatMap(_.artifacts)
    .collect{case (a, f) if a.classifier == Some("sources") => f}
}
*/

addCommandAlias("amm", s"test:runMain amm --predef ammonite-init.sc")

// initialize REPL
initialCommands := s"""
    // import fs2._, cats.effect._, cats.effect.implicits._, cats.implicits._
    // import scala.concurrent.ExecutionContext.Implicits.global
    import scala.concurrent.duration._
    // import scala.language.higherKinds
  """

