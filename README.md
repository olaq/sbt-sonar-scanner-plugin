# sbt-sonar-scanner-plugin
SonarQube Scanner for SBT

[![Build Status](https://travis-ci.org/olaq/sbt-sonar-scanner-plugin.svg?branch=master)](https://travis-ci.org/olaq/sbt-sonar-scanner-plugin)

## About
This is sbt plugin that is a wrapper for sonar-scanner (similar to Ant or Gradle scanners).
Uses embedded sonar-scanner-api, hence does not require you to download separate binaries manually.

## Install
Add following to statement to project/plugins.sbt
```scala
addSbtPlugin("com.olaq" % "sbt-sonar-scanner-plugin" % "1.1.0")
```

## Configuration
Add `sonarProperties` to build.sbt. Example:
```scala
sonarProperties ++= Map(
  "sonar.host.url" -> "http://sonar.somewhere.com:9000",
  "sonar.java.source" -> "1.8",
  "sonar.junit.reportsPath" -> "target/test-reports",
  "sonar.jacoco.reportPaths" -> "target/scala-2.11/jacoco/jacoco.exec"
)
```
Any valid sonar parameter can be used.
Valid parameters can be found here: https://docs.sonarqube.org/display/SONAR/Analysis+Parameters
  
Following parameters have default value provided by plugin.
      
      "sonar.host.url"
      "sonar.projectKey"
      "sonar.projectVersion"
      "sonar.sources"
      "sonar.java.binaries"
      "sonar.java.test.binaries"
      "sonar.java.libraries"
      "sonar.java.test.libraries"
      
To print properties run:
```sbtshell
sbt printSonarProperties
```

## Run
```sbtshell
sbt sonar
```


