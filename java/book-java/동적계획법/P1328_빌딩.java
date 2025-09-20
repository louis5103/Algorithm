import java.util.Scanner;
public class P1328_���� {
  static long mod = 1000000007;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int L = sc.nextInt();
    int R = sc.nextInt();
    long D[][][] = new long[101][101][101];
    D[1][1][1] = 1; // ������ 1���̸� ���� �� �ִ� ����� ���� 1��
    for (int i = 2; i <= N; i++) {
      for (int j = 1; j <= L; j++) {
        for (int k = 1; k <= R; k++) {
          D[i][j][k] = (D[i - 1][j][k] * (i - 2) + (D[i - 1][j][k - 1] + D[i - 1][j - 1][k])) % mod;
        }
      }
    }
    System.out.println(D[N][L][R]);
  }
}
