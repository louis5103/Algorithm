package numberTheory;

import java.io.*;
import java.util.StringTokenizer;

public class GCD_1934 {
    public static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            System.out.println(a*b/gcd(a, b));
        }
    }
    public static int gcd(int n, int m){
        int a = n, b = m;
        while(a%b != 0){
            int tmp = a%b;
            a = b;
            b = tmp;
        }
        return b;
    }
}
