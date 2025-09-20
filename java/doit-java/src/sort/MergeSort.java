package sort;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MergeSort {
    static int[] nums, temp;
    static long acc;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        nums = new int[N+1];
        temp = new int[N+1];
        acc = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        mergeSort(0, N-1);
        System.out.println(acc);
    }
    private static void mergeSort(int S, int E) {
        if(S==E) return;

        int middle = (S+E)/2;
        mergeSort(S, middle);
        mergeSort(middle+1, E);

        for(int i=S; i<=E; i++){
            temp[i] = nums[i];
        }
        int k=S; int i=S; int j=middle+1;
        while(i<=middle && j<=E){
            if(temp[i] <= temp[j]){ // else문을 if문으로 전환할때 사용시의 >의 반대는 <=이다.
                nums[k++] = temp[i++];
            }
            else{
                acc += (j-k);
                nums[k++] = temp[j++];
            }
        }
        while(i<=middle){
            nums[k++] = temp[i++];
        }
        while(j<=E){
            nums[k++] = temp[j++];
        }
    }
}
