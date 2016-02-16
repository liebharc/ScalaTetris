name := "scala-tetris"
 
version := "0.1"
 
scalaVersion := "2.11.7"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"
 
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.4-SNAPSHOT"
libraryDependencies += "com.novocode" % "junit-interface" % "0.8" % "test->default"
libraryDependencies += "org.scala-lang" % "scala-swing" % "2.11+"