package edu.bridgeport.mohammad;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Application {
	public static void main(String[] args) throws IOException {
		// Prefix Notation 
		System.out.println("--------Postfix Notation using Stack------------"); 
		FileReader fob = new FileReader("postfix.txt");
		Scanner in = new Scanner(fob); 
		while(in.hasNextLine()) postfix(in.nextLine()); 
		in.close();
		
		// Palindrome 
		System.out.println();
		System.out.println("--------Palindromes using Stack------------"); 
		fob = new FileReader("palindrome.txt"); 
		in = new Scanner(fob); 
		while(in.hasNextLine()) palindrome(in.nextLine()); 
		in.close(); 
		
		// Parenthesis 
		System.out.println();
		System.out.println("------Balanced Parenthesis check using Stack-----"); 
		fob = new FileReader("paren.txt"); 
		in = new Scanner(fob); 
		while(in.hasNextLine()) parenthesis(in.nextLine()); 
		in.close(); 
		
		
		// Reverse 
		System.out.println();
		System.out.println("--------Reverse a string using Stack------------"); 
		fob = new FileReader("reverse.txt"); 
		in = new Scanner(fob); 
		while(in.hasNextLine()) reverse(in.nextLine());
		in.close(); 
	}

	private static void reverse(String line) {
		Stack<String> stack = new Stack<String>();
		Scanner input = new Scanner(line);
		
		System.out.print("Orginal string: ");
		while(input.hasNext()){
			String next = input.next();
			System.out.print(next + " ");
			stack.push(next);
		}
		System.out.println();
		
		System.out.print("Reverse string: ");
		while(!stack.isEmpty()){
			System.out.print(stack.pop() + " ");
		}
		System.out.println();
	}

	private static void parenthesis(String line) {
		Stack<Character> stack = new Stack<Character>();
		
		System.out.print(line + " ");
		
		for( char token : line.toCharArray() ) {
			if(token == '(') {
				stack.push(token);
			} else if(token == ')') {
				if(stack.isEmpty()) {
					System.out.println("has unbalanced parenthesis (found closing with no matching opening parenthesis)");
					return;
				} else {
					stack.pop();
				}
			}
		}
		
		if(stack.isEmpty()) {
			System.out.println("has balanced parenthesis");
		} else {
			System.out.println("has unbalanced parenthesis (missing closing parenthesis)");
		}
	}

	private static void palindrome(String line) {
		int stop = line.length() / 2;
		boolean odd = line.length() % 2 == 1;
		Stack<Character> stack = new Stack<Character>();
		
		for(int i = 0; i < stop; i++) {
			stack.push(line.charAt(i));
		}
		
		System.out.print(line + " ");
		int start = stop;
		if(odd) start++;
		
		for(int i = start; i < line.length(); i++) {
			if(stack.pop() != line.charAt(i)){
				System.out.println("is not a palindrome");
				return;
			}
		}
		
		System.out.println("is a palindrome");
	}

	private static void postfix(String line) {
		Stack<Double> tokens = new Stack<Double>();
		Scanner input = new Scanner(line);
		System.out.print(line);
		
		while(input.hasNextDouble()) {
			tokens.push(input.nextDouble());
		}
		
		while(input.hasNextLine()){
			for(char op : input.nextLine().toCharArray()) {
				if(op != ' '){
					double rightSide;
					double leftSide = rightSide = 0.0;
					boolean noTokens = false;
					
					if(!tokens.isEmpty()){
						rightSide = tokens.pop();
						if(!tokens.isEmpty()){
							leftSide = tokens.pop();
						} else {
							noTokens = true;
						}
					} else {
						noTokens = true;
					}
					
					if(noTokens) {
						System.out.println(" - Invalid postfix notation");
						return;
					}
					
					if(op == '+') {
						tokens.push(leftSide + rightSide);
					} else if(op == '-') {
						tokens.push(leftSide - rightSide);
					} else if(op == '*') {
						tokens.push(leftSide * rightSide);
					} else if(op == '/') {
						tokens.push(leftSide / rightSide);
					}
				}
			}
		}
		
		double last = 0.0;
		if(!tokens.isEmpty()){
			last = tokens.pop();
		}
		
		if(tokens.isEmpty()) {
			System.out.println(" is " + last);
		} else {
			System.out.println(" is invalid");
		}
	}
}