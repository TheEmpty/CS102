package edu.bridgeport.mohammad.bank;

public class Teller {
	Customer helping = null;
	int startedHelping = 0;
	String name;
	
	public Teller(String name) {
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setHelping(Customer helping, int time) {
		this.helping = helping;
		this.startedHelping = time;
	}
	
	public Customer getHelping() {
		return this.helping;
	}
	
	public int getStartedHelping() {
		return this.startedHelping;
	}
	
	public boolean isAvailable() {
		return helping == null;
	}
}
