package flowFree;

import java.util.ArrayList;

public class Node {
	public int x,y, cost, smallestCost;
	public char value;
	public boolean isVisited, hasPrev, goal, isSource;
	public ArrayList<Node> neighbors = new ArrayList<>();
	public Node prev;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Node(int x, int y, char c) {
		this.x = x;
		this.y = y;
		value = c;
		isSource = false;
		cost = 0;
		prev = null;
	}
	
	public void setAsSource() {
		isSource = true;
	}
	
	public boolean isSource() {
		return isSource;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public void setSmallestCost(int cost) {
		smallestCost = cost;
	}
	
	public int getSmallestCost() {
		return smallestCost;
	}
	
	public int getCost() {
		return cost;
	}
	
	public void addNeighbor(Node n) {
		neighbors.add(n);
	}
	public ArrayList<Node> getNeighbors() {
		return neighbors;
	}
	public boolean isVisited() {
		return isVisited;
	}
	
	public void setVisited() {
		isVisited = true;
	}
	
	public void setValue(char c) {
		value = c;
	}
	
	public char getValue() {
		return value;
	}
	
	public void setPrev(Node n) {
		prev = n;
		hasPrev = true;
	}
	
	public Node getPrev() {
		return prev;
	}
	
	public boolean hasPrev() {
		return hasPrev;
	}
}


