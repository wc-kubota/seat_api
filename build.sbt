name := """SeatAppApi"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)aggregate(codegen)
lazy val codegen = (project in file("codegen")).settings(
    name := "codegen",
    version := "1.0",
    libraryDependencies += "com.typesafe.play" %% "play-slick" % "5.0.0",
    libraryDependencies += "com.typesafe.slick" %% "slick" % "3.3.3",
    libraryDependencies += "com.typesafe.slick" %% "slick-codegen" % "3.3.3",
    libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.49",
    libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.31",
    libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.7.31",
)

scalaVersion := "2.13.6"

libraryDependencies ++= Seq(
    guice,
    ws,
    "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
    "org.typelevel" %% "cats-core" % "2.2.0",
    "org.typelevel" %% "cats-effect" % "2.5.1",
    "org.typelevel" %% "mouse" % "1.1.0",
    "com.typesafe.play" %% "play-mailer" % "8.0.1",
    "com.typesafe.play" %% "play-mailer-guice" % "8.0.1",
    "com.typesafe.play" %% "play-slick" % "5.0.0",
    "com.typesafe.slick" %% "slick-codegen" % "3.3.3",
    "com.dripower" %% "play-circe" % "2814.2",
    "io.circe" %% "circe-generic-extras" % "0.14.1",
    "mysql" % "mysql-connector-java" % "5.1.49",
    "com.ibm.icu" % "icu4j" % "69.1",
    "com.opencsv" % "opencsv" % "5.5",
    "com.google.cloud" % "google-cloud-storage" % "2.4.2",
)

val circeVersion = "0.14.1"

libraryDependencies ++= Seq(
    "io.circe" %% "circe-core",
    "io.circe" %% "circe-generic",
    "io.circe" %% "circe-parser",
    "io.circe" %% "circe-generic-extras"
).map(_ % circeVersion)

scalacOptions ++= Seq(
    "-Ymacro-annotations",
)

// Slick CodeGen
// code generation task
addCommandAlias("codegen", "codegen/runMain SlickModelGenerator")

//sources in (Compile, doc) := Seq.empty
//publishArtifact in (Compile, packageDoc) := false


// ▼ default writing

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "app.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "app.binders._"
