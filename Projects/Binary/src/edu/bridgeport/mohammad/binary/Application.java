package edu.bridgeport.mohammad.binary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Application {
	private final static String twosComplimentForm = "  %8s   2's complement     %4d";
	private final static String biasedNotationForm = "+ %8s   biased notation  + %4d";
	private final static String resultForm         = "  %-9s  2's complement     %4d";
	
	public static void main(String[] args) throws FileNotFoundException{		
		Scanner input = new Scanner(new FileInputStream("binary-input.txt"));
		
		while(input.hasNextLine()) {
			Byte twosCompliment = new Byte(input.next());
			Byte biasedNotation = new Byte(input.next());
			Byte biasedToCompliment = biasedNotation.biasedToTwosCompliment();
			Byte result = biasedToCompliment.add(twosCompliment);

			System.out.println(String.format(twosComplimentForm, twosCompliment.toString(), twosCompliment.magnitude()));
			System.out.println(String.format(biasedNotationForm, biasedNotation.toString(), biasedToCompliment.magnitude()));
			System.out.println("  --------                      ----");
			System.out.println(String.format(resultForm, result.toString(), result.magnitude()));
			System.out.println();
		}
	}
}
