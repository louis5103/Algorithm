package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class dijkstra_1753 {
    static int V, E;
    static int start_node;

    static List<node>[] adj_list;
    static boolean[] visited;
    static int[] D;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        start_node = Integer.parseInt(st.nextToken());

        adj_list = new List[V+1];
        visited = new boolean[V+1];
        D = new int[V+1];
        for(int i=1; i<=V; i++) {
            adj_list[i] = new ArrayList<>();
            D[i] = Integer.MAX_VALUE;
        }

        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adj_list[a].add(new node(b,w));
        }
        D[start_node] = 0;
        dijkstra();

        for(int i=1; i<=V; i++){
            if(!visited[i]) System.out.println("INF");
            else System.out.println(D[i]);
        }
    }
    static void dijkstra() {
        Queue<node> queue = new PriorityQueue<>();
        queue.offer(new node(start_node, 0));

        while(!queue.isEmpty()) {
            node cur = queue.poll();

            if(visited[cur.node]) continue;
            visited[cur.node] = true;

            for(node next : adj_list[cur.node]) {
//                if(D[cur.node] != cur.weight) continue;
                if(!visited[next.node] && D[next.node] > D[cur.node] + next.weight) {
                    D[next.node] = D[cur.node] + next.weight;
                    queue.offer(new node(next.node, D[next.node]));
                }
            }
        }
    }
}
class node implements Comparable<node>{
    int node;
    int weight;
    node(int node, int weight){
        this.node = node;
        this.weight = weight;
    }
    @Override
    public int compareTo(node o) {
        if(this.weight > o.weight) return 1;
        else return -1;
    }
}
