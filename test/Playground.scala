import java.nio.file.Paths
import java.util.concurrent.TimeUnit

import utilities.HashUtils

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

object Playground
{
  def main(args: Array[String]): Unit =
  {
    println {
      Await.result(
        HashUtils.fileHash(Paths.get("/Users/ruchira/Downloads/SampleVideo_1280x720_10mb.mp4").toFile),
        Duration(30, TimeUnit.SECONDS)
      )
    }
  }
}
