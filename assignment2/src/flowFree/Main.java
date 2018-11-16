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
		Maze maze1 = new Maze("5x5maze");
		Maze maze2 = new Maze("7x7maze");
		Maze maze3 = new Maze("8x8maze");
		Maze maze4 = new Maze("9x9maze");
		Maze maze5 = new Maze("10x10maze");
		//Maze maze6 = new Maze("12x12maze");
		
		runDumbSearch(maze1, writer);
		runDumbSearch(maze2, writer);
		runDumbSearch(maze3, writer);
		runDumbSearch(maze4, writer);
		runDumbSearch(maze5, writer);
		//runDumbSearch(maze6, writer);
		
		runSmartSearch(maze1,writer);
		runSmartSearch(maze2,writer);
		runSmartSearch(maze3,writer);
		runSmartSearch(maze4,writer);
		//runSmartSearch(maze5,writer);
		//runSmartSearch(maze6,writer);
		
		writer.close();
	}
	
	// Method for calling and running the DumbBackTracking implementation. Prints output to Output.txt
	public static void runDumbSearch(Maze maze, PrintWriter writer) {
		writer.println("============================");
		writer.println("Dumb Backtracking: " + maze.getMazeType());
		writer.println("----------------------------");
		DumbBackTracking dbts = new DumbBackTracking(maze);
		writer.println("Original:\n");
		printAssignment(maze.getNodeMatrix(), writer);
		dbts.backtrack();
		writer.println("\nSolution:\n");
		printAssignment(maze.getNodeMatrix(), writer);
		writer.println("\nStats:");
		
		// Print stats for the search (execution time and number of nodes colored)
		for (String s : dbts.getStats()) {
			writer.println(s);
		}
	}
	
	public static void runSmartSearch(Maze maze, PrintWriter writer) {
		writer.println("============================");
		writer.println("Smart Backtracking: " + maze.getMazeType());
		writer.println("----------------------------");
		writer.println("Original:\n");
		printAssignment(maze.getNodeMatrix(), writer);
		CSP csp = new CSP(maze);
		writer.println("\nSolution:\n");
		printAssignment(maze.getNodeMatrix(), writer);
		writer.println("\nStats:");
		
		// Print stats for the search (execution time and number of nodes colored)
		for (String s : csp.getStats()) {
			writer.println(s);
		}
	}
	// Helper method used for printing the node matrix
	public static void printAssignment(Node[][] nodes, PrintWriter writer) {
		for(int i = 0; i < nodes.length; i++) {
			for(int j = 0; j < nodes[0].length; j++) {
				writer.print(nodes[i][j].getValue());
			}
			writer.println();
		}		
	}
}
