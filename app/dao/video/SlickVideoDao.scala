package dao.video

import java.nio.file.Path

import dao.slick.SlickMappedColumns
import javax.inject.{Inject, Singleton}
import org.joda.time.DateTime
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import services.video.models.Video
import slick.jdbc.JdbcProfile
import slick.lifted.ProvenShape
import utilities.models.FutureMonad

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

@Singleton
class SlickVideoDao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends VideoDao
  with HasDatabaseConfigProvider[JdbcProfile] with SlickMappedColumns
{
  import profile.api._

  class VideoTable(tag: Tag) extends Table[Video](tag, "videos")
  {
    def id: Rep[String] = column[String]("id", O.PrimaryKey)

    def addedAt: Rep[DateTime] = column[DateTime]("added_at")

    def fileName: Rep[String] = column[String]("file_name")

    def fileSize: Rep[Double] = column[Double]("file_size")

    def location: Rep[Path] = column[Path]("location")

    override def * : ProvenShape[Video] =
      (id, addedAt, fileName, fileSize, location) <> (Video.apply _ tupled, Video.unapply)
  }

  val videos = TableQuery[VideoTable]

  override def insert(video: Video)(implicit executionContext: ExecutionContext): Future[Video] =
    db.run(videos += video).map(_ => video)

  override def findById(id: String)(implicit executionContext: ExecutionContext): FutureMonad[Option, Video] =
    FutureMonad {
      db.run(videos.filter(_.id === id).result.headOption)
    }
}
