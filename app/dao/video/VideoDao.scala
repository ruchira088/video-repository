package dao.video

import services.video.models.Video
import utilities.models.FutureMonad

import scala.concurrent.{ExecutionContext, Future}

trait VideoDao
{
  def insert(video: Video)(implicit executionContext: ExecutionContext): Future[Video]

  def findById(id: String)(implicit executionContext: ExecutionContext): FutureMonad[Option, Video]
}
