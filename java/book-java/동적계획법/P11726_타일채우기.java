import java.util.Scanner;
public class P11726_Ÿ��ä��� {
  static long mod = 10007;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    long D[] = new long[1001];
    D[1] = 1;  //���̰� 2*1�϶� Ÿ�� ����� ��
    D[2] = 2;  //���̰� 2*2�϶� Ÿ�� ����� ��
    for(int i=3; i<=N; i++){
      D[i] = (D[i-1] + D[i-2])%mod; 
    }
    System.out.println(D[N]);
  }
}
