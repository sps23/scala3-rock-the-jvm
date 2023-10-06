package silverstar.rockthejvm.scala3

import silverstar.rockthejvm.scala3.Using.Person

object Givens {
  private val personOrderingBySurname: Ordering[Person] = (x: Person, y: Person) => x.surname.compareTo(y.surname)

  given personOrderingByAge: Ordering[Person] = (x: Person, y: Person) => x.age.compareTo(y.age)

  given standardPersonOrdering: Ordering[Person] = personOrderingBySurname

  given optionOrdering[T](using ord: Ordering[T]): Ordering[Option[T]] = (x: Option[T], y: Option[T]) =>
    (x, y) match {
      case (None, None)       => 0
      case (None, _)          => -1
      case (_, None)          => 1
      case (Some(a), Some(b)) => ord.compare(a, b)
    }
}

object Using {
  case class Person(name: String, surname: String, age: Int)

  def listPersons(persons: Seq[Person])(using ordering: Ordering[Person]): Seq[Person] = persons.sorted

  def sort[T](list: List[T])(using ordering: Ordering[T]): List[T] = list.sorted

  def main(args: Array[String]): Unit = {
//    import Givens.standardPersonOrdering
    import Givens.personOrderingByAge

    val persons = List(
      Person("a", "b", 1),
      Person("a", "a", 2),
      Person("b", "a", 3),
      Person("c", "c", 4)
    )
    val maybePersons: List[Option[Person]] = persons.map(Some(_))

    println(listPersons(persons))
    println(sort(maybePersons))
  }
}
