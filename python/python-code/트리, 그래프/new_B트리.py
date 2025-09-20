import math

class Queue:
    def __init__(self):
        self.container = list()
    def push(self, item):
        self.container.append(item)
    def pop(self):
        return self.container.pop(0)
    def is_empty(self):
        if len(self.container) == 0:
            return True
        return False

class BNode:
    def __init__(self) -> None:
        self.key_data = list()
        self.link_data = list()
        self.parent_link = None

    def add_key(self, key, succesion_link=None):                
        #TODO: 
        # 1. if BNode가 비여있다면, -> key 추가하고 link_data에 더미링크 두개 추가.
        if len(self.key_data) == 0:
            self.key_data.append(key)
            self.link_data.append(None)
            self.link_data.append(succesion_link)
            return
        # 2. else BNode에 키가 존재한다면, -> key_data에 insert(i+1, key), link_data에 insert(i+2, key)
        else:
            for i, i_key in enumerate(self.key_data):
                if key < i_key:
                    self.key_data.insert(i, key)
                    self.link_data.insert(i+1, succesion_link)      
                    return
            self.key_data.append(key)
            self.link_data.append(succesion_link)
            return 
        # 3. add key로 인해 밀린 key들의 link_data에서 하위 BNode들의 parent idx += 1 하기. -> i 이후의 인덱스에서. 즉, i+1~마지막.
                #FIXME: -> add_key를 사용하는 쪽에서.
                #FIXME: parent_idx 삭제 결정. 할 필요 없음.
        # 4. 삽입된 key의 idx 반환.
    
