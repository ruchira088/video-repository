package dao.slick

import java.nio.file.{Path, Paths}
import java.sql.Timestamp

import org.joda.time.DateTime
import play.api.db.slick.HasDatabaseConfigProvider
import slick.jdbc.JdbcProfile

trait SlickMappedColumns
{
  self: HasDatabaseConfigProvider[JdbcProfile] =>

  import self.profile.api._

  implicit val dateTimeColumnType: BaseColumnType[DateTime] =
    MappedColumnType.base[DateTime, Timestamp](
      dateTime => new Timestamp(dateTime.getMillis),
      timeStamp => new DateTime(timeStamp.getTime)
    )

  implicit val pathColumnType: BaseColumnType[Path] =
    MappedColumnType.base[Path, String](_.toString, string => Paths.get(string))
}