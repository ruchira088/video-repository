package services.indexing.models

import play.api.libs.json.{Json, OFormat}
import services.video.models.Video

case class IndexingResultsSummary(allVideosInPath: Int, newlyAddedVideos: List[Video])

object IndexingResultsSummary
{
  implicit val indexingResultsSummaryFormat: OFormat[IndexingResultsSummary] =
    Json.format[IndexingResultsSummary]
}