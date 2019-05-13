
Task 1: Implement higher order functions map(), flatMap() and withFiler() for Option(Int).
It wraps Int in a class. Each value may be either Int or Null

flatMap() differs from map(), since it provides data flattening. For example, if you apply map() twice to your data structure, your result should be map(map(Option(Int))) -> Option(Option(Int)), whereas flatMap(flatMap(Option[Int])) -> Option(Int)

withFiler() is a Higher Order Function, which takes a function on input and applies it as a filter to every element

Task 2: Given an implementation from Task1, build a 1000 element

A. Unsorted binary tree.
B. Red-black tree

and calculate the sum of all nodes for Option(Int). Your filtering functions should be:

A. a % 2 == 0 (even numbers)
B. (a % 3 == 0) && (a % 5 == 0) (divisible by 3 and 5)
