import math

class MinHeap:
    MAX_SIZE = 1000
    def __init__(self):
        self.heap_size = 0
        self.heap = [ None for _ in range(self.MAX_SIZE)] 

    def left(self, idx):
        return idx << 1
    def right(self, idx):
        return (idx << 1) + 1
    def parent(self, idx):
        return idx >> 1

    def is_empty(self):
        if self.heap_size == 0:
            return True
        return False
    def is_full(self):
        if self.heap:
            return True
        return False

    def insert(self, item):
        self.heap_size += 1
        self.heap[self.heap_size] = item

        cur_idx = self.heap_size
        while cur_idx > 1 and item.bound_value < self.heap[self.parent(cur_idx)].bound_value:
            self.heap[cur_idx] = self.heap[self.parent(cur_idx)]
            cur_idx = self.parent(cur_idx)
        self.heap[cur_idx] = item

    def pop(self):
        ret_element = self.heap[1]
        item = self.heap[self.heap_size]
        self.heap[self.heap_size] = None
        self.heap_size -= 1

        cur_idx = 1
        child = self.left(cur_idx)
        while child <= self.heap_size:
            if child < self.heap_size and self.heap[child].bound_value > self.heap[child+1].bound_value:
                child = self.right(cur_idx)

            if item.bound_value < self.heap[child].bound_value:
                break

            self.heap[cur_idx] = self.heap[child] 
            cur_idx = child
            child = self.left(cur_idx)
        self.heap[cur_idx] = item
        return ret_element

class Graph:            
    #TODO: 노드 개수로 초기화.
    ORIGINAL = None
    def __init__(self, vertex_num) -> None: 
        self.vertex_num = vertex_num
        self.adj = [ [] for _ in range(self.vertex_num)]
        self.edge = [ [ None for _ in range(self.vertex_num) ] for _ in range(self.vertex_num)]

    def add_edge(self, u, v, w):
        self.adj[u].append(v)
        self.adj[v].append(u)
        self.edge[u][v] = w
        self.edge[v][u] = w

class Branch_Node:
    VERTEX_NUM = 5
    #activeNodes 정보를 다룸.
    #TODO: vertex_num 정보
    def __init__(self, start, node_tuple, parent=None) -> None:
        #TODO: 부모의 그래프 정보 + 추가된 노드와의 정보
        self.start = start
        #TODO: 부모 그래프의 node_tuple 순서 + 추가된 노드
        self.node_tuple = node_tuple
        self.visited = [ False for _ in range(Branch_Node.VERTEX_NUM)]
        self.bound_edge = [ [] for _ in range(Branch_Node.VERTEX_NUM)]
        self.bound_value = None
        self.init_node_value()

        self.parent = parent
        #TODO: 생성 시 visited를 토대로 다음 child노드 넣을 수 있음.
        self.child = list()

    def init_node_value(self):
    #TODO: node_tuple 정보로 visited, bound_edge 초기화.
        next = self.start
        # print(next)

        while self.node_tuple[next] != self.start:
            self.visited[next] = True
            self.visited[self.node_tuple[next]] = True
            self.bound_edge[next].append(self.node_tuple[next])
            self.bound_edge[self.node_tuple[next]].append(next) 
            
            next = self.node_tuple[next]

        self.bound_value = self.__cal_bound_value()
    
    def __cal_bound_value(self):
        for i in range(Branch_Node.VERTEX_NUM):
            while len(self.bound_edge[i]) < 2:
                min = (None, math.inf)
                for next_node in Graph.ORIGINAL.adj[i]:
                    if next_node not in self.bound_edge[i] and Graph.ORIGINAL.edge[i][next_node] < min[1]:
                        min = (next_node, Graph.ORIGINAL.edge[i][next_node])
                if min[0] == None:
                    raise Exception("None Type error in min[0]")

                self.bound_edge[i].append(min[0])
            # print("{}의 bound_edge 정보: ".format(i), self.bound_edge[i])
        # print(self.bound_edge)
        total_value = 0
        for i in range(Branch_and_Bound.VERTEX_NUM):
            for j in self.bound_edge[i]:
                total_value += Graph.ORIGINAL.edge[i][j]
        return math.ceil(total_value/2)

