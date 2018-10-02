package Search;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		Maze mediumMaze = new Maze("medium_maze");
		Maze largeMaze = new Maze("large_maze");
		Maze openMaze = new Maze("open_maze");
		mediumMaze.printMaze();
		largeMaze.printMaze();
		openMaze.printMaze();
		PrintWriter writer = new PrintWriter("Output.txt", "UTF-8"); //outputs to directory Assignment1
		printResults(writer);
		writer.close();
	}
	public static void printResults(PrintWriter writer) {
		writer.print("Some results...");
	}
	
}
