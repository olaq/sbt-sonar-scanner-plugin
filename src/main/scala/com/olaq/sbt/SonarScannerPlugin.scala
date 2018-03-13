package com.olaq.sbt

import java.util.Properties

import org.sonarsource.scanner.api.{EmbeddedScanner, LogOutput}
import sbt.{Def, _}
import Keys._

import scala.collection.JavaConverters._

object SonarScannerPlugin extends AutoPlugin {

  object autoImport {
    val sonar: TaskKey[Unit] = taskKey[Unit]("Runs sonar-scanner")
    val printSonarProperties: TaskKey[Unit] = taskKey[Unit]("Prints Sonar properties for current project")
    val sonarProperties: TaskKey[Map[String, String]] = taskKey[Map[String, String]]("Sonar Scanner properties")
  }

  import autoImport._

  override def trigger: PluginTrigger = allRequirements

  override def projectSettings = Seq(
    sonar := sonarScanTask.value,
    printSonarProperties := printSonarPropertiesTask.value,
    sonarProperties := sonarPropertiesTask.value
  )

  private lazy val sonarPropertiesTask = Def.task {
    Map(
      "sonar.host.url" -> "http://localhost:9000",
      "sonar.projectKey" -> s"${organization.value}:${name.value}",
      "sonar.projectVersion" -> version.value,
      "sonar.sources" -> sourceDirectory.in(Compile).value.absolutePath,
      "sonar.tests" -> sourceDirectory.in(Test).value.absolutePath,
      "sonar.java.binaries" -> classDirectory.in(Compile).value.absolutePath,
      "sonar.java.test.binaries" -> classDirectory.in(Test).value.absolutePath,
      "sonar.java.libraries" -> dependencyClasspath.in(Compile).value
        .map(p => p.data.absolutePath)
        .mkString(","),
      "sonar.java.test.libraries" -> dependencyClasspath.in(Test).value
        .map(p => p.data.absolutePath)
        .filter(s => s.endsWith(".jar"))
        .mkString(",")
    )
  }

  private lazy val sonarScanTask = Def.task {
    val properties = new Properties()
    properties.putAll(sonarProperties.value.asJava)

    val runner = EmbeddedScanner.create(new LogOutputImpl(sLog.value))
      .addGlobalProperties(properties)

    runner.start()
    runner.runAnalysis(properties)
    runner.stop()
  }

  private lazy val printSonarPropertiesTask = Def.task {
    val logger = sLog.value
    logger.info("Printing sonar properties:")
    logger.info(sonarProperties.value.mkString("\n"))
  }

  class LogOutputImpl(logger: Logger) extends LogOutput {
    override def log(formattedMessage: String, level: LogOutput.Level): Unit = {
      level match {
        case LogOutput.Level.TRACE => logger.debug(formattedMessage)
        case LogOutput.Level.DEBUG => logger.debug(formattedMessage)
        case LogOutput.Level.INFO => logger.info(formattedMessage)
        case LogOutput.Level.WARN => logger.warn(formattedMessage)
        case LogOutput.Level.ERROR => logger.error(formattedMessage)
      }
    }
  }

}