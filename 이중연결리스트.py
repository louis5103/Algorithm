class Node:
    def __init__(self, data=None):
        self.data = data
        self.prev = None
        self.next = None

    @property
    def data(self):
        return self.__data
    @data.setter
    def data(self, data):
        self.__data = data

    @property
    def prev(self):
        return self.__prev
    @prev.setter
    def prev(self, data):
        self.__prev = data

    @property
    def next(self):
        return self.__data
    @next.setter
    def next(self, data):
        self.__next = data

class DoubleLinked():
    def __init__(self):
        self.head = Node()
        self.tail = Node()
        self.d_size = 0
        self.head.next = self.tail
        self.tail.prev = self.head

    def is_empty(self):
        if self.d_size == 0:
            return True
        return False

    def add_first(self, data):
        new_node = Node(data)
        new_node.prev = self.head
        new_node.next = self.head.next
        self.head.next.prev = new_node
        self.head.next = new_node

    def add_last(self, data):
        new_node = Node(data)
        new_node.prev = self.tail.prev
        new_node.next = self.tail
        self.tail.prev.next = new_node
        self.tail.prev = new_node

    def insert_before(self, data, node):
        new_node = Node(data)
        new_node.prev = node.prev
        new_node.next = node
        node.prev.next = new_node
        node.prev = new_node

    def insert_after(self, data, node):
        new_node = Node(data)
        new_node.prev = node
        new_node.next = node.next
        node.next.prev = new_node
        node.next = new_node

    def search_data(self, data):
        cur = self.head.next
        # if DoubleLinked.is_empty():
        #     return None
        while cur is not self.tail:
            if cur.data == data:
                return cur
            cur = cur.next
        return None

    def show_list(self):
        cur = self.head.next
        while cur is not self.tail:
            print(cur.data, end=" ")
            cur = cur.next

    def del_first(self):
        self.head.next = self.head.next.next
        self.head.next.prev = self.head
        self.d_size -= 1

    def del_last(self):
        self.tail.prev = self.tail.prev.prev
        self.tail.prev.next = self.tail
        self.d_size -= 1