package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MST_17472 {
    static int N, M;
    static int num;
    static int[][] map;
    static boolean[][] visited; // for map

    static List<List<int[]>> islands = new ArrayList<>();
    static Kruscal kruscal;
    static int[][] vector = new int[][] {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] == 1 && !visited[i][j]) {
                    num++;
                    bfs(new int[] {i, j});
                }
            }
        }

        for(int i=0; i<N; i++) {
            System.out.println(Arrays.toString(map[i]));
        } System.out.println("-----------------");
        for(int i=0; i< islands.size(); i++) {
            for(int j=0; j<islands.get(i).size(); j++) {
                System.out.print(Arrays.toString(islands.get(i).get(j)) + " ");
            }
            System.out.println();
        }
        kruscal = new Kruscal();
        searching_edge();
        System.out.println("-----------------");
        kruscal.__kruscal();
    }
    static void bfs(int[] coordinate){
        List<int[]> list = new ArrayList<>();

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(coordinate);
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int y = cur[0];
            int x = cur[1];

            if(!visited[y][x])
                list.add(cur);

            visited[y][x] = true;
            map[y][x] = num;
            for(int[] next : vector) {
                if(     y + next[0] < 0 ||
                        x + next[1] < 0 ||
                        y + next[0] > N-1 ||
                        x + next[1] > M-1
                ) {
                    continue;
                }
                else if(!visited[y+next[0]][x+next[1]] && map[y+next[0]][x+next[1]] == 1) {
                    queue.offer(new int[] {y+next[0], x+next[1]});
                }
            }
        }
        islands.add(list);
    }
    static void searching_edge(){
        for(List<int[]> island : islands) { // 각 섬 개수만큼
            for(int[] island_coordinate : island){  // 섬에 속한 좌표만큼
                int cur_island_num = map[island_coordinate[0]][island_coordinate[1]];
                for(int[] coordinate_vector : vector){ // 상, 하, 좌, 우 별로
//                    int[] cur_coordinate = new int[] {island_coordinate[0], island_coordinate[1]};
                    int[] cur_coordinate = island_coordinate;

                    int next_y = cur_coordinate[0] + coordinate_vector[0];
                    int next_x = cur_coordinate[1] + coordinate_vector[1];
                    int length=0;
                    while(
                            next_y >= 0 &&
                            next_y <= N-1 &&
                            next_x >= 0 &&
                            next_x <= M-1
                    ){
                        if(cur_island_num == map[next_y][next_x]){
                            break;
                        } // 같은 섬일때
                        else if(map[next_y][next_x] != 0 && cur_island_num != map[next_y][next_x]){
                            if(length > 1)
                                kruscal.edge_list.add(new Edge(cur_island_num, map[next_y][next_x], length));
//                            System.err.println(cur_island_num + " " + map[next_y][next_x] + " " + length);
                            break;
                        }  // 다른 섬일때
                        else{
                            cur_coordinate[0] = next_y;
                            cur_coordinate[1] = next_x;
                            next_y += coordinate_vector[0];
                            next_x += coordinate_vector[1];
                            length++;
                        } // 바다일때
                    }
                }
            }
        }
    }
    private static class Kruscal{
        static Queue<Edge> edge_list;
        static int[] parent;
        static int edgeCount;

        static int total;
        public Kruscal(){
            edge_list = new PriorityQueue<Edge>( new Comparator<Edge>() {
                @Override
                public int compare(Edge o1, Edge o2) {
                    if(o1.weight > o2.weight) return 1;
                    else return -1;
                }
            });
            parent = new int[num+1];
            for(int i=1; i<=num; i++) {
                parent[i] = -1;
            }
        }
        static void __kruscal(){
            while(!edge_list.isEmpty()){
                Edge edge = edge_list.poll();
                if(collapsing_find(edge.start) == collapsing_find(edge.end)){
                    continue;
                }
                else{
                    weighted_union(edge.start, edge.end);
                    total += edge.weight;
                    edgeCount++;
                }
            }
            if(edgeCount == num-1)
                System.out.println(total);
            else
                System.out.println(-1);
        }
        static void weighted_union(int u, int v){
            int u_group = collapsing_find(u);
            int v_group = collapsing_find(v);

            if(u_group > v_group){ // -3 > -5
                parent[v_group] += parent[u_group];
                parent[u_group] = v_group;
            }
            else{
                parent[v_group] += parent[u_group];
                parent[v_group] = u_group;
            }
        }
        static int collapsing_find(int cur){
            int root = simple_find(cur);

            while(root != cur){
                int lead = parent[cur];
                parent[cur] = root;
                cur = lead;
            }
            return root;
        }
        static int simple_find(int cur){
            while(parent[cur] > 0){
                cur = parent[cur];
            }
            return cur;
        }
    }
    private static class Edge{
        int start;
        int end;
        int weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
}
