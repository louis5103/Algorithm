package search.bfs;

import java.io.*;
import java.util.*;

public class BFS_1260 {
    public static int N;
    public static int M;
    public static int S;

    public static ArrayList<Integer>[] adj_list;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        adj_list = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        for(int i=1; i<=N; i++){
            adj_list[i] = new ArrayList<>();
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj_list[a].add(b);
            adj_list[b].add(a);
        }
        for(int i=1; i<=N; i++){
            Collections.sort(adj_list[i]);
        }
        dfs(S);
        System.out.println();

        visited = new boolean[N + 1];
        bfs(S);
        System.out.println();
    }

    public static void dfs(int cur){
        if(visited[cur]) return;
        visited[cur] = true;
        System.out.print(cur + " ");
        for(int next_cur : adj_list[cur]){
            if(!visited[next_cur]){
                dfs(next_cur);
            }
        }
    }
    public static void bfs(int cur){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(cur);
        visited[cur] = true;
        while(!queue.isEmpty()){
            cur = queue.poll();
            System.out.print(cur+" ");
            for(int next_cur : adj_list[cur]){
                if(!visited[next_cur]){
                    queue.add(next_cur);
                    visited[next_cur] = true;
                }
            }
        }
    }

}
