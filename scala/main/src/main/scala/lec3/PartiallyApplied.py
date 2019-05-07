from functools import partial

def power(base, exponent):
    return base ** exponent

square = partial(power, exponent=2)
print(square(2))

powers = []
for x in range(2, 1001):
    powers.append(partial(power, exponent = x))

print(powers[0](3))
