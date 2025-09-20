package Graph;

import java.io.*;
import java.util.*;

public class Dijkstra_1854 {
    static int start = 1;
    static int V, E, K;
    static List<Edge>[] adj_list;
//    static int[] D;
    static Queue<Integer>[] pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        adj_list = new ArrayList[V+1];
//        D = new int[V+1];
        pq  = new PriorityQueue[V+1];
        for(int i=0; i<=V; i++){
            adj_list[i] = new ArrayList<>();
            pq[i] = new PriorityQueue<>(K, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 < o2 ? 1 : -1;
                }
            });
        }

        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adj_list[a].add(new Edge(b, weight));
        }
        pq[start].offer(0);
        dijkstra();
        for(int i=1; i<=V; i++){
            if(pq[i].size()==K)
                bw.write(pq[i].peek() + "\n");
            else
                bw.write("-1\n");

        }
        bw.flush();
        bw.close();

    }
    static void dijkstra(){
        Queue<Edge> q = new PriorityQueue<>();
        q.offer(new Edge(start, 0));

        while(!q.isEmpty()){
            Edge cur = q.poll();

            for(Edge next : adj_list[cur.node]){
                if(pq[next.node].size() < K){
                    q.offer(new Edge(next.node, cur.weight + next.weight));
                    pq[next.node].offer(cur.weight + next.weight);
                }
                else if(pq[next.node].peek() > cur.weight + next.weight){
                    q.offer(new Edge(next.node, cur.weight + next.weight));
                    pq[next.node].poll();
                    pq[next.node].offer(cur.weight + next.weight);
                }
            }
        }

    }
    private static class Edge implements Comparable<Edge> {
        int node;
        int weight;
        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge o) {
            if(this.weight < o.weight) return -1;
            else return 1;
        }
    }
}

