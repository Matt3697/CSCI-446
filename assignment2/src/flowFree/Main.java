package flowFree;
/*
 * Authors: Carie Pointer, Hugh Jackovich, Matthew Sagen
 * Date:    11/13/18
 * Artificial Intelligence: Flow Free
 */

public class Main {

	public static void main(String[] args) {
		Maze maze = new Maze("5x5maze");
		/*Maze maze1 = new Maze("7x7maze");
		Maze maze2 = new Maze("8x8maze");
		Maze maze3 = new Maze("9x9maze");
		Maze maze4 = new Maze("10x10maze");
		Maze maze5 = new Maze("12x12maze");
		maze1.printMaze();
		maze2.printMaze();
		maze3.printMaze();
		maze4.printMaze();
		maze5.printMaze();
		*/
		maze.printMaze();
		DumbBackTracking dbts = new DumbBackTracking(maze);
		//dbts.backTrackSearch();
		dbts.backtrack2();
		maze.printNodeMatrix();
	}
}
