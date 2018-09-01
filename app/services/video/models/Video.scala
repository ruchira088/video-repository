package services.video.models

import java.nio.file.Path

import org.joda.time.DateTime

case class Video(id: String, addedAt: DateTime, fileName: String, fileSize: Double, location: Path)
