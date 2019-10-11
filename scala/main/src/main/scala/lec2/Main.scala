// Polymorphism and pattern matching

package lec2

object Main extends App {

  // Questions
  // What is Polymorphism ?
  // What kind of polymorphisms do you know ?

  //--------------------------------------------------------------------------------------------
  // Dynamic Polymorphism in Scala
  //--------------------------------------------------------------------------------------------

  // From Lec1 Questions../
  abstract class Animal {
    def speak(): Unit // abstract method, defined not implemented
    // can't instantiate abstract classes
  }

  trait Cat extends Animal {
    override def speak() = println("Meow")
  }

  trait Dog extends Animal {
    override def speak() = println("Woof")
  }

  // Watch the order of mix-ins
  class Mutant0(name: String) extends Animal with Cat with Dog
  val m0 = new Mutant0("Peggy")
  m0.speak()

  class Mutant1(name: String) extends Animal with Dog with Cat
  val m1 = new Mutant1("Wolfy")
  m1.speak()

  //--------------------------------------------------------------------------------------------
  // Parametric Polymorphism in Scala
  //--------------------------------------------------------------------------------------------
  sealed abstract class Shape[A] {
    def Area: A // abstract by default
  }

  // Take 1 - Simple approach
  //class RectangleBasic[A] (a:A, b:A) extends Shape[A] {
  //  def Area():A  = a * b
  //}

  // Take 2 - Constrain the data type
  class Rectangle[A: Numeric](a: A, b: A) extends Shape[A] {

    // Define and import all numeric
    val num = implicitly[Numeric[A]]
    import num._

    def Area(): A = a * b
  }

  // Int
  val intRectangle = new Rectangle[Int](2, 3)
  val res0         = intRectangle.Area()
  println(s"res0 = $res0")

  // Float
  val floatRectangle = new Rectangle[Float](-1.0f, 4.0f)
  val res1           = floatRectangle.Area()
  println(s"res1 = $res1")

  //--------------------------------------------------------------------------------------------
  // Ad-hoc Polymorphism
  //--------------------------------------------------------------------------------------------
  import Common._
  import Fruits._
  //import Fruits.Addable._
  //import Fruits.Addable.ops._

  def AppleCount(apples: Vector[Apple]): Int = apples.size
  //def OrangeCount (oranges:Vector[Int]):Int = oranges.sum

  val apples = Vector(new Apple("red"))
  val res2   = AppleCount(apples)

  println(s"res2 = $res2")

  //val oranges =  Vector(3)

  val apple0 = new Apple("yellow")
  val apple1 = new Apple("white")

  val orange0 = new Orange(3)
  val orange1 = new Orange(5)
  //val apple2  = apple0 + apple1

  // Add using simple functions
  val res3 = Fruits.addApples(apple0, apple1)
  val res4 = Fruits.addOranges(orange0, orange1)

  println(s"Simple Apple add = $res3")
  println(s"Simple Orange add = $res4")

  def mysum[A](a: A, b: A)(implicit ev: Addable[A]): A =
    ev.add(a, b)

  val res5 = mysum(apple0, apple1).toString
  val res6 = mysum(orange0, orange1)
  //val res60  = mysum(2.0, 3.0)

  println(s"Mysum Apple add = $res5")
  println(s"Mysum Orange add = $res6")

}
