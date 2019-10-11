package lec2

import Common._

object Fruits {

  // Simple, boilerplate approach
  def addApples(a: Apple, b: Apple): String = a.color + b.color
  def addOranges(a: Orange, b: Orange): Int = a.size + b.size

  // FP approach

  trait Addable[A] {
    def add(a: A, b: A): A
  }

  // trait Monoid[A] extends Semigroup[A] {
  //   def zero: A
  // }

  object Addable {
    implicit val AppleAdd: Addable[Apple] = new Addable[Apple] {
      def add(a: Apple, b: Apple): Apple = new Apple(a.color + b.color)

    }

    implicit val OrangeAdd: Addable[Orange] = new Addable[Orange] {
      def add(a: Orange, b: Orange): Orange = new Orange(a.size + b.size)

    }

  }

}
