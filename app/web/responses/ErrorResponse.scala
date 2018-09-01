package web.responses

import play.api.libs.json.{Json, OWrites}

case class ErrorResponse(errors: List[String])

object ErrorResponse
{
  def apply(throwable: Throwable): ErrorResponse = ErrorResponse(List(throwable.getMessage))

  implicit val errorResponseFormat: OWrites[ErrorResponse] = Json.writes[ErrorResponse]
}
