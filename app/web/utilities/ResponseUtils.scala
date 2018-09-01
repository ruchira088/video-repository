package web.utilities

import exceptions.{JsonParseException, VideoNotFoundException}
import play.api.libs.json.Json.{toJson => json}
import play.api.mvc.Result
import play.api.mvc.Results._
import web.responses.ErrorResponse

import scala.concurrent.{ExecutionContext, Future}

object ResponseUtils
{
  def handleExceptions(result: Future[Result])(implicit executionContext: ExecutionContext): Future[Result] =
    result.recover {
      case JsonParseException(errors) =>
        BadRequest {
          json(ErrorResponse(errors.map(_.toString)))
        }

      case videoNotFoundException: VideoNotFoundException =>
        NotFound {
          json(ErrorResponse(videoNotFoundException))
        }

      case throwable =>
        InternalServerError {
          json(ErrorResponse(throwable))
        }
    }
}