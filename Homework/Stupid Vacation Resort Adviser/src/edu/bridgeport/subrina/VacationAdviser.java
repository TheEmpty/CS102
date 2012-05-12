package edu.bridgeport.subrina;

import java.util.Scanner;
import edu.bridgeport.mohammad.AnAwesomeArray;;
/**
* Simple program that utilizes Java's built-in ArrayList class
* User: Subrina
* Date: 2/8/12
* Time: 8:11 PM
*/
// Modified 2/10/12 by Mohammad El-Abid to use AnAwesomeArray instead of ArrayList. Also declares vars at top.

public class VacationAdviser {
	public static void main(String[] args) {
		AnAwesomeArray<String> vacationSpots = new AnAwesomeArray<String>();
		Scanner userInput = new Scanner(System.in);
		String name;
		Integer nameLength, vacationIndex;
		
		vacationSpots.add("Italian Riviera");
		vacationSpots.add("Jersey Shore");
		vacationSpots.add("Puerto Rico");
		vacationSpots.add("Los Cabos Corridor");
		vacationSpots.add("Lubmin");
		vacationSpots.add("Coney Island");
		vacationSpots.add("Karlovy Vary");
		vacationSpots.add("Bourbon-l'Archambault");
		vacationSpots.add("Walt Disney World Resort");
		vacationSpots.add("Barbados");
		vacationSpots.add("Riu Palace - Riviera Maya");
		
		System.out.println("Stupid Vacation Resort Adviser");
		System.out.print("Enter your name: ");
		
		name = userInput.nextLine();
		nameLength = name.length();
		
		if (nameLength == 0) {
			System.out.println("empty name entered");
			return; // System.exit(1) ?
		}
		
		vacationIndex = nameLength % vacationSpots.size();
		
		System.out.println(
				new StringBuilder().
				append("\nYour name is ").
				append(name).
				append(", its length is ").
				append(nameLength).
				append(" characters,\n").append("that's why we suggest you to go to ").
				append(vacationSpots.get(vacationIndex)).toString());
	}
}