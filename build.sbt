lazy val root = (project in file(".")).
  settings(
    organization := "za.co.kierendavies",
    name := "tweetfeed",
    version := "1.0",
    scalaVersion := "2.11.6",

    libraryDependencies += "com.github.scopt" %% "scopt" % "3.3.0",
    libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
    libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.7.12",

    libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test",
    libraryDependencies += "org.scalamock" %% "scalamock-scalatest-support" % "3.2" % "test"
  )
