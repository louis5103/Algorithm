package sort;

import java.io.*;

public class MergeSort_2751 {
    public static int[] nums;
    public static int[] tmp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        nums = new int[N];
        tmp = new int[N];

        for(int i=0; i<N; i++){
            nums[i] = Integer.parseInt(br.readLine());
        }
        mergeSort(0, N-1);

        for(int i=0; i<N; i++){
            bw.write(nums[i]+"\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }
    public static void mergeSort(int S, int E){
        if(S==E) return;

        int middle = (S+E)/2;
        mergeSort(S, middle);
        mergeSort(middle+1, E);

        // TODO: 데이터 개수가 2개이상일때부터 S~E 정렬.
        int k=S; int i=S; int j=middle+1;
        for(int index=S; index<=E; index++){
            tmp[index] = nums[index];
        }

        while(i<=middle && j<=E){
            if(tmp[i] < tmp[j]){
                nums[k] = tmp[i];
                k++;
                i++;
            }
            else if(tmp[i] > tmp[j]){
                nums[k] = tmp[j];
                k++;
                j++;
            }
        }
        while(i<=middle){
            nums[k] = tmp[i];
            k++;
            i++;
        }
        while(j<=E){
            nums[k] = tmp[j];
            k++;
            j++;
        }
    }
}
