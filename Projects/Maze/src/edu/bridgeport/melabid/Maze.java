package edu.bridgeport.melabid;

import java.util.Random;

public class Maze {
	private boolean[][] maze;
	private int row_length, column_length;
	private int start, end;
	private final int CORES_AVAILABLE = Runtime.getRuntime().availableProcessors();;
	private int coresUsed = 0;
	
	
	public Maze() {
		this(10,10);
	}
	
	public Maze(int row_length, int column_length) {
		this.row_length = row_length;
		this.column_length = column_length;
		
		maze = new boolean[row_length][column_length];
		randomizeMaze();
	}
	
	public long generateSolvable() {
		long iterations = 0;
		while(!solvable()) {
			randomizeMaze();
			iterations++;
		}
		return iterations;
	}
	
	public boolean solvable() {
		boolean[][] stepped = new boolean[row_length][column_length];
		Stack<String> stepping = new Stack<String>();
		// stepping.push("x,y");
		// stepping.pop().split(",");
		
		stepping.push("0," + start);
		
		// stepping.look();
		// from this position, check left, up, down, right.
		// if this position is a wall, ignore
		// if this position is already checked, ignore
		// otherwise, push it to stepping and move there
		// if there is no space to move, pop and check that position
		
		// get current position
		while(!stepping.isEmpty()) {
			String[] str_position = stepping.top().split(",");
			int x = Integer.parseInt(str_position[0]);
			int y = Integer.parseInt(str_position[1]);
						
			if(x == row_length - 1 && y == end) {
				return true;
			}
			else if(x != 0 && !stepped[x-1][y]) {
				// check left
				step(x - 1, y, stepping, stepped);
			}
			else if(y != 1 && !stepped[x][y-1]) {
				// check up
				step(x, y - 1, stepping, stepped);
			}
			else if(y < column_length - 1 && !stepped[x][y+1]) {
				// check down
				step(x, y + 1, stepping, stepped);
			}
			else if(x < row_length - 1 && !stepped[x+1][y]) {
				// check right
				step(x + 1, y, stepping, stepped);
			} else {
				// Not able to move left, right, up, or down. Go backward.
				stepping.pop();
			}
		}
		
		return false;
	}
	
	private void step(int x, int y, Stack<String> stepping, boolean[][] stepped) {
		if(!maze[x][y]) stepping.push(x+","+y);
		stepped[x][y] = true;
	}
	
	public String toString() {
		StringBuilder build = new StringBuilder();

		for(int i = 0; column_length > i; i++) {
			for(int j = 0; row_length > j; j++) {
				if(maze[j][i]) {
					build.append('#'); // wall
				} else if(j == 0 && i == start) {
					build.append('S'); // South
				} else if(i == end && j == row_length - 1 ) {
					build.append('E'); // East ..., or end
				} else {
					build.append('.'); // free
				}
			}
			build.append("\n");
		}
		
		return build.toString();
	}
	
	public void randomizeMaze() {
		Random rand = new Random();
		
		for(int i = 0; maze.length > i; i++) {
			if(i == 0 || i == maze.length - 1) { // left or right
				for(int j = 0; maze[i].length > j; j++) {
					maze[i][j] = true;
				}
			} else {
				maze[i][0] = true; // top
				for(int j = 1; maze[i].length > j + 1; j++) maze[i][j] = rand.nextBoolean();
				maze[i][column_length - 1] = true; // bottom
			}
		}
		
		// pick start point, end point
		start = rand.nextInt(column_length - 2) + 1;
		end   = rand.nextInt(column_length - 2) + 1;
		maze[0][start] = false;
		maze[row_length - 1][end] = false;
	}
}
