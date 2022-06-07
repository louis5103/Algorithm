import sys
n = int(sys.stdin.readline())
list = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]

def distance(a, b):
    return (a[0]-b[0]) ** 2 + (a[1]-b[1]) ** 2

def closet_pair(start, end):
    if start == end:
        return float('inf')
    else:
        mid = (start + end) // 2

        left = closet_pair(start, mid)
        right = closet_pair(mid+1, end)

        dist = min(left, right)
        temp = []

        for i in range(mid, start-1, -1):
            if dist < (list[mid][0] - list[i][0]) ** 2:
                break
            temp.append(list[i])

        for j in range(mid+1, end+1):
            if (list[j][0] - list[mid][0]) ** 2 > dist:
                break
            temp.append(list[j])

        temp.sort(key=lambda x: x[1])

        for i in range(len(temp)-1):
            for j in range(i+1, len(temp)):
                if (temp[j][1] - temp[i][1]) ** 2 < dist:
                    dist = min(dist, distance(temp[i], temp[j]))
                else:
                    break
        return dist

list.sort(key=lambda x: x[0])

print(closet_pair(0, len(list)-1))
