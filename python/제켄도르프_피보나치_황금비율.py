def fibo(num):
    cur=1
    last=1
    list_a=[]
    while last<=num:
        temp=last
        last+=cur
        cur=temp
        list_a.append(cur)
    return list_a

In=int(input())
list_a=fibo(In)
if In in list_a:
    Index=list_a.index(In)-1
    print(list_a[Index])
else:
    list_b=[]
    for i in range(len(list_a)-1,-1,-1):
        if list_a[i]<=In:
            In-=list_a[i]
            if i-1<0:
                list_b.append(1)
            else:
                list_b.append(list_a[i-1])
    print(sum(list_b))
print(list_a)


