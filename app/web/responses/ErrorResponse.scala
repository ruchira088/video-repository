package web.responses

import play.api.libs.json.{JsString, Json, OWrites, Writes}

case class ErrorResponse(errors: List[Exception])

object ErrorResponse
{
  implicit val exceptionWrite: Writes[Exception] = (exception: Exception) => JsString(exception.getMessage)

  implicit val errorResponseFormat: OWrites[ErrorResponse] = Json.writes[ErrorResponse]
}
