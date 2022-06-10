def setting(data):
    return data[0]


monster_hp = int(input())
hunter_weapon_number = int(input())
cnt = 0
List = []
for i in range(hunter_weapon_number):
    List.append(list(map(int, input().split())))

List = sorted(List, key=setting, reverse=True)
for i in range(hunter_weapon_number):
    for j in reversed(range(List[i][1])):  # 내구도 깍이는거
        if monster_hp <= 0:
            break
        monster_hp -= List[i][0]
        cnt += 1
print(cnt)
