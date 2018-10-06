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
		root.last = null;
		root.next = null;
		int i = 0;
		while(!goal) {
			if(!visited(y, x+1)) { //if we have not visited the space to the right.
				if(matrix[y][x] == '%') { //if there are no children nodes, then pop the node from the stack.
					x-=1;
				}
				else{
					x += 1;
					Node node = new Node(x,y);
					path.put(y,x);
				}
			}
			else if(!visited(y -1, x)) {//if we have not expanded the node above.
				if(matrix[y-1][x] == '%') { //if there are no children nodes, then pop the node from the stack.
					y+=1;
				}
				else{
					y -= 1;
					Node node = new Node(x,y);
					path.put(y,x);
				}
			}
			else if(!visited(y, x-1)) { //if we have not expanded the node to the left.
				if(matrix[y][x-1] == '%') { //if there are no children nodes, then pop the node from the stack.
					x+=1;
				}
				else{
					x -= 1;
					Node node = new Node(x,y);
					path.put(y,x);
				}
			}
			else if(!visited(y+1,x)) {//if we have not expanded the node below.
				if(matrix[y+1][x] == '%') { //if there are no children nodes, then pop the node from the stack.
					y-=1;
				}
				else{
					y += 1;
					Node node = new Node(x,y);
					path.put(y,x);
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
