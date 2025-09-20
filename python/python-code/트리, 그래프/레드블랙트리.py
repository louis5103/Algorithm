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
        self.EXT_niL = Node(None)
    
    def search_item(self, item):
        if item == self.root:
            return self.root
        
        cur = self.root
        while cur.key is not None:
            if item.key < cur.key:
                cur = cur.left
            elif item.key > cur.key:
                cur = cur.right
            else:
                if cur.key = None:
                    print(f"The key: {item.key} is not exist")
                else:
                    return cur
        
    def left_rotate(self, l, r):
        n = r.left
        n.parent = l
        l.right = n
        
        if l.parent.left == l:
            l.parent.left = r
            r.parent = l.parent
        else:
            l.parent.right = r
            r.parent = l.parent
        r.left = l
        l.parent = r
        
    def right_rotate(self, l, r):
        n = l.right
        n.parent = r
        r.left = n
        
        if r.parent.left == r:
            r.parent.left = l
            l.parent = r
        else:
            r.parent.right = l
            l.parent = r.parent
        l.right = r
        r.parent = l
        
    def insert(self, item):
        item.left = self.EXT_niL
        item.right = self.EXT_niL
        
        if self.root == None:
            self.root = item
            item.color == "BLACK"

        cur = self.root
        while True:
            if item.key < cur.key:
                if cur.left is None:
                    cur.left = item
                    item.parent = cur
                    break
                cur = cur.left
                
            else:       #NOTE: item.key > cur.key and cur.right is not None:
                if cur.right is None:
                    cur.right = item
                    item.parent = cur
                    break
                cur = cur.right
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
                        self.left_rotate(p, n)
                        n, p = p, n
                    self.right_rotate(p, g)
                    p.color = "BLACK"
                    g.color = "RED"
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
                        self.right_rotate(n, p)
                        n, p = p, n
                    self.left_rotate(g, p)
                    p.color == "BLACK"
                    g.color = "RED"
                else:       #NOTE: u.color =="RED"
                    p.color = u.color = "BLACK"
                    g.color = "RED"
                    #TODO: n,p,g,u 초기화
                    n = g
                    p = n.parent
        self.root.color == "BLACK"
                    
    def delete(self, cur, target):
        #TODO: 이진탐색트리의 삭제연산을 따라간다.
        #TODO: 삭제연산에서 삭제할 데이터 발견시 item의 자식 노드 개수에 따라 삭제색 판별.
            # 1. 삭제색이 RED일 경우 단순실행.
            # 2. 삭제색이 BLACK일 경우 extra-black 추가하여 delete_fix() 실행.
        
            if target.key < cur.key:
                new_left = self.delete(cur.left, target)
                self.make_left(cur, new_left)
            elif target.key > cur.key:
                new_right = self.delete(cur.right, target)
                self.make_right(cur, new_right)
            else:       #NOTE: left, right자식이 둘 다 있을때.
                if cur.left is None and cur.right is None:
                    pass
                elif cur.left is None:
                    "오른쪽 노드에서 가장 작은 노드를 찾아서 change한 후에 삭제"
                elif cur.right is None:
                    "왼쪽 노드에서 가장 작은 노드를 찾아서 change한 후에 삭제"
                else:
                    "오른쪽 노드에서 가장 작은 노드를 찾아서 key만 change한 후에 삭제"
                return self.EXT_niL

        pass