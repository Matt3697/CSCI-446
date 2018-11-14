package flowFree;
import java.util.ArrayList;
import java.util.HashSet;

/*
 * Authors: Carie Pointer, Hugh Jackovich, Matthew Sagen
 * Date:    11/13/18
 * Artificial Intelligence: Assignment 2
 * 
 * Class DumbBackTracking search solves a flow-free maze
 */

public class DumbBackTracking {
	Maze maze;
	Node[][] nodeMaze;
	public int x,y,counter;
	private HashSet<Character> colors;
	private ArrayList<Node> startNodes;
	private ArrayList<Node> varList;
	public ArrayList<String> stats = new ArrayList<String>();

	public DumbBackTracking(Maze maze) {
		this.maze = maze;
		this.varList = maze.getVarList();
		this.nodeMaze = maze.getNodeMatrix();
	}
	
	/* Initializes recursive backtracking solver */
	public void backtrack() {
		colors = new HashSet<Character>();
		startNodes = maze.getStartNodes();
		
		// Add possible colors to the domain, which will consist of the colors in the startNodes list
		for (Node n : startNodes) {
			colors.add(n.getValue());	
		}

		Node[][] assignment = maze.getNodeMatrix();
		if (backtrackRecursive(assignment) == false)
			System.out.println("no solution");
	}
	
	
	/* Recursive function to search through maze and assign colors to variables.
	 * Returns true if all variables are colored and all constraints are held
	 */
	public boolean backtrackRecursive(Node[][] assignment) {
		if (assignmentComplete(assignment)) {
			System.out.println("Solution:");
			printAssignment(assignment);
			return true;
		}
		
		// Get variable that has not yet been assigned 
		Node n = getVariable();
		if (n == null) {
			return false;
		}

		// Start with first color in list of possible colors
		for (char c : colors) {
			
			n.setValue(c);
			
			// Check if this color assignment is valid
			if (canMakeAssignment(n, c, assignment)) {
				
				// Recursive call to function if previous assignment is valid
				if (backtrackRecursive(assignment))
					return true;
				
				// If recursive call fails, change assignment back to a blank
				n.setValue('_');
			}
			else
				n.setValue('_');
		}

		return false;
	}
	
	/* Returns first variable in list that is not assigned a color */
	public Node getVariable() {
		for (Node n : varList) {
			if (n.getValue() == '_')
				return n;
		}
		return null;
	}
	
	/* Checks if current node has a valid assignment by checking each of its neighbors. 
	 * Returns false if one of the neighbors invalidates a constraint, or true on success 
	 */
	public boolean canMakeAssignment(Node n, char c, Node[][] assignment) {
		for (Node neighbor : n.getNeighbors()) {
			if(!checkNeighborConstraints(neighbor, c, assignment))
				return false;
		}

		return true;
	}
	
	/* Checks all neighbors of a given node n to ensure no constraints are broken 
	 * Returns true on success, or false if one of the constraints is broken
	 */
	public boolean checkNeighborConstraints(Node n, char c, Node[][] assignment) {
		int sameColor = 0;
		int blanks = 0;
		
		if (n.getValue() == '_')
			return true;

		// Count number of neighbors that are unassigned or the same color as n
		for (Node neighbor : n.getNeighbors()) {
			if (neighbor.getValue() == '_')
				blanks++;
			else if (neighbor.getValue() == n.getValue()) {
				sameColor++;
			}
			
		}
		
		// Source cannot have more than one neighbor of the same color 
		// and non-source nodes cannot have more than two neighbors of the same color
		if ((sameColor > 1 && n.isSource()) || (sameColor > 2 && !n.isSource())) {
			return false;
		}
			
		// Source nodes must have one neighbor of the same color
		// and non-source nodes must have two neighbors of same color.
		// Unassigned nodes are potential neighbors of same color.
		if (((sameColor + blanks < 1) && n.isSource()) || ((sameColor + blanks < 2) && !n.isSource())) {
			return false;
		}
		
		return true;
	}
	
	/* Print method for the assignment */
	public void printAssignment(Node[][] assignment) {
		for (int i = 0; i < assignment.length; i++) {
			for (int j = 0; j < assignment[0].length; j++) {
				System.out.print(assignment[i][j].getValue());
			}
			System.out.println();
		}
		System.out.println();	
	}
	
	/* Checks if the assignment is completed by counting all of the assigned nodes.
	 * returns true if all nodes have been assigned
	 */
	public boolean assignmentComplete(Node[][] assignment) {
		int count = 0;
		for (int i = 0; i < assignment.length; i++) {
			for (int j = 0; j < assignment[0].length; j++) {
				if (assignment[i][j].getValue() != '_') {
					count++;
				}
			}
		}
		
		if (count == maze.getSize())
			return true;
		
		return false;
	}

	public ArrayList<String> getStats(){
		return stats;
	}
}
