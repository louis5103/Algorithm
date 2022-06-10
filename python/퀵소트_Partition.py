import sys

size = int(input())

array = [int(sys.stdin.readline()) for _ in range(size)]


def quick_sort(array, left, right):
    if len(array) <= 1:
        return array
    pivot = array[left]
    low = left + 1
    while True:

    left_side = [x for x in array[1:] if x <= array[0]]
    right_side = [x for x in array[1:] if x > array[0]]  # inplace 방식으로 대체하면 메모리 제한 초과 해결 가능.

    return quick_sort(left_side) + array[0] + quick_sort(right_side)


print(quick_sort(array))




# size = int(input())
# array = [i for i in range(size)]
# def quick_sort(array):
#
#     if len(array) <= 1:
#         return array
#
#
#     pivot = array[0]
#     tail = array[1:]
#
#     left_side = [x for x in tail if x <= pivot]
#     right_side = [x for x in tail if x > pivot]
#
#
#     return quick_sort(left_side) + [pivot] + quick_sort(right_side)
#
# print(quick_sort(array))





# import sys
#
# size = int(input())
#
# array = [int(sys.stdin.readline()) for _ in range(size)]
#
#
# def quick_sort(array):
#     if len(array) <= 1:
#         return array
#
# #    pivot = array[0]
# #    tail = array[1:]
#
#     left_side = [x for x in array[1:] if x <= array[0]]
#     right_side = [x for x in array[1:] if x > array[0]]  # inplace 방식으로 대체하면 메모리 제한 초과 해결 가능.
#
#     return quick_sort(left_side) + array[0] + quick_sort(right_side)
#
#
# print(quick_sort(array))