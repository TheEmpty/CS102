package edu.bridgeport.mohammad;

import java.util.Scanner;

public class Application {
	public static void main(String[] args) {
		AnAwesomeArray<Item> items = new AnAwesomeArray<Item>();
		Scanner input = new Scanner(System.in);
		int index = 0;
		
		do {
			
			Item temp = new Item();
			System.out.print("Enter an item name: ");
			temp.name = input.nextLine();
			System.out.print("Enter a price: ");
			temp.price = input.nextDouble();
			
			input.nextLine(); // move to end of line
			items.add(temp); // add item to array
			
		} while(items.get(index++).price != -1.00);
		
		// Calculate average and check for world peas
		boolean output_average = false;
		double average = 0;
		
		for(int i = 0; items.size() > i; i++){
			Item temp = items.get(i);
			
			if(temp.price != -1.00) {
				average += temp.price;
				if(i != 0) average /= 2;
			}
			
			if(temp.name.equalsIgnoreCase("peas")) {
				output_average = true;
			}
		}
		
		if(output_average) {
			System.out.println("Average is: " + average);
		} else {
			System.out.println("No average available. Try rerunning and entering in a product named 'peas'.");
		}
	}
}