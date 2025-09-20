package sort;

import java.util.Scanner;

public class SelectionSort_1427 {
    public int[] parseNumber(String str){
        int[] arr = new int[str.length()];
        for(int i = 0; i < arr.length; i++){
            arr[i] = Integer.parseInt(str.substring(i,i+1));
        }
        return arr;
    }
    public void selectionSort(int[] arr){
        for(int i = 0; i < arr.length-1; i++){
            int min = i;
            for(int j = i+1; j<arr.length; j++){
                if(arr[j] < arr[min]){
                    min = j;
                }
            }
            swap_element(arr, i, min);
        }
        for(int i=0; i<arr.length; i++){ System.out.print(arr[i]);}
    }
    public static void swap_element(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j]=temp;
    }

    public void main(){
        Scanner sc = new Scanner(System.in);
        String str = sc.next();

        int[] arr = parseNumber(str);
        this.selectionSort(arr);
        sc.close();
    }
}