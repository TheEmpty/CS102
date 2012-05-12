package edu.bridgeport.mohammad;

public class AnAwesomeArray <T> {
	T[] array;
	int insert_index;
	
	public AnAwesomeArray(){
		adjustArray(10);
	}
	
	public AnAwesomeArray(int size){
		adjustArray(size);
	}
	
	public AnAwesomeArray(AnAwesomeArray<T> copy){
		array =  (T[]) new Object[copy.array.length];	
		System.arraycopy( copy.array, 0, array, 0, copy.array.length );
		this.insert_index = copy.insert_index;
	}
	
	private void adjustArray() {
		if( array == null ){
			adjustArray(10);
		} else {
			adjustArray(array.length/4);
		}
	}

	private void adjustArray(int size) {
		if( array == null) {
			array = (T[]) new Object[size];
		} else {
			T[] new_array = (T[]) new Object[array.length + size];
			System.arraycopy(array, 0, new_array, 0, array.length);
			array = new_array;
		}
	}
	
	public boolean add(T element){
		if(insert_index < array.length) {
			array[insert_index++] = element;
			return true;
		} else {
			adjustArray();
			add(element); // retry insertion
			return false;
		}
	}
	
	public int find(T element){
		for(int i = 0; i < insert_index; i++){
			if(array[i] != null){
				if(array[i].equals(element)){
					return i;
				}
			}
		}
		return -1;
	}
	
	public boolean delete(T element){
		int shift = find(element);
		if(shift < 0) return false;
		
		while(shift < insert_index){
			array[shift] = array[shift+1];
			shift++;
		}
		insert_index--;
		array[insert_index] = null;
		
		return true;
	}
	
	public Integer size() {
		return insert_index;
	}

	public T get(Integer index) {
		return array[index];
	}
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		
        for( T element : array ){
        	if(element != null){
        		builder.append(element.toString()).append(", ");
        	}
        }
        
        if( builder.length() >= 2 ) builder.delete(builder.length() - 2, builder.length());
        return builder.toString();
	}
}
