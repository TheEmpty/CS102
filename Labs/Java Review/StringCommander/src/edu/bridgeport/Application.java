package edu.bridgeport;

import java.util.Scanner;

public class Application {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Please enter a sentence: ");
		
		String line = ""; // Stop the "may not be initialized" error
		try {
			line = input.nextLine();
		} catch(Exception e) {
			System.out.println();
			System.out.println(e);
			System.exit(1);
		}
		StringBuilder modify = new StringBuilder(line);
		
		System.out.println("Original sentence: " + line);
		
		int uppercases, lowercases, spaces, others;
		uppercases = lowercases = spaces = others = 0;
		
		// Iterate through the string
		for(int i = 0; i < line.length(); i++) {
			int character = (int) line.charAt(i);
			
			if( character >= 65 && character <= 90) {
				uppercases++;
			} else if( character >= 97 && character <= 122) {
				lowercases++;
				if(line.charAt(i-1) == ' ') modify.setCharAt(i, (char)(character - 32));
			}
			else if( character == 32 ) {
				spaces++;
			} else {
				others++;
			}
		}
		
		// Over 9,000 reference
		if(line.toLowerCase().matches("over nine thousand") && line.length() <= 9000 ) {
			System.out.println("That's under nine thousand, learn how to count.");
		} else if( line.length() > 9000 ) {
			System.out.println("IT'S OVER NINE THOUSAND characters.");
		}
		
		// UPPER CASE
		System.out.println("Upper case letters: " + uppercases);
		// LOWER CASE
		System.out.println("Lower case letters: " + lowercases);
		// BLANK SPACES
		System.out.println("Blank spaces: " + spaces);
		// OTHER
		System.out.println("Other characters: " + others);
		// TOTAL
		System.out.println("Grand total: " + line.length());
		// MODIFIED
		System.out.println("Modified sentence: " + modify.toString());
	}

}