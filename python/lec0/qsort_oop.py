# This implements OOP style implementation of a quicksort algorithm

import random

def quicksort(nums):
   if len(nums) <= 1:
       return nums
   else:
       q = random.choice(nums)
       s_nums = []
       m_nums = []
       e_nums = []
       for n in nums:
           if n < q:
               s_nums.append(n)
           elif n > q:
               m_nums.append(n)
           else:
               e_nums.append(n)
       return quicksort(s_nums) + e_nums + quicksort(m_nums)

# Improved memory usage
def quicksort_improved(nums, fst, lst):
   if fst >= lst: return

   i, j = fst, lst
   pivot = nums[random.randint(fst, lst)]

   while i <= j:
       while nums[i] < pivot: i += 1
       while nums[j] > pivot: j -= 1
       if i <= j:
           nums[i], nums[j] = nums[j], nums[i]
           i, j = i + 1, j - 1
   quicksort(nums, fst, j)
   quicksort(nums, i, lst)


if __name__ == "__main__":

    nums = [1,3,-2,11,0,4]

    res = quicksort (nums)

    for i in res:
        print (i)
