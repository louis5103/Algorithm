package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Dynamic_2098 {
    static final int INF = 1000000 * 16 + 1;
    static int N;
    static int[][] weight;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        weight = new int[N+1][N+1];
        dp = new int[N+1][1<<N];
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<= N; j++){
                int w = Integer.parseInt(st.nextToken());
                weight[i][j] = w;
            }
        }
        System.out.println(tsp(1, 1));
    }
    private static int tsp(int start, int binary){
        if(binary == (1<<N)-1)
            return weight[start][1] == 0 ? INF : weight[start][1];

        if(dp[start][binary] != 0)
            return dp[start][binary];

        int min = INF;
        for(int next=1; next<=N; next++){
            if(!is_visited(next, binary) && weight[start][next] !=  0){
                min = Math.min(min, tsp(next, binary | 1<<(next-1)) + weight[start][next]);
            }
        }
        dp[start][binary] = min;
        return dp[start][binary];
    }
    private static boolean is_visited(int node, int binary){
        if((binary & 1<<(node-1)) == 0) return false;
        return true;
    }


}
