import java.io.*;
public class P11689_GCDNK1 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    long n = Long.parseLong(br.readLine());
    long result = n;
    for (long p = 2; p <= Math.sqrt(n); p++) { // �����ٱ����� ����
      if (n % p == 0) { // p�� ���μ����� Ȯ��
        result = result - result / p; // ��� �� ������Ʈ
        while (n % p == 0) { // �ش� ���μ��� ������ 2^7*11�̶�� 2^7�� ���ְ� 11�� ����
          n /= p;
        }
      }
    }
    if (n > 1) // ���� ���μ� ������ �����ִ� ���
//(�ݺ������� �����ٱ����� Ž���߱� ������ 1���� ���μ��� �����Ǵ� ���̽�)
      result = result - result / n;
    System.out.println(result);
  }
}
