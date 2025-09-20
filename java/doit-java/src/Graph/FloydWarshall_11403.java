package Graph;

import java.io.*;
import java.util.StringTokenizer;

public class FloydWarshall_11403 {
    static int V;
    static int[][] D;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());

        D = new int[V][V];
        for(int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < V; j++) {
                D[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int k=0; k<V; k++) {
            for(int i=0; i<V; i++) {
                for(int j=0; j<V; j++) {
                    if(D[i][k] == 1 && D[k][j] == 1)
                        D[i][j] = 1;
                }
            }
        }
        for(int i=0; i<V; i++) {
            for(int j=0; j<V; j++) {
                bw.write(D[i][j] + " ");
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
}
