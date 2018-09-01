package services.indexing

import java.io.File
import java.nio.file.Path

import javax.inject.Singleton
import services.indexing.models.IndexingResult
import utilities.HashUtils

import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.Future.fromTry

@Singleton
class IndexingServiceImpl extends IndexingService
{
  override def index(path: Path)(implicit executionContext: ExecutionContext): Future[IndexingResult] = ???

  override def generateId(file: File)(implicit executionContext: ExecutionContext): Future[String] =
    for {
      fileContentHash <- HashUtils.fileHash(file)
      fileNameHash <- fromTry(HashUtils.stringHash(file.getName))
      fileSize = file.length()
    }
    yield s"$fileNameHash-$fileSize-$fileContentHash"
}
