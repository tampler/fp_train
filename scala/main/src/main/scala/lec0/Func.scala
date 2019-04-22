
package lec0

object Func extends App {

  // Simple function
  def byTwo (a:Int):Int = 2*a
  
  val p  = byTwo(5) // p = 10
  

  // Simple class
  class Sample (str:String) {
    def speak():Unit = println (str)
  }
  

  // Create class instance
  val cl  = new Sample("Meow")

  // create function from the class method
  val method  = cl.speak()

  println ((method))
  

  // Pure function 
  // https://alvinalexander.com/scala/fp-book/definition-of-pure-function
  def sinx (x:Float)  = math.sin(3*math.Pi/2 * x)
  val res0  = sinx (1.0F)
  println (res0)


  // Impure function 
  def impure (host:String, port:Int)  = {

    // All below are "effects", which make this function impure 

    // host = "mystring" // <-- impure, changes input data 
    // println (host) // <-- impure, printing to console  
    // connectDb (host, port) // <-- impure , connects to db 
    // showGUI () // <-- impure, prints to gui

    val tmp = 3
    tmp // equal to return tmp
    
  
  }
  

}

