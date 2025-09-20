package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Dynamic_2342 {
    static int[][][] D;
    static int[][] mp = {
            {2, 2, 2, 2, 2}, // 0 to 0~4
            {3, 1, 3, 4, 3}, // 1
            {3, 3, 1, 3, 4}, // 2
            {3, 4, 3, 1, 3}, // 3
            {3, 3, 4, 3, 1}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = st.countTokens() - 1;

        D = new int[N+1][5][5];
        for(int i=0; i<=N; i++){
            for(int j=0; j<5; j++){
                Arrays.fill(D[i][j], 5000);
        }

        } D[0][0][0] = 0;
        int k = 1;
        while(true){
            int next_pos = Integer.parseInt(st.nextToken());
            if(next_pos == 0) break;

            for(int i=0; i<5; i++) { // 왼발
                if(next_pos == i) continue;
                for(int j=0; j<5; j++) { // 오른발
                    D[k][i][next_pos] = Math.min(D[k][i][next_pos], D[k-1][i][j] + mp[j][next_pos]);
                }
            }
            for(int j=0; j<5; j++) { // 오른발
                if(next_pos == j) continue;
                for(int i=0; i<5; i++) { // 왼발
                    D[k][next_pos][j] = Math.min(D[k][next_pos][j], D[k-1][i][j]  + mp[i][next_pos]);
                }
            }
            k++;
        }
        k--;
        int min = 5000;
        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                min = Math.min(min, D[N][i][j]);
            }
        } System.out.println(min);

    }
}

