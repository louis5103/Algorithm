package numberTheory;

import java.util.Scanner;

public class NumberTheory_11689 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long k = n;
        for(int i = 2; i <= Math.sqrt(n); i++) {
            if(n%i==0){
                k -= k/i;
            }
            while(n%i==0){
                n /= i;
            }
        }
        if(n>1)
            k -= k/n;
        System.out.println(k);
    }
}
