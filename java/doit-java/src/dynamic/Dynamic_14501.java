package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Dynamic_14501 {
    static int N;
    static int[] T, P, D;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        T = new int[N+1];
        P = new int[N+1];
        D = new int[N+2];
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=N; i>=1; i--) {
            if(N+1 < i+T[i]) D[i] = D[i+1];
            else D[i] = Math.max(D[i+1], D[i + T[i]] + P[i]);
        }
        System.out.println(D[1]);
    }
}
