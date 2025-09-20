def shellsort(list):
    h_list = [1, 4, 10, 23, 57]
    h_list.sort(reverse=True)
    for h in h_list:
        for i in range(h, len(list)):
            # print(h, i)  
            current_element = list[i]
            j = i

            while j>=h and list[j-h] > current_element:
                list[j] = list[j-h]
                j -= h
            list[j] = current_element
    return list

test = [30,60, 90, 10, 40, 80, 40, 20, 10, 60, 50, 30, 40, 90, 80]
print(shellsort(test))