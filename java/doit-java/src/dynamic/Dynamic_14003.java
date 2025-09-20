package dynamic;

import java.io.*;
import java.util.StringTokenizer;

public class Dynamic_14003 {
    static int N;
    static int[] nums, dp, B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        nums = new int[N+1];
        dp = new int[N+1];
        B = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        dp[1] = 1;
        B[1] = nums[1];
        int end_index = 1;
        for(int i=2; i<=N; i++){
            if(nums[i] > B[end_index]){
                B[++end_index] = nums[i];
                dp[i] = end_index;
            }
            else{
                // nums[i] < B[end_index]
                int target_index = binary_search(end_index, nums[i]);
                B[target_index] = nums[i];
                dp[i] = target_index;
            }
        }
        bw.write(end_index + "\n"); // length
        int[] ans = new int[end_index];
        int count_value = B[end_index];
        int count_index = end_index;
        for(int i=N; i>=1; i--){
            if(count_index == dp[i] && nums[i] <= count_value){
                ans[count_index-1] = nums[i];
                count_value = nums[i];
                count_index--;
            }
        }
        for(int i=0; i<end_index; i++){
            bw.write(ans[i] + " ");
        } bw.flush();
//        System.out.println(Arrays.toString(nums));
//        System.out.println(Arrays.toString(dp));
//        System.out.println(Arrays.toString(B));
//        System.out.println();

        bw.close();

    }
    private static int binary_search(int cur_index, int cur_value){
        // B 배열에서 cur_value와 끝에서 처음으로 크거나 같은 값의 인덱스.
        int left = 1;
        int right = cur_index;

        while(left < right){
            int mid = (left+right) / 2;
            if(B[mid] < cur_value){ // 같으면 오른쪽으로
                left = mid+1;
            }
            else{
                right = mid;
            }
        }
        return left;
    }
}
//8
//10 20 90 80 100 90 30 110