import sys
from math import inf

N, M = map(int, sys.stdin.readline().split())
adj_mat = [[0] * N for _ in range(N)]
adj_list = [[] for _ in range(N)]
for _ in range(M):
    u, v, w = map(int, sys.stdin.readline().split())
    adj_mat[u][v] = w
    adj_mat[v][u] = w

    adj_list[u].append([v, w])
    adj_list[v].append([u, w])


def Prim_mat(start):
    visited = set()
    visited.add(start)

    total_distance = 0

    for _ in range(N - 1):
        min_distance, next_node = inf, None
        for node in visited:
            for j in range(len(adj_mat[node])):
                print(j)
                if j not in visited and 0 < adj_mat[node][j] < min_distance:
                    min_distance = adj_mat[node][j]
                    next_node = j
        total_distance += min_distance
        visited.add(next_node)

    return total_distance


def Prim_list(start):
    visited = set()
    visited.add(start)

    total_distance = 0
    for _ in range(N - 1):
        min_distance, next_node = inf, None
        for Node in visited:
            for j in adj_list[Node]:
                if j[0] not in visited and 0 < j[1] < min_distance:
                    min_distance = j[1]
                    next_node = j[0]
        total_distance += min_distance
        visited.add(next_node)

    return total_distance


print(Prim_mat(0))
print(Prim_list(0))

# 3 3
# 0 1 5
# 0 2 6
# 1 2 7
