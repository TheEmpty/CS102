package edu.bridgeport.mohammad;

public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinaryNumber num1 = new BinaryNumber("010110"); // 22
		System.out.println(num1 + " is " + num1.toInt());
		BinaryNumber num2 = new BinaryNumber("1010"); // 10
		System.out.println(num2 + " is " + num2.toInt());
		BinaryNumber num3 = num1.add(num2);
		System.out.println(num3 + " is " + num3.toInt()); // 32
	}

}
