class Graph:
    def __init__(self, vertex_num = 0):
        self.adj_list = []
        self.vtx_num = 0
        self.vtx_arr = []

        if vertex_num:      # 초기화
            self.vtx_num = vertex_num
            self.vtx_arr = [True for _ in range(self.vtx_num)]
            self.adj_list = [[] for _ in range(self.vtx_num)]   #

    def is_empty(self):
        if self.vtx_num == 0:
            return True
        return False

    def add_vertex(self):
        for i in range(self.vtx_num):
            if self.vtx_arr[i] == False:
                self.vtx_arr[i] = True
                self.vtx_num += 1
                return i
        self.vtx_num += 1
        self.vtx_arr.append(True)
        self.adj_list.append([])
        return self.vtx_num - 1

    def delete_vertex(self, v):
        if v > self.vtx_num -1:
            raise Exception("존재하지 않는 노드입니다.")
        self.vtx_num -= 1
        self.vtx_arr[v] = False
        self. adj_list[v] = []

        for adj in self.adj_list:
            for vertex in adj:
                if vertex == v:
                    adj.remove(vertex)

    def add_edge(self, u, v):       # 추가할 edge가 이미 존재한다면?
        self.adj_list[u].append(v)
        self.adj_list[v].append(u)

    def delete_edge(self, u, v):        # 삭제할 edge가 존재하지 않는다면?
        self.adj_list[u].remove(v)
        self.adj_list[v].remove(u)
