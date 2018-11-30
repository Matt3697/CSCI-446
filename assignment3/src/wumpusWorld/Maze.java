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
	private ArrayList<Node> varList = new ArrayList<Node>();
	private Node[][] nodeMaze;
	private int length, height;
	
	public Maze(int length, int height) { //constructor for Maze
		this.length = length;
		this.height = height;
		create_maze();
	}
	
	public void create_maze() { //reads from different text files to create mazes.

		maze = new char[height][length];		//[rows][columns]
		nodeMaze = new Node[height][length];
		char[] rows = new char[height];
		char[] columns = new char[length];
		Gold gold = new Gold(height);
		Wumpus wumpus = new Wumpus(height);
		Pit pit = new Pit(height);
		for(int i = 0; i < rows.length; i++) {//loop left to right, top to bottom, to initialize maze.
			for(int y = 0; y < columns.length; y++) {
				maze[i][y] = '_';
			}
		}
		//Possibly add conditional to check if gold and wumpus are on same square, call setLocation again...
		maze[gold.getX()][gold.getY()] = gold.getId();
		maze[wumpus.getX()][wumpus.getY()] = wumpus.getId();
		
		
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
		HashMap<Character, int[]> vals = new HashMap<Character, int[]>();
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				int[] temp = {i, j};
				Node n = new Node(i, j, maze[i][j]);
				nodeMaze[i][j] = n;
				varList.add(n);
				if(n.getValue() != '_') {
					
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
	
	public ArrayList<Node> getVarList() {
		return varList;
	}
	public Node getNode(int x, int y){
		return nodeMaze[x][y];
	}
}
