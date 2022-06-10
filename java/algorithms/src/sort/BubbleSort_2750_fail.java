package sort;

import java.util.Arrays;
import java.util.Scanner;

public class BubbleSort_2750_fail {
    public static void swap_element(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    public static void bubbleSort(int[] arr) {
        for(int i=0; i<arr.length-1; i++){
            for(int j=0; j<arr.length-1-i; j++){
                if(arr[j] > arr[j+1]) swap_element(arr, j, j+1);
            }
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int[] arr = new int[num];

        for(int i=0; i<num; i++){ arr[i] = sc.nextInt(); }
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
