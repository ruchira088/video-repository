package utilities.models

import exceptions.EmptyOptionException

import scala.language.higherKinds
import scala.util.{Failure, Success, Try}

trait Monad[M[+ _]]
{
  def flatMap[A, B](f: A => M[B])(value: M[A]): M[B]

  def map[A, B](f: A => B)(value: M[A]): M[B]

  def pure[A](value: A): M[A]

  def fold[A, B](failure: Throwable => B, success: A => B)(value: M[A]): B

  def failure(throwable: Throwable): M[Nothing]
}

object Monad
{
  implicit object TryMonad extends Monad[Try]
  {
    override def flatMap[A, B](f: A => Try[B])(value: Try[A]): Try[B] =
      value.flatMap(f)

    override def map[A, B](f: A => B)(value: Try[A]): Try[B] =
      value.map(f)

    override def pure[A](value: A): Try[A] = Success(value)

    override def fold[A, B](failure: Throwable => B, success: A => B)(value: Try[A]): B =
      value.fold(failure, success)

    override def failure(throwable: Throwable): Try[Nothing] = Failure(throwable)
  }

  implicit object OptionMonad extends Monad[Option]
  {
    override def flatMap[A, B](f: A => Option[B])(value: Option[A]): Option[B] =
      value.flatMap(f)

    override def map[A, B](f: A => B)(value: Option[A]): Option[B] =
      value.map(f)

    override def pure[A](value: A): Option[A] = Option(value)

    override def fold[A, B](failure: Throwable => B, success: A => B)(value: Option[A]): B =
      value.fold[B](failure(EmptyOptionException))(success)

    override def failure(throwable: Throwable): Option[Nothing] = None
  }
}
