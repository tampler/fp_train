# This implements a FP style implementation of a quicksort algorithm

import random

def quicksort(nums):
   if len(nums) <= 1:
       return nums
   else:
       q = random.choice(nums)
   l_nums = [n for n in nums if n < q]

   e_nums = [q] * nums.count(q)
   b_nums = [n for n in nums if n > q]
   return quicksort(l_nums) + e_nums + quicksort(b_nums)

if __name__ == "__main__":

    nums = [1,3,-2,11,0,4]

    res = quicksort (nums)

    for i in res:
        print (i)
