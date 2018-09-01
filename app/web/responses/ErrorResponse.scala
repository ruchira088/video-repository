package web.responses

import play.api.libs.json.{Json, OWrites}

case class ErrorResponse(errors: List[String])

object ErrorResponse
{
  def apply(error: String): ErrorResponse = ErrorResponse(List(error))

  implicit val errorResponseFormat: OWrites[ErrorResponse] = Json.writes[ErrorResponse]
}
