package lec1

import scala.collection.mutable.ArrayBuffer


//--------------------------------------------------------------------------------------------
// Immutable/mutable array for mutable data
//--------------------------------------------------------------------------------------------
object App0 extends App {


  def printme (arr: ArrayBuffer[Int]):Unit = {
    arr foreach { println }
  }


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

//--------------------------------------------------------------------------------------------
// Traits and Basic Inheritance
//--------------------------------------------------------------------------------------------
object App1 extends App {

  // See Odersky p.272

 
  // Food hierarchy
  trait Food 
  trait Meat  extends Food 
  trait Grass extends Food

  // Animals hierarchy
  abstract class Animal {
    def speak:Unit    // abstract method, defined not implemented
                      // can't instantiate abstract classes
  }

  // val animal  = new Animal() // won't compile
  
  
  trait Cat extends Animal with Grass with Meat {
    override def speak() = println ("Meow")
  }
  
  trait Dog extends Animal with Meat with Grass {
    override def speak() = println ("Woof")
  }
  
  // Watch the order of mix-ins
  class Mutant0 (name:String) extends Animal with Cat with Dog 
  val m0  = new Mutant0 ("Peggy")
  m0.speak()


  class Mutant1 (name:String) extends Animal with Dog with Cat
  val m1  = new Mutant1 ("Wolfy")
  m1.speak()

  
}

//--------------------------------------------------------------------------------------------
// File IO and parsing
//--------------------------------------------------------------------------------------------

object App2 extends App {

  import java.io.File
  import scala.io.Source
  
  // Person to parse
  case class Person (first:String, last:String, born:Int)

  //--------------------------------------------------------------------------------------------
  // Simple, Imperative, OOP-type with low safety and possible exceptions
  //--------------------------------------------------------------------------------------------
  
  val arr = ArrayBuffer[Person] ()
  
  // Read text file, imperative OOP style
  val fname  = "dump.txt"

  // danger! Read file may cause an exception
  for (line <- Source.fromResource(fname).getLines){
    println (line)

    val cols  = line.split(" ").map(_.trim)
    
    // danger! toInt may cause an exception
    arr += Person(cols(0), cols(1), cols(2).toInt)
  
  }

  println ("Parsed data:")
  arr foreach { println }
  
  

}
