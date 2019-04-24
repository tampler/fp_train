
package lec1 

object Func extends App {

  // Simple function

  def myfunc (a:Int):Int = {
    val tmp  = 2*a
    tmp
  }
  
  val res0  = myfunc(4)
  println (s"res0 = $res0")
  
  
  // Class method 

  class MyClass (name:String) {
    def hello ():Unit = println (s"Hello, $name")
  }

  val guy  = new MyClass ("Boris")
  guy.hello()
  
  // Lambda function 

  def lambda (multiplier: Int => Int):Int = {
    1 to 4 filter (_ % 2 == 0) map multiplier reduce ( _ * _)
  }


  val res1  = lambda ( (x:Int) => { 2*x } )
  println (s"res1 = $res1")
  
  // Closure  

  val factor = 3

  val res2  = lambda ( (x:Int) => { factor*x } )
  println (s"res2 = $res2")

  // Curried function
  def normal (a:Int, b:Int, c:Int) = a + b +c 
  def curr0 (a:Int)(b:Int)(c:Int) = a + b +c
  def curr1 = (normal _).curried 

  val res3  = normal (4,5,6)
  val res4  = curr0 (4)(5)(6)
  val res5  = curr1 (4)(5)(6)
  
  println (s"res3 = $res3")
  println (s"res4 = $res4")
  println (s"res5 = $res5")

  // Partially defined function 

  //val pf0: PartialFunction[Int]
  

  // Partially applied function 
  // Implicit function
  // Higher Order function

}

