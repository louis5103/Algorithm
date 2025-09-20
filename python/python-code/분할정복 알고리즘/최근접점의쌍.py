import math

class ClosestPair:
    def __init__(self, arr) -> None:
        self.arr = arr
        self.arr.sort()
        print(self.arr)
        
    def distance(self, dot1, dot2): #NOTE: x 또는 y축 상에 위치한 점에 대해서 거리 비교.
        x = (dot1[0] - dot2[0])**2 if dot1[0] != dot2[0] else 0
        y = (dot1[1] - dot2[1])**2 if dot1[1] != dot2[1] else 0
        return math.sqrt(x+y)
                    
    def closeset_pair(self, left, right):
        if right-left+1 <= 1:
            print("case 1: left and right: ",left, right)
            return 
        
        elif right-left+1 == 2:
            print("="*40)
            print("case 2: left and right: ",left, right)
            print("return min: ", self.distance(self.arr[left], self.arr[right]))
            print("="*40)
            return self.distance(self.arr[left], self.arr[right])
        
        elif right-left+1 == 3:
            tmp = math.inf
            for i in range(left, right+1):
                for j in range(i+1, right+1):                
                    tmp = min(tmp, self.distance(self.arr[i], self.arr[j]))
            print("="*40)
            print("case 3: left and right: ",left, right)
            print("return min: ", tmp)
            print("="*40)
            return tmp
        else:
            print("="*60,"IN")
            print("case 4: left and right: ", left, right)
            mid = (left + right) // 2
            CPL = self.closeset_pair(left, mid)
            CPR = self.closeset_pair(mid+1, right)
            
            d = min(CPL, CPR)
            start = mid
            end = mid + 1
            
            for i in range(mid, left-1, -1):
                if self.arr[i][0] < self.arr[mid][0] - d: 
                    start = i+1    
                    break
                start = i

            for i in range(mid+1, right+1):
                if self.arr[i][0] > self.arr[mid+1][0] + d:
                    end = i-1
                    break
                end = i
            
            min_distance = d
            y_sorted = self.arr[start:end+1]
            y_sorted.sort(key= lambda x: x[1])
            print("arr", self.arr)
            print("y_sorted", y_sorted)
            
            for i in range(len(y_sorted)):
                for j in range(i+1, len(y_sorted)):
                    if y_sorted[j][1] - y_sorted[i][1] > d:
                        break
                    print("y_sorted[i] and y_sorted[j]",y_sorted[i], y_sorted[j] ,"y_sorted_i", self.distance(y_sorted[i], y_sorted[j]))
                    min_distance = min(min_distance, self.distance(y_sorted[i], y_sorted[j]))
            print("="*60,"out")
            return min_distance
            
        
test = [[3,3], [8,3], [4,6], [11,7], [6,6], [5,1], [1,7], [11,1], [10,9]]
testing = ClosestPair(test)
print(testing.closeset_pair(0, len(test)-1))

