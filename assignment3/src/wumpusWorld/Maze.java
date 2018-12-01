package wumpusWorld;
/*
 * Authors: Carie Pointer, Hugh Jackovich, Matthew Sagen
 * Date:    11/30/18
 * Programming Assignment 3: Wumpus World
 * 
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Maze {
	
	public char[][] maze = null;
	public char[][] DFSmaze = null;
	private Node[][] nodeMaze;
	private int length, height, size, upperBound;
	
	public Maze(int length, int height, Agent agent) { //constructor for Maze
		this.length = length;
		this.height = height;
		this.upperBound = length;
		create_maze(agent);
	}
	
	public void create_maze(Agent agent) { //reads from different text files to create mazes.

		maze = new char[height][length];		//[rows][columns]
		nodeMaze = new Node[height][length];
		char[] rows = new char[height];
		char[] columns = new char[length];
		size = length * height;
		
		//create gold,wumpus,pits,agent
		Gold gold = new Gold(height);
		Wumpus wumpus = new Wumpus(height);
		//Agent agent = new Agent(0,0);//the agent always starts at square {1,1}

		for(int i = 0; i < rows.length; i++) {//loop left to right, top to bottom, to initialize maze.
			for(int y = 0; y < columns.length; y++) {
				maze[i][y] = '_';
			}
		}
		//place wumpus-->gold-->for every square place a pit if not start, not gold
		//Gold and wumpus cannot be spawned at same spot as agent --> (0,0).
	
		while(!wumpus.isValidLocation()) {
			wumpus.setLocation();
		}
		while(!gold.isValidLocation()) {
			gold.setLocation();
		}
		
		maze[agent.getX()][agent.getY()] = agent.getId();
		maze[gold.getX()][gold.getY()] = gold.getId();
		maze[wumpus.getX()][wumpus.getY()] = wumpus.getId();
		
		for(int i = 0; i < rows.length; i++) {//loop left to right, top to bottom, to initialize maze. pit has 20% chance at each spot to occur. 
			for(int y = 0; y < columns.length; y++) {
				Random rnd = new Random();
				int random = rnd.nextInt(10);
				if(random == 2 && i != 0 && y != 0) {//pit can't form at start position
					Pit pit = new Pit(i,y);
					maze[i][y] = pit.getId();
				}
			}
		}
        makeNodeMatrix();
   }

	public char[][] getMatrix(){//returns the maze
		return maze;
	}
	
	public Node[][] getNodeMatrix() {
		return nodeMaze;
	}
	
	
	// Method for updating matrix values to track visited nodes
	public void updateValue(int x, int y, char c) {
		maze[x][y] = c;
	}
	
	// Create a matrix where each item is a Node
	public void makeNodeMatrix() {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				int[] temp = {i, j};
				Node n = new Node(i, j, maze[i][j]);
				nodeMaze[i][j] = n;
				if(maze[i][j] == 'W') {
					n.setWumpus();
				}
				else if(maze[i][j] == 'P') {
					n.setPit();
				}
				else if(maze[i][j] == 'G') {
					n.setGold();
				}
			}
		}
		setNeighbors();
	}
	
	public void printNodeMatrix() {
		System.out.println(length + "x" + height + " world:");
		System.out.println("==================");
		for (int i = 0; i < nodeMaze.length; i++) {
			for (int j = 0; j < nodeMaze[0].length; j++) {
				System.out.print(nodeMaze[i][j].getValue());
			}
			System.out.println();
		}
		System.out.println("==================");
	}
	
	// Set all neighbors of all nodes
	public void setNeighbors() {
		Node n;
		for(int i = 0; i < nodeMaze.length; i++) {
			for(int j = 0; j < nodeMaze[0].length; j++) {	
				n = nodeMaze[i][j];
				if (i+1 <= nodeMaze.length-1)
					n.addNeighbor(nodeMaze[i+1][j]); // South neighbor
				if (i-1 >= 0)
					n.addNeighbor(nodeMaze[i-1][j]); // North neighbor
				if (j+1 <= nodeMaze[0].length-1)
					n.addNeighbor(nodeMaze[i][j+1]); // East  neighbor
				if (j-1 >= 0)
					n.addNeighbor(nodeMaze[i][j-1]); // West  neighbor
			}
		}
	}
	
	public Node getNode(int x, int y){
		return nodeMaze[x][y];
	}
	public int getUpperBound() {//return length for upper bound. Since all mazes are square, choice doesn't matter between lenght and height.
		return upperBound;
	}
}
