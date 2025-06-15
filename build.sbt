name := """FIAP-X-web"""
organization := "com.fiapx"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.16"

libraryDependencies += guice
libraryDependencies += ws
libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.8.5"
libraryDependencies += "org.apache.httpcomponents.client5" % "httpclient5" % "5.2.1"
libraryDependencies += "org.apache.httpcomponents.core5" % "httpcore5" % "5.2.1"
libraryDependencies += "org.apache.httpcomponents" % "httpmime" % "4.5.14"


