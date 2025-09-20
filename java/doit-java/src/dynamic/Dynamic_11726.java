package dynamic;

import java.util.Scanner;

public class Dynamic_11726 {
    static int N;
    static long[] D;
    static long mod = 10007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        D = new long[N+1];

        D[1] = 1;
        D[2] = 2;
        for(int i=3; i<=N; i++){
            D[i] = (D[i-1] + D[i-2]) % mod;
        }
        System.out.println(D[N]);
    }
}
