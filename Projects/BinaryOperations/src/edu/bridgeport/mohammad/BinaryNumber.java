package edu.bridgeport.mohammad;

public class BinaryNumber {
	boolean[] number = new boolean[8];
	
	BinaryNumber(String binary){
		char[] chars = binary.toCharArray();
		for(int i = 0; i < chars.length; i++){
			number[number.length - chars.length + i] = (chars[i] == '1' || chars[i] == 't');
		}
	}
	
	public BinaryNumber add(BinaryNumber other){
		int carryOver = 0;
		BinaryNumber res = new BinaryNumber("0");
		
		for(int i = other.number.length-1; 0 <= i ;i--){
			System.out.println("i is " + i);
			boolean hasAddition = number[i] || other.number[i];
			boolean needsCarry = number[i] && other.number[i];
			System.out.println(number[i] + " + " + other.number[i]);
			if(hasAddition){
				if(needsCarry){
					System.out.println("Needs to carry");
					res.number[i] = false;
					carryOver++;
				} else {
					System.out.println("Add");
					res.number[i] = hasAddition;
				}
			} else if(carryOver > 0) {
				System.out.println("Carry");
				res.number[i] = true;
				carryOver--;
			}
		}
		return res;
	}
	
	public int toInt() {
		int value = 0;
		for(int i = 0; number.length > i; i++){
			if(i == number.length - 1) continue; // left most bit
			if(number[i]) value += Math.pow(2, number.length - i - 1);
		}
		return value;
	}
	
	@Override
	public String toString() {
		StringBuilder value = new StringBuilder(number.length);
		for(boolean flag : number) {
			if(flag) value.append("1");
			else     value.append("0");
		}
		return value.toString();
	}
}
