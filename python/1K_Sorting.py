# 버블정렬

list = []
list_num = int(input())

for i in range(list_num):
    list.append(int(input()))

for i in range(list_num):
    for j in range(0, list_num-i-1, 1):
        if list[j+1] < list[j]:    # 방향만 바꾸면 내림차순.
            list[j+1], list[j] = list[j], list[j+1]

# for i in range(list_num):
#     print(list[i])

print(list)