package silverstar.rockthejvm.scala3

object Enums {

  private enum Permissions {
    case READ, WRITE, EXEC, NONE
  }

  private enum PermissionsWithBits(val bits: Int) {
    case READ extends PermissionsWithBits(4)  // 100
    case WRITE extends PermissionsWithBits(2) // 010
    case EXEC extends PermissionsWithBits(1)  // 001
    case NONE extends PermissionsWithBits(0)  // 000

    def toHex: String = Integer.toHexString(bits)
  }

  private object PermissionsWithBits {
    def fromBits(bits: Int): PermissionsWithBits = PermissionsWithBits.NONE
  }

  private val read = Permissions.READ
  private val read2 = PermissionsWithBits.READ

  def main(args: Array[String]): Unit = {

    println(read)
    println(Permissions.EXEC.ordinal)
    println(read2)
    println(read2.bits)
    println(read2.toHex)
    println(PermissionsWithBits.fromBits(12))
    println(Permissions.values.mkString("Permissions(", ", ", ")"))
    println(Permissions.valueOf("read".toUpperCase))
    println(Permissions.valueOf("EXEC"))
  }
}
