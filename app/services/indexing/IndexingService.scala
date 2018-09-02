package services.indexing

import java.io.File
import java.nio.file.Path

import org.joda.time.DateTime
import services.indexing.models.IndexingResultsSummary
import services.video.models.Video

import scala.concurrent.{ExecutionContext, Future}

trait IndexingService
{
  def index(path: Path)(implicit executionContext: ExecutionContext): Future[IndexingResultsSummary]

  def generateId(file: File)(implicit executionContext: ExecutionContext): Future[String]
}

object IndexingService
{
  val VIDEO_FILE_FORMATS = List("mp4")

  def isVideoFile(file: File): Boolean = VIDEO_FILE_FORMATS.exists(file.getPath.endsWith)

  def fromFile(indexingService: IndexingService)(file: File)
              (implicit executionContext: ExecutionContext): Future[Video] =
    indexingService.generateId(file).map {
      videoId => Video(videoId, DateTime.now(), file.getName, file.length(), file.toPath)
    }
}