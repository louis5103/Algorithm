import sys
from queue import Queue

vtx_num, edge_num, start = list(map(int, sys.stdin.readline().strip().split()))
start -= 1

class Graph:
    def __init__(self, vertex_num):
        self.adj_list = [[] for _ in range(vertex_num)]
        self.visited = [ False for _ in range(vertex_num)]

    def add_edge(self, u, v):
        self.adj_list[u].append(v)
        self.adj_list[v].append(u)

    def init_visited(self):
        for i in range(len(self.visited)):
            self.visited[i] = False

    def sort_adj_list(self):
        for i in range(len(self.adj_list)):
            self.adj_list[i].sort()

class Stack:
    def __init__(self):
        self.data = list()

    def empty(self):
        if not self.data:
            return True
        return False

    def push(self, data):
        self.data.append(data)

    def pop(self):
        return self.data.pop()

    def peek(self):
        return self.data[-1]

class DFS(Graph):
    def __init__(self, vertex_num):
        super(DFS, self).__init__(vertex_num)

    def __dfs_recursion(self, start):
        self.visited[start] = True
        print(start+1, end=' ')
        adj_v = self.adj_list[start]

        for vtx in adj_v:
            if not self.visited[vtx]:
                self.__dfs_recursion(vtx)

    def dfs(self, start):
        self.init_visited()
        self.__dfs_recursion(start)

    def iter_dfs(self, start):
        s = Stack()
        s.push(start)
        self.visited[start] = True
        print(start+1, end=' ')
        is_visited = False

        while not s.empty():
            v = s.peek()
            adj_v = self.adj_list[v]
            is_visited = False

            for u in adj_v:
                if not self.visited[u]:
                    s.push(u)
                    self.visited[u] = True
                    print(u+1, end=' ')
                    is_visited = True
                    break

            if not is_visited:
                s.pop()

class BFS(Graph):
    def __init__(self, vertex_num):
        super(BFS, self).__init__(vertex_num)

    def bfs(self, start):
        q = Queue()
        q.put(start)
        print(start+1, end=' ')
        self. visited[start] = True

        while not q.empty():
            vtx = q.get()
            adj_v = self.adj_list[vtx]

            for v in adj_v:
                if not self.visited[v]:
                    self.visited[v] = True
                    q.put(v)
                    print(v+1, end=' ')

dfs = DFS(vtx_num)
bfs = BFS(vtx_num)

for _ in range(edge_num):
    u, v = list(map(int, sys.stdin.readline().strip().split()))
    dfs.add_edge(u-1, v-1)
    bfs.add_edge(u-1, v-1)

dfs.sort_adj_list()
bfs.sort_adj_list()

dfs.iter_dfs(start)
print()
bfs.bfs(start)