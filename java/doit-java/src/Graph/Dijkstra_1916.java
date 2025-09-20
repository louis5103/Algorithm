package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Dijkstra_1916 {
    static int V, E;
    static int start, end;
    static List<Edge>[] adj_list;
//    static boolean[] visited;
    static int[] D;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        E = Integer.parseInt(st.nextToken());

        adj_list = new ArrayList[V+1];
//        visited = new boolean[V+1];
        D = new int[V+1];
        for(int i=1; i<=V; i++){
            adj_list[i] = new ArrayList<>();
            D[i] = Integer.MAX_VALUE;
        }

        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adj_list[a].add(new Edge(b, weight));
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        D[start] = 0;
        dijkstra();
        System.out.println(D[end]);
    }
    static void dijkstra(){
        Queue<Edge> q = new PriorityQueue<>();
        q.offer(new Edge(start, 0));

        while(!q.isEmpty()){
            Edge cur = q.poll();

//            if(visited[cur.node]) continue;
//            visited[cur.node] = true;
            if(D[cur.node] != cur.weight) continue;
            for(Edge next : adj_list[cur.node]){
                if(D[next.node] > D[cur.node] + next.weight){
                    D[next.node] = D[cur.node] + next.weight;
                    q.offer(new Edge(next.node, D[next.node]));
                }
            }
        }
    }
    private static class Edge implements Comparable<Edge>{
        int node;
        int weight;
        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
        public int compareTo(Edge o) {
            if(this.weight < o.weight) return -1;
            else return 1;
        }
    }
}

