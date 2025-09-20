package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Dynamic_11049 {
    static int N;
    static matrix[] matrix_list;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        matrix_list = new matrix[N+1];
        dp = new int[N+1][N+1];
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            matrix_list[i] = new matrix(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                if(i == j) dp[i][j] = 0;
                else dp[i][j] = -1;
            }
        }
        for(int level=1; level<=N; level++){
            for(int i=1; i<=N-level; i++){
                if(dp[i][i+level] != -1) continue;
                int min = Integer.MAX_VALUE;
                for(int k=0; k<level; k++){
                    int a = matrix_list[i].rows *
                            matrix_list[i+k].cols *
                            matrix_list[i+level].cols;
//                    System.out.println(a);
                    min = Math.min(min, dp[i][i+k] + dp[i+k+1][i+level]+a);
//                    System.out.println(min);
                }
                dp[i][i+level] = min;
            }
        }
//        for(int i=1; i<=N; i++){
//            System.out.println(Arrays.toString(dp[i]));
//        }
        System.out.println(dp[1][N]);
    }
    private static class matrix{
        int rows;
        int cols;

        public matrix(int rows, int cols){
            this.rows = rows;
            this.cols = cols;
        }
    }
}
