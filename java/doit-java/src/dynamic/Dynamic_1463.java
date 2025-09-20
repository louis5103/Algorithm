package dynamic;

import java.util.Scanner;

public class Dynamic_1463 {
    static int N;
    static int[] dp;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        dp = new int[N+1];
        dp[1] = 0;
        for(int i=2; i<=N; i++) {
            dp[i] = dp[i-1] + 1;
            if(i%2 == 0) dp[i] = Math.min(dp[i], dp[i/2]+1);
            if(i%3 == 0) dp[i] = Math.min(dp[i], dp[i/3]+1);
        }
        System.out.println(dp[N]);
    }
}
