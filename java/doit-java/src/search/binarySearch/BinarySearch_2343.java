package search.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BinarySearch_2343 {
    static int N, M;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        binarySearch();
    }
    public static void binarySearch(){
        int start = 0, end = 0;
        for(int i=0; i<N; i++) {
            if(start < nums[i]) start = nums[i];
            end += nums[i];
        }

        while(start <= end){
            int middle = (start + end) / 2;
            int sum = 0, count = 0;

            for(int i=0; i<N; i++) {
                if(sum + nums[i] > middle){
                    count++;
                    sum = 0;
                }
                sum += nums[i];
            }
            if(sum!=0) count++;

            if(count <= M){
                end = middle-1;
                continue;
            }
            else {
                start = middle+1;
                continue;
            }
        } System.out.println(start);
    }
}
