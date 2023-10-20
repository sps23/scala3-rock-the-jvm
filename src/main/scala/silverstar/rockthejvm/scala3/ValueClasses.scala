package silverstar.rockthejvm.scala3

import jdk.jfr.Description

object ValueClasses {

  // online store
  case class Product(code: String, description: String)

  trait Backend {
    def findByCode(code: String): Option[Product]
    def findByDescription(description: String): Option[Product]
  }

  // solution 1: case classes
  case class BarCode(code: String)
  object BarCode {
    def apply(code: String): Either[String, BarCode] = Either.cond(
      code.matches("\\d-\\d{5}-\\d{5}"),
      new BarCode(code),
      "Code is invalid"
    )
  }
  case class Description(txt: String)

  trait Backend2 {
    def findByCode(code: BarCode): Option[Product]

    def findByDescription(description: Description): Option[Product]
  }

  // solution 2: Value class:
  // no runtime overhead
  // no other vals just methods
  // cannot be extended
  // can only extend universal traits
  case class BarCodeVC(code: String) extends AnyVal {
    def countryCode: Char = code.charAt(0)
  }

  // solution 3: opaque type
  opaque type BarCodeO = String

  object BarCodeO {
    def apply(code: String): Either[String, BarCodeO] = Either.cond(
      code.matches("\\d-\\d{5}-\\d{5}"),
      code,
      "Code is invalid"
    )
  }

  def main(args: Array[String]): Unit = {

    val aCode = "1-12345-12345"
    val aDesc = "great product"

    val backend = new Backend:
      override def findByCode(code: String): Option[Product] = None

      override def findByDescription(description: String): Option[Product] = None

    backend.findByCode(aCode)
    backend.findByDescription(aDesc)
    backend.findByDescription(aCode) // coding mistake, but compiles

    val backend2 = new Backend2:
      override def findByCode(code: BarCode): Option[Product] = None

      override def findByDescription(description: Description): Option[Product] = None

    backend2.findByCode(new BarCode(aCode))
    backend2.findByDescription(Description(aCode)) // coding mistake

    BarCode(aCode).map(backend2.findByCode) // better
    BarCode(aDesc).map(backend2.findByCode)

    println(BarCodeVC(aCode).toString)
    println(BarCodeVC(aDesc).toString)
    println(BarCodeO.apply(aCode))
    println(BarCodeO.apply(aDesc))
  }
}
