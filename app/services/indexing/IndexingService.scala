package services.indexing

import java.io.File
import java.nio.file.Path

import services.indexing.models.IndexingResult

import scala.concurrent.{ExecutionContext, Future}

trait IndexingService
{
  def index(path: Path)(implicit executionContext: ExecutionContext): Future[IndexingResult]

  def generateId(file: File)(implicit executionContext: ExecutionContext): Future[String]
}
