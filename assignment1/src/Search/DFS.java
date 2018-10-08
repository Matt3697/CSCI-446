package Search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import javafx.scene.shape.Path;

/*
 * DFS (Depth First Search)
 */
public class DFS {
	
	public Stack<Node> nodes = new Stack<Node>();
	
	public void solve(Maze maze) {
		maze.printMaze();
		Node[][] nodeMaze;
		Node currNode = maze.getStartingPoint();
		nodeMaze = maze.getNodeMatrix(); // Get maze as a 2D array of Nodes
		System.out.println("Depth First Search:");
		System.out.println("start: (" + currNode.getX() + ", " + currNode.getY() + ")");  
		int expanded = 0;
		nodes.push(currNode);
		while(!nodes.isEmpty()) {
			currNode = nodes.pop(); //get the node off of the top of the stack.
			if (currNode.getValue() == '*') { // End has been found
				System.out.println("total cost: " + currNode.getCost());
				System.out.println("total expanded: " + expanded);
				while (currNode.getValue() != 'P') { // Update visual path of least cost in the maze, represented with 'o' char
					maze.updateValue(currNode.getX(), currNode.getY(), 'o');
					currNode = currNode.getPrev();
				}
				maze.updateValue(currNode.getX(), currNode.getY(), 'o'); 
				maze.printMaze();
			}
			else {
				for (Node n : currNode.getNeighbors()) {
					if (!n.isVisited()) { // If neighbor has not been visited, add it to the stack and update cost
						n.setVisited();
						expanded++;
						n.setCost(currNode.getCost() + 1); // Cost is current node cost + 1 for the neighbor
						n.setPrev(currNode); // Previous node of neighbor is current node
						maze.updateValue(n.getX(), n.getY(), '.'); // (optional) Update char maze to represent nodes that have been visited
						nodes.push(n);
					}
				}
			}
		}
	}
}
			


