package lec2

import cats.{Monoid}
import cats.implicits._

object SimpleSolutions extends App {
// task 1: Dynamic Polymorphism
// Implement a class hierarchy, according to the company.png UML diagram
// Implement method def speak():Unit, which will display "I'm an employee", for example

  sealed abstract class Human {
    def speak:Unit
  }
  
  trait Person extends Human {
    def speak():Unit = println(s"I'm a Person") 
  }
  
  trait Engineer extends Human {
    def speak():Unit = println(s"I'm an Engineer") 
  }

  trait Founder extends Engineer with Person {
    override def speak():Unit = println(s"I'm an Founder") 
  }
  
  trait Employee extends Human {
    def speak():Unit = println(s"I'm an Employee") 
  }
  
  class Investor extends Human {
    def speak():Unit = println(s"I'm an Investor") 
  }
  
  trait Owner extends Investor with Founder {
    override def speak():Unit = println(s"I'm an Owner") 
  }
  
  trait Manager extends Employee {
    override def speak():Unit = println(s"I'm a Manager") 
  }

  class Coworker extends Owner with Manager {
    override def speak():Unit = println(s"I'm a Coworker") 
  }
 
  val guy  = new Coworker
  guy.speak
  

// task2: Parametric Polymorphism
// Implement a correct version of the less() method for Int and String types

  class Duo[T:Ordering](first: T, second: T) { // Use Context bound 
    val ev  = implicitly[Ordering[T]] // define an evidence for implicits
    
    def less(): T = {
      if (ev.lt(first, second)) first else second
    }
  }

  val p = new Duo("d", "c")
  val d = new Duo(1.1, 2.2)
  println(p.less())
  println(d.less())

// task 3: Ad-hoc Polymorphism
// Reimplement the reduce function from lec1 home work with ad-hoc Polymorphism. Implement both handwritten and library solutions

  trait Addable[A] {
    def add(a:A, b:A):A
  }
  
  trait AddableWithZero[A] extends Addable[A] {
    def zero:A
  }

  object AddableWithZero {

    implicit val IntAddableWithZero  = new AddableWithZero[Int] {
      def zero:Int = 0
      def add(a:Int, b:Int):Int = a + b 
    }
    implicit val StringAddableWithZero  = new AddableWithZero[String] {
      def zero:String = ""
      def add(a:String, b:String):String = a + b 
    }
    
  }
  
  // Define a function in terms of Type parameter with a Context Bound 
  // Context bounds are preferable, since they give an immediate understanding of what the input type may be 
  def reduce[A:AddableWithZero] (arr:Vector[A]):A  = {
    val ev  = implicitly[AddableWithZero[A]]

  // This is equivalent
  //def reduce[A] (arr:Vector[A])(implicit ev:AddableWithZero[A]):A  = {
    
    val tmp = arr.foldLeft(ev.zero)(ev.add)
    tmp
  }

  val vint = Vector(-1, 2, 4)
  val vstr = Vector("a", "b", "c")

  val res0  = reduce(vint)
  val res1  = reduce(vstr)
  
  println (s"res0 = $res0")
  println (s"res1 = $res1")
 
  // Or even better! Instantiate Cats/ScalaZ monoid instance and avoid reinventing own type classes
  // More info here: https://typelevel.org/cats/typeclasses/monoid.html
  def reduceMonoid[A] (arr:Vector[A])(implicit ev:Monoid[A]):A  = arr.foldLeft(ev.empty)(ev.combine)

  val res2  = reduceMonoid(vint)
  val res3  = reduceMonoid(vstr)
  
  println (s"res2 = $res2")
  println (s"res3 = $res3")

}

