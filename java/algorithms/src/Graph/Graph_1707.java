package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Graph_1707 {
    static int K, N, M;
    static List<Integer>[] adj_list;
    static boolean[] visited;
    static int[] checked;
    static boolean cycleCheck;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        for(int i=0; i<K; i++){
            cycleCheck = true;

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            adj_list = new ArrayList[N+1];
            visited = new boolean[N+1];
            checked = new int[N+1];
            for( int j=1; j<=N; j++){
                adj_list[j] = new ArrayList<>();
            }
            for(int j=0; j<M; j++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                adj_list[a].add(b);
                adj_list[b].add(a);
            }

            for(int j=1; j<=N; j++){
                if(cycleCheck)
                    dfs(j);
                else
                    break;
            }
            if(cycleCheck)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }

    private static void dfs(int cur) {
        for(int next : adj_list[cur]){
            if(!visited[next]){
                visited[cur] = true;
                checked[next] = (checked[cur] + 1) % 2;
                dfs(next);
            }
            else if(checked[cur] == checked[next]){
                if(visited[cur] == false) {
                    visited[cur] = true;
                    checked[cur] = (checked[next] + 1) % 2;
                }
                else cycleCheck = false;
            }
        }
    }
}

// Case 1.
//1
//8 8
//2 3
//3 6
//8 5
//5 6
//4 1
//1 7