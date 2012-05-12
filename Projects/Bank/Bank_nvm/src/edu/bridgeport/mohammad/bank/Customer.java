package edu.bridgeport.mohammad.bank;

public class Customer implements Comparable<Customer> {
	private String name;
	private int arrivalTime, serviceLength;
	
	public Customer(String name, int arrivalTime, int serviceLength) {
		this.setName(name);
		this.setArrivalTime(arrivalTime);
		this.setServiceLength(serviceLength);
	}
	
	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getServiceLength() {
		return serviceLength;
	}

	public void setServiceLength(int serviceLength) {
		this.serviceLength = serviceLength;
	}

	@Override
	public String toString() { return getName(); }
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int compareTo(Customer c2) {
		if(arrivalTime > c2.getArrivalTime()) {
			return 1;
		} else if(arrivalTime == c2.getArrivalTime()) {
			return 0;
		} else {
			return -1;
		}
	}
}
