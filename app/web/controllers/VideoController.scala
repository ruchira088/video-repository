package web.controllers

import java.nio.file.Paths

import javax.inject.{Inject, Singleton}
import play.api.libs.json.JsValue
import play.api.libs.json.Json.{toJson => json}
import play.api.mvc.{AbstractController, Action, ControllerComponents}
import services.indexing.IndexingService
import web.requests.IndexVideosRequest
import web.utilities.RequestUtils.extract
import web.utilities.ResponseUtils.handleExceptions

import scala.concurrent.ExecutionContext
import scala.concurrent.Future.fromTry

@Singleton
class VideoController @Inject()(controllerComponents: ControllerComponents, indexingService: IndexingService)
                               (implicit executionContext: ExecutionContext)
  extends AbstractController(controllerComponents)
{
  def index(): Action[JsValue] =
    Action.async(parse.json) {
      implicit request =>
        handleExceptions {
          for {
            indexVideoRequest <- fromTry(extract[IndexVideosRequest])
            indexingResultsSummary <- indexingService.index(indexVideoRequest.indexPath)
          }
          yield Ok(json(indexingResultsSummary))
        }
    }
}
