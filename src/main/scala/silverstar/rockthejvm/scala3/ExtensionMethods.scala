package silverstar.rockthejvm.scala3

object ExtensionMethods {

  case class Person(name: String) {
    def greet: String = s"Hi, my name is $name"
  }

  implicit class PersonLike(name: String) {
    def greet: String = Person(name).greet
  }

  extension (name: String) {
    def greeting: String = Person(name).greet + " !!!"
  }

  sealed abstract class Tree[+A]
  case class Leaf[+A](value: A)                        extends Tree[A]
  case class Branch[+A](left: Tree[A], right: Tree[A]) extends Tree[A]

  extension [A](tree: Tree[A])
    def filter(predicate: A => Boolean): Boolean = tree match
      case Leaf(value)         => predicate(value)
      case Branch(left, right) => left.filter(predicate) && right.filter(predicate)

    def map[B](f: A => B): Tree[B] = tree match
      case Leaf(value)         => Leaf(f(value))
      case Branch(left, right) => Branch(left.map(f), right.map(f))

  extension [A](tree: Tree[A])
    def sum(using numeric: Numeric[A]): A = tree match
      case Leaf(value)         => value
      case Branch(left, right) => numeric.plus(left.sum, right.sum)

  def main(args: Array[String]): Unit = {
    println("Sylwester".greet)
    println("Sylwester".greeting)

    val tree = Branch(Leaf(1), Leaf(2))
    println(tree.filter(_ > 1))
    println(tree.map(_ * 10))
    println(tree.sum)
  }

}
