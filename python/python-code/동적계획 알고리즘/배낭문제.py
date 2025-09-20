def knapsack(item, C):
    def show_K():
        for row in K:
            print(row)
            
    K = [ [None  for _ in range(C+1)] for _ in range(len(item)+1) ]
    for i in range(len(item)+1):
        K[i][0] = 0
    for w in range(C+1):
        K[0][w] = 0
    
    for i in range(1, len(item)+1):
        for w in range(1, C+1):
            if item[i-1][0] <= w:
                K[i][w] = max(K[i-1][w], K[i-1][ w - item[i-1][0] ] + item[i-1][1] )
            else:
                K[i][w] = K[i-1][w]
    show_K()
    return K[-1][-1]
    
item = [[5, 10], [4, 40], [6, 30], [3, 50]] #NOTE: item[i]: [무게, 가치]
C = 10
result = knapsack(item, C)
print(result)