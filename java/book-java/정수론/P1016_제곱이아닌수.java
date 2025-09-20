import java.util.*;
public class P1016_�����̾ƴѼ� {
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    long Min = sc.nextLong();
    long Max = sc.nextLong();
    boolean[] Check = new boolean[(int) (Max - Min + 1)]; // �ִ� �ּ� ���̸�ŭ �迭 ����
    // 2�� �������� 4���� max���� �۰ų� ���� ���� �ݺ�
    for (long i = 2; i * i <= Max; i++) {
      long pow = i * i; // ������
      long start_index = Min / pow;
      ;
      if (Min % pow != 0)
        start_index++; // �������� ������ 1�� �����־�� Min���� ū ������ ���� ���۵�
      for (long j = start_index; pow * j <= Max; j++) { // �������� true�� ����
        Check[(int) ((j * pow) - Min)] = true;
      }
    }
    int count = 0;
    for (int i = 0; i <= Max - Min; i++) {
      if (!Check[i]) {
        count++;
      }
    }
    System.out.println(count);
  }
}