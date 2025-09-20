package search.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DFS_11724 {
    static ArrayList<Integer>[] adj_list;
    static boolean[] visited;
    static int N_K;
    static int E_K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N_K = Integer.parseInt(st.nextToken());
        E_K = Integer.parseInt(st.nextToken());

        adj_list = new ArrayList[N_K+1];
        visited = new boolean[N_K+1];

        for(int i=1; i<=N_K; i++){
            adj_list[i] = new ArrayList<>();
        }
        for(int i=0; i<E_K; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj_list[a].add(b);
            adj_list[b].add(a);
        }
        int count = 0;
        for(int i=1; i<=N_K; i++){
            if(!visited[i]){
                count++;
                dfs(i);
            }
        }
        System.out.println(count);
    }
    public static void dfs(int i){
        if(visited[i]) return;

        visited[i] = true;
        for(int node : adj_list[i]){
            if(!visited[node]){
                dfs(node);
            }
        }
    }
}
