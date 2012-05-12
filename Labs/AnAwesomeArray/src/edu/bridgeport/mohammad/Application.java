package edu.bridgeport.mohammad;

public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AnAwesomeArray<Integer> arr = new AnAwesomeArray<Integer>();
		System.out.println("Added 5? " + arr.add(5));
		System.out.println("Added 9? " + arr.add(9));
		System.out.println("Added 9? " + arr.add(0));
		System.out.println("Has 5? " + arr.find(5));
		System.out.println("Has 1337? " + arr.find(1337));
		System.out.println(arr);
		System.out.println("Delete 3? " + arr.delete(3));
		System.out.println("Delete 5? " + arr.delete(5));
		System.out.println(arr);
		
		AnAwesomeArray<Integer> ar2 = new AnAwesomeArray<Integer>(arr);
		System.out.println("ar2 (Integer deep copy) is " + ar2);
		ar2.delete(9);
		System.out.println("arr(" + arr + ") is not (" + ar2 + ")");
	}

}
