# sbt-sonar-scanner-plugin
SonarQube Scanner for SBT

[![Build Status](https://travis-ci.org/olaq/sbt-sonar-scanner-plugin.svg?branch=master)](https://travis-ci.org/olaq/sbt-sonar-scanner-plugin)

#About
This is sbt plugin that is a wrapper for sonar-scanner (similar to Ant or Gradle scanners).
Uses embedded sonar-scanner-api, hence does not require you to download separate binaries manually.

#Install
Add following to statement to project/plugins.sbt
```scala
resolvers += Resolver.url(
    "bintray-olaq-sbt-plugins",
    url("http://dl.bintray.com/olaq/sbt-plugins"))(
    Resolver.ivyStylePatterns)

addSbtPlugin("com.olaq" % "sbt-sonar-scanner-plugin" % "1.0.0")
```

#Configuration
Add following statement to build.sbt. You can use any valid sonar parameter.
```scala
sonarProperties ++= Map(
      "sonar.host.url" -> "http://sonar.somewhere.com:9000"
)
```
Some parameters are set by default based on project settings (like name, version).  
Valid parameters can be found here: https://docs.sonarqube.org/display/SONAR/Analysis+Parameters

#Run
```sbtshell
sbt sonar
```


