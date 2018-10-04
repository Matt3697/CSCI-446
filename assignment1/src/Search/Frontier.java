package Search;

import java.util.ArrayList;
import java.util.Stack;

public class Frontier {
	public Stack<Integer> xLoc = new Stack<Integer>();
	public Stack<Integer> yLoc = new Stack<Integer>();
	
	public Frontier() {
		
	}
	
	public void push(int x, int y) {
		xLoc.push(x);
		yLoc.push(y);
	}
	public void pop() {
		xLoc.pop();
		yLoc.pop();
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
