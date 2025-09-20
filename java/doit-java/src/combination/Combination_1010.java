package combination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Combination_1010 {
    static int T;
    static int[][] D;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        D = new int[30][30];
        for(int i=1; i<30; i++) {
            for(int j=0; j<=i; j++) {
                if(i==j || j==0) D[i][j] = 1;
                else D[i][j] = D[i-1][j] + D[i-1][j-1];
            }
        }
        for(int i=1; i<=T; i++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            System.out.println(D[M][N]);
        }
    }
}
