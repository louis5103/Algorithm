import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;
public class P2164_ī�� {
 public static void main(String[] args) {
 Scanner sc = new Scanner(System.in);
 Queue<Integer> myQueue = new LinkedList<>();
 int N = sc.nextInt();
 for (int i = 1; i <= N; i++) { // ī�带 ť�� �����ϱ�
 myQueue.add(i);
 }
 while (myQueue.size() > 1) { // ī�尡 1�� ���� ������
 myQueue.poll(); // �� ���� ī�带 ����
 myQueue.add(myQueue.poll()); // �� ���� ī�带 ���� �Ʒ� ī�� ������ �̵�
 }
 System.out.println(myQueue.poll()); // ���������� ���� ī�� ���
 }
}