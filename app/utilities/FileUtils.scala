package utilities

import java.io.File
import java.nio.file.Path

object FileUtils
{
  val VIDEO_FILE_FORMATS = List("mp4", "flv")

  def listFiles(path: Path): List[File] =
  {
    val items = path.toFile.listFiles()

    items.filter(_.isFile).toList ++
      items.filter(_.isDirectory).flatMap(directory => listFiles(directory.toPath))
  }
}
