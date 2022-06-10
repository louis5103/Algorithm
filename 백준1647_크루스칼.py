import sys

node_num, edge_num = list(map(int, sys.stdin.readline().split()))
list = [list(map(int, sys.stdin.readline().split())) for _ in range(edge_num)]

list.sort(key=lambda x: x[2])
print(list)

def kruscal():
    visited_set = ()
    for i in range(edge_num):
        if