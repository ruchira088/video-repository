package dao.video

import com.typesafe.config.ConfigFactory
import services.video.models.Video
import utilities.models.FutureMonad

import scala.concurrent.{ExecutionContext, Future}

trait VideoDao
{
  def insert(video: Video)(implicit executionContext: ExecutionContext): Future[Video]

  def findById(id: String)(implicit executionContext: ExecutionContext): FutureMonad[Option, Video]

  def getAll(page: Int)(implicit executionContext: ExecutionContext): Future[List[Video]]
}

object VideoDao
{
  val PAGE_SIZE: Int = ConfigFactory.load().getInt("videoRepository.query.pageSize")
}
