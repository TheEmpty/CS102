package edu.bridgeport.mohammad;
public class Stack <T> {
	private Node<T> topNode;
	
	public Stack(){
	}
	
	public Stack(T[] objs) {
		for(T object : objs) {
			if(object != null) push(object);
		}
	}
	
	public boolean push(T obj) {
		Node<T> node = new Node<T>(obj);
		
		if(topNode != null) node.setNext(topNode);
		topNode = node;
		return true;
	}
	
	public T pop() {
		Node<T> node = topNode;
		topNode = node.getNext(); // will return null if none, which is what we want	
		return node.getData();
	}
	
	public T top() {
		return topNode.getData();
	}
	
	public void reset() {
		topNode = null; // Since this object will be deleted by garbage collector, children will be too.
	}
	
	public boolean isEmpty() {
		return topNode == null;
	}
	
	public boolean isFull() {
		return false;
	}
}