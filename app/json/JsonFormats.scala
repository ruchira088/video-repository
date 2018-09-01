package json

import java.nio.file.{Path, Paths}

import org.joda.time.DateTime
import play.api.libs.json._

import scala.util.Try

object JsonFormats
{
  val STRING_EXCEPTION_MESSAGE = "Must be a string"

  implicit object PathFormat extends Format[Path]
  {
    override def writes(path: Path): JsValue = JsString(path.toString)

    override def reads(json: JsValue): JsResult[Path] =
      json match {
        case JsString(value) =>
          Try(Paths.get(value)).fold(throwable => JsError(throwable.getMessage), path => JsSuccess(path))

        case _ => JsError(STRING_EXCEPTION_MESSAGE)
      }
  }

  implicit object DateTimeFormat extends Format[DateTime]
  {
    override def writes(dateTime: DateTime): JsValue = JsString(dateTime.toString)

    override def reads(json: JsValue): JsResult[DateTime] =
      json match {
        case JsString(value) =>
          Try(DateTime.parse(value)).fold(throwable => JsError(throwable.getMessage), dateTime => JsSuccess(dateTime))

        case _ => JsError(STRING_EXCEPTION_MESSAGE)
      }
  }
}