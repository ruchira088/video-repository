import sbt._

object Dependencies
{
  lazy val jodaTime = "joda-time" % "joda-time" % "2.10"

  lazy val playSlick = "com.typesafe.play" %% "play-slick" % "3.0.3"

  lazy val playSlickEvolutions = "com.typesafe.play" %% "play-slick-evolutions" % "3.0.3"

  lazy val postgresql = "org.postgresql" % "postgresql" % "42.2.4"

  lazy val h2 = "com.h2database" % "h2" % "1.4.197"

  lazy val tikaCore = "org.apache.tika" % "tika-core" % "1.18"

  lazy val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging" % "3.9.0"

  lazy val logbackClassic = "ch.qos.logback" % "logback-classic" % "1.2.3"

  lazy val scalaTestPlusPlay = "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2"
}
