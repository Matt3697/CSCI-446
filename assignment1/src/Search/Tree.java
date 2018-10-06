package Search;

import java.util.HashMap;

public class Tree {
	public HashMap<Integer, Integer> path = new HashMap<Integer,Integer>();
	public Frontier frontier = new Frontier();
	public int x, y;
	public char[][] matrix;
	
	public Tree(int x, int y, char[][] maze) {
		this.x = x;
		this.y = y;
		matrix = maze;
	}
	public void createTree() {
		boolean goal = false;
		Node parent = new Node(x,y);
		parent.setParent(null);
		path.put(y, x);
		Node node = null;
		Node lastnode = null;
		int i = 0;
		while(!goal) {
			System.out.println(i);
			if(!visited(y, x+1)) { //if we have not visited the space to the right.
				if(matrix[y][x] == '%') { 
					x = node.getParent().getX();
				}
				else{
					x += 1;
					node = new Node(x,y);
				}
				path.put(y,x);
			}
			else if(!visited(y-1, x)) {//if we have not visited the space above.
				if(matrix[y-1][x] == '%') {
					y = node.getParent().getY();
				}
				else{
					y -= 1;
					node = new Node(x,y);
				}
				path.put(y,x);
			}
			else if(!visited(y, x-1)) { //if we have not visited the space to the left.
				if(matrix[y][x-1] == '%') { 
					x = node.getParent().getX();
				}
				else{
					x -= 1;
					node = new Node(x,y);
				}
				path.put(y,x);
			}
			else if(!visited(y+1,x)) {//if we have not visited the space below.
				if(matrix[y+1][x] == '%') { 
					y = node.getParent().getY();
				}
				else{
					y += 1;
					node = new Node(x,y);
				}
				path.put(y,x);
			}
			lastnode = node;
			if(i == 0) {//fill tree left to right
				parent.setLeftChild(lastnode);
				lastnode.setParent(parent);
			}
			else {
				if(lastnode.getParent().hasLeftChild() && lastnode.getParent().hasRightChild()) {//if the last node's parent has a right child, branch off of the last node
					lastnode.setLeftChild(node);
					node.setParent(lastnode);
				}
				else {//otherwise set the node as the right child of the last node's parent.
					lastnode.getParent().setRightChild(node);
				}
			}
			goal = checkForGoalState(y,x); //Step 4: Check for goal state
			if(goal) {
				node.isGoal(true);
			}
			i++;
		}
	}
		
	public boolean visited(int y, int x) {
		if(path.containsKey(y) && path.get(y) != x) { //return false if we have not visited a spot.
			return false;
		}
		else { //return true if we have visited the spot
			return true;
		}
	}
	public boolean checkForGoalState(int y, int x) {
		boolean goal = false;
		if(matrix[y][x] == '*'){//if the goal is to the left
			goal = true;
			path.put(x, y); //add the x and y position to the path list
		}
		else if(matrix[y][x] == '*'){//if the goal is above
			goal = true;
			path.put(x, y); //add the x and y position to the path list
		}
		else if(matrix[y][x] == '*'){//if the goal is to the right
			goal = true;
			path.put(y,x); //add the x and y position to the path list
		}
		else if(matrix[y][x] == '*'){//if the goal is below
			goal = true;
			path.put(y,x); //add the x and y position to the path list
		}
		return goal;
	}
}
