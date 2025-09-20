import math

def matrixchain(A):
    def show_matrix():
        for row in C:
            print(row)

    C = [ [None for _ in A] for _ in A]
    for i in range(1, len(A)):
        C[i][i] = 0
    
    for L in range(1, len(A)):
        for i in range(1, len(A)-L):
            j = i + L
            C[i][j] = math.inf
            for k in range(i, j):
                temp = C[i][k] + C[k+1][j] + A[i][0]*A[k][1]*A[j][1]
                if temp < C[i][j]:
                    C[i][j] = temp
    show_matrix()
    
A = [ None, [10, 20], [20, 5], [5, 15], [15, 30] ]
matrixchain(A)
