package numberTheory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class GCD_1850 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        long a = sc.nextLong();
        long b = sc.nextLong();
        for(long i=0; i<gcd(a, b); i++) {
            bw.write("1");
        }
        bw.flush();
        bw.close();
    }

    private static long gcd(long a, long b) {
        long n=a, m=b;
        while(m != 0){
            long tmp = n%m;
            n=m;
            m = tmp;
        }
        return n;
    }
}
