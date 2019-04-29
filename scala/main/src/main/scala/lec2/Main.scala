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
    def speak:Unit    // abstract method, defined not implemented
                      // can't instantiate abstract classes
  }

  trait Cat extends Animal {
    override def speak() = println ("Meow")
  }
  
  trait Dog extends Animal  {
    override def speak() = println ("Woof")
  }
  
  // Watch the order of mix-ins
  class Mutant0 (name:String) extends Animal with Cat with Dog 
  val m0  = new Mutant0 ("Peggy")
  m0.speak()


  class Mutant1 (name:String) extends Animal with Dog with Cat
  val m1  = new Mutant1 ("Wolfy")
  m1.speak()

  //--------------------------------------------------------------------------------------------
  // Parametric Polymorphism in Scala
  //--------------------------------------------------------------------------------------------
  sealed abstract class Shape[A] {
    def Area:A // abstract by default
  }

  class Rectangle[A : Numeric ] (a:A, b:A) extends Shape[A] {

    // Define and import all numeric 
    val num  = implicitly[Numeric[A]]
    import num._
    
    def Area():A  = a * b
  }
  
  
  // Int
  val intRectangle  = new Rectangle[Int] (2,3)
  val res0  = intRectangle.Area()
  println (s"res0 = $res0")
  
  // Float
  val floatRectangle  = new Rectangle[Float] (-1.0F,4.0F)
  val res1  = floatRectangle.Area()
  println (s"res1 = $res1")
  
  


}
