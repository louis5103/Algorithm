package combination;

import java.util.Scanner;

public class Combination_11050 {
    static int N, K;
    static int[][] DP;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        K = in.nextInt();
        DP = new int[N+1][K+1];
        for(int i=0; i<=N; i++) {
            for(int j=0; (j<=i && j<=K); j++) {
                if(i == j || j == 0)
                    DP[i][j] = 1;
                else{
                    DP[i][j] = DP[i-1][j-1] + DP[i-1][j];
                    DP[i][j] %= 10007;
                }
            }
        }
        System.out.println(DP[N][K]);
    }
}
