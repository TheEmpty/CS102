package edu.bridgeport.mohammad;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Application {
	// MacOS with homebrew:
	//   brew install sox
	// Uninstall sox with deps:
	//   brew uninstall sox pkg-config libogg libvorbis lame flac libao mad

	/**
	 * location of sox binary, can be found on NIX by `which sox`
	 */
	static final String SOX_BINARY = "/usr/local/bin/sox";
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		// Convert to .dat
		System.out.println("Calling sox to convert to dat");
		Runtime.getRuntime().exec(SOX_BINARY + " secret.wav secret.dat");
		
		// Start pushing to stack
		System.out.println("Loading .dat");
		FileReader data = new FileReader("secret.dat");
		Scanner input = new Scanner(data);
		Stack<String> stack = new Stack<String>();
		// header should be lines that start with ;, though in this case it's the first two
		StringBuilder header = new StringBuilder("");
		while(input.hasNext("/^;/")){
			header.append(input.nextLine() + "\n");
		}
		
		while(input.hasNextLine()) {
			stack.push(input.nextLine());
		}
		
		input.close();
		data.close();
		
		// Create reveresed .dat file
		System.out.println("Reversing .dat");
		FileWriter revealedData = new FileWriter("secret-revealed.dat");
		BufferedWriter output = new BufferedWriter(revealedData);
		
		output.write(header.toString());
		while(!stack.isEmpty()) {
			output.write(stack.pop() + "\n");
		}
		output.close();
		revealedData.close();
		stack = null;
		
		// Convert to .wav
		System.out.println("Calling sox to convert back to wav");
		Runtime.getRuntime().exec(SOX_BINARY + " secret-revealed.dat secret-revealed.wav");
		
		System.out.println("Done!");
		System.out.println("It was inevitable: the scent of bitter almonds always reminded him of the fate of unrequited love. - Gabriel Garc’a M‡rquez");
	}

}
