package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class greedy_1541 {
    static int total;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] lines = br.readLine().split("-");
        for(int i = 0; i < lines.length; i++) {
            int sum = 0;
            for(String num : lines[i].split("[+]")){
                sum += Integer.parseInt(num);
            }
            if(i == 0) total += sum;
            else total -= sum;
        }
        System.out.println(total);
    }
}
