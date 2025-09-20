package combination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Combination_1722 {
    static int N, Problem, K;
    static LinkedList<Integer> D;
    static int[] F;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        Problem = Integer.parseInt(st.nextToken());
        D = new LinkedList<>();
        F = new int[N+1];
        for(int i = 1; i <= N; i++) {
            D.add(i);
        }
        if(Problem == 1){
            K = Integer.parseInt(st.nextToken());
            F[0] = 1;
            for(int i=1; i<=N; i++){
                F[i] = i*F[i-1];
            }
            problem_1();
        }
        else{
            for(int i =0; i<N; i++)
                D.add(Integer.parseInt(st.nextToken()));
            problem_2();
        }
    }

    private static void problem_1() {
        int index=1;
        for(int pointer=N; index<=pointer && pointer!=1 && K!=1;){
            if(K <= F[index]){
                if(1 <= pointer - index) {
                    for(int i=0; i<pointer-index; i++)
                        System.out.print(D.pop() + " ");
                }
                int cnt;
                for(cnt=1; K>F[index-1]*cnt; cnt++);
                K -= F[index-1]*(cnt-1);

                System.out.print(D.remove(cnt-1)+ " ");
                pointer = index-1;
                index=0;
            }
            index++;
        }
        while(D.size()!=0)
            System.out.print(D.pop()+" ");
    }

    private static void problem_2() {
    }
}
