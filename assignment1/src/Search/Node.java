package Search;

import java.util.ArrayList;

public class Node {
	Node right;
	Node left;
	Node parent;
	ArrayList<Node> neighbors = new ArrayList<>();
	boolean leftChild,rightChild,goal;
	int x,y;
	boolean isVisited;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
		leftChild = false;
		rightChild = false;
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
	public void removeNeighbor(Node n) {
		neighbors.remove(n);
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
}

