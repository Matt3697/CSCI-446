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
		Node root = new Node(x,y);
		root.setParent(null);
		path.put(y, x);
		Node node = null;
		Node lastnode = null;
		int i = 0;
		while(!goal) {
			System.out.println(i);
			if(!visited(y, x+1)) { //if we have not visited the space to the right.
				x += 1;
				if(matrix[y][x] == '%') { 
					x = node.getParent().getX();
				}
				else{
					node = new Node(x,y);
				}
				path.put(y,x);
			}
			else if(!visited(y-1, x)) {//if we have not visited the space above.
				y -= 1;
				if(matrix[y][x] == '%') {
					y = node.getParent().getY();
				}
				else{
					node = new Node(x,y);
				}
				path.put(y,x);
			}
			else if(!visited(y, x-1)) { //if we have not visited the space to the left.
				x -= 1;
				if(matrix[y][x] == '%') { 
					x = node.getParent().getX();
				}
				else{
					node = new Node(x,y);
				}
				path.put(y,x);
			}
			else if(!visited(y+1,x)) {//if we have not visited the space below.
				y += 1;
				if(matrix[y][x] == '%') { 
					y = node.getParent().getY();
				}
				else{
					node = new Node(x,y);
				}
				path.put(y,x);
			}

			if(i == 0) {//fill tree left to right
				root.setLeftChild(node);
				node.setParent(root);
			}
			else {
				System.out.println("x: "+lastnode.getParent().getX());
				System.out.println("y: "+lastnode.getParent().getY());
				if(lastnode.getParent().hasLeftChild() && lastnode.getParent().hasRightChild()) {//if the last node's parent has a left&right child, branch off of the last node
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
			lastnode = node;
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
