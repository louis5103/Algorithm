import math

class Queue:
    def __init__(self) -> None:
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
    def __init__(self, m):
        self.container = []
        self.parent = None
        
    def show_BNode(self):
        for item in self.container:
            print(item.key, end=' ')
        print()

    def add_key(self, item):
        if not self.container:
            self.container.append(item)
            return 
          
        if self.container[0].key > item.key:
            self.container[0].left = item.right
            self.container.insert(0, item)
            
        elif self.container[-1].key < item.key:
            self.container[-1].right = item.left
            self.container.append(item)
            
        else:       #NOTE: 중간에 낄 때.
            for i in range(len(self.container)):
                if self.container[i].key < item.key < self.container[i+1].key:
                    self.container[i+1].left = item.right
                    self.container[i].right = item.left
                    self.container.insert(i+1, item)
                    break

class BTree:
    def __init__(self, m):
        self.root = None

    def insert_fix(self, cur_BNode):
        def slicing(b_idx):
            new_BNode = BNode(m)
            new_BNode.container = cur_BNode.container[b_idx+1:]

            cur_BNode.container[b_idx].right = None
            del cur_BNode.container[b_idx+1:]
            return new_BNode

        def succession():
            if cur_BNode.parent == None:     #NOTE: 루트노드일 때.
                new_BNode = BNode(m)
                cur_BNode.container[-1].left = cur_BNode
                cur_BNode.parent = new_BNode
                
                new_BNode.add_key(cur_BNode.container[-1])
                del cur_BNode.container[-1]
                self.root = new_BNode
            else:       #NOTE: 일반 이계의 경우.
                # print(cur_BNode.container[-1].key)
                # print(cur_BNode.parent.container[0].key)
                cur_BNode.container[-1].left = cur_BNode
                cur_BNode.parent.add_key(cur_BNode.container[-1])
                del cur_BNode.container[-1]
                return cur_BNode.parent                
        # cur_BNode.show_BNode()
        new_BNode = slicing(math.ceil(m/2)-1)

        print("test", cur_BNode.show_BNode(), new_BNode.show_BNode())
        print(cur_BNode.parent, new_BNode)
        cur_BNode.container[-1].right = new_BNode
        new_BNode.parent = cur_BNode
        
        succession()
        cur_BNode = cur_BNode.parent
        # cur_BNode.show_BNode()
        if cur_BNode and len(cur_BNode.container) >= m:
            self.insert_fix(cur_BNode)

        # print('='*50)
        # print('='*50)
    def insert(self, key):
        node = Node(key)
        cur_BNode = self.root

        if cur_BNode == None:
            cur_BNode = BNode(m)
            cur_BNode.add_key(node)
            self.root = cur_BNode
            return 
        else:
            while True:
                bool_= False
                for item in cur_BNode.container:
                    # print(item.key)
                    if item.key > key:
                        if item.left is None:
                            cur_BNode.add_key(node)
                            # print(cur_BNode.show_BNode())
                            bool_ = True
                            break
                        cur_BNode = item.left
                        

                    if item == cur_BNode.container[-1] and item.key < key:
                        # print(key, item.right.show_BNode() if item.right != None else None)
                        if item.right is None:
                            cur_BNode.add_key(node)
                            bool_ = True
                            break
                        cur_BNode = item.right
                        
                if bool_ == True:
                    break
                    
        if len(cur_BNode.container) >= m:
            self.insert_fix(cur_BNode)

    def show_tree(self):
        que = Queue()
        if self.root == None:
            print("This Tree is empty.")
            return
        que.insert(self.root)
        while not que.is_empty():        
            item = que.pop()
            print("item code pointer: ", item)
            for key_node in item.container:
                print(key_node.left, key_node.key, key_node.right)
                if key_node.left != None:
                    que.insert(key_node.left)
                if key_node == item.container[-1] and key_node.right != None:
                    que.insert(key_node.right)
            print()
            print('='*40)
m = 3
keys = [1, 15, 2, 5, 30]
test = BTree(m)
for key in keys:
    test.insert(key)
    test.show_tree()
    # test.root.show_BNode()
    
    print(f'-'*100)
