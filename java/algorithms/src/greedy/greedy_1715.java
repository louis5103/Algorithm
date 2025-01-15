package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class greedy_1715 {
    static int N;
    static int sum;
    static PriorityQueue<Integer> pq;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        pq = new PriorityQueue<>();
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }
        greedy();
    }
    public static void greedy(){
        while(!pq.isEmpty()){
            if(pq.size() == 1) {
                break;
            }
            int item1 = pq.poll();
            int item2 = pq.poll();

            sum += item1+item2;
            pq.add(item1+item2);
        }
        System.out.println(sum);
    }
}

