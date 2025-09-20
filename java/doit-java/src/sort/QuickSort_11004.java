package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class QuickSort_11004 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int num = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[num];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < num; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        quickSort(arr, 0, arr.length-1, k-1);
        System.out.println(arr[k-1]);
    }
    public static void quickSort(int[] arr, int left, int right, int k){
        int start = left;
        int end = right;
        int pivot = arr[(start+end)/2];

        swap(arr, start, (start+end)/2);
        while(left<right){
            while(arr[left]<pivot && left<right){

            }
        }
    }
    public static void partition(int[] arr, int left, int right){

    }
    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
