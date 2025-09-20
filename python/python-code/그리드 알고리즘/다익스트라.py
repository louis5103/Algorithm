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
        
class Daijkstra:
    def __init__(self, vertex_num) -> None:
        self.vertex_num = vertex_num
        self.adj = [ [] for _ in range(self.vertex_num)]
        self.edge = [ [ None for _ in range(self.vertex_num)] for _ in range(self.vertex_num)]
        self.minheap = MinHeap()
        
    def add_edge(self, u, v, w):
        self.adj[u].append(v)
        self.adj[v].append(u)
        self.edge[u][v] = w
        self.edge[v][u] = w
    
    def daijkstra(self, start):
        mst = []
        S = set()
        D = [ [None, math.inf] for _ in range(self.vertex_num)]
        #NOTE: self.minheap
        
        D[start] = [None, 0]
        for i in range(self.vertex_num):
            if i == start:
                self.minheap.insert((start, 0))
                continue
            self.minheap.insert([i, math.inf])
            
        print(self.minheap.heap[:7])

        while len(S) < self.vertex_num:
            print("="*40)
            print("pop 전 heap: ", self.minheap.heap[:7])
            node = self.minheap.pop()
            print("pop item: ", node)
            print("pop 후 heap: ", self.minheap.heap[:7])
            u = node[0]
            w = node[1]
            _from = D[u][0]
            
            if w != D[u][1]:
                print("continue case:", _from, u, w)
                continue
            
            S.add(u)
            mst.append([_from, u, w])
            adj_node = self.adj[u]
            print(u, "의 adj_node: ", adj_node)
            for v in adj_node: 
                if v not in S and D[v][1] > D[u][1] + self.edge[u][v]:
                    print(v,"에서 변경된 D 전: ", D)
                    D[v] = [u, D[u][1] + self.edge[u][v]]
                    print(v,"에서 변경된 D 후: ", D)
                    self.minheap.insert([v, D[v][1]])
            print(mst)
            print(D)
                    
# test = Daijkstra(4)
# test.add_edge(0, 1, 10)
# test.add_edge(0, 2, 3)
# test.add_edge(1, 3, 5)
# test.add_edge(2, 1, 5)
# test.add_edge(2, 3, 8)
# test.add_edge(3, 1, 4)
# test.add_edge(3, 2, 12)

# test.daijkstra(0)


test = Daijkstra(10)
test.add_edge(0, 1, 12)
test.add_edge(0, 2, 15)
test.add_edge(1, 3, 4)
test.add_edge(1, 4, 10)
test.add_edge(2, 7, 7)
test.add_edge(2, 9, 21)
test.add_edge(3, 4, 3)
test.add_edge(3, 6, 13)
test.add_edge(4, 7, 10)
test.add_edge(6, 5, 15)
test.add_edge(7, 8, 19)
test.add_edge(9, 8, 25)
test.add_edge(7, 5, 9)
test.add_edge(8, 5, 5)
test.daijkstra(0)







