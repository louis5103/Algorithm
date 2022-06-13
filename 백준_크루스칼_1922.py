import sys

def kruscal(N, M, G):
    G.sort(key=lambda x: x[2])
    parent = [i for i in range(node_num)]
    edge_cost = 0

    def parent_find(parent, vertex):
        if parent[vertex] != vertex:
            parent[vertex] = parent_find(parent, parent[vertex])
        return parent[vertex]

    def union_find(parent, vertex_1, vertex_2):
        vertex_1 = parent_find(parent, vertex_1)
        vertex_2 = parent_find(parent, vertex_2)

        if vertex_1 < vertex_2:
            parent[vertex_2] = vertex_1
        else:
            parent[vertex_1] = vertex_2

    for i in range(M):
        if parent_find(parent, G[i][0]) != parent_find(parent, G[i][1]):
            union_find(parent, G[i][0], G[i][1])
        else:
            edge_cost += G[i][2]
    return edge_cost


while True:
    node_num, edge_num = map(int, sys.stdin.readline().split())
    if node_num == 0 and edge_num == 0:
        break

    edge = [tuple(map(int, sys.stdin.readline().split())) for _ in range(edge_num)]  # G
    print(kruscal(node_num, edge_num, edge))