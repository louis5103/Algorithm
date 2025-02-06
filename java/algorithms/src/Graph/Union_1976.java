package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Union_1976 {
    static int N, M;
    static int[] parents;
    static int[][] matrix;
    static int[] route;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        matrix = new int[N+1][N+1];
        parents = new int[N+1];
        route = new int[M+1];
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
           for(int j=1; j<=N; j++){
               matrix[i][j] = Integer.parseInt(st.nextToken());
           }
        }
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=M; i++){
            route[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=1; i<=N; i++){
            parents[i] = -1;
        }
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                if(matrix[i][j] == 1)
                    weighted_union(i, j);
            }
        }
        int index = collapse_find(route[1]);
        for(int i=2; i< route.length; i++){
            if(index != collapse_find(route[i])){
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }
    private static void weighted_union(int a, int b) {
        int root_a = collapse_find(a);
        int root_b = collapse_find(b);

        if(root_a == root_b) return;
        else if(parents[root_a] > parents[root_b]){
            parents[root_b] += parents[root_a];
            parents[root_a] = root_b;
        } else {
            parents[root_a] += parents[root_b];
            parents[root_b] = root_a;
        }
    }

    private static int collapse_find(int a){
        int root = find_root(a);
        int trail = a;

        while(trail != root) {
            int parent = parents[trail];
            parents[trail] = root;
            trail = parent;
        }
        return root;
    }
    private static int find_root(int a) {
        int parent = a;

        while(parents[parent] >= 0 ){
            parent = parents[parent];
        }
        return parent;
    }
}

