
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

  // Partially defined function 

  

  // Partially applied function
  // Higher Order function

}

