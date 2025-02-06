package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BellmanForm_11657 {
    static int V, E;
    static Edge[] edge_list;
    static long[] D;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        edge_list = new Edge[E];
        D = new long[V+1];
        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edge_list[i] = new Edge(a,b,w);
        }
        for( int i=1; i<=V; i++){
            D[i] = Integer.MAX_VALUE;
        }
        D[1] = 0;
        for(int i=1; i<V; i++) {
            for (Edge cur_edge : edge_list) {
                if (D[cur_edge.start] != Integer.MAX_VALUE
                        && D[cur_edge.end] > D[cur_edge.start] + cur_edge.weight
                ) {
                    D[cur_edge.end] = D[cur_edge.start] + cur_edge.weight;
                }
            }
        }
        boolean mCycle = false;
        for(int i=0; i<E; i++){
            Edge cur_edge = edge_list[i];
            if(D[cur_edge.start] != Integer.MAX_VALUE
                    && D[cur_edge.end] > D[cur_edge.start] + cur_edge.weight
            ){
                mCycle = true;
            }
        }
        if(mCycle || V == 1)
            System.out.println("-1");
        else {
            for (int i=2; i<=V; i++) {
                if(D[i] != Integer.MAX_VALUE)
                    System.out.println(D[i]);
                else
                    System.out.println("-1");
            }
        }
    }
    private static class Edge{
        int start;
        int end;
        int weight;
        public Edge(int start, int end, int weight){
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
}
