package silverstar.rockthejvm.scala3

object Traits {

  trait Talker(val subject: String) {
    def talkTo(anotherTalker: Talker): String
  }

  class Person(name: String) extends Talker("music") {
    override def talkTo(anotherTalker: Talker): String = "I love music"
  }

  class RockFan extends Talker("rock music"):
    override def talkTo(anotherTalker: Talker): String = subject

  class RockFanatic extends RockFan with Talker // no constructor arg in Talker mixin

  trait BrokenRecord extends Talker // do not pass constructor arg in trait

  class AnnoyingFriend extends BrokenRecord with Talker("politics"):
    override def talkTo(anotherTalker: Talker): String = subject + subject + subject

  // super traits
  /*super*/
  trait Paintable

  trait Color
  case object Red  extends Color with Paintable
  case object Blue extends Color with Paintable

  def color(i: Int): Color = if (i > 10) Red else Blue

  def main(args: Array[String]): Unit = {
    println(color(1))
    println(color(20))
  }
}
