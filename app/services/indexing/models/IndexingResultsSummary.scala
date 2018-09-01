package services.indexing.models

import services.video.models.Video

case class IndexingResultsSummary(allVideosInPath: Int, newlyAddedVideos: List[Video])