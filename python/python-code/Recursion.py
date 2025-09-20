def recursion(i):
    if i<=0:
        return 1
    return i*recursion(i-1)
    
for i in range(1, 5):
    print(recursion(i))