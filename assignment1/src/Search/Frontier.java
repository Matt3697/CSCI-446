package Search;

import java.util.ArrayList;
import java.util.Stack;

public class Frontier {
	public Stack<Integer> xLoc = new Stack<Integer>();
	public Stack<Integer> yLoc = new Stack<Integer>();
	public Stack<Node> nodes = new Stack<Node>();

	public Frontier() {
		
	}
	
	public void push(Node node) {
		nodes.push(node);
	}
	public void pop() {
		nodes.pop();
	}
	public ArrayList<Integer> getStackX() {
		ArrayList<Integer> x = new ArrayList<Integer>();
		while(!xLoc.isEmpty()) {
			x.add(xLoc.pop());
		}
		return x;
	}
	public ArrayList<Integer> getStackY() {
		ArrayList<Integer> y = new ArrayList<Integer>();
		while(!yLoc.isEmpty()) {
			y.add(yLoc.pop());
		}
		return y;
	}
	
}
