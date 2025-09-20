package combination;

import java.io.IOException;
import java.util.Scanner;

public class Combination_1947 {
    static int N;
    static long[] D;
    static long mod = (long)Math.pow(10, 9);

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        D = new long[N+1];

        D[1] = 0;
        if(N==1) {
            System.out.println(D[1]);

        }
        else{
            D[2] = 1;
            for (int i=3; i <= N; i++) {
                D[i] = (i-1)*(D[i-1] + D[i-2]) % mod;
            }
            System.out.println(D[N]);
        }
    }
}
