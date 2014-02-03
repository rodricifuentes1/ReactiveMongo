name := "ReactiveMongo"

version :="1.0"

scalaVersion := "2.10.3"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
 
libraryDependencies ++= {
  Seq(
    "com.typesafe.akka"   %%  "akka-actor"    % "2.2.3",
    "org.reactivemongo" %% "reactivemongo" % "0.10.0",
"junit" % "junit" % "4.8.1" % "test",
"org.scalatest" % "scalatest_2.10" % "2.0" % "test"
  )
}
