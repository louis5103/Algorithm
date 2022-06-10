class Heap:
    HEAPSIZE = 100
    def __init__(self):
        self.container = [None for _ in range(self.HEAPSIZE)]
        self.heap_size = 0

    def is_empty(self):
        if self.container:
            return True
        return False

    def parent(self, n):
        return n >> 1
    def left(self, n):
        return n << 1
    def right(self, n):
        return (n << 1) + 1

    def push(self, value):
        self.heap_size += 1
        cur_idx = self.parent(self.heap_size)

        while cur_idx != 1 and value > self.container[cur_idx]:
            self.container[cur_idx] = self.container[self.parent(cur_idx)]
            cur_idx = self.parent(cur_idx)

        self.container[cur_idx] = value

    def pop(self):
        ret = self.container[1]

        cur_idx = 1
        temp = self.container[self.heap_size]
        self.container[self.heap_size] = None
        self.heap_size -= 1

        child = self.left(cur_idx)
        while child <= self.heap_size:
            var = child < self.heap_size and self.container[self.left(cur_idx)] < self.container[self.right(cur_idx)]
            if var:
                child = self.right(cur_idx)

            if self.container[cur_idx] > self.container[child]:
                break

            self.container[cur_idx] = self.container[child]
            cur_idx = child
            child = self.left(cur_idx)

        self.container[cur_idx] = temp
        return ret

