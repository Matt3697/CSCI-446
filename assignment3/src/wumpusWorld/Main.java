package wumpusWorld;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
/*
 * Authors: Carie Pointer, Hugh Jackovich, Matthew Sagen
 * Date: 11/30/18
 * Programming Assignment 3: Wumpus World
 */


public class Main {
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		//list to hold random wumpus worlds.
		PrintWriter writer = new PrintWriter("Output.txt", "UTF-8"); //outputs to directory Assignment1
		Agent agent = new Agent(0,0);//the agent always starts at square {1,1}
		Maze maze1 = new Maze(4,4,agent);
		
		//Maze maze2 = new Maze(5,5, agent);
		//Maze maze3 = new Maze(8,8);
		//Maze maze4 = new Maze(10,10);
		maze1.printNodeMatrix();
		//maze1.printNodeMatrix();
		//maze2.printNodeMatrix();
		//maze3.printNodeMatrix();
		//maze4.printNodeMatrix();
		
		
	}
}
