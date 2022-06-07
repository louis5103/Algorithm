import sys, math

input = sys.stdin.readline
size = int(input())
array = [list(map(int, sys.stdin.readline().strip().split(', '))) for _ in range(size)]


array.sort(key=lambda x: x[0])


def getDist(p1, p2):
    return (p1[0] - p2[0]) ** 2 + (p1[1] - p2[1]) ** 2


def closet_pair(start, end):

    if start == end:
        return float('inf')


    if end - start == 1:
        return getDist(array[start], array[end])


    mid = (start + end) // 2
    minDist = min(closet_pair(start, mid), closet_pair(mid, end))



    middle = [array[i] for i in range(start, end + 1) if (array[mid][0] - array[i][0]) ** 2 < minDist]
    middle.sort(key=lambda x: x[1])


    t = len(middle)
    for i in range(t - 1):
        for j in range(i + 1, t):
            if (middle[i][1] - middle[j][1]) ** 2 < minDist:
                minDist = min(minDist, getDist(middle[i], middle[j]))
            else:
                break

    return minDist


print("{:.6f}".format(math.sqrt(closet_pair(0, size - 1))))


