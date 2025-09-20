package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MST_Kruscal_1197 {
    static int V, E;
    static int total_weight;
    static Queue<Edge> edge_list;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        edge_list = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                if(o1.weight > o2.weight) return 1;
                return -1;
            }
        });
        parent = new int[V+1];
        for(int i=1; i<=V; i++) {
            parent[i] = -1;
        }
        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edge_list.offer(new Edge(u, v, w));
        }

        while(!edge_list.isEmpty()){
            Edge cur_edge = edge_list.poll();
            if(collapsing_find(cur_edge.start) != collapsing_find(cur_edge.end)){
                weighted_union(cur_edge.start, cur_edge.end);
                total_weight += cur_edge.weight;
            }
        }
        System.out.println(total_weight);

//        System.out.println(Arrays.toString(parent));
    }
    static void weighted_union(int node_a, int node_b){
        int a_group = collapsing_find(node_a);
        int b_group = collapsing_find(node_b);
        if(parent[a_group] > parent[b_group]){  // -3 > -5
            parent[b_group] += parent[a_group];
            parent[a_group] = b_group;
        }
        else{
            parent[a_group] += parent[b_group];
            parent[b_group] = a_group;
        }
    }
    static int collapsing_find(int cur){
        int root = simple_find(cur);
        while(root != cur){
            int trail = parent[cur];
            parent[cur] = root;
            cur = trail;
        }
        return root;
    }
    static int simple_find(int cur){
        int trail = parent[cur];
        while(trail > 0){
            cur = trail;
            trail = parent[cur];
        }
        return cur;
    }
    private static class Edge {
        int start, end;
        int weight;
        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
}
