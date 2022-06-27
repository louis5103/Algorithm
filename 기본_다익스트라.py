# 1916
import sys
from math import inf

N = int(sys.stdin.readline())
M = int(sys.stdin.readline())

adj_mat = [[inf] * (N + 1) for _ in range(N + 1)]

for _ in range(M):  # 노드번호 0부터 시작?
    u, v, w = map(int, sys.stdin.readline().split())
    adj_mat[u][v] = w


def Daijkstra(start):
    def get_minD():
        min = inf
        index = inf
        for i in range(N + 1):
            if D[i] < min and not visited[i]:
                min = D[i]
                index = i
        return index

    visited = [False for _ in range(N + 1)]
    D = [inf] * (N + 1)

    for i in range(N + 1):
        D[i] = adj_mat[start][i]
    visited[start] = True

    for _ in range(N - 1):
        # 방문하지 않은 노드중에서 D의 최소값인 노드번호 구하기
        cur = get_minD()
        visited[cur] = True

        for j in range(N + 1):
            if not visited[j]:
                if D[cur] + adj_mat[cur][j] < D[j]:
                    D[j] = D[cur] + adj_mat[cur][j]

    return D


print(adj_mat)
print(Daijkstra(1))
