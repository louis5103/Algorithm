package search.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DFS_13023 {
    public static int N;
    public static int M;
    public static ArrayList<Integer>[] nums;
    public static boolean[] visited;
    public static boolean arrived;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new ArrayList[N];
        visited = new boolean[N];
        for(int i=0; i<N; i++){
            nums[i] = new ArrayList<>();
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            nums[a].add(b);
            nums[b].add(a);
        }
        for(int i=0; i<N; i++){
            dfs(i, 1);
            if(arrived) break;
        }
        if(arrived) System.out.println("1");
        else System.out.println("0");
    }
    public static void dfs(int cur, int depth){
        if(depth==5 || arrived) {
            arrived = true;
            return;
        }
        if(visited[cur]){
            return;
        }
        visited[cur] = true;
        for(int next_cur : nums[cur]){
            if(!visited[next_cur])
                dfs(next_cur, depth+1);
        }
        visited[cur] = false;
    }
}
