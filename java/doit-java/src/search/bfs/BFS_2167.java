package search.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BFS_2167 {
    static int V;
    static ArrayList<Edge>[] adj;
    static boolean[] visited;
    static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        adj = new ArrayList[V+1];
        visited = new boolean[V+1];
        distance = new int[V+1];

        for(int i=1; i<=V; i++){
            adj[i] = new ArrayList<>();
        }
        for(int i=1; i<=V; i++){
            st = new StringTokenizer(br.readLine());
            int source = Integer.parseInt(st.nextToken());
            while(true){
                int dest = Integer.parseInt(st.nextToken());
                if(dest == -1) break;
                int weight = Integer.parseInt(st.nextToken());
                adj[source].add(new Edge(dest, weight));

            }
        }

        bfs(1);
        int max_dist_idx = 1;
        for(int i=1; i<=V; i++){
            if(distance[max_dist_idx] < distance[i]){
                max_dist_idx = i;
            }
        }

        distance = new int[V+1];
        visited = new boolean[V+1];
        bfs(max_dist_idx);
        max_dist_idx = 1;
        for(int i=1; i<=V; i++){
            if(distance[max_dist_idx] < distance[i]){
                max_dist_idx = i;
            }
        }
        System.out.println(distance[max_dist_idx]);
    }
    public static void bfs(int start_idx){
        Queue<Integer> queue = new LinkedList<>();
        visited[start_idx] = true;
        queue.add(start_idx);
        while(!queue.isEmpty()){
            int cur_idx = queue.poll();
            for(Edge edge : adj[cur_idx]){
                if(!visited[edge.dest]){
                    visited[edge.dest] = true;
                    distance[edge.dest] = distance[cur_idx] + edge.weight;
                    queue.add(edge.dest);
                }
            }
        }

    }
}

class Edge{
    int dest;
    int weight;

    public Edge(int dest, int weight){
        this.dest = dest;
        this.weight = weight;
    }
    public int getDest(){
        return this.dest;
    }
    public int getWeight(){
        return this.weight;
    }
    public void setWeight(int weight){
        this.weight = weight;
    }
    public void setDest(int dest){
        this.dest = dest;
    }
}