package silverstar.rockthejvm.scala3

object NewTypes {

  // literal types
  val aNumber          = 3
  private val three: 3 = 3 // 3 is a subtype of Int
  val truth: true      = true

  private def passNumber(i: Int): Unit = println(i)
  private def passThree(i: 3): Unit    = println(i)

  // union types
  private def unionFunc(input: String | Int): Unit = input match
    case s: String => println(s"s: $s")
    case i: Int    => println(s"i: $i")

  private type ErrorOr[T] = T | "error"
  private def handleError(input: ErrorOr[Int]): Unit = input match
    case i: Int => println(s"i: $i")
    case e      => println(s"s: $e")

  private def stringOrInt(i: Int): Any           = if (i > 0) i else "error" // return type Any
  private def aStringOrInt(i: Int): Int | String = if (i > 0) i else "error"

  // intersection types
  private trait Camera {
    def takePhoto(): Unit = println("snap")
    def use(): Unit       = println("camera")
  }

  private trait Phone {
    def makeCall(): Unit = println("ring")
    def use(): Unit      = println("phone")
  }

  private def useSmartDevice(device: Camera & Phone) = {
    device.takePhoto()
    device.makeCall()
    device.use()
  }

  private class Smartphone extends Camera with Phone {
    override def use(): Unit = println("smartphone")
  }

  private trait HostConfig
  private trait HostController {
    def get: Option[HostConfig]
  }

  private trait PortConfig

  private trait PortController {
    def get: Option[PortController]
  }

  private def getConfigs(controller: HostController & PortController): Option[HostConfig & PortController] = controller.get

  def main(args: Array[String]): Unit = {
    passNumber(45)
    passNumber(three)
    passThree(three)

    unionFunc("Scala")
    unionFunc(23)
    handleError(1)
    handleError("error")
    stringOrInt(1)
    aStringOrInt(-1)

    useSmartDevice(new Smartphone)
    // getConfigs()
  }
}
