package web.controllers

import javax.inject.{Inject, Singleton}
import org.apache.tika.Tika
import play.api.libs.json.JsValue
import play.api.libs.json.Json.{toJson => json}
import play.api.mvc._
import services.indexing.IndexingService
import services.video.VideoService
import web.requests.IndexVideosRequest
import web.responses.Videos
import web.utilities.RequestUtils.extract
import web.utilities.ResponseUtils.handleExceptions

import scala.concurrent.ExecutionContext
import scala.concurrent.Future.fromTry

@Singleton
class VideoController @Inject()(
  controllerComponents: ControllerComponents,
  indexingService: IndexingService,
  tika: Tika,
  videoService: VideoService)(implicit executionContext: ExecutionContext)
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

  def all(page: Int): Action[AnyContent] =
    Action.async {
      handleExceptions {
        for {
          videos <- videoService.getAll(page)
        }
        yield Ok(json(Videos(videos)))
      }
    }

  def view(videoId: String): Action[AnyContent] =
    Action.async {
      request =>
      handleExceptions {
        for {
          video <- videoService.getById(videoId)
        }
        yield RangeResult.ofPath(video.location, request.headers.get(RANGE), Some(tika.detect(video.location)))
      }
    }
}
