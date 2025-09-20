package queue;

public class CircularQueue {
    private int[] arr;
    private int size;
    private int front;
    private int rear;

    public CircularQueue(int size) {
        this.size = size;
        arr = new int[size];
        front = 0;
        rear = 0;
    }

    public void push(int item){}
    public int pop(){return 0;}
    public boolean isEmpty(){return false;}
}