class Branch_and_Bound:
    VERTEX_NUM = 5
    def __init__(self):
        #NOTE: 한정값을 기준으로하는 최소힙. activeNodes를 담음.
        self.container = MinHeap()     #NOTE: 최소힙으로 구현.
        self.bestValue = math.inf
        self.bestSolution = None   

        self.test_container = []
    def branch_and_bound(self, start):
        # def __init__(self, graph, start, node_tuple, parent=None) -> None:
        no_tu = [ None if i != start else start for i in range (Branch_and_Bound.VERTEX_NUM) ]
        print("first_init no_tu: ", no_tu)
        first_Node = Branch_Node(start, no_tu)
        self.container.insert(first_Node)

        #TODO: 자식 노드 생성 및 담기.
        #FIXME: ???
        # Branch_Node( first_Node.graph, tuple(map(lambda x: x, first_Node.node_tuple)), first_Node.visited)

        while not self.container.is_empty():
            # 1. 최소힙 pop()
            target = self.container.pop()
            print(f"pop한 Branch-Node: {target.node_tuple}")
            # print(target.node_tuple, target.bound_value)
            #     1.1 pop한 노드가 완전한 해일 경우 bestValue 비교연산
            total_value = 0
            if False not in target.visited:
                next = start
                while target.node_tuple[next] != start:
                    print(f"완전한 해 발견: {target.node_tuple}, next: {next}")
                    total_value += Graph.ORIGINAL.edge[next][target.node_tuple[next]]
                    next = target.node_tuple[next]
                total_value += Graph.ORIGINAL.edge[next][start]
                if total_value < self.bestValue:
                    print(f"갱신된 best 노드: {target.node_tuple}, 한계값: {total_value}")
                    self.bestSolution = target.node_tuple
                    self.bestValue = total_value
                    self.test_container.append([target, total_value])

            #     1.2 pop한 노드가 완전한 해는 아니지만 bestValue보다 클 경우 가지치기
            elif total_value > self.bestValue:
                print(f"가지치기: {target.node_tuple}")
                pass

            # NOTE: 경우가 없는 해인지는 Branch_Node에서 해결, 다음 분기노드는 visited로 확인.
            # 2. pop한 노드의 자식 생성 및 최소힙에 push()
            else:
                # 2.1 target.visited가 False인 노드 자식으로 생성 및 push.
                for i in range(Branch_Node.VERTEX_NUM):
                    child_tuple = target.node_tuple[:]
                    last = child_tuple.index(start)
                    if target.visited[i] == False and Graph.ORIGINAL.edge[last][i]:
                        child_tuple[last] = i
                        child_tuple[i] = start
                        
                        child_node = Branch_Node(start, child_tuple, target)  #FIXME: 매개변수 추가 필요, parent 설정은 매개변수에서 됨.
                        target.child.append(child_node)
                        print(f"insert된 Branch-Node: {child_node.node_tuple}")
                        self.container.insert(child_node)      

if __name__ == "__main__":
    start = 0
    original_graph = Graph(5)
    w = [[None,2,7,3,10], [2,None,3,5,4],[7,3,None,6,1],[3,5,6,None,9],[10,4,1,9,None]]
    for i in range(4):
        for j in range(i+1, 5):
            original_graph.add_edge(i,j,w[i][j])
    Graph.ORIGINAL = original_graph
    # print(Graph.ORIGINAL.edge)
    # print(Graph.ORIGINAL.adj)
    

    test = Branch_and_Bound()
    test.branch_and_bound(start)

    print("완료된 test_container에서의 결과 출력")
    for solution, value in test.test_container:
        print(f"solution_tuple: {solution.node_tuple}")
        print(f"solution_value: {value}")

        # 0 - 1 - 4 - 2 - 3 - 0
        14302
        # 0 - 1 - 4 - 3 - 2 - 0

        # 0 - 3 - 1 - 2 - 4 - 0
print(test.bestValue)