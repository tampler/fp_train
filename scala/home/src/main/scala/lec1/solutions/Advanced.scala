package lec1

import scalaz._
import zio.{ DefaultRuntime, Ref, UIO }
import zio.console._

object AdvancedSolutions extends App {

  def Sum(vec: Vector[Int]): Int = ???

  // Task 1

  // Implement a function, which sums all elevements in the int vector in several different ways

  // With a lib function
  // Using vars
  // using immutables only
  // using for iteration
  // using recursion
  // your own idea

  type VecInt = Vector[Int]

  // Lib method
  def Sum0(vec: VecInt): Int = vec.sum

  // mutable variables
  def Sum1(vec: VecInt): Int = {
    var res = 0

    // iterate over array as in Java and Python and add to the output sum
    for (item <- vec)
      res += item

    res
  }

  // mutable variables. Iterate with map
  def Sum2(vec: VecInt): Int = {
    var res = 0
    vec map (v => res = res + v)

    res
  }

  // mutable variables. Iterate with foreach
  def Sum3(vec: VecInt): Int = {
    var res = 0
    vec foreach (v => res = res + v)

    res
  }

  // Loop thru the vector
  def Sum4(vec: VecInt): Int = {
    var res = 0

    for (i <- 0 until vec.length)
      res += vec(i)

    res
  }

  def Sum5(vec: VecInt): Int = {
    val res = vec reduce (_ + _)
    res
  }

  def Sum6(vec: VecInt): Int = {
    val res = vec.foldLeft(0)(_ + _)
    res
  }

  //--------------------------------------------------------------------------------------------
  // Using a case class to wrap the value
  // Note: Immutable !
  //--------------------------------------------------------------------------------------------

  case class Wrap(a: Int) {
    def add(b: Int): Wrap = copy(a = a + b)
  }

  // Using immutable copy
  def Sum7(vec: VecInt) = {
    val init: Wrap = Wrap(0)

    // This creates a Vector of objects, each contains a value from input VecInt
    val tmp: Vector[Wrap] = vec map (v => init.add(v))

    // Walk thru the vector of wraps, fetch the value and add it to the accumulator
    val res: Int = tmp map (v => v.a) reduce (_ + _)

    res
  }

  //--------------------------------------------------------------------------------------------
  // Using a case class to wrap the value and ScalaZ lenses to get/set/modify immutables for free !
  // Note: Immutable !
  // Hint: think of Lens as immutable getters/setters/modifiers
  //--------------------------------------------------------------------------------------------
  val wrapL = Lens.lensu[Wrap, Int](
    (a, value) => a.copy(a = value),
    _.a
  )

  val init    = Wrap(0)
  val newinit = wrapL.set(init, 2)
  val out     = wrapL.get(newinit)

  println(s"init = $init")
  println(s"newinit = $newinit")

  // Using ScalaZ lenses
  def Sum8(vec: VecInt) = {

    // Use lenses to immutably transform initial object and use Lens setters to add each element of Vector[Int]
    val tmp = vec map (v => wrapL.set(init, v))

    // Walk thru the vector of wraps, fetch the value with lenses and add it to the accumulator
    val res: Int = tmp map (v => wrapL.get(v)) reduce (_ + _)

    res
  }

  //--------------------------------------------------------------------------------------------
  // Using ZIO Ref - treat immutable values as variables with getters and setters.
  // Similar technique to ScalaZ Lens
  //--------------------------------------------------------------------------------------------
  val runtime = new DefaultRuntime {}

  // Create and init a zero reference
  def makeZero: UIO[Ref[Int]] = Ref.make(0)

  // Return value is a monadic output
  def Sum9(vec: VecInt): UIO[Int] =
    for {

      out <- makeZero // create a ref
      //ints <- UIO.collectAll(vec)
      _   <- UIO.foreach(vec)(i => out.update(_ + i)) // update a ref for each element of inpu vector
      res <- out.get                                  // read the value, wrapped inside the ref

    } yield res

  //--------------------------------------------------------------------------------------------
  // Let's test all this stuff
  //--------------------------------------------------------------------------------------------

  val arr = Vector(1, 2, 3)

  for (i <- 0 until 9) {
    val res = i match {
      case 0 => Sum0(arr)
      case 1 => Sum1(arr)
      case 2 => Sum2(arr)
      case 3 => Sum3(arr)
      case 4 => Sum4(arr)
      case 5 => Sum5(arr)
      case 6 => Sum6(arr)
      case 7 => Sum7(arr)
      case 8 => Sum8(arr)
      //case 9 =>  Sum9(arr)
      case _ => 0
    }
    println(s"res $i = $res")
  }

  runtime.unsafeRun(Sum9(arr).flatMap(num => putStrLn(s"res 9 = $num")))
  //--------------------------------------------------------------------------------------------

  // Task 2

  // For recursive Implementation, can you implement a function, which sums up very large vectors? What are potential dangers for a recursive implementation?

}
