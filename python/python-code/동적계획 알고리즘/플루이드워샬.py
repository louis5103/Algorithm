import math

def show_D(D):
    for row in D:   
        print(row)
    
def floyd_warshall(D, n):
    for k in range(1, n+1):
        for i in range(1, n+1):
            if k == i:
                continue
            for j in range(1, n+1):
                if k == j:
                    continue
                print("="*40)
                show_D(D)
                print("-"*40)
                print(i, k, j)
                print(D[i][k], D[k][j])
                D[i][j] = min(D[i][k]+D[k][j], D[i][j])
        
def initial_D():
    for i in range(10):
        D.append([ 0 for _ in range(i)])
                
    D[1].extend([40,41,154,232,320,297,408,432])
    D[2].extend([55,174,253,352,318,447,453])
    D[3].extend([133,189,300,268,356,391])
    D[4].extend([97,185,149,259,283])
    D[5].extend([106,220,331,323])
    D[6].extend([219,330,268])
    D[7].extend([111,136])
    D[8].extend([53])

def initial_D2():
    D = [ [] for _ in range(6)]

    D[1].extend([None, 0, 4, 2, 5, math.inf])
    D[2].extend([None, math.inf, 0, 1, math.inf, 4])
    D[3].extend([None, 1, 3, 0, 1, 2])
    D[4].extend([None, -2, math.inf, math.inf, 0, 2])
    D[5].extend([None, math.inf, -3, 3, 1, 0])
    
    return D

D = initial_D2()
# D = initial_D()
show_D(D)
floyd_warshall(D, len(D)-1)
show_D(D)