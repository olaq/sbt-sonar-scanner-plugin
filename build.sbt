
lazy val commonSettings = Seq(
  version in ThisBuild := "1.3.0",
  organization in ThisBuild := "com.olaq"
)

lazy val root = (project in file(".")).settings(
  commonSettings,

  sbtPlugin := true,
  name := "sbt-sonar-scanner-plugin",
  crossSbtVersions := Seq("0.13.16", "1.1.0"),
  licenses += ("MIT", url("http://opensource.org/licenses/MIT")),
  bintrayRepository := "sbt-plugins",
  bintrayOrganization in bintray := None,
  libraryDependencies += "org.sonarsource.scanner.api" % "sonar-scanner-api" % "2.9.0.887",
)

scriptedLaunchOpts := { scriptedLaunchOpts.value ++
  Seq("-Xmx512M", "-Dplugin.version=" + version.value)
}
scriptedBufferLog := false