package services.video

import services.video.models.Video

import scala.concurrent.{ExecutionContext, Future}

trait VideoService
{
  def getById(id: String)(implicit executionContext: ExecutionContext): Future[Video]

  def getAll(page: Int)(implicit executionContext: ExecutionContext): Future[List[Video]]
}