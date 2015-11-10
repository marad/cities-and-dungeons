import com.lihaoyi.workbench.Plugin._

enablePlugins(ScalaJSPlugin)

workbenchSettings

name := "Cities & Dungeons"
version := "0.1-SNAPSHOT"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "io.github.widok" %%% "widok" % "0.2.2"
  )

bootSnippet := "example.ScalaJSExample().main(document.getElementById('canvas'));"
updateBrowsers <<= updateBrowsers.triggeredBy(fastOptJS in Compile)

persistLauncher := true
