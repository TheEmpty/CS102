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
		adjustArray(copy.array.length);
			
		System.arraycopy( copy.array, 0, array, 0, copy.array.length );
		this.insert_index = copy.insert_index;
	}

	private void adjustArray(int size) {
		if( array == null) array = (T[]) new Object[size];
	}
	
	public boolean add(T element){
		if(insert_index < array.length) {
			array[insert_index++] = element;
			return true;
		} else {
			return false;
		}
	}
	
	public T get(int index){
		return array[index];
	}
	
	public int size() {
		return insert_index;
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
