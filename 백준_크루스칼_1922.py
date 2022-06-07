import sys

node_num = int(sys.stdin.readline())    # N
edge_num = int(sys.stdin.readline())    # M
edge = [list(map(int, sys.stdin.readline().split())) for _ in range(edge_num)]  # G

edge.sort(key=lambda x: x[2])
set = ()
def kruscal(N, M, G):
    while len(set) < node_num:
