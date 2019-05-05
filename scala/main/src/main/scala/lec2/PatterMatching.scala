
package lec2

// Taken from Alexander Alvin: Scala Cookbook, OReilly 2013. Paragraph 3.11, p.79

object PatternMatching extends App {

  case class Person(firstName: String, lastName: String)
  case class Dog(name: String)

  def echo(x: Any, y:Any): String = x,y match {

    // constant patterns
    case 0 => "zero"
    case true => "true"
    case "hello" => "you said 'hello'"
    case Nil => "an empty List"

    // sequence patterns
    case List(0, _, _) => "a three-element list with 0 as the first element"
    case List(1, _*) => "a list beginning with 1, having any number of elements"
    case Vector(1, _*) => "a vector starting with 1, having any number of elements"

    // tuples
    case (a, b) => s"got $a and $b"
    case (a, b, c) => s"got $a, $b, and $c"

    // constructor patterns
    case Person(first, "Alexander") => s"found an Alexander, first name = $first"
    case Dog("Suka") => "found a dog named Suka"

    // typed patterns
    case s: String => s"you gave me this string: $s"
    case i: Int => s"thanks for the int: $i"
    case f: Float => s"thanks for the float: $f"
    case a: Array[Int] => s"an array of int: ${a.mkString(",")}"
    case as: Array[String] => s"an array of strings: ${as.mkString(",")}"
    case d: Dog => s"dog: ${d.name}"
    case list: List[_] => s"thanks for the List: $list"
    case m: Map[_, _] => m.toString
    
    // Exceptions
    case e:Exception => "Got an exception"
    
    // the default wildcard pattern 

    case _ => "Unknown"
  }

  val res0  = echo(1)
  val res1  = echo(new RuntimeException ("Some error"))
  val res2  = echo (List(1,2,3,4,5))
  val res3  = echo ("CloverGroup")
  
  
  
  println (s"res0 = $res0")
  println (s"res1 = $res1")
  println (s"res2 = $res2")
  println (s"res3 = $res3")
  

}
