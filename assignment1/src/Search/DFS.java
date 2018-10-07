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
	public Stack<Node> nodes = new Stack<Node>();
	public Frontier frontier = new Frontier();
	public char[][] matrix = null;
	
	public DFS() {
		
	}
	
	public void solve(Maze maze) {
		matrix = maze.getMatrix();
		getInitialState(matrix); //step 1: get initial state.
		int y = startingY; 
		int x = startingX;
		//Step One: push starting node to the stack,update the path, and mark it as visited.
		boolean goal = false;
		Node root = new Node(x,y);
		root.setParent(null);
		nodes.push(root);
		path.put(y, x);
		Node node = null;
		Node lastnode = root;
		matrix[y][x] = '.';
		int i = 0;
		//Step Two: while node has left child, and the goal is not met, follow left branch.
		//Once there is no left child,find closest parent with a right child. follow its right branch.
		while(!goal) {
			System.out.println(i);
			printMaze();
			int numberSurroundings = 0;
			if(!visited(y, x+1)) { //if we have not visited the space to the right.
				x += 1;
				node = new Node(x,y);
				path.put(y,x);
				node.setParent(lastnode);
				numberSurroundings = checkSurroundings(node);
				System.out.println("num:" + numberSurroundings);
				if(matrix[y][x+1] == '%'){
						handleWall(node);
				}
			}
			else if(!visited(y-1, x)) {//if we have not visited the space above.
				y -= 1;
				node = new Node(x,y);
				path.put(y,x);
				node.setParent(lastnode);
				numberSurroundings = checkSurroundings(node);
				System.out.println("num:" + numberSurroundings);
				if(matrix[y-1][x] == '%'){
					handleWall(node);
				}
			}
			else if(!visited(y, x-1)) { //if we have not visited the space to the left.
				x -= 1;
				node = new Node(x,y);
				path.put(y,x);
				node.setParent(lastnode);
				numberSurroundings = checkSurroundings(node);
				System.out.println("num:" + numberSurroundings);
				if(matrix[y][x-1] == '%'){
					handleWall(node);
				}
			}
			else if(!visited(y+1,x)) {//if we have not visited the space below.
				y += 1;
				node = new Node(x,y);
				path.put(y,x);
				node.setParent(lastnode);
				numberSurroundings = checkSurroundings(node);
				System.out.println("num:" + numberSurroundings);
				if(matrix[y+1][x] == '%'){
					handleWall(node);
				}
			}
			if(numberSurroundings > 1) { //if there are alternate paths at a node, set alternatePaths to true.
				node.setAlternatePath(true);
			}
			if(matrix[y][x] == ' ') {
				lastnode.setLeftChild(node);	
				matrix[y][x] = '.';
			}
			goal = checkForGoalState(y,x); //Check for goal state
			lastnode = node;
			i++;
		}
	}
	public Node getNextPath(Node node) {//find the closest parent node with another possible path.
		while(!node.getParent().hasAlternatePath()) {
			System.out.println(node.getX());
			node = node.getParent();
		}
		return node;
	}
	public void handleWall(Node node) {
		Node ancestorNode = getNextPath(node);
		int lastX = ancestorNode.getX();
		int lastY = ancestorNode.getY();
		int x = ancestorNode.getPossiblePathX();
		int y = ancestorNode.getPossiblePathY();
		node = new Node(x,y);
		node.setParent(ancestorNode);
		ancestorNode.setRightChild(node);
	}
	public int checkSurroundings(Node node) {//checks to see if there are any alternate paths at this node.
		int counter = 0;
		int y = node.getY();
		int x = node.getX();
		if(matrix[y][x+1] == ' ' && !visited(y,x+1)) {
			counter++;
			node.addPossiblePathX(x+1); 
			node.addPossiblePathY(y);
		}
		if(matrix[y][x-1] == ' ' && !visited(y,x-1)) {
			counter++;
			node.addPossiblePathX(x-1); 
			node.addPossiblePathY(y);		}
		if(matrix[y+1][x] == ' ' && !visited(y+1,x)) {
			counter++;
			node.addPossiblePathX(x); 
			node.addPossiblePathY(y+1);		}
		if(matrix[y-1][x] == ' ' && !visited(y-1,x)) {
			counter++;
			node.addPossiblePathX(x); 
			node.addPossiblePathY(y-1);		}
		return counter;
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
	public boolean visited(int y, int x) {
		if(path.containsKey(y) && path.get(y) != x) { //return false if we have not visited a spot.
			return false;
		}
		else { //return true if we have visited the spot
			return true;
		}
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
	public void printMaze() {
		for(int i = 0; i < matrix.length; i++) { //looping through columns
			for(int y = 0; y < matrix[0].length; y++) { //looping through rows
     			System.out.print(matrix[i][y]);
     		}
			System.out.println();
		}
	}
}

