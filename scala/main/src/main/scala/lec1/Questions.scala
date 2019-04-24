package lec1

import scala.collection.mutable.ArrayBuffer

object App0 extends App {

  def printme (arr: ArrayBuffer[Int]):Unit = {
    arr foreach { println }
  }

  //--------------------------------------------------------------------------------------------
  // Immutable/mutable array for mutable data
  //--------------------------------------------------------------------------------------------

  // Immutable array buffer
  val arr0, arr1 = ArrayBuffer[Int] (1,2)
  var arr2 = ArrayBuffer[Int](-1,-2)


  println ("Initial data:")
  printme (arr0)
  printme (arr1)
  printme (arr2)

  // ok, arr0 and arr1 are from the mutable package
  arr0 += 1
  arr1 += 2 

  //arr0 = arr1 // won't compile
  arr2 = arr1 // ok, arr2 is var

  println ("Updated data:")

  printme (arr0)
  printme (arr1)
  printme (arr2)
  
}

object App1 extends App {

  // See Odersky p.272

  //--------------------------------------------------------------------------------------------
  // Trait
  //--------------------------------------------------------------------------------------------
  
  trait Food 
  trait Meat  extends Food 
  trait Grass extends Food

  abstract class Animal {
    def speak:Unit    
  }
  
  trait Cat extends Animal with Grass with Meat {
    def speak() = println ("Meow")
  }
  
  trait Dog extends Animal with Meat with Grass {
    def speak() = println ("Woof")
  }
  
  //class Mutant (name:String) extends Animal with Cat with Dog {
  class Mutant (name:String) extends Cat with Dog {
    //def speak = ???
  }
  
  //trait Mutant  extends Animal with Cat with Dog {
  //  //def speak = ???
  //}
  trait Base { override def toString = "Base" }
  class A extends Base { override def toString = "A->" + super.toString }
  trait B extends Base { override def toString = "B->" + super.toString }
  trait C extends Base { override def toString = "C->" + super.toString }
  class D extends A with B with C { override def toString = "D->" + super.toString } 
  
}
