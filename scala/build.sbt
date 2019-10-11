name := "fp_train"
version := "0.0.1"
scalaVersion := "2.12.10"

val ZioVersion        = "1.0.0-RC14"
val ScalazVersion     = "7.2.28"
val CatsVersion       = "2.0.0"
val SimulaVersion     = "1.0.0"
val ScalacheckVersion = "1.14.0"

lazy val commonSettings = Seq(
  maxErrors := 5,
  scalacOptions --= Seq(
    "-Xfatal-warnings"
  ),
  libraryDependencies ++= Seq(
    "org.typelevel" %% "cats-core"   % CatsVersion,
    "org.scalaz"    %% "scalaz-core" % ScalazVersion,
    "dev.zio"       %% "zio"         % ZioVersion,
    "org.typelevel" %% "simulacrum"  % SimulaVersion
    //"org.scalacheck" %% "scalacheck" % scalacheckVersion,
  )
)

lazy val main = project
  .in(file("main"))
  .settings(commonSettings)

lazy val home = project
  .in(file("home"))
  .settings(commonSettings)

lazy val grad = project
  .in(file("grad"))
  .settings(commonSettings)

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full)

// Aliases
addCommandAlias("com", "all compile test:compile")
addCommandAlias("lint", "; compile:scalafix --check ; test:scalafix --check")
addCommandAlias("fix", "all compile:scalafix test:scalafix")
addCommandAlias("fmt", "; scalafmtSbt; scalafmtAll; test:scalafmtAll")
//addCommandAlias("fmt", "; scalafmtSbt;")
addCommandAlias("chk", "; scalafmtSbtCheck; scalafmtCheck; test:scalafmtCheck")
