package web.utilities

import exceptions.JsonParseException
import play.api.libs.json.{JsValue, Reads}
import play.api.mvc.Request

import scala.util.{Failure, Success, Try}

object RequestUtils
{
  def extract[A](implicit request: Request[JsValue], reads: Reads[A]): Try[A] =
    request.body.validate[A]
      .fold[Try[A]](errors => Failure(JsonParseException.create(errors)), Success.apply)
}