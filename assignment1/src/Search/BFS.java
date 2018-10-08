package Search;

import java.util.Queue;
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
		m.printMaze();
		Node currNode = m.getStartingPoint();
		nodeMaze = m.getNodeMatrix(); // Get maze as a 2D array of Nodes
		System.out.println("start: (" + currNode.getX() + ", " + currNode.getY() + ")");  
		q.add(currNode); 
		int explored = 0;
		while (!q.isEmpty()) {
			currNode = q.remove();
			if (currNode.getValue() == '*') { // End has been found
				System.out.println("total cost: " + currNode.getCost());
				System.out.println("explored: " + explored);
				while (currNode.getValue() != 'P') { // Update visual path of least cost in the maze, represented with 'o' char
					m.updateValue(currNode.getX(), currNode.getY(), 'o');
					currNode = currNode.getPrev();
				}
				m.updateValue(currNode.getX(), currNode.getY(), 'o'); 
				m.printMaze();
			}
			else {
				for (Node n : currNode.getNeighbors()) {
					if (!n.isVisited()) { // If neighbor has not been visited, add it to queue and update cost
						n.setVisited();
						explored++;
						n.setCost(currNode.getCost() + 1); // Cost is current node cost + 1 for the neighbor
						n.setPrev(currNode); // Previous node of neighbor is current node
						m.updateValue(n.getX(), n.getY(), '.'); // (optional) Update char maze to represent nodes that have been visited
						q.add(n); 
					}
				}
			}
		}
	}
}
