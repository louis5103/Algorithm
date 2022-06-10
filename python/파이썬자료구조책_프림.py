# https://www.fun-coding.org/Chapter20-prim-live.html
# https://deep-learning-study.tistory.com/595
# https://codingcocoon.tistory.com/131

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

    def push(self, item):       # item은 노드 클래스의 인스턴스
        # 1: 힙 사이즈 +1
        # 2 : 마지막 힙 사이즈에 item.w 추가
        # 3 : 최고 루트 노드와 swap
        # 4 : 무너진 최소 힙 정상화하는 과정
        self.heap_size += 1
        cur_idx = self.heap_size

        while cur_idx != 1 and item.w < self.container[cur_idx]:
            self.container[cur_idx] = self.container[self.parent(cur_idx)]
            self.pos[self.container[cur_idx].v] = cur_idx       # v로 가는 최소의 가중치를 가진 인덱스를 저장하는 pos 리스트

            cur_idx = self.parent(cur_idx)

        self.container[cur_idx] = item
        self.pos[item.v] = cur_idx

    def pop(self):
        realse_pop = self.container[1]

        temp = self.container[self.heap_size]
        self.heap_size -= 1

        cur_idx = 1
        child = self.left(cur_idx)

        while child <= self.heap_size and self.container[child].w < self.container[cur_idx].w:
            if self.right(cur_idx) <= self.heap_size and self.container[child].w > self.container[self.right(cur_idx)].w:
                child = self.right(cur_idx)

            if temp.w < self.container[child].w:
                break

            self.container[cur_idx] = self.container[child]
            self.pos[self.container[cur_idx].v] = cur_idx

            cur_idx = child
            child = self.left(cur_idx)

        self.container[cur_idx] = temp
        self.pos[temp.v] = cur_idx

        return realse_pop

    def decrease_weight(self, new_update):
        pass


class Prim_MST:
    def __init__(self):
        pass

