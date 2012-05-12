package edu.bridgeport.mohammad;

public class StupidQueue {
	private int[] data = new int[5];
	private int front = 0;
	private int rear  = 0;
	
	// enqueue, deque, size, toString
	public boolean enqueue(int data) {
		if(!isFull()){
			this.data[rear] = data;
			rear = next_index_from(rear);
			return true;
		}
		return false;
	}
	
	public int dequeue() {
		if(!isEmpty()) {
			int temp = data[front];
			front = next_index_from(front);
			return temp;
		}
		return -1;
	}
	
	public boolean isEmpty() {
		return front == rear;
	}
	
	public boolean isFull() {
		return next_index_from(rear) == front;
	}
	
	public int look(){
		return data[front];
	}
	
	public int next_index_from(int i) {
		return (i + 1) % data.length;
	}
}
