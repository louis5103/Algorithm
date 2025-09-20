import java.util.*;
public class P18352_Ư���Ÿ�����ã�� {
  static int visited[];
  static ArrayList<Integer>[] A;
  static int N,M,K,X;
  static List<Integer> answer;
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    N = scan.nextInt(); // ������ ��
    M = scan.nextInt(); // ������ ��
    K = scan.nextInt(); // ������ ��
    X = scan.nextInt(); // ������ ��
    A = new ArrayList[N + 1];
    answer = new ArrayList<>();
    for (int i = 1; i <= N; i++) {
      A[i] = new ArrayList<Integer>();
    }
    for (int i = 0; i < M; i++) {
      int S = scan.nextInt();
      int E = scan.nextInt();
      A[S].add(E);
    }
    visited = new int[N + 1];  //�湮 �迭 �ʱ�ȭ
    for(int i=0;i<=N;i++){
      visited[i]=-1;
    }
    BFS(X);
    for(int i=0;i<=N;i++){
      if(visited[i]==K) {
          answer.add(i);
      }
    }
    if(answer.isEmpty()){
      System.out.println("-1");
    }
    else{
      Collections.sort(answer);
      for(int temp:answer){
       System.out.println(temp);
      }
    }
  }
  // BFS����
  private static void BFS(int node) {  
    Queue<Integer> queue = new LinkedList<Integer>();
    queue.add(node);
    visited[node]++;
    while (!queue.isEmpty()) {
      int now_node = queue.poll();
      for (int i : A[now_node]) {
        if (visited[i]==-1) {
          visited[i] = visited[now_node]+1;
          queue.add(i);
        }
      }
    }
  }
}
