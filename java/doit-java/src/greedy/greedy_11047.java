package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class greedy_11047 {
    static int N, Total, count;
    static int[] nums;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Total = Integer.parseInt(st.nextToken());
        nums = new int[N];
        for(int i=0; i<N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }
        greedy();
    }

    private static void greedy() {
        count = 0;
        while(Total > 0){
            for(int i=nums.length-1; i>=0; i--) {
                if(Total >= nums[i]){
                    int coin = Total/nums[i];
                    int remainder = Total%nums[i];
                    count += coin;
                    Total = remainder;
                    break;
                }
            }
        }
        System.out.println(count);
    }
}
