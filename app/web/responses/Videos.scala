package web.responses

import play.api.libs.json.{Json, OFormat}
import services.video.models.Video

case class Videos(videos: List[Video])

object Videos
{
  implicit val videosFormat: OFormat[Videos] = Json.format[Videos]
}
