

import java.util.Scanner;

public class P2747_�Ǻ���ġ��_TopDown {
  static int[] D;
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    D = new int[n + 1];
    for (int i = 0; i <= n; i++) {
      D[i] = -1;
    }
    D[0] = 0;
    D[1] = 1;
    fibo(n);
    System.out.println(D[n]);
  }

  static int fibo(int n) {
    if (D[n] != -1) //������ ����� ���� �ִ� �κ� ������ �ٽ� ������� �ʰ� �����Ѵ�.
      return D[n];
    return D[n] = fibo(n - 2) + fibo(n - 1); //�޸������̼� �κ�
  }
}
