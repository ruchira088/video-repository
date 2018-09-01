package services.video

import dao.video.VideoDao
import exceptions.VideoNotFoundException
import javax.inject.{Inject, Singleton}
import services.video.models.Video

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class VideoServiceImpl @Inject()(videoDao: VideoDao) extends VideoService
{
  override def getById(id: String)(implicit executionContext: ExecutionContext): Future[Video] =
    videoDao.findById(id).success(VideoNotFoundException(id))

  override def getAll(page: Int)(implicit executionContext: ExecutionContext): Future[List[Video]] =
    videoDao.getAll(page)
}