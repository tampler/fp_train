module Main where

import Lib

main = do
  let result = qsort arr
  putStrLn $ show result 
  where 
    arr = [1,3,-2,11,0,4]
