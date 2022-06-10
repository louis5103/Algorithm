import math
import sys


def sqrt_function(array_a, array_b):
    return math.sqrt((array_a[0] - array_b[0])**2 + (array_a[1] - array_b[1])**2)


def closet_pair(array, start, end):
    size = end - start + 1
    mid = start + size // 2

    if size == 2:
        return sqrt_function(array[start], array[end])
    elif size == 3:
        return min(sqrt_function(array[start], array[end]), sqrt_function(array[start], array[mid]), sqrt_function(array[mid], array[end]))
    else:
        left_closet = closet_pair(array, start, mid)
        right_closet = closet_pair(array, mid + 1, end)

        dist = min(left_closet, right_closet)
        tmp = []
        for i in range(size):
            if math.sqrt((array[mid][0] - array[i][0])**2) <= dist:
                tmp.append(array)

        if len(tmp) >= 2:
            tmp.sort(key=lambda x: x[1])
            for i in range(len(tmp)-1):
                for j in range(i+1, len(tmp)):
                    if array[i][1] - array[j][1] >= dist:
                        break
                    dist = min(dist, sqrt_function(tmp[i], tmp[j]))
        return dist


size = int(sys.stdin.readline())
array = [list(map(int, sys.stdin.readline().split())) for _ in range(size)]


print(array)
array.sort(key=lambda x: x[0])
print(closet_pair(array, 0, size-1))