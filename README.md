# sbt-sonar-scanner-plugin
SonarQube Scanner for SBT

[![Build Status](https://travis-ci.org/olaq/sbt-sonar-scanner-plugin.svg?branch=master)](https://travis-ci.org/olaq/sbt-sonar-scanner-plugin)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/9142bef73f4b4fd2bb2c77c38dab42fd)](https://www.codacy.com/app/olaq.github/sbt-sonar-scanner-plugin?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=olaq/sbt-sonar-scanner-plugin&amp;utm_campaign=Badge_Grade)

## About
This is sbt plugin that is a wrapper for sonar-scanner (similar to Ant or Gradle scanners).
Uses embedded sonar-scanner-api, hence does not require you to download separate binaries manually.

## Install
Add following to statement to project/plugins.sbt
```scala
addSbtPlugin("com.olaq" % "sbt-sonar-scanner-plugin" % "1.3.0")
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


