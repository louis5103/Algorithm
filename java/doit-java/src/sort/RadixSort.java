package sort;

import java.io.*;

public class RadixSort {
    private int[] nums;
    private final int N;

    public RadixSort() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        this.N = Integer.parseInt(br.readLine());
        this.nums = new int[N];

        for(int i=0; i<N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }
        br.close();
    }
    public RadixSort(int[] arr){
        this.N = arr.length;
        this.nums = arr;
    }

    public static void main(String[] args) throws IOException {
        RadixSort rs = new RadixSort();
        rs.radixSort(5);
        rs.print();
    }
    public void radixSort(int max_radix){
        int[] output = new int[N];
        int radix = 1;
        int count = 0;

        while(count<max_radix){
            int[] bucket = new int[10];
            for(int i=0; i<N; i++){ // 합배열 초기화를 위한 자릿수 큐 count 증가.
                bucket[(nums[i]/radix)%10]++;
            }
            for(int i=1; i<10; i++){ // 합배열 초기화. 합배열에 담긴 값이 그 실제 배열에 담길 인덱스 위치.
                bucket[i] += bucket[i-1];
            }
            for(int i=N-1; i>=0; i--){
                int hold = (nums[i] / radix) % 10;
                output[bucket[hold]-1] = nums[i];
                bucket[hold]--;
            }
            for(int i=0; i<N; i++){
                nums[i] = output[i];
            }
            radix *= 10;
            count++;
        }
    }
    public void print() throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i=0; i<N; i++){
            bw.write(nums[i] + "\n");
        }
        bw.flush();
        bw.close();
    }
}
