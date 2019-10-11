package lec2
package Common

class Apple(val color: String) {
  override def toString = color
}

class Orange(val size: Int) {
  override def toString = size.toString
}

class Box(numOranges: Int, numApples: Int) {
  def count: Int = ??? // how to add Apples to Oranges ???
}
