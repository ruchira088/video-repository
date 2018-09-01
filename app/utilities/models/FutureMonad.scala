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

  def transform[B](monadFailure: Throwable => Future[B])(success: A => Future[B])
                  (implicit monad: Monad[M], executionContext: ExecutionContext): Future[B] =
    future.flatMap {
      monad.fold(monadFailure, success)
    }

  def success(onFailure: => Throwable)(implicit monad: Monad[M], executionContext: ExecutionContext): Future[A] =
    future.flatMap {
      monad.fold[A, Future[A]](_ => Future.failed(onFailure), Future.successful)
    }
}

object FutureMonad
{
  def apply[M[+ _], A](value: M[A]): FutureMonad[M, A] =
    FutureMonad {
      Future.successful(value)
    }

  def apply[M[+ _], A](future: Future[A])
                      (implicit monad: Monad[M], executionContext: ExecutionContext): FutureMonad[M, A] =
    FutureMonad {
      future.map(monad.pure)
    }

  def apply[M[+ _], A](value: A, monad: Monad[M]): FutureMonad[M, A] =
    apply(monad.pure(value))
}
