package modules

import com.google.inject.AbstractModule
import dao.video.{SlickVideoDao, VideoDao}
import services.indexing.{IndexingService, IndexingServiceImpl}
import services.video.{VideoService, VideoServiceImpl}

class ApplicationModule extends AbstractModule
{
  override def configure(): Unit =
  {
    bind(classOf[IndexingService]).to(classOf[IndexingServiceImpl])
    bind(classOf[VideoDao]).to(classOf[SlickVideoDao])
    bind(classOf[VideoService]).to(classOf[VideoServiceImpl])
  }
}
