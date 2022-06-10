def binary_search(list, target):
    start = 0
    end = len(list) -1

    while start <= end:
        middle = start + end // 2
        if list[middle] == target:
            return middle

        elif list[middle] > target:
            end = middle -1

        else:
            start = middle + 1
    return None

class TreeNode:
    def __init__(self, key):
        self.key = key
        self.left = None
        self.right = None
        self.parent = None

    @property
    def key(self):
        return self._key
    @key.setter
    def key(self, data):
        self._key = data

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

    @property
    def parent(self):
        return self._parent
    @parent.setter
    def parent(self, data):
        self._parent = data

class BST:
    def __init__(self):
        self.root = None

    def get_root(self):
        return self.root

    def preorder_traverse(self, cur, func):
        if not cur:
            return
        func(cur)
        self.preorder_traverse(cur.left, func)
        self.preorder_traverse(cur.right, func)

    def inorder_traverse(self, cur, func):
        if not cur:
            return
        self.preorder_traverse(cur.left, func)
        func(cur)
        self.preorder_traverse(cur.right, func)

    def __make_left(self, cur, target):
        cur.left = target
        if target:
          target.parent = cur

    def __make_right(self, cur, target):
        cur.right = target
        if target:
          target.parent = cur

    def insert(self, key):
        target = TreeNode(key)
        if not self.root:
            self.root = target
            return
        cur = self.root
        while cur:
            parent = cur
            if cur.key > target.key:
                cur = cur.left
                if not cur:
                    self.__make_left(parent, target)

            else:
                cur = cur.right
                if not cur:
                    self.__make_right(parent, target)

    def search(self, key):
        cur = self.root
        while True:
            if cur.key > key:
                cur = cur.left
            elif cur.key < key:
                cur = cur.right
            else:
                if not cur:
                    return None
                return cur

    def max(self, cur):
        # TODO: cur의 하위트리중에서 가장 큰 값의 키를 가지는 노드 반환
        while not cur.right:
            cur = cur.right
        return cur

    def min(self, cur):
        while not cur.left:
            cur = cur.left
        return cur
    def __delete_recursion(self, cur, target):
        while True:
            if not cur:
                return None
            elif cur.key > target:
                new_left = self.__delete_recursion(cur.left, target)
                self.__make_left(cur, new_left)
            elif cur.key < target:
                new_right = self.__delete_recursion(cur.right, target)
                self.__make_right(cur, new_right)
            else:
                if not cur.left and cur.right:
                    cur = None
                elif not cur.right:
                    cur = cur.left
                elif not cur.left:
                    cur = cur.right
                else:
                    replace = cur.left
                    replace = self.max(replace)
                    cur.key, replace.key = replace.key, cur.key
                    new_left = self.__delete_recursion(cur.left, replace.key)
                    self.__make_left(cur, new_left)
            return cur

    def delete(self, target):
        new_root = self.__delete_recursion(self.root, target)
        self.root = new_root

    def prev(self, cur):
        if cur.left:
            return self.max(cur.left)

        parent = cur.parent
        while parent and parent.left == cur:
            cur = parent
            parent = parent.parent

        return parent

    def next(self, cur):
        if cur.right:
            return self.min(cur.right)

        parent = cur.parent
        while parent and parent.right == cur:
            cur = parent
            parent = parent.parent

        return parent