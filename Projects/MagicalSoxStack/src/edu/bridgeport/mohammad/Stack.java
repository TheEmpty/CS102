package edu.bridgeport.mohammad;
public class Stack <T> {
	private T[] elements;
	int insert_index = 0;
	
	public Stack(){
		constructElementArray(10);
	}
	
	public Stack(T[] objs) {
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
	
	public Stack(int size) {
		constructElementArray(size);
	}
	
	@SuppressWarnings("unchecked")
	private void constructElementArray(int size) {
		elements = (T[]) new Object[size];
		insert_index = 0;
	}

	@SuppressWarnings("unchecked")
	private void addElementLength(int addLength) {
		T[] new_elements = (T[]) new Object[elements.length + addLength];
		System.arraycopy(elements, 0, new_elements, 0, elements.length);
		elements = new_elements;
	}
	
	public boolean push(T obj) {
		if(insert_index >= elements.length){
			addElementLength(15);
		}
		elements[insert_index++] = obj;
		return true;
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
