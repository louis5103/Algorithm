package search.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DFS_2023 {
    public static int N;
    public static int[] nums;
    public static int[] target = {2, 3, 5, 7};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int cur : target){
            DFS(cur, 1);
        }

    }
    public static void DFS(int num, int radix){
        if (radix==N) {
            if(isPrime(num)) System.out.println(num);
        }
        for(int i=1; i<10; i++){
            if(!(i%2==0) && isPrime(num*10+i)) {
                DFS(num*10+i, radix+1);
            }
        }
    }
    public static boolean isPrime(int num){
        for(int i=2; i<=Math.sqrt(num)+1; i++){
            if(num%i==0) return false;
        }
        return true;
    }
}
