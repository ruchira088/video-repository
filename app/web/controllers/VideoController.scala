package web.controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, ControllerComponents}
import services.indexing.IndexingService

@Singleton
class VideoController @Inject()(controllerComponents: ControllerComponents, indexingService: IndexingService)
  extends AbstractController(controllerComponents)
{

}
