package flowFree;
/*
 * Authors: Carie Pointer, Hugh Jackovich, Matthew Sagen
 * Date:    11/13/18
 * Artificial Intelligence: Assignment 2
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter("Output.txt", "UTF-8"); //outputs to directory Assignment1
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
		Node[][] nodes = maze.getNodeMatrix();
		writer.println("Dumb Backtracking: " + maze.getMazeType());
		writer.println("----------------------------");
		for(int i = 0; i < nodes.length; i++) {//prints the dumb backtracking solution maze to the output file for 5x5 maze
			for(int j = 0; j < nodes[0].length; j++) {
				writer.print(nodes[i][j].getValue());
			}
			writer.println();
		}
		writer.println();
		writer.println("Smart Backtracking: " + maze.getMazeType());
		writer.println("----------------------------");
		/*for(int i = 0; i < nodes.length; i++) {//prints the smart backtracking solution maze to the output file for 5x5 maze
			for(int j = 0; j < nodes[0].length; j++) {
				writer.print(nodes[i][j].getValue());
			}
			writer.println();
		}*/
		
		writer.close();
	}
}
