
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
    def __init__(self, key, value=None) -> None:
        self.key = key
        self.value = value
        self.color = "RED"

        self.left = None
        self.right = None
        self.parent = None
    

class RBTree:
    def __init__(self) -> None:
        self.root = None

    def EXT_niL(self):
        item = Node(None)
        item.color = "BLACK"
        return item
    
    def left_rotate(self, n):
        r = n.right
        l = r.left
        l.parent = n
        n.right = l
        
        if n == self.root:
            self.root = r
        elif n.parent.left == n:
            n.parent.left = r
        else:
            n.parent.right = r
        r.parent = n.parent
        
        r.left = n
        n.parent = r
        
    def right_rotate(self, n):
        l = n.left
        r = l.right
        
        r.parent = n
        n.left = r
        
        if n == self.root:
            self.root = l
        elif n.parent.left == n:
            n.parent.left = l
        else:
            n.parent.right = l
        l.parent = n.parent
        
        l.right = n
        n.parent = l
        
    def insert(self, item):
        item.left = self.EXT_niL()
        item.right = self.EXT_niL()
        
        if self.root == None:
            self.root = item
            self.root.color = "BLACK"
            return

        cur = self.root
        while True:
            if item.key < cur.key:
                if cur.left.key is None:
                    self.make_left(cur, item)
                    break
                cur = cur.left
                
            else:       #NOTE: item.key > cur.key and cur.right is not None:
                if cur.right.key is None:
                    self.make_right(cur, item)
                    break
                cur = cur.right
        if item.parent.color == "RED":        
            self.insert_fix(item)

    def insert_fix(self, cur):
        n = cur
        p = g = u = None

        p = n.parent
        while p is not None and p.color == "RED":
            g = p.parent
            if p == g.left:
                u = g.right
                if u.color == "BLACK" or u.key == None:  #NOTE: -> restruct
                    if p.right == n:
                        self.left_rotate(p)
                        n, p = p, n
                    self.right_rotate(g)
                    p.color = "BLACK"
                    g.color = "RED"
                    return
                else:       #NOTE: ->u.color == "RED"
                    p.color = u.color = "BLACK"
                    g.color = "RED"
                    #TODO: n,p,g,u 초기화
                    n = g
                    p = n.parent    
            else:       #NOTE: -> p == g.right
                u = g.left
                if u.color =="BLACK" or u.key == None:
                    if p.left == n:
                        self.right_rotate(p)
                        n, p = p, n
                    p.color = "BLACK"
                    g.color = "RED"
                    self.left_rotate(g)
                    return
                else:       #NOTE: u.color =="RED"
                    p.color = u.color = "BLACK"
                    g.color = "RED"
                    #TODO: n,p,g,u 초기화
                    n = g
                    p = n.parent
        self.root.color = "BLACK"
                       
    def min(self, cur):
        while cur.left.key is not None: 
            cur = cur.left
        return cur
    def max(self, cur):
        while cur.right.key is not None:
            cur = cur.right
        return cur
    def make_left(self, p, n):
        p.left = n
        n.parent = p
    def make_right(self, p, n):
        p.right = n
        n.parent = p
    

    def bst_delete(self, cur):
        del_color = cur.color
        
        if cur.left.key is not None and cur.right.key is not None:
            min_node = self.min(cur.right)
            cur.key, min_node.key = min_node.key, cur.key
            #FIXME: bst_color = min_node.color로 초기화.
            cur, del_color = self.bst_delete(min_node)    
            
        elif cur.left.key is not None:
            if cur.parent and cur.parent.left == cur:
                self.make_left(cur.parent, cur.left)
            elif cur.parent and cur.parent.right == cur:
                self.make_right(cur.parent, cur.left)
            else:       # -> cur의 자식이 cur.left밖에 없고 cur이 루트노일때.
                self.root = cur
                cur.parent = None
            cur = cur.left
        elif cur.right.key is not None:
            if cur.parent and cur.parent.left == cur:
                self.make_left(cur.parent, cur.right)
            elif cur.parent and cur.parent.right == cur:
                self.make_right(cur.parent, cur.right)
            else:
                self.root = cur.right
                cur.right.parent = None
            cur = cur.right
        else:       # cur이 리프노드일때
            if cur.parent and cur.parent.left == cur:
                item = self.EXT_niL()
                self.make_left(cur.parent, item)
                cur = item
            elif cur.parent and cur.parent.right == cur:
                item = self.EXT_niL()
                self.make_right(cur.parent, item)
                cur = item
            else:       # cur이 리프노드이면서 root일때
                self.root = None
                cur = None
        return cur, del_color
    
    def rbt_delete(self, cur):
        cur, del_color = self.bst_delete(cur)
        # print(f'cur: {cur.key}, {cur.color}')
        # print(f'del_color: {del_color}')
        while True:
            if del_color == "RED" or cur == None:
                return
            elif del_color == "BLACK" and cur.color == "RED":
                cur.color = "BLACK"
                return
            elif del_color == "BLACK" and cur.color == "BLACK":
                bro = cur.parent.left if cur == cur.parent.right else cur.parent.right
                # print(f'bro: {bro.key}, {bro.color}')
                if cur == cur.parent.left:
                    bro = cur.parent.right
                    if bro.color == "BLACK" and bro.right.color == "RED":
                        bro.color = cur.parent.color
                        cur.parent.color = bro.right.color = "BLACK"
                        self.left_rotate(cur.parent)
                        return 
                    elif bro.color == "BLACK" and bro.right.color == "BLACK" and bro.left.color == "RED":
                        bro.color, bro.left.color = bro.left.color, bro.color
                        self.right_rotate(bro)
                        #NOTE: 1번 case 실행.
                        
                    elif bro.color == "BLACK" and bro.left.color == "BLACK" and bro.right.color == "BLACK":
                        bro.color = "RED"
                        cur = cur.parent        #NOTE: 지정대상을 부모로 변경
                        if cur == self.root:
                            cur.color = "BLACK"
                            return
                        else:
                            #NOTE: 루프반복
                            pass
                    elif cur.parent.color == "BLACK" and bro.left.color == "BLACK" and bro.right.color == "BLACK" and bro.color == "RED":
                        cur.parent.color, bro.color = bro.color, cur.parent.color
                        self.left_rotate(cur.parent)
                        #NOTE: 루프반복
                    else:
                        print("what?")
                else:       # cur == cur.parent.right
                    bro = cur.parent.left
                    if bro.color == "BLACK" and bro.left.color =="RED":
                        bro.color = cur.parent.color
                        cur.parent.color = bro.left.color = "BLACK"
                        self.right_rotate(cur.parent)
                        return
                    elif bro.color == "BLACK" and bro.left.color == "BLACK" and bro.right.color == "RED":
                        bro.color, bro.right.color = bro.right.color, bro.color
                        self.left_rotate(bro)
                        #NOTE: 1번 case 실행.
                    elif bro.color == "BLACK" and bro.right.color == "BLACK" and bro.left.color == "BLACK":
                        bro.color = "RED"
                        cur = cur.parent        #NOTE: 지정대상을 부모로 변경
                        if cur == self.root:
                            cur.color = "BLACK"
                            return
                        else:
                            #NOTE: 루프반복
                            pass
                    elif cur.parent.color == "BLACK" and bro.right.color == "BLACK" and bro.left.color == "BLACK" and bro.color == "RED":
                        cur.parent.color, bro.color = bro.color, cur.parent.color
                        self.right_rotate(cur.parent)
                        #NOTE: 루프반복
                    else:
                        print("what??")
    def search(self, key):
        cur = self.root
        if cur is None:
            raise Exception("This Tree is empty")
        
        while True:
            if key < cur.key:
                cur = cur.left
            elif key > cur.key:
                cur = cur.right
            else:
                return cur
        
    def show_tree(self):
        que = Queue()
        que.insert(self.root)
        
        while not que.is_empty():
            item = que.pop()
            print(f'key: {item.key}, color: {item.color}')
            
            if item.key is not None:
                que.insert(item.left)
                que.insert(item.right)
        print('='*40)
        
        
def init_test():
    test.insert(Node(20))
    test.insert(Node(10))
    test.insert(Node(50))
    test.insert(Node(30))


    test.insert(Node(80))
    test.insert(Node(40))
    test.insert(Node(35))
    test.insert(Node(25))
test = RBTree()
index_list = [35, 20, 50, 10, 30, 40, 80, 5, 15, 25, 33, 37, 45, 2, 27]
for i in index_list:
    test.insert(Node(i))
test.show_tree()

test.rbt_delete(test.search(15))
test.rbt_delete(test.search(33))
test.rbt_delete(test.search(37))
test.rbt_delete(test.search(35))
test.rbt_delete(test.search(40))
test.rbt_delete(test.search(50))
test.rbt_delete(test.search(80))
test.rbt_delete(test.search(27))
test.show_tree()
