package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Tree_11725 {
    static int N;
    static List<Integer>[] adj_list;
    static boolean[] visited;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());

        adj_list = new ArrayList[N+1];
        visited = new boolean[N+1];
        parent =  new int[N+1];
        for(int i=1; i<=N; i++) {
            adj_list[i] = new ArrayList<>();
        }
        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(in.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj_list[u].add(v);
            adj_list[v].add(u);
        }
        dfs();
        for(int i=2; i<=N; i++){
            System.out.println(parent[i]);
        }
    }
    static void dfs(){
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        visited[1] = true;

        while(!stack.isEmpty()){
            int cur = stack.pop();
            for(int next : adj_list[cur]){
                if(!visited[next]){
                    stack.push(next);
                    visited[next] = true;
                    parent[next] = cur;
                }
            }
        }
    }
}
