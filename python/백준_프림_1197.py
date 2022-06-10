import sys

node_num, edge_num = map(int, sys.stdin.readline().split())
adj_list = [[] for _ in range(node_num)]
for i in range(edge_num):
    u, v, distance = map(int, sys.stdin.readline().split())
    adj_list[u].append([v, distance])
    adj_list[v].append([u, distance])

T = set()
start = 0
T.add(start)
D = []
while len(T) < node_num:
    for i in T:
        for j in adj_list[i]:
            pass
        pass
    pass

# https://velog.io/@nathan29849/Python-Algorithm-class-Graph-7-Prim-Kruskal
