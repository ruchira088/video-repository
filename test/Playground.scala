import java.nio.file.Paths

import org.apache.tika.Tika

object Playground
{
  def main(args: Array[String]): Unit =
  {
    println(new Tika().detect(Paths.get("/Users/ruchira/Downloads/SampleVideo_1280x720_10mb.mp4")))
  }
}
