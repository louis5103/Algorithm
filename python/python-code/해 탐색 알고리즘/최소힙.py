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
        while cur_idx > 1 and item[1] < self.heap[self.parent(cur_idx)][1]:
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
        self.pos[item[0]] = cur_idx
        return ret_element

    def decrease_weight(self, item):    # item의 가중치 w를 감소시킴. w가 무조건 기존 w보다 작아야됨.
        cur_idx = self.pos[item[0]]
        print("item: ", item, "pos: ", cur_idx)
        while cur_idx > 1 and item[1] < self.heap[self.parent(cur_idx)][1]:
            self.heap[cur_idx] = self.heap[self.parent(cur_idx)]
            self.pos[self.heap[cur_idx][0]] = cur_idx
            print("전", cur_idx)
            cur_idx = self.parent(cur_idx) 
            print("후", cur_idx)
        self.heap[cur_idx] = item
        self.pos[self.heap[cur_idx][0]] = cur_idx
        print(self.heap[0:7])
        print(cur_idx)
