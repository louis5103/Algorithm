package numberTheory;

import java.util.Scanner;

public class NumberTheory_1747 {
    static boolean[] nums;
    static int N;
    static int start;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        start = sc.nextInt();
        N = 1004000;
        nums = new boolean[N+1];

        for(int i=2; i<=N; i++){
            nums[i] = true;
        }
        for(int i=2; i<Math.sqrt(N+1); i++){
            if(!nums[i]) continue;
            for(int j= 2*i; j<N+1; j+=i){
                nums[j]=false;
            }
        }
        for(int i=start; i<N+1; i++){
            if(!nums[i]) continue;
            if(isPalindrome(i)){
                System.out.println(i);
                break;
            }
        }
    }
    private static boolean isPalindrome(int i) {
        char[] number = String.valueOf(i).toCharArray();
//        for(int j=0; j<=(number.length-1)/2; j++){
//            if(number[j] != number[number.length-j-1]) return false;
//        }
        int S=0, E=number.length-1;
        while(S<E){
            if(number[S++] != number[E--]) return false;
        }
        return true;
    }
}
