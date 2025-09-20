package dynamic;

import java.util.Scanner;

public class Dynamic_10844 {
    static int N;
    static long[][] D;
    static long mod = 1000000000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        D = new long[N+1][10];

        for(int i=1; i<10; i++) // 0은 시작 수가 아님.
            D[1][i] = 1;
        for(int i=2; i<=N; i++){
            D[i][0] = D[i-1][1];
            D[i][9] = D[i-1][8];
            for(int j=1; j<9; j++){
                D[i][j] = (D[i-1][j-1] + D[i-1][j+1]) % mod;
            }
        }
        long sum=0;
        for(int i=0; i<10; i++) {
            sum += D[N][i];
            sum %= mod;
        }
        System.out.println(sum);
    }
}
