package Search;

import java.util.ArrayList;
import java.util.Stack;


/*
 * DFS (Depth First Search)
 */
public class DFS {
	public int x,y,cost;
	public Stack<Node> nodes = new Stack<Node>();
	public char[][] maze;
	public ArrayList<Node> expanded;
	public ArrayList<String> stats = new ArrayList<String>();
	public void solve(Maze maze) {
		this.maze = maze.getMatrix();
		cost = 0;
		expanded = new ArrayList<>();
		maze.printNodeMatrix();
		Node[][] nodeMaze;
		Node currNode = maze.getStartingPoint();
		Node goalNode = maze.getGoalNode();
		x = currNode.getX();
		y = currNode.getY();
		nodeMaze = maze.getNodeMatrix(); // Get maze as a 2D array of Nodes
		System.out.println("Depth First Search:");
		System.out.println("start: (" + currNode.getX() + ", " + currNode.getY() + ")");  
		nodes.push(currNode);
		while(!nodes.isEmpty()) {
			currNode = nodes.pop(); //get the node off of the top of the stack.
			if (currNode == goalNode) { // End has been found
				System.out.println("total cost: " + currNode.getCost());
				cost = currNode.getCost();
				System.out.println("total expanded: " + expanded.size());
				while (currNode.getValue() != 'P') { // Update visual path of least cost in the maze, represented with 'o' char
					currNode.setValue('.');
					currNode = currNode.getPrev();
				}
				currNode.setValue('.'); 
				maze.printNodeMatrix();
				addStats();
			}
			else {
				for (Node n : currNode.getNeighbors()) {
					if (!n.isVisited()) { // If neighbor has not been visited, add it to the stack and update cost
						n.setVisited();
						n.setCost(currNode.getCost() + 1); // Cost is current node cost + 1 for the neighbor
						n.setPrev(currNode); // Previous node of neighbor is current node
						//maze.updateValue(n.getX(), n.getY(), '.'); // (optional) Update char maze to represent nodes that have been visited
						nodes.push(n);
					}
				}
			}
			if (!expanded.contains(currNode)) {
				expanded.add(currNode);
			}
		}
	}
	
	public String getTotalCost() {
		String totalCost = ("total cost: " + cost);
		return totalCost;
	}
	public String getTotalExpanded() {
		String totalExpanded = ("total expanded: " + expanded.size());
		return totalExpanded;
	}
	
	public void addStats() {
		String start = ("start: " + "(" + x + ", " + y + ")");
		String totalCost = ("total cost: " + cost);
		String totalExpanded = ("total expanded: " + expanded.size());
		stats.add(start);
		stats.add(totalCost);
		stats.add(totalExpanded);	
	}
	public ArrayList<String> getStats(){
		return stats;
	}
}

			


