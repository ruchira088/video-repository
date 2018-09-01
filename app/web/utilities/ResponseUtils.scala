package web.utilities

import play.api.mvc.Result

import scala.concurrent.{ExecutionContext, Future}

object ResponseUtils
{
  def handleExceptions(result: Future[Result])(implicit executionContext: ExecutionContext): Future[Result] =
    result.recover {
      case _ => ???
    }
}
