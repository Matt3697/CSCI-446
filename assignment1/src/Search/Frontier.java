package Search;

import java.util.ArrayList;
import java.util.Stack;

public class Frontier {
	public Stack<Integer> xLoc = new Stack<Integer>();
	public Stack<Integer> yLoc = new Stack<Integer>();
	public Stack<Node> nodes = new Stack<Node>();
	public ArrayList<Node> nodeList = new ArrayList<Node>();

	public Frontier() {
		
	}
	
	public void push(Node node) {
		nodes.push(node);
	}
	public void pop() {
		nodes.pop();
	}
	
	public void addNode(Node node) {
		nodeList.add(node);
	}
	
	public Node findSmallestCost() {
		int pos = 0;
		System.out.println(nodes.size());
		int cost = nodeList.get(0).getCost();
		Node curNode = nodeList.get(0);
		
		for(int i = 1; i < nodeList.size(); i++) {
			if(nodeList.get(i).getCost() < cost) {
				cost = nodeList.get(i).getCost();
				curNode = nodeList.get(i);
				pos = i;
			}
		}
		nodeList.remove(pos);
		return curNode;
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
