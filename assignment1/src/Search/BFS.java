package Search;

import java.util.Queue;
import java.util.LinkedList;

/*
 * BFS (Breadth First Search)
 */
public class BFS {
	
	Queue<Node> q;
	char[][] maze;
	
	public BFS() {
		q = new LinkedList<>();
	}
	
	public void solve(Maze m) {
		maze = m.getMatrix();
		Node currNode = m.getStartingPoint();
		
		System.out.println("start x=" + currNode.getX() + " start y=" + currNode.getY());
		q.add(currNode);
		
		while (q.peek() != null) {
			currNode = q.remove();
			if (m.getCurrentChar(currNode) == '*') {
				System.out.println("FINISH");
				//done;
			}
			else {
				setNeighbors(currNode, m);
				for (Node n : currNode.getNeighbors()) {
					if (!n.isVisited && (m.getCurrentChar(n) != '*') && (m.getCurrentChar(n) != '%') ) {
						m.updateValue(n, '.');
						n.setVisited();
						q.add(n);
					}
					else if (!n.isVisited && m.getCurrentChar(n) == '*') {
						n.setVisited();
						q.add(n);
					}
				}
				currNode.setVisited();
				m.updateValue(currNode, '.');
			}
			m.printMaze();
		}
	}
	
	public void setNeighbors(Node n, Maze m) {
		//if (m.getMatrix()[n.getX()+1][n.getY()] != '%')
			n.addNeighbor(new Node(n.getX()+1, n.getY()));
		//if (m.getMatrix()[n.getX()-1][n.getY()] != '%')
			n.addNeighbor(new Node(n.getX()-1, n.getY()));
		//if (m.getMatrix()[n.getX()][n.getY()-1] != '%')
			n.addNeighbor(new Node(n.getX(), n.getY()-1));
		//if (m.getMatrix()[n.getX()][n.getY()+1] != '%')
			n.addNeighbor(new Node(n.getX(), n.getY()+1));
	}
}
