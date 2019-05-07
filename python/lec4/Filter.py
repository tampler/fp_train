
numbers = [13, 4, 18, 35]
div_by_5 = filter(lambda num: num % 5 == 0, numbers)

# We can convert the iterator into a list
print(list(div_by_5)) # [35]

# Combine map and filter


# Let's arbitrarily get the all numbers divisible by 3 between 1 and 20 and cube them
arbitrary_numbers = map(lambda num: num ** 3, filter(lambda num: num % 3 == 0, range(1, 21)))

print(list(arbitrary_numbers)) # [27, 216, 729, 1728, 3375, 5832]
