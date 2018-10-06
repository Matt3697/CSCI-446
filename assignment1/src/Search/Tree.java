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
		Node node = null;
		Node temp = null;
		int i = 0;
		while(!goal) {
			if(!visited(y, x+1)) { //if we have not visited the space to the right.
				if(matrix[y][x] == '%') { 
					x-=1;
				}
				else{
					x += 1;
					node = new Node(x,y);
				}
				path.put(y,x);
			}
			else if(!visited(y-1, x)) {//if we have not visited the space above.
				if(matrix[y-1][x] == '%') {
					y+=1;
				}
				else{
					y -= 1;
					node = new Node(x,y);
				}
				path.put(y,x);
			}
			else if(!visited(y, x-1)) { //if we have not visited the space to the left.
				if(matrix[y][x-1] == '%') { 
					x+=1;
				}
				else{
					x -= 1;
					node = new Node(x,y);
				}
				path.put(y,x);
			}
			else if(!visited(y+1,x)) {//if we have not visited the space below.
				if(matrix[y+1][x] == '%') { 
					y-=1;
				}
				else{
					y += 1;
					node = new Node(x,y);
				}
				path.put(y,x);
			}
			temp = node;
			if(i == 0) {//fill tree left to right
				parent.setLeftChild(node);
				node.setParent(parent);
			}
			else {
				if(temp.getParent().hasLeftChild() && temp.getParent().hasRightChild()) {//if the last node's parent has a right child, branch off of the last node
					temp.setLeftChild(node);
					node.setParent(temp);
				}
				else {//otherwise set the node as the right child of the last node's parent.
					temp.getParent().setRightChild(node);
				}
			}
			goal = checkForGoalState(y,x); //Step 4: Check for goal state
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
		if(matrix[y][x-1] == '*'){//if the goal is to the left
			goal = true;
			path.put(x-1, y); //add the x and y position to the path list
		}
		else if(matrix[y+1][x] == '*'){//if the goal is above
			goal = true;
			path.put(x, y+1); //add the x and y position to the path list
		}
		else if(matrix[y][x+1] == '*'){//if the goal is to the right
			goal = true;
			path.put(y,x+1); //add the x and y position to the path list
		}
		else if(matrix[y-1][x] == '*'){//if the goal is below
			goal = true;
			path.put(y-1,x); //add the x and y position to the path list
		}
		return goal;
	}
}
