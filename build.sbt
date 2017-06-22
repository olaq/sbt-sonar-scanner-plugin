name := "sbt-sonar-scanner-plugin"

version := "1.1.0"

organization := "com.olaq"

scalaVersion := "2.10.6"

sbtPlugin := true

libraryDependencies += "org.sonarsource.scanner.api" % "sonar-scanner-api" % "2.9.0.887"

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))