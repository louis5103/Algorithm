import sys


class Stack:
    def __init__(self):
        self.container = []

    def push(self, key):
        self.container.append(key)

    def pop(self):
        return self.container.pop()

    def peek(self):
        return self.container[-1]

    def is_empty(self):
        if not self.container:
            return False
        return True

class DFS:
    def __init__(self, node_num, edge_num, start):
        self.visited = [False for _ in range(node_num)]
        self.adj_list = [[] for _ in range(node_num)]
        self.start_num = start - 1

    def init_visited(self):
        for i in range(len(self.visited)):
            self.visited[i] = False

    def add_edge(self, u, v):
        self.adj_list[u].append(v)
        self.adj_list[v].append(u)

    def __recursion(self, vertex):
        self.visited[vertex] = True
        print(vertex+1)
        adjcent_list = self.adj_list[vertex]
        for vtx in adjcent_list:
            if not self.visited[vtx]:
                self.__recursion(vtx)

    def dfs_recursion(self):
        self.init_visited()
        self.__recursion(self.start_num)


    def iter_recursion(self, vertex):
        s = Stack()
        self.init_visited()

        self.visited[vertex] = True
        s.push(vertex)
        print(vertex+1)

        while s.is_empty():
            tmp = s.peek()
            adjcent_list = self.adj_list[tmp]
            is_visited = False

            for i in adjcent_list:
                if not self.visited[i]:
                    s.push(i)
                    print(i+1)
                    self.visited[i] = True
                    break
                is_visited = True

            if is_visited:
                s.pop()





V, E, N = list(map(int, sys.stdin.readline().split()))
test = DFS(V, E, N)
for _ in range(E):
    u, v = list(map(int, sys.stdin.readline().split()))
    test.add_edge(u-1, v-1)

# test.dfs_recursion()
test.iter_recursion(test.start_num)