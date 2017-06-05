name := "sbt-sonar-scanner-plugin"

version := "1.0.0-SNAPSHOT"

organization := "com.github.olaq"

scalaVersion := "2.10.6"

sbtPlugin := true

libraryDependencies += "org.sonarsource.scanner.api" % "sonar-scanner-api" % "2.9.0.887"
        