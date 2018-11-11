package flowFree;

import java.util.ArrayList;
import java.util.Stack;


public class DumbBackTracking {
	Maze maze;
	public Stack<Node> nodes = new Stack<Node>();
	public int x,y;
	
	public DumbBackTracking(Maze maze) {
		this.maze = maze;
	}
		
	
	public void backTrackSearch() {
		Node[][] nodeMaze;
		ArrayList<Node> startNodes = maze.getStartNodes();
		nodeMaze = maze.getNodeMatrix(); // Get maze as a 2D array of Nodes
		Node currNode = nodeMaze[0][0];
		Node goalNode = maze.getGoalNode();
		x = currNode.getX();
		y = currNode.getY();
		char val = currNode.getValue();
		
		System.out.println("BackTracking Search:");
		nodes.push(currNode);
		while(!nodes.isEmpty()) {
			currNode = nodes.pop(); //get the node off of the top of the stack.
			if (currNode == goalNode) { // End has been found
				while (currNode.getValue() != 'P') { // Update visual path of least cost in the maze, represented with 'o' char
					currNode.setValue(val);
					currNode = currNode.getPrev();
				}
				currNode.setValue(val); 
				maze.printNodeMatrix();
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
		}
	}
}
