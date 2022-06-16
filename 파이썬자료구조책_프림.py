class Node:
    def __init__(self, u, v, w):
        self.u = u
        self.v = v
        self.w = w


class Priority_Queue:
    MAX_HEAP = 100

    def __init__(self):
        self.container = [None for _ in range(Priority_Queue.MAX_HEAP)]  # weight값 담는 리스트
        self.pos = [None for _ in range(Priority_Queue.MAX_HEAP)]  # index값 담는 리스트
        self.heap_size = 0

    def parent(self, node):
        return node >> 1

    def left(self, node):
        return node << 1

    def right(self, node):
        return (node << 1) + 1

    def push(self, item):
        # 1: 힙 사이즈 +1
        # 2 : 마지막 힙 사이즈에 item.w 추가
        # 3 : 최고 루트 노드와 swap
        # 4 : 무너진 최소 힙 정상화하는 과정
        self.heap_size += 1
        cur_idx = self.heap_size

        while cur_idx != 1 and item.w < self.container[cur_idx]:
            self.container[cur_idx] = self.container[self.parent(cur_idx)]
            pass
        pass

    def pop(self):
        pass

    def decrease_weight(self, new_update):
        pass


class Prim_MST:
    def __init__(self):
        pass

