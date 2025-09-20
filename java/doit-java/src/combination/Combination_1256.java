package combination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Combination_1256 {
    static int N, M, K;
    static int[][] D;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        D = new int[N+M+1][M+1];
        D[0][0] = 1;
        for(int i=1; i<=N+M; i++){
            for(int j=0; (j<=i && j<=M); j++){
                if(i == j || j==0)
                    D[i][j] = 1;
                else {
                    D[i][j] = D[i - 1][j - 1] + D[i - 1][j];
                    if(D[i][j] > 1000000000)
                        D[i][j] = 1000000001;
                }
            }
        }
        if(D[N+M][M] < K)
            System.out.println(-1);
        else{
            while(N!=0 || M!=0){
                if(D[N-1+M][M] >= K){
                    System.out.print("a");
                    N--;
                }
                else{
                    System.out.print("z");
                    K -= D[N-1+M][M];
                    M--;
                }
            }
        }
    }
}
