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

	
		runDumbSearch(maze1, writer, false);
		runDumbSearch(maze2, writer, false);
		runDumbSearch(maze3, writer, false);
		runDumbSearch(maze4, writer, false);
		runDumbSearch(maze5, writer, false);
		//runDumbSearch(maze6, writer);

		Maze maze11 = new Maze("5x5maze");
		Maze maze22 = new Maze("7x7maze");
		Maze maze33 = new Maze("8x8maze");
		Maze maze44 = new Maze("9x9maze");
		Maze maze55 = new Maze("10x10maze");
		//Maze maze66 = new Maze("12x12maze");
	
		runDumbSearch(maze11, writer, true);
		runDumbSearch(maze22, writer, true);
		runDumbSearch(maze33, writer, true);
		runDumbSearch(maze44, writer, true);
		runDumbSearch(maze55, writer, true);
		//runDumbSearch(maze66, writer);
		
		writer.close();
	}
	
	// Method for calling and running the DumbBackTracking implementation. Prints output to Output.txt
	public static void runDumbSearch(Maze maze, PrintWriter writer, boolean isSmart) {
		writer.println("============================");
		if (isSmart)
			writer.println("Smart Backtracking: " + maze.getMazeType());
		else
			writer.println("Dumb Backtracking: " + maze.getMazeType());
		writer.println("----------------------------");
		DumbBackTracking dbts = new DumbBackTracking(maze, isSmart);
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
	// Method for calling and running the smart backtracking implementation. Prints output to Output.txt
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
