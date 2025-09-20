package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;


public class MST_1414 {
    static int N;
    static int total;
    static int[] parent;
    static PriorityQueue<Edge> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        pq = new PriorityQueue<>();
        parent = new int[N];
        for(int i=0; i<N; i++)
            parent[i] = -1;

        for(int i=0; i<N; i++){
            char[] weight = br.readLine().toCharArray();
            for(int j=0; j<N; j++){
                int int_weight;

                if(weight[j] == '0') continue;
                else if(weight[j] >= 'a' && weight[j] <= 'z') int_weight = (int)(weight[j] - 'a' + 1);
                else int_weight = (int)(weight[j] - 'A' + 27);

                total += int_weight;
                if(i != j) {
                    pq.add(new Edge(i, j, int_weight));
                }
            }
        }
        kruskal();
    }
    static void kruskal(){
        int count = 0;
        while(!pq.isEmpty()){
            Edge cur_edge = pq.poll();
            if(collapsing_find(cur_edge.start) != collapsing_find(cur_edge.end)){
                weighted_union(cur_edge.start, cur_edge.end);
                total -= cur_edge.weight;
                count++;
            }
        }
        if(count == N-1)
            System.out.println(total);
        else
            System.out.println("-1");
    }

    static void weighted_union(int u, int v){
        int u_group = collapsing_find(u);
        int v_group = collapsing_find(v);

        if(u_group > v_group){
            parent[v_group] += parent[u_group];
            parent[u_group] = v_group;
        }
        else{
            parent[u_group] += parent[v_group];
            parent[v_group] = u_group;
        }
    }

    private static int collapsing_find(int cur) {
        int root = simple_find(cur);
        while(root != cur){
            int trail = parent[cur];
            parent[cur] = root;
            cur = trail;
        }
        return root;
    }

    private static int simple_find(int cur) {
        while(parent[cur] >= 0){
            cur = parent[cur];
        }
        return cur;
    }

    private static class Edge implements Comparable<Edge>{
        int start;
        int end;
        int weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge o) {
            if(this.weight > o.weight)
                return 1;
            return -1;
        }
    }
}
