package web.utilities

import play.api.libs.json.{JsValue, Reads}
import play.api.mvc.Request

import scala.util.{Success, Try}

object RequestUtils
{
  def extract[A](implicit request: Request[JsValue], reads: Reads[A]): Try[A] =
    request.body.validate[A]
      .fold[Try[A]](_ => ???, Success.apply)
}
