package flowFree;
/*
 * Authors: Carie Pointer, Hugh Jackovich, Matthew Sagen
 * Date:    11/13/18
 * Artificial Intelligence: Assignment 2
 */
import java.util.ArrayList;
import java.util.HashSet;


public class DumbBackTracking {
	Maze maze;
	public int x,y,counter;
	private int variables;
	private HashSet<Character> domain;
	private ArrayList<Node> startNodes;
	public ArrayList<String> stats = new ArrayList<String>();

	public DumbBackTracking(Maze maze) {
		this.maze = maze;
	}
	
	// This is my (Carie's) method/attempt for implementing the search
	public void backtrack2() {
		
	   /* Variables: 25 for 5x5 matrix;
		* Domain of empty values: {b, r, o, y, g} for 5x5 matrix;
		* Constraints:
		* 1. only two neighbors can be of same value (color) for connecting nodes
		* 2. unless it's a source node, two neighbors MUST be same value (color)
		* 
		*/
		
		variables = maze.getSize();
		domain = new HashSet<Character>();
		int startVar = 1;
		//Node currNode;
		startNodes = maze.getStartNodes();
		
		// Add possible colors to the domain, which will consist of the colors in the startNodes list
		for (Node n : startNodes) {
			domain.add(n.getValue());	
		}
		
		// First node will just be the first startNode
		Node currNode = startNodes.get(0);
		
		// First call to recursive function
		//for(Node currNode : startNodes) {
			//maze.setUnvisited();
			if (backtrackNode(currNode, currNode.getValue(), startVar) != true) {
				System.out.println("no solution");
			}
		//}
	}
	
	/* Recursive algorithm for coloring all nodes of the graph.
	 * TODO: Need to figure out how to iterate through all colors in the maze; right now it only solves the first color it is given
	 */
	public boolean backtrackNode(Node n, char currColor, int num) {
		System.out.println("*******NODE: "+ n.getValue() + " IS VISITED? "+ n.isVisited() + " IS SOURCE? " +n.isSource() + " (" +n.getX() + ","+ n.getY() + ") COLOR " + n.getValue());
		char prevColor;
		
		/* If the current number equals the number of total nodes in the graph, then all nodes
		 * have been colored and we have found a solution.
		 */
		if (num == variables) {
			System.out.println("END");
			System.out.println("number of variables colored = " + num); //this should be 25 at the end for a 5x5 maze
			return true;
		}
		
		//TODO: This should eventually be removed (or possibly moved to a different spot to check if the end of a color has been found). 
		// Technically the algorithm should end when all nodes are colored (see the check above), so this is not implemented properly.
		if (!startNodes.contains(n) && n.isSource()) {
			System.out.println("END");
			System.out.println("number of variables colored = " + num); //this should be 25 at the end for 5x5 maze
			if(counter <= startNodes.size()) {
				//maze.setUnvisited();
				counter++;
				Node node = startNodes.get(counter);
				backtrackNode(node, node.getValue(), num);
			}
			
			return true;
		}
		
		// Print maze for debugging purposes
		maze.printNodeMatrix();
		System.out.println();
		
		// The iteration of colors in the domain is currently commented out -- some form of this will probably be needed in order to check for all possibilities of colorings.
		// Right now it is just using the current color of the first startNode -- which is B for the 5x5 maze.
		//for (char color : domain) {		
			for (Node next : n.getNeighbors()) { // Go through list of neighbors to assign next color to
				prevColor = next.getValue();
				if (canAssign(next, currColor) && !next.isVisited()) { // If constraints are held, this neighbor can be assigned a color
					next.setValue(currColor);
					next.setVisited(); // Set that the node was "visited" or colored -- might not need this
					
					// Recursive call to function with the next node in neighbors
					if (backtrackNode(next, currColor, num + 1) == true) {
						return true;
					} 
					next.setValue(prevColor); // Set the color back to it's original if the new color choice breaks constraints
				}
			}
		return false;
	}
	
	/* Checks the constraints against the current node.
	 * Returns true if the node can be assigned the given color 
	 */
	public boolean canAssign(Node n, char currColor) {
		int numNeighbors = 0;
		
		// Count number of neighbors with the same color
		for (Node neighbor : n.getNeighbors()) {
			if (neighbor.getValue() == currColor)
				numNeighbors++;
		}
		
		/* If it is a source node, then only one neighbor can be the same color;
		 * if it is not a source node, then only two neighbors can be the same color; 
		 */
		
		if ((!n.isSource() && numNeighbors > 2) || (n.isSource() && numNeighbors > 1) || (n.isSource && n.getValue() != currColor) )
			return false;
		else 
			return true;
	}
	public ArrayList<String> getStats(){
		return stats;
	}
}
