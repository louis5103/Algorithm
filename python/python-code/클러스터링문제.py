import math

def distance(item1, item2):
    x_distance = (item2[0] - item1[0]) ** 2 if item2[0]-item1[0] != 0 else 0
    y_distance = (item2[1] - item1[1]) ** 2 if item2[1]-item1[1] != 0 else 0
    return math.sqrt(x_distance + y_distance)

def klustering(coordinates, k):
    n = len(coordinates)
    kluster = [ [] for _ in range(k)]
    C = [ None for _ in range(k)]
    C[0] = 1
    
    for j in range(1, k):
        D = [ None for _ in range(n) ]
        for i in range(n):
            if i not in C:
                distance_set = [ (i, distance(coordinates[i], coordinates[C[J]])) for J in range(j) ]
                D[i] = min( distance_set, key= lambda l: l[1])
            else:
                D[i] = (None, 0)
        C[j] = max(D, key= lambda l: l[1])[0]
        

    for i in range(n):
        if i not in C:
            data_set = [ (j, distance(coordinates[i], coordinates[C[j]])) for j in range(k) ]
            idx, value = min( data_set, key=lambda l:l[1])
            kluster[idx].append(coordinates[i])
    return C, kluster

coordinates = [(1,5), (3,6), (5,2), (7,10), (8,5), (22,26), (25,20), (28,30), (29, 25), (32, 18), (33,25), (10,50), (15,51), (20,58), (16,54), (12,60), (48,46), (46,39), (50,40)]
coordinates.sort()

k = 4
test_C, test_kluster = klustering(coordinates, k)

print(f' 센터: {[ coordinates[i] for i in test_C ]}')
for i, set in enumerate(test_kluster):
    print(f'{i}번째 클러스터: {set}')

