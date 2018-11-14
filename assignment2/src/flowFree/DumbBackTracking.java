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
	Node[][] nodeMaze;
	public int x,y,counter;
	private int variables;
	private HashSet<Character> domain;
	private HashSet<Character> colors;
	private ArrayList<Node> startNodes;
	private ArrayList<Node> varList;
	private ArrayList<Node> coloredList;
	
	public ArrayList<String> stats = new ArrayList<String>();

	public DumbBackTracking(Maze maze) {
		this.maze = maze;
		this.varList = maze.getVarList();
		this.coloredList = new ArrayList<Node>();
		this.nodeMaze = maze.getNodeMatrix();
	}
	
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
	
	public boolean backtrackRecursive(Node[][] assignment) {
		if (assignmentComplete(assignment)) {
			System.out.println("Maze:");
			maze.printNodeMatrix();
			System.out.println();
			return true;
		}
		
		Node n = getVariable();
		if (n == null) {
			return false;
		}
		System.out.println(colors);
		printAssignment(assignment);
		
		for (char c : colors) {
			
			n.setValue(c);
			if (canMakeAssignment(n, c, assignment)) {
				
				if (backtrackRecursive(assignment))
					return true;
				
				n.setValue('_');
			}
			else
				n.setValue('_');
		}

		return false;
	}
	
	public Node getVariable() {
		for (Node n : varList) {
			if (n.getValue() == '_')
				return n;
		}
		return null;
	}
	
	public boolean canMakeAssignment(Node n, char c, Node[][] assignment) {
		//System.out.println("checking neighbors of node " + n.getX() + " " + n.getY());
		//System.out.println("current color: " + n.getValue());
		for (Node neighbor : n.getNeighbors()) {
			if(!checkNeighborConstraints(neighbor, c, assignment))
				return false;
		}

		return true;
	}
	
	public boolean checkNeighborConstraints(Node n, char c, Node[][] assignment) {
		int sameColor = 0;
		int blanks = 0;
		
		if (n.getValue() == '_')
			return true;

		//System.out.println("curr node " + n.getX() + " " + n.getY() + " color "+n.getValue());
		for (Node neighbor : n.getNeighbors()) {
			//System.out.println("neighbor node " + neighbor.getX() + " " + neighbor.getY() + " " + neighbor.getValue());
			if (neighbor.getValue() == '_')
				blanks++;
			else if (neighbor.getValue() == n.getValue()) {
				sameColor++;
			}
			
		}
		if ((sameColor > 1 && n.isSource()) || (sameColor > 2 && !n.isSource())) {
			//System.out.println("failed");
			return false;
		}
			
		if (((sameColor + blanks < 1) && n.isSource()) || ((sameColor + blanks < 2) && !n.isSource())) {
			//System.out.println("sameColor + blanks = " + (sameColor + blanks));
			return false;
		}
		
		return true;
	}
	
	public void printAssignment(Node[][] assignment) {
		for (int i = 0; i < assignment.length; i++) {
			for (int j = 0; j < assignment[0].length; j++) {
				System.out.print(assignment[i][j].getValue());
			}
			System.out.println();
		}
		System.out.println();
		
	}
	
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
