package Search;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) {
		Maze mediumMaze = new Maze("medium_maze");
		Maze largeMaze = new Maze("large_maze");
		Maze openMaze = new Maze("open_maze");
		mediumMaze.printMaze();
		largeMaze.printMaze();
		openMaze.printMaze();
		
	}
	
	
}
