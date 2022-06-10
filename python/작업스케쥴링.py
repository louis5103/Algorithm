import sys

task_num = int(sys.stdin.readline())
schedule_time = [list(map(int, sys.stdin.readline().split())) for _ in range(task_num)]
schedule_time.sort(key= lambda x: x[0])


# machine1 은 시간 제한도 넘기고 메모리 제한도 넘긴다.
# machine = []
# for i in range(task_num):
#     if len(machine) == 0:
#         machine.append(i)
#     else:
#         for j in range(len(machine)):
#             if schedule_time[machine[j]][1] < schedule_time[i][0]:
#                 machine[j] = i
#                 break
#         else:
#             machine.append(i)
#
# print(len(machine))

# machine2는 메모리 제한을 넘기지 않지만 시간 제한을 넘긴다.  성능 : machine2 > machine1
machine2 = []
for i in range(task_num):
    if len(machine2) == 0:
        machine2.append([schedule_time[i]])
    else:
        for j in range(len(machine2)):
            if machine2[j][-1][1] < schedule_time[i][0]:    # 여기에서 오류걸리는 지 의심해보자.
                machine2[j].append(schedule_time[i])
                break
        else:
            machine2.append([schedule_time[i]])



for num, machine in enumerate(machine2):
    print(num, " : ",machine)
print()
print("machine2", machine2)
print(len(machine2))






# import sys
#
# task_num = int(sys.stdin.readline())
# schedule_time = [list(map(int, sys.stdin.readline().split())) for _ in range(task_num)]
# schedule_time.sort(key= lambda x: x[0])
#
# print(schedule_time)
# machine = []
# for i in range(task_num):
#     boolin = True
#     if len(machine) == 0:
#         machine.append(schedule_time[i])   # i
#     else:
#         # 모든 머신이  > schedule_time[i][0] 이면 머신 추가
#         # 아니면 그 머신에 i 값 저장
#         for j in range(len(machine)):
#             if machine[j][1] < schedule_time[i][0]:   # if schedule_time[machine[j]][1] < schedule_time[i][0]:
#                 machine[j] = schedule_time[i]   # i
#                 boolin = False
#                 break
#         if boolin:
#             machine.append(schedule_time[i])  # i
#
#
# print(len(machine))






