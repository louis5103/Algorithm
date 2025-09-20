import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
public class P1916_�ּҺ�뱸�ϱ� {
  static int N, M;
  static ArrayList<Node_1916>[] list; // ��������Ʈ�� �׷��� ǥ���ϱ�.
  static int[] dist; // �ִܰŸ� �迭.
  static boolean[] visit; // ��������� Ȯ���ϴ� �迭.
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;
    N = Integer.parseInt(br.readLine());
    M = Integer.parseInt(br.readLine());
    list = new ArrayList[N + 1];
    dist = new int[N + 1];
    visit = new boolean[N + 1];
    Arrays.fill(dist, Integer.MAX_VALUE);
    for (int i = 0; i <= N; i++) {
      list[i] = new ArrayList<Node_1916>();
    }
    for (int i = 0; i < M; i++) { // �־��� �׷����� �������� ��������Ʈ �ڷᱸ���� �ִ� �κ�
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());
      list[start].add(new Node_1916(end, weight));
    }
    st = new StringTokenizer(br.readLine());
    int startIndex = Integer.parseInt(st.nextToken());
    int endIndex = Integer.parseInt(st.nextToken());
    // ���ͽ�Ʈ�� �˰��� ����
    bw.write(dijkstra(startIndex, endIndex) + "\n");
    bw.flush();
    bw.close();
    br.close();
  }
  public static int dijkstra(int start, int end) {   // ���ͽ�Ʈ�� �˰���
    PriorityQueue<Node_1916> pq = new PriorityQueue<>();
    pq.offer(new Node_1916(start, 0));
    dist[start] = 0;
    while (!pq.isEmpty()) {
      Node_1916 nowNode_1916 = pq.poll();
      int now = nowNode_1916.targetNode_1916;
      if (!visit[now]) {
        visit[now] = true;
        for (Node_1916 n : list[now]) { // ���ó�� + ��� < Ÿ�ϳ���� ��� ���� �����ϴ� �κ�
          if (!visit[n.targetNode_1916] && dist[n.targetNode_1916] > dist[now] + n.value) {
            dist[n.targetNode_1916] = dist[now] + n.value;
            pq.add(new Node_1916(n.targetNode_1916, dist[n.targetNode_1916]));
          }
        }
      }
    }
    return dist[end];
  }
}
class Node_1916 implements Comparable<Node_1916> {
  int targetNode_1916;
  int value;

  Node_1916(int targetNode_1916, int value) {
    this.targetNode_1916 = targetNode_1916;
    this.value = value;
  }
  @Override
  public int compareTo(Node_1916 o) {
    return value - o.value;
  }
}
