package Graph;

import java.io.*;
import java.util.*;

public class Graph_1325 {
    static int N, M;
    static List<Integer>[] adj_list;
    static boolean[] visited;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw  = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj_list = new ArrayList[N+1];
        nums = new int[N+1];
        for(int i=1; i<=N; i++) {
            adj_list[i] = new ArrayList<>();
        }

        for(int i=1; i<=M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj_list[b].add(a);
        }
        for(int i=1; i<=N; i++){
            visited = new boolean[N+1];
            bfs(i);
        }
        int maxVal = 0;
        for(int i=1; i<=N; i++){
            maxVal = Math.max(maxVal, nums[i]);
        }
        for(int i=1; i<=N; i++){
            if(nums[i] == maxVal)
                bw.write(i + " ");
        }
        bw.flush();
        bw.close();
    }
    private static void bfs(int cur){
        Queue<Integer> queue = new LinkedList<>();
        int start_node = cur;

        queue.add(cur);
        visited[cur] = true;
        nums[start_node]++;
        while(!queue.isEmpty()){
            cur = queue.poll();
            for(int next : adj_list[cur]){
                if(visited[next]) continue;

                visited[next] = true;
                queue.add(next);
                nums[start_node]++;
            }
        }
    }
}
