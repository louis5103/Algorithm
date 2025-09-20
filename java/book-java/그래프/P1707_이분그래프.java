import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
public class P1707_�̺б׷��� {
  static ArrayList<Integer>[] A;
  static int[] check;
  static boolean visited[];
  static boolean IsEven;
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    for (int t = 0; t < N; t++) {
      String[] s = br.readLine().split(" ");
      int V = Integer.parseInt(s[0]);
      int E = Integer.parseInt(s[1]);
      A = new ArrayList[V + 1];
      visited = new boolean[V + 1];
      check = new int[V + 1];
      IsEven = true;
      for (int i = 1; i <= V; i++) {
        A[i] = new ArrayList<Integer>();
      }
      for (int i = 0; i < E; i++) { // ���� ����Ʈ�� �׷��� ����
        s = br.readLine().split(" ");
        int Start = Integer.parseInt(s[0]);
        int End = Integer.parseInt(s[1]);
        A[Start].add(End);
        A[End].add(Start);
      }
      for (int i = 1; i <= V; i++) { // �־��� �׷����� �ϳ��� ����Ǿ� �ִٴ� ������ �����Ƿ� ��� �������� ����
        if (IsEven)
          DFS(i);
        else
          break;
      }
      check[1] = 0;
      if (IsEven)
        System.out.println("YES");
      else
        System.out.println("NO");
    }
  }
  public static void DFS(int node) { // DFS����
    visited[node] = true;
    for (int i : A[node]){
      if (!visited[i]) {
        check[i] = (check[node] + 1) % 2; // ������ ������ ���� ������ �ƴϹǷ� ���� ������ �ٸ� �������� ó��
        DFS(i);
      }
      else if (check[node] == check[i]){ // �̹� �湮�� ������ ���� �� ������ ���� �����̸� �̺� �׷����� �ƴ�
        IsEven = false;
      }
    }
  }
}
