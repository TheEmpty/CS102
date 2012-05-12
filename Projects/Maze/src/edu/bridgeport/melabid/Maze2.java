package edu.bridgeport.melabid;

import java.util.Random;

public class Maze2 {
	private char[][] maze;
	private int row_length, column_length;
	private int start, end;
	public final static char WALL = '|';
	public final static char OPEN = ' ';
	public final static char CHECKED = ',';
	public final static char START = 'S';
	public final static char END = 'E';
	public final static char WALKED = 'X';

	public Maze2() {
		this(10,10);
	}

	public Maze2(int row_length, int column_length) {
		this.row_length = row_length;
		this.column_length = column_length;

		maze = new char[row_length][column_length];
		randomizeMaze();
	}

	// Testing purposes
	public Maze2(char[][] map) {
		this.maze = map;
		this.row_length = map.length;
		this.column_length = row_length > 0 ? map[0].length : 0;
	}

	public boolean solvable() {
		return mazeTraversal(false);
	}

	public boolean mazeTraversal(boolean verbal) {
		// Reset the maze
		for(int i = 0; column_length > i; i++) {
			for(int j = 0; row_length > j; j++) {
				if(maze[j][i] == WALKED) {
					maze[j][i] = OPEN;
				}
			}
		}
		// Solve
		boolean result = mazeTraversal(0, start, verbal);
		// side effect is that START becomes CHECKED, so reset it here
		maze[0][start] = START;
		return result;
	}

	private boolean mazeTraversal(int x, int y, boolean verbal) {

		if(maze[x][y] == END) {
			return true;
		} else if(maze[x][y] == WALL) {
			return false;
		} else if(maze[x][y] == CHECKED || maze[x][y] == WALKED) {
			return false;
		// This breaks because we are starting at start, thus returning false and BANG!
		//} else if(maze[x][y] == START) {
		//	return false;
		} else {
			maze[x][y] = CHECKED;
			if(verbal) {
				maze[x][y] = WALKED;
				System.out.println(toString());
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		// END is to the south-east, so check down and right first
		// Go left after checking all other possibilities since it's most likely backtracing

		if(x + 1 < row_length) {
			// right
			if(mazeTraversal( x + 1, y, verbal)) {
				maze[x][y] = WALKED;
				return true;
			}
		}

		if(y + 1 < column_length) {
			// down
			if(mazeTraversal(x, y + 1, verbal)) {
				maze[x][y] = WALKED;
				return true;
			}
		}

		if(y - 1 >= 0) {
			// up
			if(mazeTraversal(x, y - 1, verbal)) {
				maze[x][y] = WALKED;
				return true;
			}
		}

		if(x - 1 >= 0) {
			// left
			if(mazeTraversal(x - 1, y, verbal)) {
				maze[x][y] = WALKED;
				return true;
			}
		}

		return false;
	}

	public void randomizeMaze() {
		Random rand = new Random();

		for(int i = 0; maze.length > i; i++) {
			if(i == 0 || i == maze.length - 1) { // left or right
				for(int j = 0; maze[i].length > j; j++) {
					maze[i][j] = WALL;
				}
			} else {
				maze[i][0] = WALL; // top
				for(int j = 1; maze[i].length > j + 1; j++) maze[i][j] = rand.nextBoolean() ? WALL : OPEN;
				maze[i][column_length - 1] = WALL; // bottom
			}
		}

		// pick start point in the first quarter of the maze
		start = rand.nextInt((column_length - 2)/4) + 1;

		// pick an end point in the last quarter of the maze
		end = rand.nextInt((column_length - 2)/4) + 1 + (column_length * 3/4);

		maze[0][start] = START;
		maze[row_length - 1][end] = END;
	}

	public long generateSolvable() {
		long iterations = 0;
		while(!mazeTraversal(false)) {
			randomizeMaze();
			iterations++;
		}
		return iterations;
	}

	public String toString() {
		StringBuilder build = new StringBuilder();

		for(int i = 0; column_length > i; i++) {
			for(int j = 0; row_length > j; j++) {
				build.append(maze[j][i]);
			}
			build.append("\n");
		}

		return build.toString();
	}
}
