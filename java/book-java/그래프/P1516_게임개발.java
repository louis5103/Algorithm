import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class P1516_���Ӱ��� {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    ArrayList<ArrayList<Integer>> A = new ArrayList<>();
    for (int i = 0; i <= N; i++) {
      A.add(new ArrayList<>());
    }
    int[] indegree = new int[N + 1]; // ���������迭
    int[] selfBuild = new int[N + 1]; // �ڱ��ڽ��� ���µ� �ɸ��� �ð�
    for (int i = 1; i <= N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      selfBuild[i] = Integer.parseInt(st.nextToken()); // �ش� �ǹ��� ���� ���� �ð�
      while (true) { // ��������Ʈ �ʱ�ȭ
        int preTemp = Integer.parseInt(st.nextToken());
        if (preTemp == -1)
          break;
        A.get(preTemp).add(i);
        indegree[i]++; // �������� �迭 �ʱ�ȭ
      }
    }
    // ���� ����
    Queue<Integer> queue = new LinkedList<>();
    for (int i = 1; i <= N; i++) {
      if (indegree[i] == 0) {
        queue.offer(i);
      }
    }
    int[] result = new int[N + 1];
    while (!queue.isEmpty()) {
      int now = queue.poll();
      for (int next : A.get(now)) {
        indegree[next]--;
        result[next] = Math.max(result[next], result[now] + selfBuild[now]);
        if (indegree[next] == 0) {
          queue.offer(next);
        }
      }
    }
    for (int i = 1; i <= N; i++) {
      System.out.println(result[i] + selfBuild[i]);
    }
  }
}