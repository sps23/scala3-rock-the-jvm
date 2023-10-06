package silverstar.rockthejvm.scala3

object MatchTypes {

  // implement lastDigitOf for:
  // BigInt -> last digit as Int
  // String -> last char
  // List -> last element

  def lastDigitOf(b: BigInt): Int  = (b % 10).toInt
  def lastCharOf(s: String): Char  = s.last
  def lastElemOf[T](l: List[T]): T = l.last

  type ElementOf[T] = T match {
    case BigInt  => Int
    case String  => Char
    case List[e] => e
  }

  def lastElementOf[T](value: T): ElementOf[T] = value match {
    case b: BigInt => lastDigitOf(b)
    case s: String => lastCharOf(s)
    case l: List[_] => lastElemOf(l)
  }

  def main(args: Array[String]): Unit = {
    val aDigit: ElementOf[BigInt] = 1
    val aChar: ElementOf[String] = 'c'
    val anElem: ElementOf[List[String]] = "elem"

    println(aDigit)
    println(aChar)
    println(anElem)

    val lastDigit = lastElementOf(BigInt(12343459))
    println(lastDigit == 9)
    val lastChar = lastElementOf("Sylwester")
    println(lastChar == 'r')
    val lastElem = lastElemOf(List("Hello", "world"))
    println(lastElem == "world")
  }
}
