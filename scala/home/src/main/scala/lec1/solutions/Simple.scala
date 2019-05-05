// Home work for Lec1: Functions in Scala 
// Fix errors, rewrite functions or write your own implementations here 
// bku Apr 27 2019
package lec1


object SimpleSolutions extends App {
  
  // Fix error
  //--------------------------------------------------------------------------------------------
  def Func0 (a:Int):Some[Int] = {
    Some(-1*a)
  }

  // Multiple solutions possible, the simplest is to allow automatic type derivation
  val res0 = Func0(7)

  // Rewrite to accept the lambda
  //--------------------------------------------------------------------------------------------
  
  def Func1 (f:(Int, Int) => Int) =  { 0 }

  val res1  = Func1 ((x:Int, y:Int) => 2*x + y)
  
  // Curry Func2 and write the full curried definition, as in Func0
  //--------------------------------------------------------------------------------------------
  def Func2 (a:Int, b:Int, c:Int) = a + b + c

  def Func22  = (Func2 _).curried 
  //Func222.boom() - this is how you can check actual function signature, which is Int => (Int => (Int => Int))
  
  // So, the answer is:
  def Func222 (a:Int) = ((b:Int) => (c:Int) => a + b + c)
  
  val res222 = Func222(1)(2)(3)
  println (s"res222 = $res222")
  


  // f(x) = 1/x was definied as Partially Defined Function. Rewrite it with 
  // A. pattern matching 
  // B. Option
  //--------------------------------------------------------------------------------------------

  def Func30 (a:Int):Int = {
    a match {
      case 0 => 0
      case _ => 1/a
    }
  }

  def Func31 (a:Int):Option[Int] = {
    if ( a == 0 ) None 
    else Some (1/a)
  }
  
  def Func32 (a:Int):Option[Int] = {
    a match {
      case 0 => None
      case _ => Some(1/a)
    }
  }
  

  // Higher order function (HOF) "reduce" from lecture notes returns incorrect result for the add reducer 
  // correct the error and implement a valid "reduce" for add and mul reducers
  //--------------------------------------------------------------------------------------------
  def reduce (arr:Vector[Int], zero:Int, f:(Int,Int) => Int ):Int  = {
    val tmp = arr.foldLeft(zero) { f }
    tmp
  }

  val data  = Vector (-1,2,4)
  

  // Build a custom reducer. 
  // NOTE There's a ***BETTER*** solution with ad-hoc polymorphism
  sealed abstract class Reducer {
    def zero:Int
    def reduce(a:Int, b:Int):Int
  }

  class AddReducer extends Reducer {
    override def zero    = 0
    override def reduce(a:Int, b:Int)  = a + b   
  }
  
  class MulReducer extends Reducer {
    override def zero    = 1
    override def reduce(a:Int, b:Int)  = a * b   
  }
  
  val adder  = new AddReducer 
  val muler  = new MulReducer 
  
  val res10  = reduce (data, adder.zero, adder.reduce)
  println (s"res10 = $res10")
  
  val res11  = reduce (data, muler.zero, muler.reduce)
  println (s"res11 = $res11")
  
  
  
  // Write an implicit function to support the operation below
  //--------------------------------------------------------------------------------------------
  
  implicit def Func4(value:Float):Int = value.toInt

  def doubleme (a:Int) = 2*a

  val res2  = doubleme(2.5F)
  

}
