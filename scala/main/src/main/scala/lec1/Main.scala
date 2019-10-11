package lec1

object Func extends App {

  // Simple function

  def myfunc(a: Int): Int = {
    val tmp = 2 * a
    tmp
  }

  val res0 = myfunc(4)
  println(s"res0 = $res0")

  // Class method

  class MyClass(name: String) {
    def hello(): Unit = println(s"Hello, $name")
  }

  val guy = new MyClass("Boris")
  guy.hello()

  // Lambda function

  def lambda(multiplier: Int => Int): Int =
    1 to 4 filter (_ % 2 == 0) map multiplier reduce (_ * _)

  val res1 = lambda((x: Int) => { 2 * x })
  println(s"res1 = $res1")

  // Closure

  val factor = 3

  val res2 = lambda((x: Int) => { factor * x }) // binds ext variable
  println(s"res2 = $res2")

  // Curried function
  def normal(a: Int, b: Int, c: Int) = a + b + c
  def curr0(a: Int)(b: Int)(c: Int)  = a + b + c
  def curr1                          = (normal _).curried

  val res3 = normal(4, 5, 6)
  val res4 = curr0(4)(5)(6)
  val res5 = curr1(4)(5)(6)

  println(s"res3 = $res3")
  println(s"res4 = $res4")
  println(s"res5 = $res5")

  // Partially applied function

  def BigFunction(a: Int, b: Int) = a + b
  def SmallFunction(a: Int)       = BigFunction(a, -2) // partial application

  val res6 = SmallFunction(4)
  println(s"res6 = $res6")

  // Partially defined function

  val div0 = new PartialFunction[Int, Int] {
    def apply(x: Int)       = 45 / x
    def isDefinedAt(x: Int) = x != 0
  }

  val div1 = new PartialFunction[Int, Int] {
    def apply(x: Int)       = 0
    def isDefinedAt(x: Int) = true
  }

  // Define a full function
  val div = div0 orElse div1

  val res7 = div(0)
  val res8 = div(-3)
  println(s"res7 = $res7")
  println(s"res8 = $res8")

  // Implicit function

  def functionTakingString(str: String) = str

  // implicit conversion
  implicit def intToStr(num: Int): String = s"The value is $num"

  val res9 = functionTakingString(11)
  println(s"res9 = $res9")

  // Higher Order function

  // A function, which takes a vector and another "reduce" function
  def reduce(arr: Vector[Int], f: (Int, Int) => Int): Int = {

    val tmp = arr.foldLeft(1) { f }
    tmp

  }

  // Reducers
  def add(a: Int, b: Int) = a + b
  def mul(a: Int, b: Int) = a * b

  val data = Vector(-1, 2, 4)

  val res10 = reduce(data, add)
  println(s"res10 = $res10")

  val res11 = reduce(data, mul)
  println(s"res11 = $res11")

}
