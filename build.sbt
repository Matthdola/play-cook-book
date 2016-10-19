name := """cook-book"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "joda-time" % "joda-time" % "2.5",
  "com.vividsolutions" % "jts" % "1.13" withSources(),
  "org.mongodb" % "mongo-java-driver" % "2.12.4" withSources(),
  "org.geotools" % "gt-data" %  "12.1" withSources(),
  "org.geotools" % "gt-shapefile" % "12.1" withSources(),
  "org.geotools" % "gt-xml" % "12.1" withSources(),
  "org.geotools.xsd" % "gt-xsd-kml" % "12.1" withSources(),
  "org.apache.commons" % "commons-csv" % "1.4" withSources(),
  "commons-validator" % "commons-validator" % "1.5.1"  withSources()
)
