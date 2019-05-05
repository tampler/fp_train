
package lec1

object AdvancedSolutions extends App {

  def Sum (vec:Vector[Int]):Int = ???

  // Task 1

  // Implement a function, which sums all elevements in the int vector in several different ways 

  // With a lib function 
  // Using vars
  // using immutables only
  // using for iteration 
  // using recursion
  // your own idea
 
  type VecInt = Vector[Int]

  // Lib method
  def Sum0 (vec:VecInt):Int = vec.sum 
  
  // mutable variables
  def Sum1 (vec:VecInt):Int = {
    var res = 0

    // iterate over array as in Java and Python and add to the output sum
    for (item <- vec)
      res += item
    
    res
  }

  // mutable variables. Iterate with map 
  def Sum2 (vec:VecInt):Int = {
    var res = 0 
    vec map ( v => res = res + v)
    
    res
  }
  
  // mutable variables. Iterate with foreach
  def Sum3 (vec:VecInt):Int = {
    var res = 0 
    vec foreach ( v => res = res + v)
    
    res
  }
  
  // Loop thru the vector
  def Sum4 (vec:VecInt):Int = {
    var res  = 0

    for (i <- 0 until vec.length)
      res += vec(i)
    
    res
  }

  def Sum5 (vec:VecInt):Int = {
    val res  = vec reduce (_ + _)
    res
  }
  
  def Sum6 (vec:VecInt):Int = {
    val res  = vec.foldLeft (0) (_ + _)
    res
  }

  
  //--------------------------------------------------------------------------------------------
  // Using a case class to wrap the value
  //--------------------------------------------------------------------------------------------
  
  case class Wrap (a:Int) {
    def add(b:Int):Wrap = copy(a = a + b)
  }

  def Sum7 (vec:VecInt) = {
    val init:Wrap = Wrap (0)
    
    // This creates a Vector of objects, each contains a value from input VecInt
    val tmp:Vector[Wrap] = vec map ( v => init.add(v) )

    // Walk thru the vector of wraps, fetch the value and add it to the accumulator
    val res:Int = tmp map ( v => v.a ) reduce (_ + _)
    
    res
  }

  //--------------------------------------------------------------------------------------------
  // Let's test all this stuff
  //--------------------------------------------------------------------------------------------
  
  val arr  = Vector (1,2,3)
  
  for (i <- 0 until 8) {
    val res = i match {
      case 0 =>  Sum0(arr)
      case 1 =>  Sum1(arr)
      case 2 =>  Sum2(arr)
      case 3 =>  Sum3(arr)
      case 4 =>  Sum4(arr)
      case 5 =>  Sum5(arr)
      case 6 =>  Sum6(arr)
      case 7 =>  Sum7(arr)
      case _ =>  0
    }
    println (s"res $i = $res")
  }
    
  
    

  //--------------------------------------------------------------------------------------------

  // Task 2 

  // For recursive Implementation, can you implement a function, which sums up very large vectors? What are potential dangers for a recursive implementation?

}
