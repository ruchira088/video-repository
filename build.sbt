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
      libraryDependencies ++=
        dependencies ++ testDependencies.map(_ % Test)
    )

lazy val dependencies =
  List(guice, jodaTime, playSlick, playSlickEvolutions, postgresql, h2)

lazy val testDependencies =
  List(scalaTestPlusPlay)