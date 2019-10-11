package lec0

object Dtypes extends App {

  // mutable

  var a = 2
  println(s"a = $a")

  a = 3
  println(s"a = $a")

  val arr = scala.collection.mutable.ArrayBuffer[Int]()
  arr += 1

  arr += (2, 3)

  arr foreach (v => println(v))

  // immutable

  val x = 5
  //x = 6 // won't compile

  val vec = Vector(7, 8)
  vec foreach { v =>
    println(v)
  } // traverse and print

  //vec(0) = -1 // won't compile
  //vec += -1 // won't compile

}
