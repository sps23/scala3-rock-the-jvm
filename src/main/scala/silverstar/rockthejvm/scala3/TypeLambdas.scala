package silverstar.rockthejvm.scala3

object TypeLambdas {

  /*
  Levels of types
  - l0 - value level types: Int, String
  - l1 - generics: List, Option
  - l2 - generics of generics: Functor, Monad
  - l3 - kinds, types of types
   */

  // l2 type
  class Functor[F[_]]

  // kinds
  type MyList               = [T] =>> List[T]
  type MapWithStringKey     = [T] =>> Map[String, T]
  type MapWithStringKey2[T] = Map[String, T]
  type EitherOption         = [T, E] =>> Either[E, Option[T]]

  // monad
  trait Monad[M[_]] {
    def pure[A](value: A): M[A]
    def flatMap[A, B](ma: M[A])(transform: A => M[B]): M[B]
  }

  class EitherMonad[E] extends Monad[[T] =>> Either[E, T]] {
    override def pure[A](value: A): Either[E, A]                                             = Right(value)
    override def flatMap[A, B](ma: Either[E, A])(transform: A => Either[E, B]): Either[E, B] = ma.flatMap(transform)
  }

  def main(args: Array[String]): Unit = {

    val aNumber = 1
    val aList   = List(1, 2, 3)

    val functorOption                           = new Functor[Option]
    val addressBook: MapWithStringKey[String]   = Map()
    val addressBook2: MapWithStringKey2[String] = Map()
    val either: EitherOption[Int, String]       = Right(Some(2))

    println(addressBook)
    println(addressBook2)
    println(either)

    println(EitherMonad[String].pure(25))
    println(EitherMonad[String].pure("test"))
  }
}
