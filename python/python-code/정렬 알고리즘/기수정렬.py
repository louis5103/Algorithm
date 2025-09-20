class Que:
    def __init__(self):
        self.que = []
    def insert(self, item):
        self.que.append(item)
    def pop(self):
        return self.que.pop(0)
    def peek(self):
        return self.que[0]
    def is_empty(self):
        if self.que:
            return False
        return True

def radixsort(list, r, k):  #NOTE: r진수, 최대 k자리수
    def show_Queue(Queue):
        for queue_idx in range(len(Queue)):
            print("queue_idx in Queue, que: ", queue_idx, Queue[queue_idx].que)

    rotation = Que()
    Queue = [ Que() for _ in range(r)]
    for node in list:
        rotation.insert(node)

    print(rotation.que)
    print("="*40)
    for radix_num in range(1, k+1):
        for _ in range(len(list)):
            node = rotation.pop()
            Queue[(node%(10**radix_num))//(10**(radix_num-1))].insert(node)
            print("자릿수,노드, 해당 자릿수의 숫자: ", radix_num, node, (node%(10**radix_num))//(10**(radix_num-1)))

        print("rotation: ", rotation.que)
        show_Queue(Queue)

        for queue in Queue:
            while not queue.is_empty():
                rotation.insert(queue.pop())
                print(rotation.que)

    print(rotation.que)    
test = [89, 999, 70, 111, 77, 851, 139, 35, 131, 910]
radixsort(test, 10, 3)
