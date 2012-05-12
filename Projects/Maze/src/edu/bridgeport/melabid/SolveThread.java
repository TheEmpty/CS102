package edu.bridgeport.melabid;

public class SolveThread extends Thread {
	private Maze2 maze;
	private long elapse;
	private long iterations;
	private boolean finished = false;
	
	public SolveThread(int width, int height) {
		maze = new Maze2(width, height);
	}
	
	public void run() {
		long start = System.currentTimeMillis();
		iterations = maze.generateSolvable();
		long end = System.currentTimeMillis();
		elapse = end-start;
		finished = true;
	}
	
	public boolean isFinished() {
		return finished;
	}
	
	public Maze2 getMaze() {
		return maze;
	}

	public long getIterations() {
		return iterations;
	}

	public long getElapse() {
		return elapse;
	}
}
