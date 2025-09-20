package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BellmanFord_1219 {
    static int V, E;
    static int start, end;

    static Edge[] adj_list;
    static long[] D;
    static long[] B;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        adj_list = new Edge[E];
        D = new long[V];
        B = new long[V];
        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adj_list[i] = new Edge(a,b,w);
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<V; i++) {
            D[i] = Long.MIN_VALUE; // mCycle=true: gg, Integer.MAX_VALUE: Gee, D[i]: D[i]
            B[i] = Long.parseLong(st.nextToken());
        }
        D[start] = B[start];
        bellmanFord();
        if(D[end] == Long.MIN_VALUE)
            System.out.println("gg");
        else if(D[end] == Long.MAX_VALUE)
            System.out.println("Gee");
        else
            System.out.println(D[end]);

    }
    static void bellmanFord(){
        for(int i=0; i<2*V; i++){
            for(Edge cur : adj_list){
                if(D[cur.src] == Long.MIN_VALUE){
                    continue;
                }
                else if(D[cur.src]==Long.MAX_VALUE){
                    D[cur.dest] = Long.MAX_VALUE;
                }
                else if(D[cur.dest] < D[cur.src] + B[cur.dest] - cur.weight){
                    D[cur.dest] = D[cur.src] + B[cur.dest] - cur.weight;
                    if(i >= V-1) D[cur.dest] = Long.MAX_VALUE;
                }
            }
        }
    }
    private static class Edge{
        int src, dest;
        int weight;
        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }
}
