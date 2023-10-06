package silverstar.rockthejvm.scala3

object GivensVsImplicits {
  // has to be imported
  import scala.language.implicitConversions

  case class Person(name: String) {
    def greet: String = s"Hi, my name is $name"
  }

  // implicit conversions
  // discouraged
  // implicit def stringToPerson(s: String): Person = Person(s)

  given stringToPerson: Conversion[String, Person] with {
    def apply(s: String): Person = Person(s)
  }

  // explicit implicits
  // def getPerson(implicit name: String): Person = Person(name)

  def getPerson(using name: String): Person = Person(name)

  def main(args: Array[String]): Unit = {

    println("Alice".greet)

    // implicit val defaultName: String = "Tom"
    // println(getPerson.greet)
    // println(getPerson("Sam").greet)

    given aName: String = "Rob"
    println(getPerson.greet)
    println(getPerson(using "Jack").greet)
  }

}
