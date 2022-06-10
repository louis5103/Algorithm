package sort;

import java.util.Scanner;

public class BubbleSort_1377 {
    public void swap_element(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public void bubbleSort(int[] arr) {
        int i;
        for(i=0; i<arr.length-1; i++){
            boolean changed = false;
            for(int j=0; j<arr.length-i-1; j++){
                if(arr[j]>arr[j+1]){
                    changed = true;
                    swap_element(arr, j, j+1);
                }
            }
            if(!changed){
                System.out.println(i+1);
                break;
            }
        }
    }
    public void main() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];

        for(int i=0; i<n; i++){ arr[i] = sc.nextInt();}
        sc.close();
        bubbleSort(arr);
    }
}
