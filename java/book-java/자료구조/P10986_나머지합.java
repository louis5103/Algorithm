import java.io.IOException;
import java.util.Scanner;
public class P10986_�������� {
  public static void main(String[] args) throws IOException {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int M = sc.nextInt();
    long[] S = new long[N];
    long[] C = new long[M];
    long answer = 0;
    S[0] = sc.nextInt();
    for (int i = 1; i < N; i++) { // ���� �չ迭 �����
      S[i] = S[i - 1] + sc.nextInt();
    }
    for (int i = 0; i < N; i++) { // �չ迭�� ��� ���� %���� �����ϱ�
      int remainder = (int) (S[i] % M);
      if (remainder == 0)
        answer++; // 0~i������ ������ ��ü�� 0�� ��� ���信 �����ֱ�
      C[remainder]++; // ���� �������� ���� �ε����� ���� ī���� ���ֱ�
    }
    for (int i = 0; i < M; i++) {
      if (C[i] > 1) {
        answer = answer + (C[i] * (C[i] - 1) / 2); // ���� �������� ���� �ε������� 2���� �̴� ����� ���� �����ֱ�
      }
    }
    System.out.println(answer);
  }
}