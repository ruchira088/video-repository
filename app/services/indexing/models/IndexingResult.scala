package services.indexing.models

import services.video.models.Video

case class IndexingResult(video: Video, inserted: Boolean)
