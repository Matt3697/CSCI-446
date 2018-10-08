package Search;

import java.util.ArrayList;
import java.util.HashMap;

public class Node {
	public Node right;
	public Node left;
	public Node parent;
	public boolean leftChild,rightChild,goal;
	public boolean alternatePath = false;
	public int x,y, cost;
	public ArrayList<Integer> possiblePathX;
	public ArrayList<Integer> possiblePathY;

	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
		leftChild = false;
		rightChild = false;
		possiblePathX = new ArrayList<Integer>();
		possiblePathY = new ArrayList<Integer>();
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
	public void addPossiblePathX(int x) {
		possiblePathX.add(x);
	}
	public void addPossiblePathY(int y) {
		possiblePathY.add(y);
	}
	public int getPossiblePathX(){
		x = possiblePathX.get(0);
		possiblePathX.remove(0); //removes path from list so we don't use it twice.
		return x;
	}
	public int getPossiblePathY(){
		y = possiblePathY.get(0);
		possiblePathY.remove(0);
		return y;	
	}
	public boolean hasAlternatePath() {
		return alternatePath;
	}
	public void setAlternatePath(boolean flag) {
		alternatePath = flag;
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
	
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public int getCost() {
		return cost;
	}
}