class BTree:
    def __init__(self, m) -> None:
        self.m = m
        self.root = None

    def insert(self, key):
        # 최초 insert 시에.
        if self.root == None:
            new_BNode = BNode()
            new_BNode.add_key(key)
            self.root = new_BNode
            return 

        # insert를 위해 리프 BNode에 접근.
        cur_BNode = self.root
        while cur_BNode.link_data[0] != None:       #NOTE: is_leapBNone() 기능.
            break_point = True
            for i, i_key in enumerate(cur_BNode.key_data):
                if key < i_key:
                    cur_BNode = cur_BNode.link_data[i]
                    break_point = False
                    break
            if break_point:
                cur_BNode = cur_BNode.link_data[i+1]
        cur_BNode.add_key(key)
        # print(cur_BNode.key_data, key)
        # print(cur_BNode.link_data)
        if len(cur_BNode.key_data) > self.m-1:
            self.insert_fix(cur_BNode)

    def insert_fix(self, cur_BNode):
        #1. 슬라이싱 기점 i: math.ceil(self.m/2)-1
        i = math.ceil(self.m/2)-1
        new_BNode = BNode()

        new_BNode.key_data = cur_BNode.key_data[i+1:]
        new_BNode.link_data = cur_BNode.link_data[i+1:]
        #1-2. cur_BNode에서 new_BNode로 이동된 Node의 link_data.parent를 new_BNode로 설정하기.
        if new_BNode.link_data[0] != None:
            for node in new_BNode.link_data:
                node.parent = new_BNode

        #2-1. 승계 시 cur_BNode가 root 이면 새로운 BNode를 생성해서 root로 변경.
        if cur_BNode == self.root:
            root_BNode = BNode()
            root_BNode.add_key(cur_BNode.key_data[i], new_BNode)
            root_BNode.link_data[0] = cur_BNode
            self.root = root_BNode

            cur_BNode.parent = root_BNode
            new_BNode.parent = root_BNode

            del cur_BNode.key_data[i:]
            del cur_BNode.link_data[i+1:]
        #2-2. 일반적인 경우의 승계 시.
        else:
            parent_BNode = cur_BNode.parent
            parent_BNode.add_key(cur_BNode.key_data[i], new_BNode)

            new_BNode.parent = parent_BNode        

            del cur_BNode.key_data[i:]
            del cur_BNode.link_data[i+1:]

            if len(cur_BNode.parent.key_data) > self.m-1:
                self.insert_fix(cur_BNode.parent)
                
    def show_BTree(self):
        que = Queue()
        que.push([self.root, 0])
        while not que.is_empty():
            items = que.pop()
            print(items[1],": ", end= ' ')
            for i in range(len(items[0].key_data)):
                print(items[0].key_data[i], end=' ')
                if items[0].link_data[i] != None:
                    que.push([items[0].link_data[i], items[1]+1])
            print()

            if items[0].link_data[i+1] != None:        
                que.push([items[0].link_data[i+1], items[1]+1]) 

    def delete(self, key):
        #TODO: 삭제 의사코드
        def search_delete_key(key):
            # 1. key가 위차한 BNode 주소를 찾는다.
            target_BNode = self.root
            ref_idx = None
            while True:
                break_point = True
                for i, i_key in enumerate(target_BNode.key_data):
                    if i_key == key:
                        return target_BNode, i, ref_idx
                    if key < i_key:
                        target_BNode = target_BNode.link_data[i]
                        ref_idx = i
                        break_point = False
                        break
                if break_point:
                    target_BNode = target_BNode.link_data[i+1]
                    ref_idx = i+1
        def search_ref_idx(cur_BNode):
            parent = cur_BNode.parent
            if parent == None:
                raise Exception("test@@@@@")

            for idx, link in enumerate(parent.link_data):
                if link == cur_BNode:
                    return idx
        def predeccessor(cur_BNode, key_idx, ref_idx):
            target_BNode = cur_BNode.link_data[key_idx]
            ref_idx = key_idx
            while target_BNode.link_data[0] != None:
                ref_idx = len(target_BNode.link_data) - 1
                target_BNode = target_BNode.link_data[-1]
            
            # cur_BNode의 key와 target_BNode의 마지막 key swap()하기.
            cur_BNode.key_data[key_idx], target_BNode.key_data[-1] = target_BNode.key_data[-1], cur_BNode.key_data[key_idx]
            return target_BNode, len(target_BNode.key_data)-1, ref_idx
        def delete_fix(cur_BNode, ref_idx):
            # 1. 조건을 만족하는 우선순위대로 return. 조건을 만족할 때 우선순위: 동생 BNode > 형 BNode > None
            def brother_search(ref_idx, parent_var):
                order = ["LEFT_여유있음", "RIGHT_여유있음", "LEFT_여유없음", "RIGHT_여유없음"]
                brother_BNode = None
                brother_idx = None
                if ref_idx != 0:
                    lower_brother_BNode = parent_var[ref_idx-1]
                    if len(lower_brother_BNode.key_data) > math.ceil(self.m/2)-1:
                        return lower_brother_BNode, order[0]
                    brother_BNode = lower_brother_BNode
                    brother_idx = 2
                # print(ref_idx, len(parent_var)-1)
                if ref_idx != len(parent_var)-1:
                    upper_brother_BNode = parent_var[ref_idx+1]
                    if len(upper_brother_BNode.key_data) > math.ceil(self.m/2)-1:
                        return upper_brother_BNode, order[1]
                    brother_BNode = upper_brother_BNode if brother_BNode == None else brother_BNode
                    brother_idx = 3 if brother_BNode == None else brother_idx
                #TODO: 마지막 return문 도달 시 여유없는 상황인 것은 확정. 여유없는 BNod지의 주소가 누구의 형제 것인지 알아야됨.
                return brother_BNode, order[brother_idx]
            
            #TODO: delete_fix() 의사코드. -> fix가 필요한 경우에만 발동되므로 ~
            # print(cur_BNode.parent.key_data)
            # print(cur_BNode.parent.link_data)
            parent_var = cur_BNode.parent.link_data
            # 1. brother_search()
            brother_BNode, order = brother_search(ref_idx, parent_var)
            
            # print(parent_var)
            # print(key, brother_BNode.key_data)
            # print(key, "order", order)
            #     - return 동생/형 BNode, True/False -> False 일 때 2.1. 수행, True 일 때 2.2 수행.
            # 2.1. return으로 반환된 값이 True이면 합치기 발동 후 종료
            parent_BNode = cur_BNode.parent
            if order=="LEFT_여유없음":
                # 1. 부모 BNode에서 동생 link와 cur link 사이의 해당 key를 cur BNode의 idx가 '0'인 위치에 삽입.
                cur_BNode.key_data.insert(0, parent_BNode.key_data[ref_idx-1])
                # 2. 동생 BNode에서 모든 key들을 cur BNode의 idx가 '0'인 위치에 붙여넣기. link_data도 포함.
                cur_BNode.key_data[0:0] = brother_BNode.key_data
                if brother_BNode.link_data[0] != None:
                    for link in brother_BNode.link_data:
                        link.parent = cur_BNode
                cur_BNode.link_data[0:0] = brother_BNode.link_data 
                # 3. 이동한 부모 key와 brother로 통하는 부모의 link 삭제.
                del parent_BNode.link_data[ref_idx-1]
                del parent_BNode.key_data[ref_idx-1]
                # 4. 동생 BNode 삭제
                del brother_BNode
            elif order == "RIGHT_여유없음":
                cur_BNode.key_data.append(parent_BNode.key_data[ref_idx])
                cur_BNode.key_data.extend(brother_BNode.key_data)
                if brother_BNode.link_data[0] != None:
                    for link in brother_BNode:
                        link.parent = cur_BNode
                cur_BNode.link_data.extend(brother_BNode.link_data)

                del parent_BNode.link_data[ref_idx+1]
                del parent_BNode.key_data[ref_idx]
                del brother_BNode
            # 2.2. False인 경우 형제 BNode에서 key를 이동하여 조건 충족시킨 후 종료.
            elif order == "LEFT_여유있음":
                cur_BNode.add_key(parent_BNode.key_data[ref_idx-1])
                parent_BNode.key_data[ref_idx-1] = brother_BNode.key_data[-1]
                del brother_BNode.key_data[-1]
            elif order == "RIGHT_여유있음":
                cur_BNode.add_key(parent_BNode.key_data[ref_idx])
                parent_BNode.key_data[ref_idx] = brother_BNode.key_data[0]
                del brother_BNode.key_data[0]
                # print("RIGHT_여유있음에서 show_BTree()")
                # self.show_BTree()
            else:
                raise Exception("Critical Error")

            # print("delete_fix 결과 재귀 전: ")
            # self.show_BTree()

            if parent_BNode == self.root and len(self.root.key_data) == 0:
                # del self.root
                self.root = cur_BNode
                cur_BNode.parent = None
                # print(parent_BNode.key_data)
                # print(cur_BNode.key_data)
                return
            cur_BNode = parent_BNode    
            if len(cur_BNode.key_data) < math.ceil(self.m/2)-1 and cur_BNode != self.root:
                ref_idx = search_ref_idx(cur_BNode)
                delete_fix(cur_BNode, ref_idx)
            #TODO: if 업데이트된 cur_BNode가 root라면 추가 동작 필요.
            #TODO: 삭제연산에서 갱신해야될 parent 메서드 변수가 있는가? -> BNode 자체를 옮기는게 아닌 클래스 안 데이터만 옮겨서 아니다?

        cur_BNode, key_idx, ref_idx = search_delete_key(key)
        # print(cur_BNode.key_data, key_idx, ref_idx)
        # 1. 해당 BNode가 리프노드가 아닐 경우 predeccessor() 호출.
        # print("삭제 전: ")
        # self.show_BTree()
        if cur_BNode.link_data[0] != None:
            cur_BNode, key_idx, ref_idx = predeccessor(cur_BNode, key_idx, ref_idx)

        del cur_BNode.key_data[key_idx]
        # print("삭제 후 delete_fix 전: ")
        # self.show_BTree()
        # 2. 삭제 후 해당 BNode의 키 개수 <= self.ceil(self.m/2) - 1 일 경우 delete_fix() 호출.

        if len(cur_BNode.key_data) < math.ceil(self.m/2)-1 and cur_BNode != self.root:
            delete_fix(cur_BNode, ref_idx)
        # print("최종 결과: ")
        # self.show_BTree()
        # 2. 일반적인 경우 삭제 후 종료함.






test = BTree(3)
keys = [5, 1,3,7,15,-19,2]
del_keys = [15, 3, 2] #3
for key in keys:
    test.insert(key)
# test.show_BTree()
# print('-'*40)

for key in del_keys:
    test.delete(key)
    test.show_BTree()
    print('-'*40) 
