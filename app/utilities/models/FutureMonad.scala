package utilities.models

import scala.concurrent.{ExecutionContext, Future}
import scala.language.higherKinds

case class FutureMonad[M[+ _], +A](future: Future[M[A]])
{
  def flatMap[B](f: A => FutureMonad[M, B])(implicit monad: Monad[M], executionContext: ExecutionContext): FutureMonad[M, B] =
    FutureMonad {
      future.flatMap {
        monad.fold(throwable => Future.successful(monad.failure(throwable)), value => f(value).future)
      }
    }

  def map[B](f: A => B)(implicit monad: Monad[M], executionContext: ExecutionContext): FutureMonad[M, B] =
    flatMap(value => FutureMonad(monad.pure(f(value))))
}

object FutureMonad
{
  def apply[M[+ _], A](value: M[A]): FutureMonad[M, A] =
    FutureMonad {
      Future.successful(value)
    }
}
