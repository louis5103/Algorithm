package numberTheory;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class NumberTheory_1929 {
    static int M, N;
    static boolean[] nums;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        M=sc.nextInt();
        N=sc.nextInt();

        nums = new boolean[N+1];
        Arrays.fill(nums, true);
        nums[1]=false;
        for(int i=2; i<=Math.sqrt(N); i++){
            if(nums[i]==false) continue;
            for(int j=i+i; j<=N; j+=i){
                nums[j]=false;
            }
        }
        for(int i=M; i<=N; i++){
            if(nums[i]) System.out.println(i);
        }
    }
}
