// https://github.com/typesafehub/sbteclipse/wiki/Using-sbteclipse
// Important - Set the JAVAFX_HOME environment variable to the root of your JavaFX installation for this script to work
//
// You can also set your scala or java home if necessary like this:
// javaHome := Some(file("/Library/Java/JavaVirtualMachines/1.6.0_24-b07-330.jdk/Contents/Home"))
// scalaHome := Some(file("/Users/Sven/scala-2.9.1/"))
// javaHome := Some(file("/Library/Java/JavaVirtualMachines/1.7.0.jdk/Contents/Home"))

javaHome := Some(file(System.getenv("JAVA_HOME")))

name := "dockfx"

version := "1.0-SNAPSHOT"

organization := "dockfx"

scalaVersion := "2.10.1"

resolvers += "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository"

// append several options to the list of options passed to the Java compiler
javacOptions ++= Seq("-source", "1.7", "-target", "1.7")

// append -deprecation to the options passed to the Scala compiler
scalacOptions += "-deprecation"

// define the statements initially evaluated when entering 'console', 'console-quick', or 'console-project'
initialCommands := """
import System.{currentTimeMillis => now}
def time[T](f: => T): T = {
val start = now
try { f } finally { println("Elapsed: " + (now - start)/1000.0 + " s") }
}
"""

libraryDependencies += "org.scalafx" % "scalafx_2.10" % "1.0.0-M2"

// set the main class for the main 'run' task
// change Compile to Test to set it for 'test:run'
mainClass in (Compile, run) := Some("dockfx.DockFX")

// disable updating dynamic revisions (including -SNAPSHOT versions)
offline := true

// set the prompt (for this build) to include the project id.
shellPrompt in ThisBuild := { state => Project.extract(state).currentRef.project + "> " }

// set the prompt (for the current project) to include the username
shellPrompt := { state => System.getProperty("user.name") + "> " }

// disable printing timing information, but still print [success]
showTiming := false

// disable printing a message indicating the success or failure of running a task
showSuccess := false

// change the format used for printing task completion time
timingFormat := {
import java.text.DateFormat
DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT)
}

// disable using the Scala version in output paths and artifacts
crossPaths := false

// fork a new JVM for 'run' and 'test:run'
fork := true

// fork a new JVM for 'test:run', but not 'run'
fork in Test := true

// add a JVM option to use when forking a JVM for 'run'
javaOptions ++= Seq (
"-Xmx512M" ,
"-Djavafx.verbose"
// , "-Djava.library.path=lib_managed/jars/com.oracle/javafx-runtime"
)

// only use a single thread for building
parallelExecution := false

// Execute tests in the current project serially
// Tests from other projects may still run concurrently.
parallelExecution in Test := false

// add JavaFX 2.0 to the unmanaged classpath
// unmanagedJars in Compile += Attributed.blank(file(System.getenv("JAVAFX_HOME") + "/rt/lib/jfxrt.jar"))
// For Java 7 update 06 the JFXRT JAR is part of the Java Runtime Environment
unmanagedJars in Compile += Attributed.blank(file(System.getenv("JAVA_HOME") + "/jre/lib/jfxrt.jar"))

// publish test jar, sources, and docs
publishArtifact in Test := false

// disable publishing of main docs
publishArtifact in (Compile, packageDoc) := false

// change the classifier for the docs artifact
artifactClassifier in packageDoc := Some("doc")

// Copy all managed dependencies to <build-root>/lib_managed/
// This is essentially a project-local cache and is different
// from the lib_managed/ in sbt 0.7.x. There is only one
// lib_managed/ in the build root (not per-project).
retrieveManaged := true

EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource

EclipseKeys.eclipseOutput := Some("target")

EclipseKeys.executionEnvironment := Some(EclipseExecutionEnvironment.JavaSE17)

EclipseKeys.withSource := true


