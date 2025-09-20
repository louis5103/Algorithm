def miller_rabin_primality_problem(n, a_list):
    def a_loop(a):
        s = 0     # r += 1
        d = n-1     # d /= 2
        # d가 짝수일 때 loop 조건 만족. -> d의 1의 자릿수가 짝수이면 조건 만족.
        while d%2 == 0:
            d //= 2
            s += 1
        case_1 = True if pow(a, d, n) == 1 else False
        case_2 = False
        for r in range(s):
            if pow(a, d*pow(2, r), n) == n-1:
                case_2 = True
        if case_1 or case_2:
            return True
        return False
        
    
    if n%2 == 0:
        return False
    #소수 판정일때 True
    for a_iter in a_list:
        if n == a_iter:
            continue
        bool_type = a_loop(a_iter)
        # print(n, a_iter, bool_type)
        if not bool_type:       # 소수이면 지나감. 소수가 아니면 return
            return False
    return True
a_int = [2, 7, 61]
a_long_long = [2, 5, 7, 11, 13, 17, 19, 23, 29, 31]
n = [40, 9, 49, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97 ]

for i in n:
    test = miller_rabin_primality_problem(i, a_int)
    print(i, test)