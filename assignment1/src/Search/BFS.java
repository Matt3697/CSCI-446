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
	Node startNode;
	String mazeType;
	int totalCost, totalExplored;
	ArrayList<String> stats = new ArrayList<>();
	
	public BFS() {
		q = new LinkedList<>(); //Frontier queue
	}
	
	public void solve(Maze m) {
		ArrayList<Node> expandedNodes = new ArrayList<>();
		m.printNodeMatrix();
		mazeType = m.getMazeType();
		startNode = m.getStartingPoint();
		Node currNode = startNode;
		Node goalNode = m.getGoalNode();
		nodeMaze = m.getNodeMatrix(); // Get maze as a 2D array of Nodes
		System.out.println("start: (" + currNode.getX() + ", " + currNode.getY() + ")");  
		q.add(currNode); 
		
		while (!q.isEmpty()) {
			currNode = q.remove();
			if (currNode == goalNode) { // End has been found
				totalCost = currNode.getCost();
				totalExplored = expandedNodes.size();
				System.out.println("total cost: " + currNode.getCost());
				System.out.println("expanded nodes: " + expandedNodes.size());
				while (currNode.getValue() != 'P') { // Update visual path of least cost in the maze, represented with 'o' char
					if (currNode != goalNode)
						currNode.setValue('.');
					currNode = currNode.getPrev();
				}
				m.printNodeMatrix();
				addStats();
				break;
			}
			
			else {
				for (Node n : currNode.getNeighbors()) {
					if (!n.isVisited()) { // If neighbor has not been visited, add it to queue and update cost
						n.setVisited();
						n.setCost(currNode.getCost() + 1); // Cost is current node cost + 1 for the neighbor
						n.setPrev(currNode); // Previous node of neighbor is current node
						q.add(n); 
					}
				}
			if (!expandedNodes.contains(currNode))
				expandedNodes.add(currNode);
			}
		}
	}
	
	public void addStats() {
		String start = ("Start: " + "(" + startNode.getX() + ", " + startNode.getY() + ")");
		String cost = ("Total least cost: " + totalCost);
		String totalExpanded = ("Total expanded: " + totalExplored);
		stats.add("BFS " + mazeType);
		stats.add(start);
		stats.add(cost);
		stats.add(totalExpanded + "\n");	
	}
	public ArrayList<String> getStats(){
		return stats;
	}
}
