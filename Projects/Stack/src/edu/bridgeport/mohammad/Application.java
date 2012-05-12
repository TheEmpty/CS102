package edu.bridgeport.mohammad;

public class Application {
	public static void main(String args[]) {
		LIFO<String> lifo_stack = new LIFO<String>();
		lifo_stack.push("Hello");
		lifo_stack.push("World");
		lifo_stack.push("Hello, world.");
		
		System.out.println(lifo_stack.top());
		while(lifo_stack.isEmpty() == false) {
			System.out.println(lifo_stack.pop());
		}
		
		lifo_stack.push("Goodbye, world!");
		System.out.println(lifo_stack.top());
		lifo_stack.reset();
		if(lifo_stack.isEmpty()){
			System.out.println("Successfully reset stack.");
			System.out.println("Top element is: " + lifo_stack.top());
		}
		
		lifo_stack = new LIFO<String>( new String[] {"One", "Two", "Three", null} );
		System.out.println("Stack of existing array of three and on null has size of: " + lifo_stack.size());
		lifo_stack.push("Four");
		System.out.println("The fourth element is now: " + lifo_stack.top());
	}
}
