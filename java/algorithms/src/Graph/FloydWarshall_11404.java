package Graph;

import java.io.*;
import java.util.StringTokenizer;

public class FloydWarshall_11404 {
    static int V, E;
    static int[][] cost;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        E = Integer.parseInt(st.nextToken());
        cost = new int[V+1][V+1];

        for(int i=1; i<=V; i++){
            for(int j=1; j<=V; j++){
                cost[i][j] = 1000001;
            }
            cost[i][i] = 0;
        }

        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            if(cost[a][b] > w) cost[a][b] = w;
        }
        for(int k=1; k<=V; k++){
            for(int i=1; i<=V; i++){
                for(int j=1; j<=V; j++){
                    cost[i][j] = Math.min(cost[i][j], cost[i][k]+cost[k][j]);
                }
            }
        }
        for(int i=1; i<=V; i++){
            for(int j=1; j<=V; j++){
                if(cost[i][j] < 10000000) bw.write(cost[i][j] + " ");
                else bw.write("0 ");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }
}
