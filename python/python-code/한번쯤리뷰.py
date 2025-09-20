import math
table = [
    ("marley", "5"),
    ("bob", "99"),
    ("another name", "3")
]
total = sum(int(v) for name,v in table)
total = sum(map(lambda x: int(x[1]), table))


test_sum = 0
test = [[(1,2), (3,3)], [(0,2),(2,3)], [(4,1),(1,3)], [(0,3),(1,5)], [(2,1),(1,4)]]
for i in range(len(test)):
    test_sum += sum( w for next, w in test[i])
print(math.ceil(test_sum/2))

