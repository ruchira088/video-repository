package exceptions

case class VideoNotFoundException(videoId: String) extends Exception
{
  override def getMessage: String = s"Video NOT found: ID = $videoId"
}
