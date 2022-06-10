class TreeNode:
    def __init__(self, key):
        self.key = key
        self.left = None
        self.right = None
        self.parent = None
        self.color = "RED"

class RBTree:
    def __init__(self):
        self.root = None

    def make_left(self, cur, target):
        cur.left = target
        if target:
            target.parent = cur

    def make_right(self, cur, target):
        cur.right = target
        if target:
            target.parent = cur

    def __insert(self, key):
        new_node = TreeNode(key)
        cur = self.root

        if not cur:
            self.root = new_node
            self.root.color = "BLACK"

        while cur:
            parent = cur
            if cur.key < new_node.key:
                cur = cur.right
                if not cur:
                    self.make_right(parent, new_node)
                    return new_node
            else:
                cur = cur.left
                if not cur:
                    self.make_left(parent, new_node)
                    return new_node

    def __double_RED(self, n):
        node = n
        parent = node.parent
        while node.color == parent.color == "RED":
            pass
        pass

    def __left_Rotate(self, n):
        r = n.right
        l = r.left

        n.right = l
        l.parent = n

        if self.root == n:
            self.root = r

        elif n.parent.left == n:
            n.parent.left = r
        else:
            n.parent.right = r

        r.parent = n.parent

        r.left = n
        n.parent = r

    def __right_Rotate(self, n):
        l = n.left
        r = l.right

        n.left = r
        r.parent = n

        if self.root == n:
            self.root = l
        elif n.parent.left == n:
            n.parent.left = r
        else:
            n.parent.right = r

        l.parent = n.parent

        l.right = n
        n.parent = l

    def insert(self, key):
        new_node = TreeNode(key)
        cur = self.root

        if not cur:
            self.root = new_node
            self.root.color = "BLACK"

        while cur:
            parent = cur
            if cur.key > key:
                cur = cur.left
                if not cur:
                    self.make_left(parent, new_node)

            else:
                cur = cur.right
                if not cur:
                    self.make_right(parent, new_node)

        self.DoubleRed(new_node)

    def DoubleRed(self, n):
        pn, gn, un = None
        pn = n.parent

        while pn is not None and pn != "RED":
            gn = pn.parent
            if gn.left == pn:
                un = gn.right
                if un.color == "RED":   # todo : Recoloring
                    gn.color = "RED"
                    pn.color = un.color = "BLACK"

                    n = gn
                    pn = n.parent
                else:
                    # todo : Restructuring
                    if n == pn.right:
                        self.__left_Rotate(pn)
                        pn, n = n, pn

                    pn.color, gn.color = gn.color, pn.color
                    self.__right_Rotate(gn)
            else:
                un = gn.left
