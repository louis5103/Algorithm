class Stack():
    def __init__(self):
        self.data = list()

    def push(self, data):
        self.data.append(data)

    def pop(self):
        if self.is_empty():
            return None
        return self.data.pop()

    def is_empty(self):
        if self.data:
            return False
        return True

    def peek(self):
        if self.empty():
            return None
        return self.data[-1]

class TreeNode:
    def __init__(self, data=None):
        self.data = data
        self.left = None
        self.right = None

    @property
    def data(self):
        return self._data

    @data.setter
    def data(self, data):
        self._data = data

    @property
    def left(self):
        return self._left

    @left.setter
    def left(self, data):
        self._left = data

    @property
    def right(self):
        return self._right

    @right.setter
    def right(self, data):
        self._right = data

def preorder(cur):
    if not cur:
        return

    print(cur.data, end=' ')
    preorder(cur.left)
    preorder(cur.right)

def inorder(cur):
    if not cur:
        return

    inorder(cur.left)
    print(cur.data, end=' ')
    inorder(cur.right)

def postorder(cur):
    if not cur:
        return

    postorder(cur.lefft)
    postorder(cur.right)
    print(cur.data, end=' ')

def iter_preorder(cur):
    s = Stack()
    while True:
        while cur:
            print(cur.data, end=' ')
            s.push(cur)
            cur = cur.left
        cur = s.pop()
        if not cur:
            break

        cur = cur.right


def iter_inorder(cur):
    s = Stack()

    while True:
        while cur:
            s.push(cur)
            cur = cur.left

        cur = s.pop()
        if not cur:
            break
        print(cur.data, end=' ')
        cur = cur.right

def iter_postorder(cur):
    pass

