package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Dynamic_13398 {
    static int N;
    static int[] D, L, R;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        D = new int[N];
        R = new int[N];
        L = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            D[i] = Integer.parseInt(st.nextToken());
        }
        result = D[0];
        init_L();
        init_R();

        for(int i=1; i<N-1; i++){
            int temp = L[i-1] + R[i+1];
            result = Math.max(result, temp);   ///
        }
        System.out.println(result);
    }

    private static int end_result() {
        if(D[0] < 0 && D[N-1] <0) {
            if (D[0] > D[N - 1])
                return L[N - 2]; // L 배열에서 0 ~ N-2 인덱스 중 연속된 값의 최대값.
            else
                return R[1];
        }
        else if(D[0] > 0 && D[N-1] < 0)
            return L[N-1];
        else if(D[0] < 0 && D[N-1] > 0)
            return R[1];
        return 0;
    }

    private static void init_L() {
        L[0] = D[0];
        for(int i=1; i<N; i++){
            L[i] = Math.max(D[i], L[i-1] + D[i]);
            result = Math.max(result, L[i]);
        }
    }

    private static void init_R() {
        R[N-1] = D[N-1];
        for(int i=N-2; i>=0; i--){
            R[i] = Math.max(D[i], R[i+1] + D[i]);
        }

    }
}
