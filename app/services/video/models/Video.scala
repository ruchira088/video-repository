package services.video.models

import java.nio.file.Path

import json.JsonFormats.{DateTimeFormat, PathFormat}
import org.joda.time.DateTime
import play.api.libs.json.{Json, OFormat}

case class Video(id: String, addedAt: DateTime, fileName: String, fileSize: Double, location: Path)

object Video
{
  implicit val videoFormat: OFormat[Video] = Json.format[Video]
}