package Search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/*
 * DFS (Depth First Search)
 */
public class DFS {
	public int startingX;
	public int startingY;
	public HashMap<Integer, Integer> path = new HashMap<Integer,Integer>();
	public Frontier frontier = new Frontier();
	public char[][] matrix = null;
	
	public DFS() {
		
	}
	
	public void solve(Maze maze) {
		
		matrix = maze.getMatrix();
		getInitialState(matrix); //step 1: get initial state.
		int y = startingY; 
		int x = startingX;
		Node root = new Node(x,y);
		frontier.push(root); //push initial state onto frontier. path is empty.
		path.put(y,x);      //add to output sequence
		System.out.println(x+ ": " + y);
		matrix[y][x] = '.';  //mark the node as visited
		System.out.println(matrix[y][x]);
		boolean goal = false;
		
		int i = 0;
		while(!goal) {
			//System.out.println(x+":"+y);
			if(path.isEmpty()) { //Return fail if the frontier is null.
				System.out.println("FAIL.");
				System.exit(0);
			}
			
			if(!visited(y, x+1)) { //if we have not expanded the node to the right.
				if(matrix[y][x] == '%') { //if there are no children nodes, then pop the node from the stack.
					frontier.pop();
					x-=1;
				}
				else{
					x += 1;
					Node node = new Node(x,y);
					frontier.push(node);
					path.put(y,x);
				}
			}
			else if(!visited(y -1, x)) {//if we have not expanded the node above.
				if(matrix[y-1][x] == '%') { //if there are no children nodes, then pop the node from the stack.
					frontier.pop();
					y+=1;
				}
				else{
					y -= 1;
					Node node = new Node(x,y);
					frontier.push(node);
					path.put(y,x);
				}
			}
			else if(!visited(y, x-1)) { //if we have not expanded the node to the left.
				if(matrix[y][x-1] == '%') { //if there are no children nodes, then pop the node from the stack.
					frontier.pop();
					x+=1;
				}
				else{
					x -= 1;
					Node node = new Node(x,y);
					frontier.push(node);
					path.put(y,x);
				}
			}
			else if(!visited(y+1,x)) {//if we have not expanded the node below.
				if(matrix[y+1][x] == '%') { //if there are no children nodes, then pop the node from the stack.
					frontier.pop();
					y-=1;
				}
				else{
					y += 1;
					Node node = new Node(x,y);
					frontier.push(node);
					path.put(y,x);

				}
			}
			goal = checkForGoalState(y,x); //Step 4: Check for goal state
			matrix[y][x] = '.'; //mark the node as visited.
			i++;
		}
		printStack();
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
			frontier.push(y,x-1);
			path.put(x-1, y); //add the x and y position to the path list
		}
		else if(matrix[y+1][x] == '*'){//if the goal is above
			goal = true;
			frontier.push(y+1,x);
			path.put(x, y+1); //add the x and y position to the path list
		}
		else if(matrix[y][x+1] == '*'){//if the goal is to the right
			goal = true;
			frontier.push(y,x+1);
			path.put(y,x+1); //add the x and y position to the path list
		}
		else if(matrix[y-1][x] == '*'){//if the goal is below
			goal = true;
			frontier.push(y-1,x);
			path.put(y-1,x); //add the x and y position to the path list
		}
		return goal;
	}
	public void printStack() {
		for(int i = 0; i < matrix.length; i++) { //looping through columns
			for(int y = 0; y < matrix[0].length; y++) { //looping through rows
     			System.out.print(matrix[i][y]);
     		}
			System.out.println();
		}
		ArrayList<Integer> x = frontier.getStackX();
		ArrayList<Integer> y = frontier.getStackY();
		for(int i = 0; i < x.size(); i++) {
			System.out.println(x.get(i) + "," + y.get(i));
		}
	}
	public void getInitialState(char[][] matrix) { //helper method to get the inital state.
		for(int i = 0; i < matrix.length; i++) { //looping through columns
			for(int y = 0; y < matrix[0].length; y++) { //looping through rows
     			if(matrix[i][y] == 'P') {
     				startingX = y;
     				startingY = i;
     				break;
     			}
     		}
		}
	}
}

