package exceptions

import play.api.libs.json.{JsPath, JsonValidationError}

case class JsonParseException(errors: List[(JsPath, List[JsonValidationError])]) extends Exception

object JsonParseException
{
  def create(errors: Seq[(JsPath, Seq[JsonValidationError])]): JsonParseException =
    JsonParseException {
      errors.toList.map { case (path, error) => path -> error.toList }
    }
}