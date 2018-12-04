package wumpusWorld;
/*
 * Authors: Carie Pointer, Hugh Jackovich, Matthew Sagen
 * Date:    11/30/18
 * Programming Assignment 3: Wumpus World
 * 
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Node {
	private int x,y;
	private char value, guess;
	private boolean stench, wumpus, pit, gold, glitter, breeze, isVisited, hasAgent;
	private boolean topWall, bottomWall, leftWall, rightWall;
	private ArrayList<Node> neighbors = new ArrayList<>();
	private int dist;
	private Node prev;
	Set<Node> nearbySources;
	
	public Node(int x, int y) { // Initiallize with x and y coords
		this.x = x;
		this.y = y;
	}
	
	public Node(int x, int y, char c) { // Initialize with x and y coordinates, as well as initial value
		this.x = x;
		this.y = y;
		value = c;
		nearbySources = new HashSet<Node>();
		this.guess = '?';//no guess currently
	}
	
	public int getDist(){
		return dist;
	}
	
	public int getX() { // Return x location of node
		return x;
	}
	
	public int getY() { // Return y location of node.
		return y;
	}
	
	public void addNeighbor(Node n) { // Add one neighbor to list of neighbors
		neighbors.add(n);
	}
	
	public ArrayList<Node> getNeighbors() { //Returns list of neighbors
		return neighbors;
	}
	
	public void setValue(char c) { // Change value of Node
		value = c;
	}
	
	public char getValue() { // Returns value of Node
		return value;
	}

	public boolean hasStench() { // Returns whether or not a node has a stench.
		return stench;
	}
	
	public void setStench(boolean flag) { // Set a node to have a stench.
		stench = flag;
		if(stench == true) {
			System.out.println(x + ": " + y + " has stench.");
		}
		else if(stench == false) {
			System.out.println(x + ": " + y + ": removing stench.");
		}
		
	}
	
	public void setWumpus(boolean flag) { // Set a pit to have the wumpus.
		wumpus = flag;
	}
	
	public boolean containsWumpus() { // Returns whether or not a pit contains the wumpus.
		return wumpus;
	}
	
	public void setPit() { // Set a node to have a pit.
		pit = true;
	}
	
	public boolean containsPit() { // Returns whether or not a node contains a pit
		return pit;
	}
	
	public void setGold() { // Set a node to have gold
		gold = true;
	}
	
	public boolean containsGold() { // Returns whether or not a node contains gold
		return gold;
	}

	public void setGlitter() { // Set a node to have glitter.
		glitter = true;
		System.out.println(x + ": " + y + " has glitter.");
	}
	
	public boolean containsGlitter() { // Returns whether or not a node has glitter in it.
		return glitter;
	}
	
	public void setBreeze() { // Set a node to have a breeze.
		breeze = true;
		System.out.println(x + ": " + y + " has breeze.");
	}
	
	public boolean hasBreeze() { // Returns whether or not a pit has a breeze.
		return breeze;
	}
	
	public void setTopWall() { // Set a wall at the top of the square
		topWall = true;
		System.out.println(x + ": " + y + " has top wall.");
	}

	public void setBottomWall() { // Set a wall at the bottom of the square
		bottomWall = true;
		System.out.println(x + ": " + y + " has bottom wall.");
	}

	public void setLeftWall() { // Set a wall on the left side of the square
		leftWall = true;
		System.out.println(x + ": " + y + " has left wall.");
	}

	public void setRightWall() { // Set a wall on the right side of the square
		rightWall = true;
		System.out.println(x + ": " + y + " has right wall.");
	}
	
	public boolean isWall() { // Returns if is a wall
		if (rightWall || leftWall || bottomWall || topWall)
			return true;
		
		else 
			return false;
	}
	
	public boolean hasTopWall() { // Returns if has top wall
		return topWall;
	}
	
	public boolean hasBottomWall() { // Returns if has bottom wall
		return bottomWall;
	}
	
	public boolean hasLeftWall() { // Returns if has left wall
		return leftWall;
	}
	
	public boolean hasRightWall() { // Returns if has right wall
		return rightWall;
	}
	
	public void setGuess(char val) { // Set the agent's current guess as to what the node could be
		guess = val;
	}
	
	public char getGuess() {
		return guess;
	}
	
	public void setHasAgent(boolean b) { // Set if this node is where the agent is currently
		this.hasAgent = b;
	}
	
	public boolean hasAgent() {
		return hasAgent;
	}
	
	public void setVisited() { // Set if the current node has been visited by our agent
		isVisited = true;
	}
	
	public boolean isVisited() {
		return isVisited;
	}

	public void setPrev(Node n) { // Set the node previous to current node
		this.prev = n;
	}
	
	public Node getPrev() {
		return prev;
	}
}