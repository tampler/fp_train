name := "fp_train"
version := "1.0"
scalaVersion := "2.12.8"
val ZIOVersion = "1.0-RC4"




lazy val commonSettings = Seq(

  maxErrors := 5,
  scalacOptions ++= Seq(
    "-language:_",
    "-Ypartial-unification",
    "-Xfatal-warnings"),
  libraryDependencies ++= Seq(
    "com.github.mpilquist"  %% "simulacrum" % "0.16.0",
    "org.scalaz" %% "scalaz-zio" % ZIOVersion,
    "org.scalacheck" %% "scalacheck" % "1.14.0"
  )

)


lazy val main = project.in(file("main"))
  .settings(commonSettings)

lazy val home = project.in(file("home"))
  .settings(commonSettings)

lazy val grad = project.in(file("grad"))
  .settings(commonSettings)

