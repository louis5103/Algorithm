package combination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Combination_13251 {
    static int M, K, T;
    static int[] D;
    static double P;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        M = Integer.parseInt(br.readLine());
        D = new int[M];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            int color = Integer.parseInt(st.nextToken());
            D[i] = color;
            T += color;
        }
        K = Integer.parseInt(br.readLine());
        for(int i=0; i<M; i++) {
            if(D[i] < K) continue;
            double propability = 1.0;

            for(int j=0; j<K; j++) {
                propability *= (double)(D[i]-j)/(T-j);
            }
            P += propability;
        }
        System.out.println(P);
    }
}