package numberTheory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Extended_GCD_21568 {
    static int A, B, C;
    static List<Integer> stages;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        A = sc.nextInt();
        B = sc.nextInt();
        C = sc.nextInt();
        stages = new ArrayList<>();

        if(C % gcd(A, B) != 0)
            System.out.println("-1");
        else {
            int K = C / gcd(A, B);
            int[] ret = egcd(A, B);
            System.out.println(K*ret[0] + " " + K*ret[1]);
        }
    }
    private static int gcd(int a, int b) {
        int n=a, m=b;
        while(m != 0){
            int tmp = n%m;
            n=m;
            m = tmp;
        }
        return Math.abs(n);
    }
    private static int[] egcd(int A, int B){
        if(B == 0) return new int[]{1, 0};

        int[] ret = egcd(B, A%B);;
        int quotient = A/B;
        return new int[]{ ret[1], ret[0]-ret[1]*quotient };
    }
}
