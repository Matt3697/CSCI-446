package Search;

import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

/*
 * BFS (Breadth First Search)
 */
public class BFS {
	
	Queue<Node> q;
	Node[][] nodeMaze;
	
	public BFS() {
		q = new LinkedList<>(); //Frontier queue
	}
	
	public void solve(Maze m) {
		ArrayList<Node> expandedNodes = new ArrayList<>();
		//m.printMaze();
		m.printNodeMatrix();
		Node currNode = m.getStartingPoint();
		Node goalNode = m.getGoalNode();
		nodeMaze = m.getNodeMatrix(); // Get maze as a 2D array of Nodes
		System.out.println("start: (" + currNode.getX() + ", " + currNode.getY() + ")");  
		q.add(currNode); 
		
		while (!q.isEmpty()) {
			currNode = q.remove();
			if (currNode == goalNode) { // End has been found
				System.out.println("total cost: " + currNode.getCost());
				System.out.println("expanded nodes: " + expandedNodes.size());
				while (currNode.getValue() != 'P') { // Update visual path of least cost in the maze, represented with 'o' char
					currNode.setValue('.');
					currNode = currNode.getPrev();
				}
				currNode.setValue('.');
				m.printNodeMatrix();
				break;
			}
			
			else {
				for (Node n : currNode.getNeighbors()) {
					if (!n.isVisited()) { // If neighbor has not been visited, add it to queue and update cost
						n.setVisited();
						n.setCost(currNode.getCost() + 1); // Cost is current node cost + 1 for the neighbor
						n.setPrev(currNode); // Previous node of neighbor is current node
						//m.updateValue(n.getX(), n.getY(), '.'); // (optional) Update char maze to represent nodes that have been visited
						q.add(n); 
					}
				}
			if (!expandedNodes.contains(currNode))
				expandedNodes.add(currNode);
			}
		}
	}
}
