package Search;

public class Node {
	Node right;
	Node left;
	Node last;
	boolean leftChild,rightChild;
	int x,y;
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
	public boolean hasRightChild() {
		if(rightChild == true) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean hasLeftChild() {
		if(leftChild == true) {
			return true;
		}
		else {
			return false;
		}
	}
	public void setLast(Node node) {
		
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}

