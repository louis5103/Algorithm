package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Graph_18352 {
    static List<Integer>[] adj_list;
    static int[] visited;
    static int N, M, K, Start;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Start = Integer.parseInt(st.nextToken());

        adj_list = new ArrayList[N + 1];
        visited = new int[N + 1];
        for(int i=1; i<=N; i++){
            adj_list[i] = new ArrayList<>();
        }
        for(int i=1; i<=M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj_list[a].add(b);
        }

        bfs(Start);

        boolean isExist = false;
        for(int i=1; i<=N; i++){
            if(visited[i]-1 == K){
                isExist = true;
                System.out.println(i);
            }
        }
        if(!isExist) System.out.println("-1");
    }
    private static void bfs(int cur){
        LinkedList<Integer> queue = new LinkedList<>();

        queue.add(cur);
        visited[cur] = 1;
        while(!queue.isEmpty()){
            cur = queue.poll();
            for(int next : adj_list[cur]){
                if(visited[next] != 0) continue;

                visited[next] = visited[cur] + 1;
                queue.add(next);
            }
        }
    }
}
