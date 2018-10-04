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
		int y = startingY; //swap x and y because of how the maze is created.
		int x = startingX;
		frontier.push(x,y); //push initial state onto frontier. path is empty.
		path.put(x,y);      //add to output sequence
		matrix[x][y] = '.';  //mark the node as visited
		System.out.println(matrix[x][y]);
		boolean goal = false;
		int i = 0;
		while(i < 1000) {
			System.out.println(x+ ": " + y);
			System.out.println(matrix[y][x]);
			if(path.isEmpty()) { //Return fail if the frontier is null.
				System.out.println("FAIL.");
				System.exit(0);
			}
			if(!visited(x+1,y)) { //if we have not expanded the node to the right.
				if(matrix[x+1][y] == '%') { //if there are no children nodes, then pop the node from the stack.
					frontier.pop();
					System.out.println("HI");
					return;
				}
				//else{
					frontier.push(x+1,y);
					x += 1;
				//}
				path.put(x+1,y);
			}
			else if(!visited(x-1,y)) { //if we have not expanded the node to the left.
				if(matrix[x-1][y] == '%') { //if there are no children nodes, then pop the node from the stack.
					frontier.pop();
				}
				//else{
					frontier.push(x-1,y);
					x -= 1;
				//}
				path.put(x-1, y);
			}
			else if(!visited(x,y+1)) {//if we have not expanded the node below.
				if(matrix[x][y+1] == '%') { //if there are no children nodes, then pop the node from the stack.
					frontier.pop();
				}
				//else{
					frontier.push(x,y+1);
					y += 1;
				//}
				path.put(x, y+1);
			}
			else if(!visited(x,y-1)) {//if we have not expanded the node above.
				if(matrix[x][y-1] == '%') { //if there are no children nodes, then pop the node from the stack.
					frontier.pop();
				}
				//else{
					frontier.push(x,y-1);
					y -= 1;
				//}
				path.put(x, y-1);
			}
			matrix[x][y] = '.'; //mark the node as visited.
			goal = checkForGoalState(x,y); //Step 4: Check for goal state
			i++;
		}
		printStack();
	}
	
	public boolean visited(int x, int y) {
		if(path.containsKey(x) && path.get(x) != y) { //return false if we have not visited a spot.
			System.out.println("hi");
			return false;
		}
		else { //return true if we have visited the spot
			return true;
		}
	}
	public boolean checkForGoalState(int x, int y) {
		boolean goal = false;
		if(matrix[x-1][y] == '*'){//if the goal is to the left
			goal = true;
			frontier.push(x-1, y);
			path.put(x-1, y); //add the x and y position to the path list
		}
		else if(matrix[x][y+1] == '*'){//if the goal is above
			goal = true;
			frontier.push(x, y+1);
			path.put(x, y+1); //add the x and y position to the path list
		}
		else if(matrix[x+1][y] == '*'){//if the goal is to the right
			goal = true;
			frontier.push(x+1, y);
			path.put(x+1, y); //add the x and y position to the path list
		}
		else if(matrix[x][y-1] == '*'){//if the goal is below
			goal = true;
			frontier.push(x, y-1);
			path.put(x, y-1); //add the x and y position to the path list
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
     				startingX = i;
     				startingY = y;
     				break;
     			}
     		}
		}
	}
}

