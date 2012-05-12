package edu.bridgeport.mohammad.bank;

import java.io.BufferedInputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import com.reliablerabbit.nyancat.NyanCat;

import edu.bridgeport.mohammad.Queue;

public class Application extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;
	private javax.swing.JTextArea display;
	// private final String[] rainbowColors = {"#ee0000","#ff7700","#eeee00","#00bb00","#0000ee","#dd00dd"};
	
	public Application() {
		setTitle("Nyan Bank");
		setSize(800, 550);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		display = new javax.swing.JTextArea();
		display.setBackground(java.awt.Color.black);
        display.setForeground(new java.awt.Color(40, 254, 20));
        display.setEditable(false);
        add(new javax.swing.JScrollPane(display));
        
        setVisible(true);
        requestFocus(true);
        
        // Nyan Cat
        NyanCat nc = new NyanCat();
        nc.setGraphics(display.getGraphics());
        nc.setX(620);
        nc.setY(25);
        new Thread(nc).start();
        
        // Nyan Music
        new Thread() {
        	public void run() {
				try {
					Player player;
					do {
						BufferedInputStream sound = new BufferedInputStream(Application.class.getResourceAsStream("/nyan.mp3"));
						player = new Player(sound);
						player.play();
						while(!player.isComplete());
					} while(true);
				} catch (JavaLayerException e) {
					e.printStackTrace();
				}
        	}
        }.start();
        
	}
	
	public javax.swing.JTextArea getDisplay() {
		return display;
	}
	
	public void display(String text) {
		display.append("   " + text + "\n");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Application gui = new Application();
		gui.display("");
		
		Queue<Customer> outSide = new Queue<Customer>();
		Queue<Customer> inSide = new Queue<Customer>();
		Teller[] tellers = {new Teller("John Went")}; // http://imgur.com/gallery/iWaY1
		int time = 0; // minutes past nine
		
		// Be creepy
		String uname = System.getProperty("user.name");
		char cap = Character.toUpperCase(uname.charAt(0));
		uname = String.valueOf(cap) + uname.substring(1);
		gui.display("Hello " + uname +", welcome to the Bank of CS102-6T1-2012SP.");
		gui.display("Our hours are from nine a.m. to nine p.m.");
		gui.display("");
		
		// Metric variables
		int customersServed = 0;
		int averageWaitTime = 0;
		int currentLineLength = 0;
		int maxLineLength = 0;
		int maxLineLengthTime = 0;
		
		// load file to seed outSide
		Scanner input = new Scanner(Application.class.getResourceAsStream("/customers.txt"));
		ArrayList<Customer> customers = new ArrayList<Customer>();
		while(input.hasNextLine()) {
			/*
			Customer temp = new Customer(input.next(), input.nextInt(), input.nextInt());
			if(input.hasNextLine()) input.nextLine(); // move to end of line
			// works because the text file list customers in order, otherwise we would need to sort them
			outSide.enqueue(temp);
			*/
			int arrivalTime = (int) (System.nanoTime() % (12 * 60)); // twelve hours max 9am-9pm
			int serviceTime = (int) (System.nanoTime() % 14); // max thirteen minutes
			Customer temp = new Customer(input.next(), arrivalTime, serviceTime);
			customers.add(temp);
			if(input.hasNextLine()) input.nextLine(); // move to end of line
		}
		Collections.sort(customers); // order in time of arrival since it was randomly generated
		for(Customer customer : customers) outSide.enqueue(customer);
		
		// tick
		ArrayList<String> actions = new ArrayList<String>();
		boolean done = false;
		
		while(!done) {
			actions.clear();
			
			while(outSide.look() != null && outSide.look().getArrivalTime() <= time) {
				Customer walkingIn = outSide.dequeue();
				actions.add(walkingIn.getName() + " just got in line.");
				inSide.enqueue(walkingIn);
				currentLineLength++;
			}
			
			for(Teller teller : tellers) {
				if(teller.getHelping() != null) {
					int doneAt = teller.getStartedHelping() + teller.getHelping().getServiceLength();
					if(time >= doneAt) {
						int minutes = (time - teller.getHelping().getArrivalTime());
						actions.add(teller.getHelping().getName() + " has left after " + minutes + " minute(s) in the bank.");
						customersServed++;
						teller.setHelping(null, 0);
					}
				}
				
				if(teller.isAvailable() && !inSide.empty()) {
					Customer next = inSide.dequeue();
					int wait = (time - next.getArrivalTime());
					averageWaitTime += wait; // gets divided later
					actions.add(next.getName() + " just stepped up to teller " + teller.getName() + " after waiting for " + wait + " minute(s).");
					currentLineLength--;
					teller.setHelping(next, time);
				}
			}
			
			// max line length
			if(currentLineLength > maxLineLength) {
				maxLineLength = currentLineLength;
				maxLineLengthTime = time;
			}
			// e/o max line length
			
			// check done
			done = true;
			if(outSide.empty() && inSide.empty()) {
				for(Teller teller : tellers) {
					if(!teller.isAvailable()) {
						done = false;
					}
				}
			} else {
				done = false;
			}
			// e/o check done
			
			if(actions.size() > 0) {
				int hour = 9;
				int minutes = time;
				
				if(minutes >= 60) {
					hour += minutes/60;
					minutes = minutes % 60;
				}
				if(hour > 12) hour = hour % 12;
				
				gui.display("Current time: " + timeStamp(time));
				
				for(int i = 0; actions.size() > i; i++) {
					gui.display(" * " + actions.get(i));
				}
				gui.display("");
				
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			time++;
		}
		// e/o tick
		
		
		// finish metrics
		try {
			averageWaitTime = averageWaitTime / customersServed;
		} catch(java.lang.ArithmeticException ex) {
			// division by zero, zero customers
			averageWaitTime = 0;
		}
		
		int hour = 9;
		int minutes = maxLineLengthTime;
		
		if(minutes >= 60) {
			hour += minutes/60;
			minutes = minutes % 60;
		}
		if(hour > 12) hour = hour % 12;
		
		// display metrics
		gui.display("Customer(s) served: " + customersServed);
		gui.display("Average wait time was about " + averageWaitTime + " minute(s).");
		gui.display("Busiest time was " + timeStamp(maxLineLengthTime) + " when there was " + maxLineLength + " customers in the line.");
		
		if(maxLineLength > 0) {
			gui.display("This queue could have been served faster with a second teller.");
		} else {
			gui.display("This queue only needed one teller.");
		}
		
		gui.display("There were zero robberies during this time interval.");
	}
	
	public static String timeStamp(int time) {
		NumberFormat formatter = new DecimalFormat("00");
		
		StringBuilder build = new StringBuilder();
		int hour = 9;
		int minutes = time;
		
		if(minutes >= 60) {
			hour += minutes/60;
			minutes = minutes % 60;
		}
		if(hour > 12) hour = hour % 12;
		
		build.append(formatter.format(hour) + ":" + formatter.format(minutes));
		return build.toString();
	}

}
