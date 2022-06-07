import random

# damage = int(input("최대 공격력을 입력하시오"))
x_list = [0 for i in range(10)]

scale = 70
normal_scale =


x = 0
while(x < 1000000):
    y = random.randint(0, 9)
    print(y)
    x_list[y] += 1
    x = x+1

# x = random.randrange(0, damage+1)
print(x_list)