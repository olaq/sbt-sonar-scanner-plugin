package com.olaq.sbt

import java.util.Properties

import org.sonarsource.scanner.api.{EmbeddedScanner, LogOutput}
import sbt.{Def, _}
import Keys._

import scala.collection.JavaConversions

object SonarScannerPlugin extends AutoPlugin {

  object autoImport {
    val sonarScan: TaskKey[Unit] = taskKey[Unit]("Runs sonar-scanner")
    val printSonarProperties: TaskKey[Unit] = taskKey[Unit]("Prints Sonar properties for current project")
    val sonarProperties: SettingKey[Map[String, String]] = settingKey[Map[String, String]]("Sonar Scanner properties")
  }

  import autoImport._

  override def projectSettings = Seq(
    sonarScan := sonarScanTask.value,
    printSonarProperties := printSonarPropertiesTask.value,
    sonarProperties := Map(
      "sonar.host.url" -> "http://localhost:9000",
      "sonar.projectKey" -> s"${organization.value}:${name.value}",
      "sonar.projectVersion" -> version.value,
      "sonar.sources" -> sourceDirectory.in(Compile).value.absolutePath
    )
  )

  private lazy val sonarScanTask = Def.task {
    val properties = new Properties()
    properties.putAll(JavaConversions.mapAsJavaMap(sonarProperties.value))

    val runner = EmbeddedScanner.create(new LogOutputImpl())
      .addGlobalProperties(properties)
      .setApp("sbt", "1.0")

    try {
      runner.start()
      runner.runAnalysis(properties)
    } finally runner.stop()
  }

  private lazy val printSonarPropertiesTask = Def.task {
    sonarProperties.value.foreach(p => println(p._1 + "=" + p._2))
  }

  class LogOutputImpl extends LogOutput {
    override def log(formattedMessage: String, level: LogOutput.Level): Unit = {
      // TODO log it properly
      println(formattedMessage)
    }
  }

}