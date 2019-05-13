product = 1
x = [1, 2, 3, 4]
for num in x:
    product = product * num

from functools import reduce

product = reduce((lambda x, y: x * y),[1, 2, 3, 4])

print(product)
