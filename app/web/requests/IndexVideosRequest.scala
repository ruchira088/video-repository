package web.requests

import java.nio.file.{Path, Paths}

import com.typesafe.config.ConfigFactory
import json.JsonFormats.PathFormat
import play.api.libs.json.{Json, OFormat}
import web.requests.IndexVideosRequest.DEFAULT_INDEX_PATH

case class IndexVideosRequest(indexPath: Path = DEFAULT_INDEX_PATH)

object IndexVideosRequest
{
  val DEFAULT_INDEX_PATH: Path =
    Paths.get(ConfigFactory.load().getString("videoRepository.indexing.defaultPath"))

  implicit val indexVideosRequestFormat: OFormat[IndexVideosRequest] =
    Json.using[Json.WithDefaultValues].format[IndexVideosRequest]
}