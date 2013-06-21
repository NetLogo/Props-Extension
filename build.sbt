scalaVersion := "2.9.2"

scalaSource in Compile <<= baseDirectory(_ / "src")

scalacOptions ++= Seq("-deprecation", "-unchecked", "-Xfatal-warnings",
                      "-encoding", "us-ascii")

libraryDependencies +=
  "org.nlogo" % "NetLogo" % "5.0.4" from
    "http://ccl.northwestern.edu/netlogo/5.0.4/NetLogo.jar"

artifactName := { (_, _, _) => "props.jar" }

packageOptions := Seq(
  Package.ManifestAttributes(
    ("Extension-Name", "props"),
    ("Class-Manager", "org.nlogo.extensions.props.PropsExtension"),
    ("NetLogo-Extension-API-Version", "5.0")))

packageBin in Compile <<= (packageBin in Compile, baseDirectory, streams) map {
  (jar, base, s) =>
    IO.copyFile(jar, base / "props.jar")
    Process("pack200 --modification-time=latest --effort=9 --strip-debug " +
            "--no-keep-file-order --unknown-attribute=strip " +
            "props.jar.pack.gz props.jar").!!
    if(Process("git diff --quiet --exit-code HEAD").! == 0) {
      Process("git archive -o props.zip --prefix=props/ HEAD").!!
      IO.createDirectory(base / "props")
      IO.copyFile(base / "props.jar", base / "props" / "props.jar")
      IO.copyFile(base / "props.jar.pack.gz", base / "props" / "props.jar.pack.gz")
      Process("zip props.zip props/props.jar props/props.jar.pack.gz").!!
      IO.delete(base / "props")
    }
    else {
      s.log.warn("working tree not clean; no zip archive made")
      IO.delete(base / "props.zip")
    }
    jar
  }

cleanFiles <++= baseDirectory { base =>
  Seq(base / "props.jar",
      base / "props.jar.pack.gz",
      base / "props.zip") }

