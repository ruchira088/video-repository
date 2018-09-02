package utilities

import java.io.{File, FileInputStream}
import java.security.{DigestInputStream, MessageDigest}
import java.util.Base64

import com.typesafe.scalalogging.Logger
import execution.BlockingExecutionContext

import scala.concurrent.Future

object HashUtils
{
  val HASHING_ALGORITHM = "MD5"

  val BUFFER_SIZE = 8192

  private val logger = Logger[HashUtils.type]

  def fileHash(file: File)(implicit blockingExecutionContext: BlockingExecutionContext): Future[String] =
    Future {
      logger.info(s"fileHash start: ${file.getAbsolutePath}")
      val startTime = System.currentTimeMillis()

      val buffer = new Array[Byte](BUFFER_SIZE)
      val messageDigest = MessageDigest.getInstance(HASHING_ALGORITHM)
      val digestInputStream = new DigestInputStream(new FileInputStream(file), messageDigest)

      def readStream(): Unit = if (digestInputStream.read(buffer) != -1) readStream() else digestInputStream.close()

      readStream()

      val hash = base64(messageDigest.digest())

      logger.info(s"fileHash finished (${System.currentTimeMillis() - startTime}ms): ${file.getAbsolutePath}")

      hash
    }

  def stringHash(string: String)(implicit blockingExecutionContext: BlockingExecutionContext): Future[String] =
    Future {
      val messageDigest = MessageDigest.getInstance(HASHING_ALGORITHM)
      base64(messageDigest.digest(string.getBytes))
    }

  def base64(bytes: Array[Byte]): String =
    new String(Base64.getEncoder.encode(bytes)).filter(_.isLetterOrDigit)
}
