import math

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
        if self.heapsize != 0:
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

    def pop(self):      #FIXME: heapsize가 1일때 동작하면 안됨
        return_item = self.container[1]
        item = self.container[self.heapsize]
        self.container[self.heapsize] = None

        self.heapsize -= 1

        if self.is_empty():     # pop시 container내에 원소가 하나면 발동. 애초에 container가 empty인 힙에서 발동안함.(그전에 발동)
            return return_item
            
        cur_idx = 1
        child = self.left(cur_idx)
        while child <= self.heapsize:
            if child < self.heapsize and self.container[self.left(cur_idx)][1] > self.container[self.right(cur_idx)][1]:
                child = self.right(cur_idx)
            if item[1] < self.container[child][1]:
                break
            self.container[cur_idx] = self.container[child]
            self.pos[self.container[cur_idx][0]] = cur_idx

            cur_idx = child
            child = self.left(cur_idx)
        self.container[cur_idx] = item
        self.pos[self.container[cur_idx][0]] = cur_idx
        # print("pop result", self.container[:7])
        return return_item

    def decrease_weight(self, to, new_w):    #item이 container 안에 위치한 곳을 찾아서 가중치를 바꾼다.  container[cur] = (to, w)
        item = (to, new_w)
        cur_idx = self.pos[to]
        # print("decrease_weight", self.container[:8])

        while cur_idx != 1 and item[1] < self.container[self.parent(cur_idx)][1]:
            self.container[cur_idx] = self.container[self.parent(cur_idx)]
            self.pos[self.container[cur_idx][0]] = cur_idx
            cur_idx = self.parent(cur_idx)
        self.container[cur_idx] = item
        self.pos[self.container[cur_idx][0]] = cur_idx 
        # print(self.container[:8])
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
        def simple_find(cur):
            while self.parent[cur] > -1:
                cur = self.parent[cur]
            return cur
        def collapsing_find(cur):
            root = simple_find(cur)     
            if cur == root:
                # print("cur==root", cur)
                return cur
            # print("collapsing 전: ", self.parent)

            trail = cur 
            while trail != root:   
                lead = self.parent[trail]
                self.parent[trail] = root
                trail = lead
            # print("collapsing 후: ", self.parent)
            return root
        def union(u, v):      #NOTE: root u, v
            # print("union 전: ", self.parent)
            if self.parent[u] < self.parent[v]:        
                self.parent[u] += self.parent[v]
                self.parent[v] = u
            else:
                self.parent[v] += self.parent[u]
                self.parent[u] = v
            # print("union 후: ", self.parent)

        self.edge.sort(key= lambda w: w[2])
        mst = TSP(self.vertex_num)      #mst의 adj 인접노드 리스트가 필요

        idx = 0
        while len(mst.edge) < self.vertex_num-1 and idx < len(self.edge):
            u, v, w = self.edge[idx]
            # print("test", u,v,w)
            if collapsing_find(u) != collapsing_find(v):
                union(u, v)
                mst.add_edge(u, v, w)
                # print("="*40)
            idx += 1
        print(f'kruscal의 최종 mst의 edge: {mst.adj}')
        return mst
        
    def prim(self, start):
        mst = TSP(self.vertex_num)
        heap = Heap()
        D = [ (None, math.inf) for _ in range(self.vertex_num)]  #각 정점에 대한 D[to] = (_from, w) 초기화. (None, math.inf) 
        S = set()

        for i in range(self.vertex_num):    #heap 초기화. start 노드:(start, 0), 나머지:(i, math.inf) -> 이후 decrease_weight로 변경.
            if i == start:
                heap.insert((i, 0))
                continue
            heap.insert((i, math.inf))
        D[start] = (None, 0)
        
        print(f'D: {D}\nheap: {heap.container[:9]}')
        while len(S) != self.vertex_num:
            _from, _from_w = heap.pop()       #FIXME: ???
            
            if D[_from][1] != _from_w:      # error는 사실 발동하면 안됨.
                print("error")
                continue

            if D[_from][0] is not None:
                mst.add_edge(D[_from][0], _from, _from_w)
            print(_from)
            S.add(_from)
            
            for adj_node in self.adj[_from]:
                to = adj_node[0]
                w = adj_node[1]
                if to not in S and w < D[to][1]:
                    print(f"update: to{{{to}}} and D[to][1]{{{D[to][1]}}} -> w{{{w}}}")
                    heap.decrease_weight(to, w)
                    D[to] = (_from, w)
                    print(f'D: {D}')
                    print(f'heap: {heap.container[:7]}')
                    print("="*40)
        print("prim의 최종 mst의 edge", mst.adj)
        return mst

    def topology_sort(self, mst, start):        
        # print(f'mst의 adj: {mst.adj}\nmst의 edge: {mst.edge}')
        visited = [ False for _ in range(self.vertex_num) ]
        order = []
        stack = Stack()
        stack.push((start, 0))
        while not stack.is_empty():
            node = stack.pop()
            order.append(node[0])
            visited[node[0]] = True
            for adj_cur in mst.adj[node[0]]:
                if not visited[adj_cur[0]]:
                    stack.push(adj_cur)
        order.append(start)
        return order
    
    def traveling_salesman_problem(self):
        #입력된 그래프 정보에 대한 최소신장트리 구하기.
        prim_mst = self.prim(0)
        kruscal_mst = self.kruscal()

        for adj_node in prim_mst.adj:   #adj[u] 내부의 v들 순서 정렬.
            adj_node.sort()
        for adj_node in kruscal_mst.adj:
            adj_node.sort()
            
        #위상정렬 수행하여 노드순서 구하기.
        kruscal_topology = self.topology_sort(kruscal_mst, 1)
        prim_topology = self.topology_sort(prim_mst, 1)
        return kruscal_topology, prim_topology

test = TSP(6)
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
# print(f'adj: {test.adj}\nedge: {test.edge}')
result_kruscal, result_prim = test.traveling_salesman_problem()
print(f"result_prim: {result_prim} \nresult_kruscal: {result_kruscal}")