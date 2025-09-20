package dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Dynamic_9252 {
    static char[] A, B;
    static int[][] D;
    static List<Character> char_list;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        A = sc.nextLine().toCharArray();
        B = sc.nextLine().toCharArray();
        D = new int[A.length + 1][B.length + 1];

        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= B.length; j++) {
                if (A[i - 1] == B[j - 1]) {
                    D[i][j] = D[i - 1][j - 1] + 1;
                }
                else {
                    D[i][j] = Math.max(D[i - 1][j], D[i][j - 1]);
                }
            }
        }
//        show_array();
        System.out.println(D[A.length][B.length]);

        char_list = new ArrayList<>();
        get_char(A.length, B.length);


        for (int i = char_list.size() - 1; i >= 0; i--) {
            System.out.print(char_list.get(i));
        }
    }

    private static void get_char(int A_index, int B_index) {
        if (A_index == 0 || B_index == 0) return;

        if (A[A_index - 1] == B[B_index - 1]) {
            char_list.add(A[A_index - 1]);
            get_char(A_index - 1, B_index - 1);
        } else {
            if (D[A_index - 1][B_index] > D[A_index][B_index - 1]) {
                get_char(A_index - 1, B_index);
            } else {
                get_char(A_index, B_index - 1);
            }
        }
    }
    private static void show_array() {
        System.out.println(Arrays.toString(A));
        System.out.println(Arrays.toString(B));
        for(int i=0; i<A.length; i++){
            System.out.println(Arrays.toString(D[i]));
        }
    }
}
