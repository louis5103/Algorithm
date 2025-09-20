import math

class Queue:
    def __init__(self):
        self.container = []

    def is_empty(self):
        if len(self.container) == 0:
            return True
        return False

    def insert(self, item):
        self.container.append(item)

    def pop(self):
        return self.container.pop(0)

class Node:
    def __init__(self, key):
        self.key = key
        self.left = None
        self.right = None

class BNode:
    def __init__(self):
        self.container = []
        self.parent = None
    
    def show_BNode(self):
        if self.container == None:
            print(None)
        for node in self.container:
            print(node.key, end=' ')
        print()

    def is_leapNode(self):
        if self.container[0].left == None:
            return True
        return False

    def add_key(self, item):
        if len(self.container) == 0:
            self.container.append(item)
            return 0

        if self.container[0].key > item.key:
            self.container[0].left = item.right
            self.container.insert(0, item)
            return 0
        elif self.container[-1].key < item.key:
            self.container[-1].right = item.left

            self.container.append(item)
            # self.container.insert(-1, item)
            return len(self.container) - 1
        else:
            for i in range(len(self.container)):
                if item.key < self.container[i].key:
                    self.container[i-1].right = item.left
                    self.container[i].left = item.right
                    self.container.insert(i, item)
                    return i
                    
class BTree:
    def __init__(self, m):
        self.root = None
        self.m = m
    def insert(self, key):
        item = Node(key)
        if self.root == None:
            new_BNode = BNode()
            new_BNode.add_key(item)
            self.root = new_BNode
            return

        cur_Node = self.root
        bool_loop = True
        while bool_loop:
            # print("전: ", end= ' ')
            # cur_Node.show_BNode()
            for node in cur_Node.container:
                if node.key > key:
                    if node.left == None:
                        bool_loop = False
                        break
                    cur_Node = node.left
                    break
                if cur_Node.container[-1] == node and cur_Node.container[-1].key < key:
                    if node.right == None:
                        bool_loop = False
                        break
                    cur_Node = node.right

        cur_Node.add_key(item)
        if len(cur_Node.container) > self.m-1:
            self.insert_fix(cur_Node)
        # print("후: ", end=' ')
        # cur_Node.show_BNode()

    def insert_fix(self, cur_BNode):
        def slicing():
            new_BNode = BNode()
            i = math.ceil(self.m/2)-1
            new_BNode.container = cur_BNode.container[i+1:]
            del cur_BNode.container[i+1:]

            if not new_BNode.is_leapNode():
                new_BNode.container[0].left.parent = new_BNode
                for node in new_BNode.container:
                    node.right.parent = new_BNode
            return i, new_BNode

        i, new_BNode = slicing()

        item = Node(cur_BNode.container[-1].key)
        item.left = cur_BNode
        item.right = new_BNode
        del cur_BNode.container[-1]

        if cur_BNode == self.root:
            root_BNode = BNode()
            root_BNode.add_key(item)
            cur_BNode.parent = root_BNode
            new_BNode.parent = root_BNode
            self.root = root_BNode
        else:
            cur_BNode.parent.add_key(item)
            new_BNode.parent = cur_BNode.parent

        cur_BNode = cur_BNode.parent
        if cur_BNode and len(cur_BNode.container) > self.m-1:
            self.insert_fix(cur_BNode)
        
    def show_BTree(self):
        que = Queue()
        que.insert(self.root)
        while not que.is_empty():
            item = que.pop()
            for node in item.container:
                # print(f'{item.parent}')
                # print(f'{node.left}, {node.key}, {node.right}')
                print(node.key, end= ' ')
                if node.left != None:
                    que.insert(node.left)
            print()
            if item.container[-1].right != None:
                que.insert(item.container[-1].right)
            print()

    def delete(self, key):
        def delete_search(key):
            cur_BNode = self.root
            if cur_BNode == None:
                raise Exception("This BTree is empty")
            
            while True:     #NOTE: node.key와 key 비교.
                for i, node in enumerate(cur_BNode.container):
                    if node.key == key:
                        return cur_BNode, i, node
                    elif node.key > key:
                        if not cur_BNode.is_leapNode():
                            cur_BNode = node.left
                            break
                    elif node.key < key and cur_BNode.container[-1] == node:
                        if not cur_BNode.is_leapNode():
                            cur_BNode = node.right
                            break
                    else:
                        raise Exception("Error!")
        
        def predeccessor(item):       #NOTE: 리프노드가 아니라는 조건하에 동작?
            cur_BNode = item.left
            while True:
                cur_BNode = cur_BNode.container[-1].right
                if cur_BNode.is_leafNode():
                    return cur_BNode, cur_BNode.container[-1]

        target_BNode, target_idx, target = delete_search(key)
        
        if not cur_BNode.is_leafNode():
            #TODO: predesseccor와 change.a
            pre_BNode, pre_idx, pre_Node = predesseccor(target)
            target.key, pre_Node.key = pre_Node.key, target.key
            target, target_BNode, target_idx = pre_BNode, pre_BNode, pre_idx
        
        if len(target_BNode.container) <= math.ceil(self.m/2) - 1:
            #TODO: if "충당 가능할 때."
            if 
                target_BNode.add_key(Node())
test = BTree(3)
keys = [3, 5, 15, 1, 4, 10, 9]
for key in keys:
    test.insert(key)
# test.show_BTree()
