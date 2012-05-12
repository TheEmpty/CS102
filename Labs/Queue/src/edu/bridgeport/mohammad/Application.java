package edu.bridgeport.mohammad;

public class Application {
	public static void main(String args[]) {
		StupidQueue q = new StupidQueue();
		
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(3);
		
		System.out.println(q.dequeue());
		
		q.enqueue(4);
		
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		
		
		Queue<Integer> queue = new Queue<Integer>();
		
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		queue.enqueue(4);
		queue.enqueue(5);
		
		System.out.println(queue);
		
		while(!queue.empty()) {
			System.out.print(queue.dequeue() + " ");
		}
		
		System.out.println();
		System.out.println(queue);
	}
}
