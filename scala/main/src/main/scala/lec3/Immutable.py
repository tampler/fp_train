
mutable_collection = ['Tim', 10, [4, 5]]
immutable_collection = ('Tim', 10, [4, 5])

# Reading from data types are essentially the same:
print(mutable_collection[2])    # [4, 5]
print(immutable_collection[2])  # [4, 5]

# Let's change the 2nd value from 10 to 15
mutable_collection[1] = 15

# This fails with the tuple
immutable_collection[1] = 15

immutable_collection[2].append(6)
print(immutable_collection[2])  # [4, 5, 6]

immutable_collection[2] = [4, 5]
# This throws a familiar error:
# TypeError: 'tuple' object does not support item assignment




