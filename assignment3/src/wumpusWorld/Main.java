package wumpusWorld;
/*
 * Authors: Carie Pointer, Hugh Jackovich, Matthew Sagen
 * Date: 11/30/18
 * Programming Assignment 3: Wumpus World
 */
import java.util.ArrayList;
import java.util.Random;

public class Main {
	public static void main(String[] args) {
		//list to hold random wumpus worlds.
		ArrayList<Maze> wumpusWorlds = new ArrayList<Maze>();
		Agent agent = new Agent(1,1);//the agent always starts at square {1,1}
		Maze maze = new Maze(4,4);
		maze.printNodeMatrix();
		//create random wumpus worlds of various size. use Maze as the problem generator.
		/*
		for(int i = 4; i < 10; i++) {
			wumpusWorlds.add(new Maze(i, i));
		}
		for(Maze world : wumpusWorlds) {
			world.printNodeMatrix();
		}
		*/
		
	}
}
