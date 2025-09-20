package numberTheory;

    import java.util.Scanner;

    public class NumberTheory_1016 {
        static long S, E;
        static boolean[] nums;
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            S = sc.nextLong();
            E = sc.nextLong();
            nums = new boolean[(int)(E-S+1)];
            for(long i=2; i*i <= E; i++){
                long pow = i*i;
                long start_index = ((S%pow)!=0) ? (S/pow)+1 : S/pow;
                for(long j=start_index; j*pow<=E; j++){
                    nums[(int)((j*pow)-S)] = true;
                }
            }
            int count = 0;
            for(boolean isTrue : nums){
                if(!isTrue){
                    count++;
                }
            }
            System.out.println(count);

        }
    }
