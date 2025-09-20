import java.util.*;
import java.io.*;
public class P17298_��ū�� {
  public static void main(String[] args) throws IOException {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(bf.readLine());
    int[]A = new int[n];    // ���� �迭 ����
    int[]ans = new int[n]; // ���� �迭 ����
    String[] str = bf.readLine().split(" ");
    for (int i = 0; i < n; i++) {
        A[i] = Integer.parseInt(str[i]);
    }
    Stack<Integer> myStack = new Stack<>();
    myStack.push(0); // ó������ �׻� ������ ��������Ƿ� ���� ���� push�Ͽ� �ʱ�ȭ
    for (int i = 1; i < n; i++) {
        //���� ������� �ʰ� ���� ������ ���� TOP�ε��� ����Ű�� �������� ũ��
        while (!myStack.isEmpty() && A[myStack.peek()] < A[i]) {  
            ans[myStack.pop()] = A[i];  //���� �迭�� ��ū���� ���� ������ �����ϱ�
        }
        myStack.push(i); //�űԵ����� push  
    }
    while (!myStack.empty()) {
        // �ݺ����� �� ���� ���Դµ� ������ ������� �ʴٸ� �� �� ����
        ans[myStack.pop()] = -1;
        // stack�� ���� index�� -1�� �ְ�
    }
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    for (int i = 0; i < n; i++) {
        bw.write(ans[i] + " ");
        // ����Ѵ�
    }
    bw.write("\n");
    bw.flush();
  }
}


