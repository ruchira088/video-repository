package utilities

import java.io.{File, FileInputStream}
import java.security.{DigestInputStream, MessageDigest}
import java.util.Base64

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Try

object HashUtils
{
  val HASHING_ALGORITHM = "MD5"

  val BUFFER_SIZE = 8192

  def fileHash(file: File)(implicit executionContext: ExecutionContext): Future[String] =
    Future {
      val buffer = new Array[Byte](BUFFER_SIZE)
      val messageDigest = MessageDigest.getInstance(HASHING_ALGORITHM)
      val digestInputStream = new DigestInputStream(new FileInputStream(file), messageDigest)

      def readStream(): Unit = if (digestInputStream.read(buffer) != -1) readStream() else digestInputStream.close()

      readStream()

      new String(Base64.getEncoder.encode(messageDigest.digest())).filter(_.isLetterOrDigit)
    }

  def stringHash(string: String): Try[String] =
    Try {
      val messageDigest = MessageDigest.getInstance(HASHING_ALGORITHM)
      messageDigest.digest(string.getBytes).map(byteToHex).mkString
    }

  def byteToHex(byte: Byte): String = "%02x".format(byte)
}
