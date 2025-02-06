package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FloydWarshal_1389 {
    static int N, M;
    static int[][] matrix;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        matrix = new int[N+1][N+1];
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                matrix[i][j] = Integer.MAX_VALUE;
            }
            matrix[i][i] = 0;
        }
        for( int i = 0; i < M; i++ ){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            matrix[a][b] = 1;
            matrix[b][a] = 1;
        }
        for(int k=1; k <= N; k++){
            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= N; j++){
                    if(matrix[i][k] == Integer.MAX_VALUE || matrix[k][j] == Integer.MAX_VALUE)
                        continue;
                    else if(matrix[i][j] > matrix[i][k] + matrix[k][j]){
                        matrix[i][j] = matrix[i][k] + matrix[k][j];
                    }
                }
            }
        }
        int min_node=0, min = Integer.MAX_VALUE;
        for(int i=1; i <= N; i++){
            for(int j=1; j <= N; j++){
                if(matrix[i][j] == Integer.MAX_VALUE) {
                    matrix[i][0] = Integer.MAX_VALUE;
                    break;
                }
                else matrix[i][0] += matrix[i][j];
            }
            if(min > matrix[i][0]) {
                min = matrix[i][0];
                min_node = i;
            }
        }
        System.out.println(min_node);
        for(int i=1; i <= N; i++){
            System.out.println(matrix[i][0]);
        }
    }
}
