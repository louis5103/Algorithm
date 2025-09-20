package dynamic;

import java.util.Scanner;

public class Dynamic_2747 {
    static int N;
    static int[] array;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        array = new int[N+1];
        for(int i = 0; i <= N; i++) {
            array[i] = -1;
        }
        array[0] = 0;
        array[1] = 1;
        System.out.println(fibonacci_topDown(N));
        System.out.println(fibonacci_bottopUp());
    }

    private static int fibonacci_topDown(int n) {
        if(array[n] != -1) return array[n];
        else
            return array[n] = fibonacci_topDown(n-1) + fibonacci_topDown(n-2);

    }
    private static int fibonacci_bottopUp() {
        for(int i=2; i<=N; i++) {
            array[i] = array[i-1] + array[i-2];
        }
        return array[N];
    }
}
