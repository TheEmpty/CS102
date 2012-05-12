package edu.bridgeport.mohammad;

public class LIFO <T> {
	private T[] elements;
	int insert_index = 0;
	
	public LIFO(){
		constructElementArray(10);
	}
	
	public LIFO(T[] objs) {
		this.elements = objs;
		
		// find first null index
		for(int i = 0; i < objs.length; i++) {
			if(objs[i] == null) {
				break;
			} else {
				insert_index++;
			}
		}
	}
	
	public LIFO(int size) {
		constructElementArray(size);
	}
	
	private void constructElementArray(int size) {
		elements = (T[]) new Object[size];
		insert_index = 0;
	}
	
	public boolean push(T obj) {
		if(insert_index >= elements.length){
			return false;
		} else {
			elements[insert_index++] = obj;
			return true;
		}
	}
	
	public T pop() {
		if(insert_index > 0) {
			insert_index--;
			T obj = elements[insert_index];
			elements[insert_index] = null;
			return obj;
		} else {
			return null;
		}
	}
	
	public T top() {
		if(insert_index > 0) {
			return elements[insert_index - 1];
		} else {
			return null;
		}
	}
	
	public void reset() {
		constructElementArray(elements.length);
	}
	
	public boolean isEmpty() {
		return insert_index == 0;
	}
	
	public boolean isFull() {
		return insert_index >= elements.length;
	}
	
	public int size() {
		return insert_index;
	}
}
