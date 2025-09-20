class Node:
    def __init__(self, key, value):
        self.key = key
        self.value = value
        self.visited = False
        self.code = ""
        
        self.left = None
        self.right = None
        self.parent = None

class MinHeap:
    MAX_SIZE = 100
    def __init__(self):
        self.heap = [ None for _ in range(self.MAX_SIZE)]
        self.heap_size = 0

    def left(self, idx):
        return idx << 1

    def right(self, idx):
        return (idx << 1) + 1

    def parent(self, idx):
        return idx >> 1

    def insert(self, item): #item: Node(key, value)
        self.heap_size += 1
        cur_idx = self.heap_size
        while cur_idx > 1 and item.value < self.heap[self.parent(cur_idx)].value:
            self.heap[cur_idx] = self.heap[self.parent(cur_idx)]
            cur_idx = self.parent(cur_idx)
        self.heap[cur_idx] = item    

    def pop(self):
        if self.heap_size == 0:
            raise Exception("This Queue is empty")

        ret_element = self.heap[1]
        item = self.heap[self.heap_size]
        self.heap[self.heap_size] = None
        self.heap_size -= 1

        cur_idx = 1
        child = self.left(cur_idx)
        while child <= self.heap_size:
            if child < self.heap_size and self.heap[child].value > self.heap[self.right(cur_idx)].value:
                child = self.right(cur_idx)

            if item.value < self.heap[child].value:
                break

            self.heap[cur_idx] = self.heap[child]
            cur_idx = child
            child = self.left(cur_idx)
        self.heap[cur_idx] = item
        # print(self.heap[:5])
        # print("="*40)
        # print(ret_element.value)
        return ret_element

class Stack:
    def __init__(self):
        self.arr = []
        
    def is_empty(self):
        if self.arr:
            return False
        return True
    
    def insert(self, item):
        self.arr.append(item)
        
    def pop(self):
        if self.is_empty():
            raise Exception("The queue is empty")
        return self.arr.pop()
    
    def peek(self):
        return self.arr[-1]

class HuffmanCoding:
    def __init__(self, dataset):
        self.queue = MinHeap()
        for node in dataset:
            self.queue.insert(node)

        # for node in self.queue.heap[1:5]:
            # print(node.value, end=' ')
        # print()

    def postorder(self, cur): 
        stack = Stack()
        stack.insert(cur)
        while not stack.is_empty():
            cur = stack.peek()
            if cur.left is not None and cur.left.visited is False:
                stack.insert(cur.left)
                cur.left.code = cur.code + "0"
            else:
                if cur.right is not None and cur.right.visited is False:
                    stack.insert(cur.right)
                    cur.right.code = cur.code + "1"
                else:
                    #TODO: cur 방문시 수행할 동작.
                    if cur.left is None and cur.right is None:
                        print(cur.key, cur.code)
                    cur.visited = True
                    stack.pop()
           
    # def search_code(self, cur):  # 각 문자에 대한 코드를 조합.      
          
    def huffmancoding(self, str):
        while self.queue.heap_size > 1:
            left_item = self.queue.pop()
            right_item = self.queue.pop()
            new_node = Node(None, left_item.value + right_item.value)
            #make child
            new_node.left = left_item
            new_node.right = right_item
            #make parent
            left_item.parent = new_node
            right_item.parent = new_node
            #make code
            left_item.code += "0"
            right_item.code += "1"

            self.queue.insert(new_node)
        print(new_node.value)
        self.postorder(new_node)
        
        str_idx = 0
        print(str)

        while str_idx<len(str):
            cur = new_node
            while cur.left is not None and cur.right is not None:
                if str[str_idx] is '0' and cur.left is not None:
                    cur = cur.left
                else:
                    cur = cur.right
                str_idx += 1
            print(cur.key)
test =  [ Node('T', 90), Node('C', 270), Node('G', 120), Node('A', 450) ]
testing = HuffmanCoding(test)
testing.huffmancoding("10110010001110101010100")
