import java.util.*;
public class P1991_Ʈ����ȸ {
  static int[][] tree;
  public static void main(String args[]) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    sc.nextLine();
    tree = new int[26][2]; // 0->���� �ڽ� ���; 1->������ �ڽ� ���
    for (int i = 0; i < n; i++) {
      String[] temp = sc.nextLine().split(" ");
      int node = temp[0].charAt(0) - 'A'; // index�� ��ȯ�� ���� A���� ���ֱ�
      char left = temp[1].charAt(0);
      char right = temp[2].charAt(0);
      // �ڽ� ��尡 ���� ��� -1�� ����
      if (left == '.') {
        tree[node][0] = -1;
      } else {
        tree[node][0] = left - 'A';
      }
      if (right == '.') {
        tree[node][1] = -1;
      } else {
        tree[node][1] = right - 'A';
      }
    }
    preOrder(0);
    System.out.println();
    inOrder(0);
    System.out.println();
    postOrder(0);
    System.out.println();
  }
  public static void preOrder(int now) {
    if (now == -1)
      return;
    System.out.print((char) (now + 'A')); // 1.���� ����
    preOrder(tree[now][0]); // 2.���� Ž��
    preOrder(tree[now][1]); // 3.������ Ž��
  }
  public static void inOrder(int now) {
    if (now == -1)
      return;
    inOrder(tree[now][0]); // 1.���� Ž��
    System.out.print((char) (now + 'A')); // 2.���� ����
    inOrder(tree[now][1]); // 3.������ Ž��
  }
  public static void postOrder(int now) {
    if (now == -1)
      return;
    postOrder(tree[now][0]); // 1.���� Ž��
    postOrder(tree[now][1]); // 2.������ Ž��
    System.out.print((char) (now + 'A')); // 2.���� ����
  }
}