package json

import org.joda.time.DateTime
import play.api.libs.json._

import scala.util.Try

object Formats
{
  implicit object DateTimeFormat extends Format[DateTime]
  {
    override def reads(jsValue: JsValue): JsResult[DateTime] =
      jsValue match {
        case JsString(string) =>
          Try(DateTime.parse(string))
            .fold[JsResult[DateTime]](
            throwable => JsError(throwable.getMessage),
            dateTime => JsSuccess(dateTime)
          )

        case _ => JsError("Must be a string")
      }

    override def writes(dateTime: DateTime): JsValue =
      JsString(dateTime.toString)
  }
}
