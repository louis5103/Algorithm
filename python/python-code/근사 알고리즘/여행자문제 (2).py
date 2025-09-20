import math

class Node:
    def __init__(self) -> None:
        pass

class Stack:
    def __init__(self) -> None:
        self.container = []
        
    def is_empty(self):
        if self.container:
            return False
        return True
    
    def push(self, item):
        self.container.append(item)
    
    def pop(self):
        return self.container.pop()

    def peek(self):
        return self.container[-1]

class Heap:
    MAX_SIZE = 100
    def __init__(self) -> None:
        self.container = [ None for _ in range(self.MAX_SIZE)]
        self.pos = [ None for _ in range(self.MAX_SIZE)]
        self.heapsize = 0

    def is_empty(self):
        if self.container:
            return False
        return True
    def left(self, idx):
        return idx << 1
    def right(self, idx):
        return (idx << 1) + 1
    def parent(self, idx):
        return idx >> 1

    def insert(self, item):     #NOTE: item은 (to, w)
        self.heapsize += 1
        cur_idx = self.heapsize

        while cur_idx != 1 and item[1] < self.container[self.parent(cur_idx)][1]:
            self.container[cur_idx] = self.container[self.parent(cur_idx)]
            self.pos[self.container[cur_idx][0]] = cur_idx
            cur_idx = self.parent(cur_idx)
        self.container[cur_idx] = item 
        self.pos[self.container[cur_idx][0]] = cur_idx

    def pop(self):
        return_item = self.container[1]
        item = self.container[self.heapsize]
        self.container[self.heapsize] = None
        self.heapsize -= 1

        cur_idx = 1
        child = self.left(cur_idx)
        while child <= self.heapsize:
            if child < self.heapsize and self.container[self.left(cur_idx)][1] > self.container[self.right(cur_idx)][1]:
                child = self.right(cur_idx)
            if item < self.container[child][1]:
                break
            self.container[cur_idx] = self.container[child]
            self.pos[self.container[cur_idx][0]] = cur_idx

            cur_idx = child
            child = self.left(cur_idx)
        return return_item

    def decrease_weight(self, to, new_w):    #item이 container 안에 위치한 곳을 찾아서 가중치를 바꾼다.  container[cur] = (to, w)
        item = (to, new_w)
        cur_idx = self.pos[to]

        while cur_idx != 1 and item[1] < self.container[self.parent[cur_idx]]:
            self.container[cur_idx] = self.container[self.parent(cur_idx)]
            self.pos[self.container[cur_idx][0]] = cur_idx
            cur_idx = self.parent(cur_idx)
        self.container[cur_idx] = item
        self.pos[self.container[cur_idx][0]] = cur_idx 

class TSP:
    #NOTE: 필요한 기능
        # 1. MST 함수: 크루스칼 알고리즘(+ Union-Find) or 프림 알고리즘(+ 최소힙, decrease-weight) 
        # 2. 위상 정렬: Stack, 인접 리스트
    
    #NOTE: 메모
        # 1. kruscal, prim 나중에 클래스로 깔끔하게 분리하기. -> init 정보
            # 1-1. self.traveling_salesman_problem의 mst 수정 필요.
    def __init__(self, vertex_num) -> None:    
        self.vertex_num = vertex_num
        self.adj = [ [] for _ in range(self.vertex_num)]    #NOTE: self.adj[u] = (v, w)
        self.edge = []      # self.edge의 요소: (u, v, w)
        
    def add_edge(self, u, v, w):
        self.adj[u].append((v, w))
        self.adj[v].append((u, w))
        self.edge.append((u, v, w))
        
    def kruscal(self):      #parent
        self.parent = [ -1 for _ in range(self.vertex_num)]
        def simple_find(self, cur):
            while self.parent[cur] != -1:
                cur = self.parent[cur]
            return self.parent[cur]
        def collapsing_find(self, cur):
            root = simple_find(cur)     
            if cur == root:
                return self.parent[cur]

            trail = cur 
            while trail != root:   
                lead = self.parent[trail]
                self.parent[trail] = root
                trail = lead
            return root
        def union(self, u, v):      #NOTE: root u, v
            if self.parent[u] < self.parent[v]:        
                self.parent[u] += self.parent[v]
                self.parent[v] = u
            else:
                self.parent[v] += self.parent[u]
                self.parent[u] = v

        self.edge.sort(key= lambda w: w[2])
        mst = TSP(self.vertex_num)      #mst의 adj 인접노드 리스트가 필요

        idx = 0
        while len(mst.edge) < n-1:
            u, v, w = self.edge[idx]
            if self.collapsing_find(u) != self.collapsing_find(v):
                self.union(u, v)
                mst.edge.append(self.edge[0])       # (u, v, w)
            idx += 1
        
    def prim(self, start):
        heap = Heap()
        visited = [ False for _ in range(self.vertex_num)]
        D = [ (None, Math.inf) for _ in range(self.vertex_num)]  #각 정점에 대한 D[to] = (_from, w) 초기화. (None, math.inf) 
        S = set()

        for i in range(self.vertex_num):    #heap 초기화. start 노드:(start, 0), 나머지:(i, math.inf) -> 이후 decrease_weight로 변경.
            if i == start:
                heap.insert((i, 0))
                continue
            heap.insert((i, math.inf))

        while not heap.is_empty():
            _from, w = heap.pop()       #FIXME: ???
            for adj_node in self.adj[cur]:
                to = adj_node
                if to not in S and "_from에서 to로 가는 w" < D[to][1]:
                    pass
        pass

    def topology_sort(self, start):        
        pass
    
    def traveling_salesman_problem(self):
        tsp = list()
        #입력된 그래프 정보에 대한 최소신장트리 구하기.
        kruscal_mst = self.kruscal()
        prim_mst = self.prim()

        #위상정렬 수행하여 노드순서 구하기.
        
        return tsp

test = TSP(7)