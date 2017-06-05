package com.olaq.sbt

import java.util.Properties

import org.sonarsource.scanner.api.{EmbeddedScanner, LogOutput}
import sbt.{Def, _}

object SonarScannerPlugin extends AutoPlugin {

  object autoImport {
    val sonarProperties: SettingKey[Map[String, String]] = SettingKey("settings for sonar scanner")
    val sonarScan: TaskKey[Unit] = TaskKey[Unit]("Runs sonar-scanner")
  }

  import autoImport._

  override lazy val projectSettings = Seq(
    sonarScan := sonarScanTask.value
  )

  private lazy val sonarScanTask = Def.task {
    println("Sonar scanner run")
    val properties = mapProperties()
    val runner = EmbeddedScanner.create(new LogOutputImpl())
      .addGlobalProperties(properties)
      .setApp("sbt", "1.0")

    try {
      runner.start()
      runner.runAnalysis(properties)
    } finally runner.stop()
  }

  def mapProperties(): Properties = {
    // TODO: map properties properly
    val properties: _root_.java.util.Properties = new Properties()
    properties.setProperty("sonar.host.url", "http://localhost:9009")
    properties.setProperty("sonar.projectKey", "PlaySmapleTODO")
    properties.setProperty("sonar.sources", "app")
    properties
  }

  class LogOutputImpl extends LogOutput {
    override def log(formattedMessage: String, level: LogOutput.Level): Unit = {
      // TODO log it properly
      println(formattedMessage)
    }
  }

}