package search.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BinarySearch_1920 {
    static int N, T;
    static int[] nums;
    static int[] target;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        } Arrays.sort(nums);

        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        target = new int[T];

        st  = new StringTokenizer(br.readLine());
        for(int i=0; i<T; i++) {
            target[i] = Integer.parseInt(st.nextToken());

            int start = 0;
            int end = nums.length - 1;
            boolean result = false;

            while(start <= end) {
                int mid = (start + end) / 2;
                if(nums[mid] == target[i]) {
                    result = true;
                    break;
                }
                else if(nums[mid] < target[i]) {
                    start = mid + 1;
                    continue;
                }
                else{
                    end = mid - 1;
                    continue;
                }
            }
            if(result) {
                System.out.println("1");
            } else System.out.println("0");

        }

    }
}
