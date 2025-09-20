package sort;

import java.util.Scanner;

public class InsertionSort_11399 {
    public void main(){
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int[] arr = new int[num];
        for (int i = 0; i < num; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(insertionSort(arr));
    }
    public int insertionSort(int[] arr){
        for(int i=0; i<arr.length-1; i++){
            int target = i+1;
            for(int j=0; j<i; j++){
                if(arr[i+1]<arr[j])
                    target = j;
            }
            insert(arr, target, i+1);
        }

        return sum(arr);
    }
    public void insert(int[] arr, int i, int j){
        int temp = arr[j];
        for(int k=j-1; k>=i; k--){
            arr[k+1] = arr[k];
        }
        arr[i] = temp;
    }
    public int sum(int[] arr){
        int total = 0;
        for(int i=0; i<arr.length; i++){
            int sum = 0;
            for(int j=0; j<=i; j++){
                sum += arr[j];
            }
            total += sum;
        }
        return total;
    }
}
