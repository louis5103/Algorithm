package search.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BFS_2178 {
    static int N, M;
    static int[][] matrix;
    static boolean[][] visited;

    static int[] dx = {-1 ,0, 0, 1}; // h j k l, < ^ v >
    static int[] dy = {0, 1, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        matrix = new int[N][M];
        visited = new boolean[N][M];

        for(int i=0; i<N; i++) {
            String row_line = br.readLine();
            for(int j=0; j<M; j++){
                matrix[i][j] = Integer.parseInt(row_line.substring(j, j+1));
            }
        }
        dfs(0, 0);
        System.out.println(matrix[N-1][M-1]);
    }
    public static void dfs(int i, int j){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        visited[i][j] = true;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            for(int k=0; k<4; k++){
                int next_x_index = cur[0] + dx[k];
                int next_y_index = cur[1] + dy[k];
                if(next_x_index>=0 && next_x_index<N && next_y_index>=0 && next_y_index<M){
                    if( !visited[next_x_index][next_y_index] && matrix[next_x_index][next_y_index] != 0){
                        queue.offer(new int[]{next_x_index, next_y_index});
                        visited[next_x_index][next_y_index] = true;
                        matrix[next_x_index][next_y_index] += matrix[cur[0]][cur[1]];
                    }
                }
            }
        }
    }
}
