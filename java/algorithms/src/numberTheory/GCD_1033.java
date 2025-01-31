package numberTheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class GCD_1033 {
    static int N;
    static long lcm=1L;

    static List<node>[] adj_list;
    static boolean[] visited;
    static long[] weight;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        adj_list = new ArrayList[N];
        visited = new boolean[N];
        weight = new long[N];

        for(int i=0; i<N; i++) {
            adj_list[i] = new ArrayList<>();
        }

        for(long i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int cur = Integer.parseInt(st.nextToken());
            int next = Integer.parseInt(st.nextToken());
            int scale_cur = Integer.parseInt(st.nextToken());
            int scale_next = Integer.parseInt(st.nextToken());
            adj_list[cur].add(new node(next, scale_cur, scale_next));
            adj_list[next].add(new node(cur, scale_next, scale_cur));
            lcm *= (scale_cur*scale_next/gcd(scale_cur, scale_next));
        }
        weight[0] = lcm;
        dfs(0);
        long mgcd = weight[0];
        for(int i=1; i<N; i++){
            mgcd = gcd(mgcd, weight[i]);
        }
        for(int i=0; i<N; i++){
            System.out.print(weight[i]/mgcd + " ");
        }
    }
    static long gcd(long a, long b) {
        long n=a, m=b;
        while(m != 0){
            long tmp = n%m;
            n=m;
            m = tmp;
        }
        return n;
    }
    static void dfs(int start){
        visited[start] = true;
        for(node cur : adj_list[start]){
            if(visited[cur.node]) continue;

            weight[cur.node] = weight[start] * cur.scale_next / cur.scale_cur;
            dfs(cur.node);
        }
    }

}
class node{
    int node;
    int scale_cur;
    int scale_next;

    public node(int node, int scale_cur, int scale_next){
        this.node = node;
        this.scale_cur = scale_cur;
        this.scale_next = scale_next;
    }
}

