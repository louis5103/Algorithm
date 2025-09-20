import math

def dpcoinchange(D, n):
    C = [  math.inf for _ in range(n+1) ]
    C[0] = 0
    for j in range(1, n+1):
        for d in D:
            if d <= j and C[j-d] + 1 < C[j]:
                print(j-d)
                C[j] = C[j-d] + 1
    print(C)
    return C[j]

n = 20
D = [16, 10, 5, 1]
result = dpcoinchange(D, n)
print(result)