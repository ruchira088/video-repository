import Dependencies._

lazy val root =
  (project in file("."))
    .enablePlugins(PlayScala, BuildInfoPlugin)
    .settings(
      name := "video-repository",
      organization := "com.ruchij",
      version := "1.0-SNAPSHOT",
      scalaVersion := "2.12.6",
      buildInfoKeys := BuildInfoKey.ofN(name, organization, version, scalaVersion, sbtVersion),
      buildInfoPackage := "project.information",
      scalacOptions ++= List("-feature"),
      libraryDependencies ++=
        dependencies ++ testDependencies.map(_ % Test)
    )

lazy val dependencies =
  List(guice, jodaTime, playSlick, playSlickEvolutions, postgresql, h2, tikaCore, scalaLogging, logbackClassic)

lazy val testDependencies =
  List(scalaTestPlusPlay)

addCommandAlias("runWithPostgres", "run -Dconfig.file=conf/application.postgres.conf")