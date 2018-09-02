package modules

import com.google.inject.AbstractModule
import dao.video.{SlickVideoDao, VideoDao}
import execution.{BlockingExecutionContext, BlockingExecutionContextImpl}
import org.apache.tika.Tika
import services.indexing.{IndexingService, IndexingServiceImpl}
import services.video.{VideoService, VideoServiceImpl}

class ApplicationModule extends AbstractModule
{
  override def configure(): Unit =
  {
    bind(classOf[IndexingService]).to(classOf[IndexingServiceImpl])
    bind(classOf[VideoDao]).to(classOf[SlickVideoDao])
    bind(classOf[VideoService]).to(classOf[VideoServiceImpl])
    bind(classOf[BlockingExecutionContext]).to(classOf[BlockingExecutionContextImpl])
    bind(classOf[Tika]).toInstance(new Tika())
  }
}
