#1916
import sys
from math import inf

N = int(sys.stdin.readline())
M = int(sys.stdin.readline())
for _ in range(M):
    u, v, w = map(int, sys.stdin.readline().split())
    pass

start, end = map(int, sys.stdin.readline().split())


def Dijkstra(list, start, end):
    D = [ inf for _ in range(N)]

    # T 밖의 인접한 노드 가중치