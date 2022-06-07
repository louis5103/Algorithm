class TreeNode:
    def __init__(self, key=None):
        self.key = key
        self.color = True  # True면 RED
        self.left = None
        self.right = None
        self.parent = None


class RBTree:
    def __init__(self):
        self.root = None

    def make_left(self, cur, left):
        cur.left = left
        if left:
            left.parent = cur

    def make_right(self, cur, right):
        cur.right = right
        if right:
            right.parent = cur

    def insert(self, key):
        new_node = TreeNode(key)
        cur = self.root
        if not cur:
            self.root = new_node

        # 기존 삽입까지의 효과
        while True:
            parent = cur
            if cur.key > new_node.key:
                cur = cur.left
                if not cur:
                    self.make_left(parent, new_node)
                    break
            else:
                cur = cur.right
                if not cur:
                    self.make_right(parent, new_node)
                    break

        if new_node.parent and new_node:
            self.double_red(new_node, new_node.parent)

    def opposite_node(self, cur):
        if cur is self.root:
            return
        parent = cur.parent

        if parent.left == cur:
            return parent.right
        else:
            return parent.left
    def node_sort(self, one, two, three):
        pass
    def double_red(self, n, p):
        node = n
        parent = p
        uncle = self.opposite_node(p)
        grand = parent.parent

        if not uncle:
            # TODO: 3개 노드 재배열 후에 return 처리
            self.node_sort(node, parent, grand)
        if uncle.color:
            # TODO: Recoloring


            zite = grand.parent
            if zite.color and grand.color:
                self.double_red(grand, zite)
        else:
            # TODO: Restructuring