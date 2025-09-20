import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class P2251_���� {
  // 6������ �̵� ���̽��� ǥ���ϱ� ���� �迭
  static int[] Sender = { 0, 0, 1, 1, 2, 2 };
  static int[] Receiver = { 1, 2, 0, 2, 0, 1 };
  static boolean visited[][];  //A B�� ���Ը� ������ C�� ���԰� �����ǹǷ� 2���θ� üũ ����
  static boolean answer[];
  static int now[];
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    now = new int[3]; // A B C ���� ���� �����ϴ� �迭
    now[0] = scan.nextInt();
    now[1] = scan.nextInt();
    now[2] = scan.nextInt();
    visited = new boolean[201][201];
    answer = new boolean[201];
    BFS();
    for (int i = 0; i < answer.length; i++) {
      if (answer[i]) System.out.print(i + " ");
    }
  }
  public static void BFS() {
    Queue<AB> queue = new LinkedList<>();
    queue.add(new AB(0, 0));
    visited[0][0] = true;
    answer[now[2]] = true;
    while (!queue.isEmpty()) {
      AB p = queue.poll();
      int A = p.A;
      int B = p.B;
      int C = now[2] - A - B; // C�� ��ü ���� �翡�� A�� B�� ���� ��
      for (int k = 0; k < 6; k++) { // A->B, A->C, B->A, B->C, C->A, C->B 6���� ���̽��� �̵�
        int[] next = { A, B, C };
        next[Receiver[k]] += next[Sender[k]];
        next[Sender[k]] = 0;
        if (next[Receiver[k]] > now[Receiver[k]]) { // ��� ������ �뷮���� ���� ���� ��ĥ ��
// �ʰ��ϴ� ��ŭ �ٽ� ���� ���뿡 �־���
          next[Sender[k]] = next[Receiver[k]] - now[Receiver[k]]; 
          next[Receiver[k]] = now[Receiver[k]]; // ��� ������ �ִ�� ä����
        }
        if (!visited[next[0]][next[1]]) { // A�� B�� ���� ���� ���Ͽ� �湮 �迭 üũ
          visited[next[0]][next[1]] = true;
          queue.add(new AB(next[0], next[1]));
          if (next[0] == 0) {  // A�� ���� ���� 0�϶� C�� ���� ���Ը� ���� ������ ����
            answer[next[2]] = true;
          }
        }
      }
    }
  }
}
//AB Ŭ�������� -> A�� B�� ���� ������ ������ C�� ���߰� �����ϱ� ������ �� ������ ���
class AB { 
  int A;
  int B;
  public AB(int A, int B) {
    this.A = A; 
this.B = B;
  }
}
