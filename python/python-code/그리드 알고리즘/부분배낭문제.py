class Knapsack:
    def __init__(self, arr, C) -> None:
        self.object = arr
        self.C = C

        self.object.sort(key= lambda node: node[2]/node[1], reverse=True)
        print("생성자: ", self.object)
        
    def knapsack(self):
        S = list()
        w = 0
        value = 0
        x_idx = 0
        while w + self.object[x_idx][1] < self.C:
            w += self.object[x_idx][1]
            value += self.object[x_idx][2] 
            
            S.append(self.object[x_idx])
            x_idx += 1
        print(w, value)
        if self.C - w > 0:
            S.append([self.object[x_idx][0], self.C - w, (self.object[x_idx][2] / self.object[x_idx][1]) * (self.C - w)] )
            value += (self.object[x_idx][2] / self.object[x_idx][1]) * ( self.C - w)
            w += self.C - w
        return S, value
    
test = [["주석", 50, 5], ["백금", 10, 60], ["은", 25, 10], ["금", 15, 75]] # [이름, 무게, 가치]#test의 요소: {"이름":[무게, 가치]}
testing = Knapsack(test, 40)
print(testing.knapsack())