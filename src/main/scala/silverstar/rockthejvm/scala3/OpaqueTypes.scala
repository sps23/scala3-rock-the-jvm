package silverstar.rockthejvm.scala3

import silverstar.rockthejvm.scala3.OpaqueTypes.Graphics.Color

object OpaqueTypes {

//  case class Name(value: String) {
//    // overhead of accessing value
//  }

  object SocialNetwork { // domain
    opaque type Name = String // type alias without overhead but only inside the domain object
    // Name == String

    // companion object
    object Name {
      def fromString(s: String): Option[Name] = if (s.isEmpty || s.charAt(0).isLower) None else Some(s)
    }
    // extension function
    extension (n: Name) {
      def length: Int = n.length
    }
  }

  object Graphics {
    opaque type Color                = Int // in hex
    opaque type ColorFilter <: Color = Int // upper type bound for opaque type

    val Red: Color                    = 0xff000000
    val Green: Color                  = 0x00ff0000
    val halfTransparency: ColorFilter = 0x88
  }

  case class OverlayFilter(c: Color)

  def main(args: Array[String]): Unit = {
    import SocialNetwork._
    // I cannot use Name type here, I can only use methods from Companion object

    val nameOpt = Name.fromString("Sylwester")
    println(nameOpt)
    nameOpt.foreach(println)
    println(nameOpt.map(_.length))

    import Graphics.*
    val fade = OverlayFilter(halfTransparency) // ColorFilter extends Color
    println(fade)
  }

}
