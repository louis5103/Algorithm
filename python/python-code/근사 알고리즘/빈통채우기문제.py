import math 

class Bin_Packing:
    def __init__(self, C, B) -> None:
        self.C = C      #NOTE: 통의 수용가능 무게
        self.OPT = [ ]       #NOTE: 통 리스트
        self.B = B      #NOTE: 물건 리스트. B[i]는 물건 i의 무게. 

    def __init_OPT(self):
        self.OPT = list()

    def optimized_solution(self):
        bin_num = math.ceil(sum(self.B) / self.C)
        return bin_num
         
    def first_fit(self):        #NOTE: 첫 번째 통부터 차례로 살펴보며, 가장 먼저 여유가 있는 토에 새 물건을 넣는다.
        self.__init_OPT()
        if len(self.OPT) == 0 and len(self.B) > 0:
            self.OPT.append([])
            
        for i in range(len(B)):     #NOTE: 모든 i에 대해서 순서대로 통에 넣는거니깐.. 통은 위치가 계속 변할 수 있기 때문에 for 문으로 사용하기에는 힘들거 같음.
            for j in range(len(self.OPT)):
                if self.B[i] <= self.C - sum(self.OPT[j]):
                    # print(self.B[i], self.OPT[j])
                    self.OPT[j].append(self.B[i])
                    break
                
                if j==len(self.OPT)-1:
                    self.OPT.append([self.B[i]])
                    break
            
        return self.OPT

    def next_fit(self):     #NOTE: 직전에 물건을 넣은 통에 여유가 있으면 새 물건을 넣는다.
        self.__init_OPT()
        
        if len(self.OPT) == 0 and len(self.B) > 0:
            self.OPT.append([])
            
        for i in range(len(self.B)):
            #NOTE: if 만약 OPT마지막 통에 들어가면 그쪽에 넣고 아니면 새통 생성해서 넣기.
            if self.B[i] <= self.C - sum(self.OPT[-1]):
                self.OPT[-1].append(self.B[i])
                continue
            else:
                self.OPT.append([self.B[i]])                
        return self.OPT

    def best_fit(self):     #FIXME: 균형이진트리 구현 후 remain_order 사용.
        self.__init_OPT()
        for i in range(len(self.B)):
            min = self.C + 1
            bi = 0
            for j in range(len(self.OPT)):
                if C - sum(self.OPT[j]) >= self.B[i] and C - sum(self.OPT[j]) < min:
                    min = C - sum(self.OPT[j])
                    bi = j
            
            if min == self.C + 1:
                self.OPT.append([self.B[i]])
            else:
                self.OPT[bi].append(self.B[i])  
                # print(i, j)
        return self.OPT

    def worst_fit(self):        #NOTE: 개존의주통 중에서 새 물건이 들어가면 남는 부분이 가장 큰 통에 새 물건을 넣는다. 
        self.__init_OPT()
        self.__init_OPT()
        remain_order = 0      #NOTE: 가장 큰 무게가 남는 통의 idx와 가장 적게 무게가 남는 통의 idx.
                                #NOTE: Heap으로도 구현.(후순위)
        if len(self.OPT) == 0 and len(self.B) > 0:
            self.OPT.append([])
            
        for i in range(len(self.B)):
            remain_order = 0
            for j in range(len(self.OPT)):
                #NOTE: "remain_order 작업 수행."
                if C - sum(self.OPT[j]) > C - sum(self.OPT[remain_order]):
                    remain_order = j
            # print(f'self.OPT: {self.OPT}')
            # print(f'remain_order: {remain_order}\nremain_order_C: {self.C - sum(self.OPT[remain_order])}')
            # print('='*40)
            if self.B[i] <= C - sum(self.OPT[remain_order]):
                self.OPT[remain_order].append(self.B[i])
            else:
                self.OPT.append([self.B[i]])
        return self.OPT
    
C =10
B = [7, 5, 6, 4, 2, 3, 7, 5]
test = Bin_Packing(C, B)
testing = []
testing.append(test.first_fit())
testing.append(test.next_fit())
testing.append(test.best_fit())       #FIXME: best_fit 함수 구현. 
testing.append(test.worst_fit())
testing.append(test.optimized_solution())
print(f'1번째: 최초적합. \t 2번째: 다음적합. \t 3번째: 최선적합. \t 4번째: 최악적합 .')
print('='*40)
for i, tested in enumerate(testing):
    print(f"{i+1}번째 적합: {tested}")
