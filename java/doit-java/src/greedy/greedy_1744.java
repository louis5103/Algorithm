package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class greedy_1744 {
    static int N;
    static PriorityQueue<Integer>[] pq;
    static int One, Zero, Sum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        pq = new PriorityQueue[2];
        pq[0] = new PriorityQueue<>(Collections.reverseOrder());
        pq[1] = new PriorityQueue<>();
        for(int i=0; i<N; i++){
            int num = Integer.parseInt(br.readLine());
            if(num > 1) pq[0].add(num);
            else if(num < 0) pq[1].add(num);
            else if(num==1) One++;
            else Zero++;
        }
        greedy();
    }
    public static void greedy(){
        while(pq[0].size() > 1){
            Sum += pq[0].poll() * pq[0].poll();
        } if(!pq[0].isEmpty()) Sum += pq[0].poll();

        while(pq[1].size() > 1){
            Sum += pq[1].poll() * pq[1].poll();
        } if(!pq[1].isEmpty() && Zero != 0) pq[1].poll();
        else if(!pq[1].isEmpty() && Zero == 0) Sum += pq[1].poll();

        Sum += One;
        System.out.println(Sum);
    }
}
