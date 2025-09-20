def editdistance(S, T):
    def show_E():
        for row in E:
            print(row)
    m = len(S)
    n = len(T)
    E = [ [None for _ in range(n+1)] for _ in range(m+1)]

    for i in range(m+1):
        E[i][0] = i
    for j in range(n+1):
        E[0][j] = j
    for i in range(1, m+1):
        for j in range(1, n+1):
            alpha = 0 if S[i-1] == T[j-1] else 1
            E[i][j] = min(E[i][j-1]+1 , E[i-1][j]+1, E[i-1][j-1] + alpha)
    show_E()
    return E[m][n]

S = "strong"
T = "stone"
minimum = editdistance(S, T)
print("result-> E[i, j] =", minimum)