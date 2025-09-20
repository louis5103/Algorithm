# 백준 1916
import sys
from math import inf

io = sys.stdin.readline

N = int(io())
M = int(io())
adj_list = [[] for _ in range(N+1)]

for _ in range(M):
    u, v, w = map(int, io().split())
    adj_list[u].append([v, w])
    adj_list[v].append([u, w])

start, end = map(int, io().split())

def Daijkstra(start, end):
    D = [inf for _ in range(N+1)]
    P = [0 for _ in range(N+1)]
    MST = []
    total_cost = 0

    S = set()
    S.add(start)

    D[start] = 0
    P[start] = 0

    cur_idx = start
    while len(S) < N:     # 또는 추가된 간선이 N-1개
        for node in adj_list[cur_idx]:    # S의 인접노드 가중치 D에 업데이트
            if node[0] not in S and D[node[0]] > node[1] + D[cur_idx]:
                D[node[0]] = node[1] + D[cur_idx]

        # 가장 작은 가중치를 가진 S 밖의 정점 선택. -> S에 추가, cost에 추가

        next_node, min_dist = 0, inf
        for i in S:
            for node in adj_list[i]:
                if node[0] not in S and min_dist > node[1]:
                    next_node, min_dist = node[0], node[1]

        D[next_node] = min_dist + D[cur_idx]
        MST.append([cur_idx, next_node, min_dist])
        S.add(next_node)
        P[next_node] = cur_idx
        cur_idx = next_node


    return MST, P
# end까지의 최소거리는 아님. 불필요한 노드 추가되어 있음.

MST, P = Daijkstra(start, end)    # 무방향에 대해 적용가능. 방향그래프는 적용안됨

print(P)

