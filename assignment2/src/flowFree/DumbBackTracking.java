package flowFree;

import java.util.ArrayList;
import java.util.Stack;


public class DumbBackTracking {
	Maze maze;
	public int x,y;
	
	public DumbBackTracking(Maze maze) {
		this.maze = maze;
	}
		
	
	public void backTrackSearch() {
		Node[][] nodeMaze;
		ArrayList<Node> startNodes = maze.getStartNodes();
		nodeMaze = maze.getNodeMatrix(); // Get maze as a 2D array of Nodes
		String mazeType = maze.getMazeType();
		
		System.out.println("BackTracking Search: " + mazeType);
		System.out.println("----------------------------------");
		for(int i = 0; i < startNodes.size(); i++) {
			Node currNode = startNodes.get(i);
			Stack<Node> nodes = new Stack<Node>();
			startNodes.remove(i);
			char goalVal = currNode.getValue();
			x = currNode.getX();
			y = currNode.getY();
			nodes.push(currNode);
			while(!nodes.isEmpty()) {
				currNode = nodes.pop(); //get the node off of the top of the stack.
				if (currNode.getValue() == goalVal) { // End has been found
					while (currNode.getValue() != goalVal) { // Update visual path of least cost in the maze, represented with same color of start and finish
						currNode.setValue(goalVal);
						currNode = currNode.getPrev();
					}
					currNode.setValue(goalVal); 
				}
				else {
					for (Node n : currNode.getNeighbors()) {
						if (!n.isVisited()) { // If neighbor has not been visited, add it to the stack
							n.setVisited();
							n.setPrev(currNode); // Previous node of neighbor is current node
							//maze.updateValue(n.getX(), n.getY(), '.'); // (optional) Update char maze to represent nodes that have been visited
							nodes.push(n);
						}
					}
				}
			}
		}
	}
}
