package Search;

import java.util.ArrayList;
import java.util.LinkedList;

public class Node {
	Node right;
	Node left;
	Node parent;
	Node prev;
	ArrayList<Node> neighbors = new ArrayList<>();
	LinkedList<Node> path = new LinkedList<>();
	boolean leftChild,rightChild,goal;
	int x,y, cost;
	boolean isVisited, hasPrev;
	char value;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
		leftChild = false;
		rightChild = false;
	}
	public Node(int x, int y, char c) {
		this.x = x;
		this.y = y;
		value = c;
		cost = 0;
		prev = null;
	}
	public void setRightChild(Node node) {
		right = node;
	}
	public void setLeftChild(Node node) {
		left = node;
	}
	public boolean hasRightChild() { //return true if the node has a right child
		if(rightChild == true) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean hasLeftChild() { //return true if the node has a left child
		if(leftChild == true) {
			return true;
		}
		else {
			return false;
		}
	}
	public Node getRightChild() {
		return right;
	}
	public Node getLeftChild() {
		return left;
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node node) {
		parent = node;
	}
	public void isGoal(boolean isGoal) {
		goal = isGoal;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
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
	public int getCost() {
		return cost;
	}
	public void updateCost(int c) {
		cost = c;
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


