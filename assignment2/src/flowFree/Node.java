package flowFree;
/*
 * Authors: Carie Pointer, Hugh Jackovich, Matthew Sagen
 * Date:    11/13/18
 * Artificial Intelligence: Assignment 2
 */
import java.util.ArrayList;

public class Node {
	private int x,y;
	private char value;
	private boolean isSource;
	private ArrayList<Node> neighbors = new ArrayList<>();
	private int[] otherSource;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Node(int x, int y, char c) {
		this.x = x;
		this.y = y;
		value = c;
		isSource = false;
	}
	
	public void setAsSource() {
		isSource = true;
	}
	
	public void setOtherSource(int[] loc)
	{
		otherSource = loc;
	}
	
	public int[] getOtherSource()
	{
		return otherSource;
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
	
	public void addNeighbor(Node n) {
		neighbors.add(n);
	}
	
	public ArrayList<Node> getNeighbors() {
		return neighbors;
	}
	
	public void setValue(char c) {
		value = c;
	}
	
	public char getValue() {
		return value;
	}
}


