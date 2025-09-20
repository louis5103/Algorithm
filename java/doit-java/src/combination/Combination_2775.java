package combination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Combination_2775 {
    static int T;
    static int[][] D;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        D = new int[15][15];
        for(int floor=0; floor<15; floor++){
            for(int room=1; room<15; room++){
                if(floor==0)
                    D[floor][room] = room;
                else if(room==1)
                    D[floor][room] = D[floor-1][room];
                else
                    D[floor][room] = D[floor][room-1] + D[floor-1][room];
            }
        }
        T = Integer.parseInt(br.readLine());
        for(int i=1; i<=T; i++){
            int N = Integer.parseInt(br.readLine());
            int K = Integer.parseInt(br.readLine());
            System.out.println(D[N][K]);
        }
    }
}
