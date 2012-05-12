package edu.bridgeport.melabid;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Application {
	
	public static void main(String[] args) {
		mainThreadedMazes(args);
	}
	
	public static void mainThreadedMazes(String[] args) {
		int width = 50;
		int height = 10;
		int threadCount = Runtime.getRuntime().availableProcessors();
		
		System.out.println("Generating a solvable " + width + " by " + height + " maze (" + (width * height) + " tiles).");
		System.out.println("Spawning " + threadCount + " threads. (Warning: IDEs 'stop' functon may orphan the threads)");
		
		SolveThread[] threads = new SolveThread[threadCount];
		
		for(int i = 0; threadCount > i; i++) {
			threads[i] = new SolveThread(width, height);
			threads[i].start();
		}
		
		System.out.println("Please wait...");
		
		int solved = -1;
		
		while(solved == -1) {
			for(int i = 0; threadCount > i; i++) {
				if(threads[i].isFinished()) {
					solved = i;
					break;
				}
			}
		}
		
		System.out.println("Solved by thread #" + (solved + 1));
		
		long iterations = 0;
		
		for(SolveThread thread : threads) {
			thread.stop(); // close threads (use stop instead of interrupt because it orphans)
			iterations += thread.getIterations();
		}
		
		
		long elapse = threads[solved].getElapse();
		Maze2 maze = threads[solved].getMaze();
		
		String timeFormatted = (new DecimalFormat("###,##0.00")).format(elapse/1000.0);
		String mazes = (new DecimalFormat("###,###")).format(iterations);
		
		System.out.println("Generated " + mazes + " mazes in " + timeFormatted + " seconds.");
		
		// number of bad moves:
		String mazeSolution = maze.toString();
		int badMoves = mazeSolution.replaceAll("[^" + Maze2.CHECKED + "]", "").length();
		String badMovesFormatted = new DecimalFormat("###,##0").format(badMoves);
		System.out.println(badMovesFormatted + " bad moves.");
				
		System.out.println();
		
		mazeSolution = mazeSolution.replaceAll(String.valueOf(Maze2.CHECKED), String.valueOf(Maze2.OPEN));
		System.out.println(mazeSolution);

		System.out.println("Display animated solution? (y/n)");
		Scanner in = new Scanner(System.in);
		String res = in.next();
		
		if(res.equalsIgnoreCase("y") || res.equalsIgnoreCase("yes")) {
			maze.mazeTraversal(true);
			System.out.println(maze);
		}
	}
	
	public static void maze2() {
		int width = 40;
		int height = 10;
		Maze2 maze = new Maze2(width, height);
		System.out.println("Generating a solvable " + width + " by " + height + " maze (" + (width * height) + " tiles).");
		System.out.println("Please wait...");
		System.out.println();
		
		long start = System.currentTimeMillis();
		long iterations = maze.generateSolvable();
		long end = System.currentTimeMillis();
		
		String timeFormatted = (new DecimalFormat("###,##0.00")).format((end - start)/1000.0);
		String mazes = (new DecimalFormat("###,###")).format(iterations);
		
		System.out.println("Generated " + mazes + " mazes in " + timeFormatted + " seconds.");
		System.out.println();
		
		String mazeSolution = maze.toString();
		mazeSolution = mazeSolution.replaceAll(String.valueOf(Maze2.CHECKED), String.valueOf(Maze2.OPEN));
		System.out.println(mazeSolution);
	}
	
	public static void maze1() {
		int width = 40;
		int height = 10;
		Maze maze = new Maze(width, height);
		System.out.println("Generating a solvable " + width + " by " + height + " maze.");
		System.out.println("Please wait...");
		
		long start = System.currentTimeMillis();
		long iterations = maze.generateSolvable();
		long end = System.currentTimeMillis();
		
		String timeFormatted = (new DecimalFormat("###,##0.00")).format((end - start)/1000.0);
		String mazes = (new DecimalFormat("###,###")).format(iterations);
		
		System.out.println("Generated " + mazes + " mazes in " + timeFormatted + " seconds.");
		System.out.println();
		System.out.println(maze);
	}
	

	/*
	char[][] map = {
				{Maze2.START, Maze2.OPEN, Maze2.WALL, Maze2.WALL},
				{Maze2.WALL,  Maze2.OPEN, Maze2.WALL, Maze2.WALL},
				{Maze2.WALL,  Maze2.OPEN, Maze2.OPEN, Maze2.OPEN},
				{Maze2.WALL,  Maze2.WALL, Maze2.WALL, Maze2.OPEN},
				{Maze2.OPEN,  Maze2.OPEN, Maze2.OPEN, Maze2.OPEN},
				{Maze2.OPEN,  Maze2.WALL, Maze2.WALL, Maze2.WALL},
				{Maze2.OPEN,  Maze2.WALL, Maze2.WALL, Maze2.WALL},
				{Maze2.END,   Maze2.WALL, Maze2.WALL, Maze2.WALL},
			};
	Maze2 test = new Maze2(map);
	test.mazeTraversal();
	System.out.println(test);
	System.exit(0);
	*/
}
