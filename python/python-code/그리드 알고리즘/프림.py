import math


class MinHeap:
    MAX_SIZE = 100
    def __init__(self):
        self.heap_size = 0
        self.heap = [ None for _ in range(self.MAX_SIZE)] 
        self.pos = [ None for _ in range(self.MAX_SIZE)]   # 정점? v 위치 찾기

    def left(self, idx):
        return idx << 1
    def right(self, idx):
        return (idx << 1) + 1
    def parent(self, idx):
        return idx >> 1

    def is_empty(self):
        if self.heap:
            return False
        return True
    def is_full(self):
        if self.heap:
            return True
        return False

    def insert(self, item): # item: (v, w)
        self.heap_size += 1
        self.heap[self.heap_size] = item

        cur_idx = self.heap_size
        while cur_idx != 1 and item[1] < self.heap[self.parent(cur_idx)][1]:
            self.heap[cur_idx] = self.heap[self.parent(cur_idx)]
            self.pos[self.heap[cur_idx][0]] = cur_idx
            cur_idx = self.parent(cur_idx)

        self.heap[cur_idx] = item
        self.pos[self.heap[cur_idx][0]] = cur_idx

    def pop(self):
        ret_element = self.heap[1]
        item = self.heap[self.heap_size]
        self.heap[self.heap_size] = None
        self.heap_size -= 1

        cur_idx = 1
        child = self.left(cur_idx)
        while child <= self.heap_size:
            if child < self.heap_size and self.heap[child][1] > self.heap[self.right(cur_idx)][1]:
                child = self.right(cur_idx)

            if item[1] < self.heap[child][1]:
                break
            self.heap[cur_idx] = self.heap[child]
            self.pos[self.heap[cur_idx][0]] = cur_idx

            cur_idx = child
            child = self.left(cur_idx)

        self.heap[cur_idx] = item
        self.pos[self.heap[cur_idx][0]] = cur_idx
        return ret_element

    def decrease_weight(self, item):    # item의 가중치 w를 감소시킴. w가 무조건 기존 w보다 작아야됨.
        cur_idx = self.pos[item[0]]
        while cur_idx > 1 and item[1] < self.heap[self.parent(cur_idx)][1]:
            self.heap[cur_idx] = self.heap[self.parent(cur_idx)]
            self.pos[self.heap[cur_idx][0]] = cur_idx
            cur_idx = self.parent(cur_idx) 
        self.heap[cur_idx] = item
        self.pos[self.heap[cur_idx][0]] = cur_idx

class Prim:
    def __init__(self, vertex_num):
        self.vertex_num = vertex_num
        self.edge = [ [ None for _ in range(self.vertex_num)] for _ in range(self.vertex_num)]
        self.adj = [ [] for _ in range(self.vertex_num)]  # u, v, w

    def add_edge(self, u, v, w):
        self.adj[u].append(v)
        self.adj[v].append(u)

        self.edge[u][v] = w
        self.edge[v][u] = w

    def prim(self, start):
        def prim_print(end):
            cur_idx = end
            while D[cur_idx][0] != None:
                cur_idx = D[cur_idx[0]]

        S = set()
        D = [ [None, math.inf] for _ in range(self.vertex_num)]
        mst = []
        minheap = MinHeap()
        D[start] = [None, 0]
        for i in range(self.vertex_num):
            if i == start:
                minheap.insert([i, 0])
                continue
            minheap.insert([i, math.inf])
        print("최소힙에 모든 노드에 대한 정보 insert: ", minheap.heap[:7])
        while len(S) != self.vertex_num:
            item = minheap.pop() 
            print("minheap에서 pop한 item: ", item)
            u = item[0]
            _from = D[u][0]
            w = item[1]

            S.add(u)
            mst.append([_from, u, w])
            print("해당 노드의 인트노드 리스트: ", self.adj[u])
            print("S 리스트 : ", S)
            for adj_node in self.adj[u]:
                if adj_node not in S and self.edge[u][adj_node] < D[adj_node][1]:
                    print()
                    D[adj_node] = [u, self.edge[u][adj_node]]   
                    print("갱신된 D[",adj_node,"]의 요소: ", D[adj_node])
                    print("decrease 전: ", minheap.heap[:7])
                    minheap.decrease_weight([adj_node, D[adj_node][1]])
                    print("decrease 후: ", minheap.heap[:7])
                    print()
                    print("="*40)
            print(mst)
            
test = Prim(6)
test.add_edge(0,1,3)
test.add_edge(0,3,2)
test.add_edge(0,4,4)
test.add_edge(1,3,4)
test.add_edge(1,2,1)
test.add_edge(1,5,2)
test.add_edge(2,5,1)
test.add_edge(3,4,5)
test.add_edge(3,5,7)
test.add_edge(4,5,9)

test.prim(2)
