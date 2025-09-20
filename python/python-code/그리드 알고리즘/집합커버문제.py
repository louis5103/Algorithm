class SetCover:
    def __init__(self, vertex_num) -> None:
        self.vertex_num = vertex_num
        self.U = [ i+1 for i in range(self.vertex_num)]   # 아직 탐색하지 않은 노드들을 담는 리스트
        self.C = set()  # 완료 node를 담을 set
        self.F = dict()     # 부분집합 S_i들을 담을 리스트
        
        # U = [0, 1, 2, 3, ..]
        # F = [S1, S2, S3, ..]
        # C = []       U -> C로 노드 이동
        
    def add_subset(self, S):
        S.append(len(S))
        self.F[len(self.F) + 1] = S
        
    def setcover(self):
        nodee = sorted(self.F.items(), key= lambda node: node[1][-1], reverse=True)
        print(nodee)
        
        idx_list = []
        cur_idx = 1

        while self.U:
            subset = nodee[cur_idx-1]
            print(subset)
            idx_list.append(subset[0])

            for i in subset[1]:
                if i in self.U:
                    self.C.add(i)
                    self.U.remove(i)
            
            cur_idx += 1
        #FIXME: 정렬된 F에서의 인덱스 번호는 0에서 마지막 인덱스까지의 순차를 나타낼 뿐. 원래의 subset의 S_i를 표시해야 됨. 즉, S_i의 원래 인덱스와 리스트 반환.
        return idx_list
        
test = SetCover(10)
test.add_subset([1,2,3,8])
test.add_subset([1,2,3,4,8])
test.add_subset([1,2,3,4])
test.add_subset([2,3,4,5,7,8])
test.add_subset([4,5,6,7])
test.add_subset([5,6,7,9,10])
test.add_subset([4,5,6,7])
test.add_subset([1,2,4,8])
test.add_subset([6,9])
test.add_subset([6,10])

print("result: ", test.setcover())
