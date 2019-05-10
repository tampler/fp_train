package lec2

import cats.{Semigroup}
import cats.implicits._


object AdvancedSolutions extends App {
// task 1: Implement the method for Vector[Int] and List[Int]
//  sealed abstract class Addable[A] {
//    def sum:A
//  }

// Taken from Dean Wampler, Alex Payne: Programming in Scala, 2-nd Edition. Chapter 15, "Higher Order Types"

  // Specify a generic Reduce Function. Use Contravariant parameter to support reduce on derived types
  trait Reduce[-F[_]] {
    def reduce[A](fa:F[A])(f:(A,A) => A):A
  }

  
  object Reduce {
    //def apply [F[_]] (implicit F: Reduce[F]): Reduce[F] = F

    implicit val SeqReduce  = new Reduce[Seq] {
      def reduce[A] (data:Seq[A])(f:(A,A) => A ):A = data reduce f
    }
    
    implicit val OptReduce  = new Reduce[Option] {
      def reduce[A] (data:Option[A])(f:(A,A) => A ):A = data reduce f
    }
    
  }

  // Generic sum function
  def sum[A:Semigroup, F[_]](container: F[A])(implicit red:Reduce[F]):A = {
    red.reduce(container)(Semigroup.combine(_,_))
  }

  val myvec   = Vector(1, 2, 3)
  val mylist  = List  (1, 2, 3)

  val mymap   = Map ( 1 -> "one",
                      2 -> "two",
                      3 -> "three"
                    )
  val myopt   = Some(1)
  val myvecopt= Vector(Some(1), Some(2), Some(3)) 
  

  val res0  = sum(myvec)
  val res1  = sum(mylist)
  val res2  = sum(myopt)
  
  // How to support THIS ?)
  //val res3  = sum(myvecopt) 
  //val res4  = sum(mymap) 

  println(s"res0 = $res0")
  println(s"res1 = $res1")
  println(s"res2 = $res2")

}

