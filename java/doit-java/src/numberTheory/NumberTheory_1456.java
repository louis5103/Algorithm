package numberTheory;

import java.util.Arrays;
import java.util.Scanner;

public class NumberTheory_1456 {
    static long N, M;
    static int count;
    static boolean nums[];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextLong();
        M = in.nextLong();

        nums = new boolean[(int)Math.sqrt(M)+1];
        Arrays.fill(nums, true);
        nums[1] = false;

        for(int i=2; i<nums.length; i++){
            if(!nums[i]) continue;
            for(int j=i+i; j<nums.length; j+=i){
                nums[j] = false;
            }
        }
        for(int i=2; i<nums.length; i++){
            if(!nums[i]) continue;
            long radix = i;
            while((double) M / i >= radix){
                if (radix >= (double) N / i) {
                    count++;
                }
                radix *= i;
            }
        }
        System.out.println(count);
    }
}
