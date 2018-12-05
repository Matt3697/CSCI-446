package wumpusWorld;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
/*
 * Authors: Carie Pointer, Hugh Jackovich, Matthew Sagen
 * Date: 11/30/18
 * Programming Assignment 3: Wumpus World
 * Output file located in: assignment3/output.txt
 */


public class Main {
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter("Output.txt", "UTF-8"); //outputs to directory Assignment3
		Agent agent = new Agent(0,0);//the agent always starts at square [0,0]
		Agent agent1 = new Agent(0,0);
		Agent agent2 = new Agent(0,0);
		Agent agent3 = new Agent(0,0);
		
		Wumpus w = new Wumpus(4);
		Wumpus w1 = new Wumpus(5);
		Wumpus w2 = new Wumpus(8);
		Wumpus w3 = new Wumpus(10);
		
		Maze maze = new Maze(4, 4, agent, w);
		Maze maze1 = new Maze(5, 5, agent1, w1);
		Maze maze2 = new Maze(8,8, agent2, w2);
		Maze maze3 = new Maze(10,10, agent3, w3);
		
		WumpusSolver wumpus = new WumpusSolver(maze, agent, w, writer);
		wumpus.solveMaze();
		writer.println();

		WumpusSolver wumpus1 = new WumpusSolver(maze1, agent1, w1, writer);
		wumpus1.solveMaze();
		writer.println();

		WumpusSolver wumpus2 = new WumpusSolver(maze2, agent2, w2, writer);
		wumpus2.solveMaze();
		writer.println();

		WumpusSolver wumpus3 = new WumpusSolver(maze3, agent3, w3, writer);
		wumpus3.solveMaze();
		writer.println();
		
		writer.close();
		
	}
}
