package sort;

import java.io.*;
import java.util.StringTokenizer;

public class QuickSort {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int num = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] arr = new int[num];

        st = new StringTokenizer(br.readLine());
        for (int i=0; i<arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        quickSort(arr, 0, num-1, k-1);
    }

    public static void quickSort(int[] arr, int left, int right, int k) {
        if(left<right) { // 데이터 1개가 들어올 경우를 확인.
            int middle = partition(arr, left, right);

            if (k < middle) quickSort(arr, left, middle-1, k);
            else if (k > middle) quickSort(arr, middle+1, right, k);
            else System.out.println(arr[middle]);
        }
        else System.out.println(arr[left]); // 데이터가 1개가 되는 경우의 출력.
    }
    public static int partition(int[] arr, int left, int right){
        if(left+1==right && arr[left]>arr[right]){ // 데이터가 2개이면 pivot을 제외하고 1개 데이터가 되기 때문에 요소 2개는 endpoint.
            swap(arr, left, right);
            return right;
        } // 따라서 결국에는 데이터는 일반적인 3개 이상 또는 재귀문으로 인한 1개부터 시작함.

        // 재귀구문 안 데이터 요소가 1개인 경우 ->
        int pl = left+1;
        int pr = right;
        int middle = (pl + pr) / 2;
        int pivot = arr[middle];
        swap(arr, left, middle);

        do { // partition에 들어오는 처음 데이터는 3개 이상이기 때문에 가능.
            while (arr[pl] < pivot) pl++;
            while (arr[pr] > pivot) pr--;

            if (pl <= pr)
                swap(arr, pl++, pr--);
        } while (pl <= pr); // pr이 left 정렬된 pl의 영역에 접근하면 쉽게 swap 가능함.
        swap(arr, left, pr);
        return pr;
    }
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
//7 3
//9 5 7 3 1 8 4
//1 3 4 5 7 8 9