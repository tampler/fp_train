module Lib
    ( qsort
    ) where

qsort [] = []

qsort (x:xs) = qsort left ++ [x] ++ qsort right
  where 
    left  = [ y | y <- xs, y < x ]
    right = [ y | y <- xs, y >= x ]
