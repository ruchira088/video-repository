package services.indexing

import java.io.File
import java.nio.file.Path

import dao.video.VideoDao
import execution.BlockingExecutionContext
import javax.inject.{Inject, Singleton}
import services.indexing.IndexingService.{fromFile, isVideoFile}
import services.indexing.models.{IndexingResult, IndexingResultsSummary}
import utilities.FileUtils.listFiles
import utilities.HashUtils

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class IndexingServiceImpl @Inject()(videoDao: VideoDao, blockingExecutionContext: BlockingExecutionContext)
  extends IndexingService
{
  self =>

  override def index(path: Path)(implicit executionContext: ExecutionContext): Future[IndexingResultsSummary] =
    for {
      videoFiles <- Future { listFiles(path).filter(isVideoFile) }

      indexingResults <- Future.sequence(videoFiles.map(index))
      newlyAddedVideos = indexingResults.filter(_.inserted).map(_.video)
    }
    yield IndexingResultsSummary(indexingResults.size, newlyAddedVideos)

  def index(file: File)(implicit executionContext: ExecutionContext): Future[IndexingResult] =
    for {
      video <- fromFile(self)(file)

      indexingResult <-
        videoDao.findById(video.id).transform[IndexingResult](
          _ => videoDao.insert(video).map(IndexingResult(_, true))) {
          _ => Future.successful(IndexingResult(video, false))
        }
    }
    yield indexingResult

  override def generateId(file: File)(implicit executionContext: ExecutionContext): Future[String] =
    for {
      fileContentHash <- HashUtils.fileHash(file)(blockingExecutionContext)
      fileSize = file.length()
    }
    yield s"$fileContentHash$fileSize"
}