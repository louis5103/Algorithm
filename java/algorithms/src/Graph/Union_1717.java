package Graph;

import java.io.*;
import java.util.StringTokenizer;

public class Union_1717 {
    static int N, M;
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parents = new int[N+1];
        for(int i=0; i<=N; i++){
            parents[i] = -1;
        }
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int select = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(select == 0) {
                weighted_union(a, b);
            }
            else
                if(collapse_find(a) == collapse_find(b))
                    bw.write("YES\n");
                else
                    bw.write("NO\n");
        }
        bw.flush();
        bw.close();
    }

    private static void weighted_union(int a, int b) {
        int root_a = collapse_find(a);
        int root_b = collapse_find(b);

        if(root_a == root_b) return;
//        else if(parents[root_a] > parents[root_b]){ // NOTE: -3 > -5
//            parents[root_b] += parents[root_a];
//            parents[root_a] = root_b;
//        }
//        else{
//            parents[root_a] += parents[root_b];
//            parents[root_b] = root_a;
//        }
        if(parents[root_a] < parents[root_b]){
            parents[root_b] += parents[root_a];
            parents[root_a] = root_b;
        } else {
            parents[root_a] += parents[root_b];
            parents[root_b] = root_a;
        }
    }
//    private static int collapse_find(int a){
//        int root = find_root(a);
//        int trail = a;
//        int parent = parents[trail];
//
//        while(parent > 0){
//            parents[trail] = root;
//            trail = parent;
//            parent = parents[trail];
//        }
//        return root;
//    }
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


