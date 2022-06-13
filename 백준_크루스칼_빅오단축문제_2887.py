import sys


def kruscal(N, G):
    parent = [i for i in range(N)]
    edge_cost = 0
    G.sort(key=lambda x: x[2])

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

    for v in range(len(G)):
        if parent_find(parent, G[v][0]) != parent_find(parent, G[v][1]):
            union_find(parent, G[v][0], G[v][1])
            edge_cost += G[v][2]

    return edge_cost


node_num = int(sys.stdin.readline())
edge = [[], [], []]

edge_2 = []
for i in range(node_num):
    x, y, z = map(int, sys.stdin.readline().split())
    edge[0].append((x ,i))
    edge[1].append((y, i))
    edge[2].append((z, i))

for dim in range(3):
    edge[dim].sort()
    for v in range(len(edge[dim])-1):
        edge_2.append((edge[dim][v][1], edge[dim][v+1][1], abs(edge[dim][v+1][0]-edge[dim][v][0])))

print(kruscal(node_num, edge_2))