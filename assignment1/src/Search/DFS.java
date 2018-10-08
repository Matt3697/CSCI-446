package Search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import javafx.scene.shape.Path;

/*
 * DFS (Depth First Search)
 */
public class DFS {
	public int startingX;
	public int startingY;
	public ArrayList<Integer> pathX = new ArrayList<Integer>();
	public ArrayList<Integer> pathY = new ArrayList<Integer>();
	public Stack<Node> nodes = new Stack<Node>();
	public Frontier frontier = new Frontier();
	public char[][] matrix = null;
	public Node ancestorNode = null;
	
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
		pathX.add(x);
		pathY.add(y);
		Node node = null;
		Node lastnode = root;
		matrix[y][x] = '.';
		int i = 0;
		//Step Two: while node has left child, and the goal is not met, follow left branch.
		//Once there is no left child,find closest parent with a right child. follow its right branch.
		while(!goal) {
			int numberSurroundings = 0;
			if(!visited(y, x+1) && matrix[y][x+1] != '%') { //if we have not visited the space to the right.
				x += 1;
				node = new Node(x,y);
				nodes.push(node);
				pathX.add(x);
				pathY.add(y);
				if(matrix[y][x] == ' ' || matrix[y][x] == '*') {
					lastnode.setLeftChild(node);	
					matrix[y][x] = '.';
				}
				node.setParent(lastnode);
				if(matrix[y][x] != '%') {
					numberSurroundings = checkSurroundings(node);
				}
				if(numberSurroundings == 0) { //if we've reached a dead end.
					if(checkForGoalState(y,x)) {
						goal = (checkForGoalState(y,x)); //check to make sure its not the goal
						printMaze();
						return;
					}
					else {
						int[] xy = handleWall(node);
						x = xy[0];
						y = xy[1];
					}
				}
				if(numberSurroundings > 1) { //if there are alternate paths at a node, set alternatePaths to true.
					node.setAlternatePath(true);
				}
			}
			else if(!visited(y-1, x)&& matrix[y-1][x] != '%') {//if we have not visited the space above.
				y -= 1;
				node = new Node(x,y);
				nodes.push(node);
				pathX.add(x);
				pathY.add(y);
				if(matrix[y][x] == ' ' || matrix[y][x] == '*') {
					lastnode.setLeftChild(node);	
					matrix[y][x] = '.';
				}
				node.setParent(lastnode);
				if(matrix[y][x] != '%') {
					numberSurroundings = checkSurroundings(node);
				}
				if(numberSurroundings == 0) { //if we've reached a dead end.
					if(checkForGoalState(y,x)) {
						goal = (checkForGoalState(y,x)); //check to make sure its not the goal
						matrix[y][x] = '.';
					}
					else {
						int[] xy = handleWall(node);
						x = xy[0];
						y = xy[1];
					}
				}
				if(numberSurroundings > 1) { //if there are alternate paths at a node, set alternatePaths to true.
					node.setAlternatePath(true);
				}				
			}
			else if(!visited(y, x-1)&& matrix[y][x-1] != '%') { //if we have not visited the space to the left.
				x -= 1;
				node = new Node(x,y);
				nodes.push(node);
				pathX.add(x);
				pathY.add(y);
				if(matrix[y][x] == ' ' || matrix[y][x] == '*') {
					lastnode.setLeftChild(node);	
					matrix[y][x] = '.';
				}
				node.setParent(lastnode);
				if(matrix[y][x] != '%') {
					numberSurroundings = checkSurroundings(node);
				}
				if(numberSurroundings == 0) { //if we've reached a dead end.
					if(checkForGoalState(y,x)) {
						goal = (checkForGoalState(y,x)); //check to make sure its not the goal
						matrix[y][x] = '.';
					}
					else {
						int[] xy = handleWall(node);
						x = xy[0];
						y = xy[1];
					}
				}
				if(numberSurroundings > 1) { //if there are alternate paths at a node, set alternatePaths to true.
					node.setAlternatePath(true);
				}
			}
			else if(!visited(y+1,x)&& matrix[y+1][x] != '%') {//if we have not visited the space below.
				y += 1;
				node = new Node(x,y);
				nodes.push(node);
				pathX.add(x);
				pathY.add(y);
				if(matrix[y][x] == ' ' || matrix[y][x] == '*') {
					lastnode.setLeftChild(node);	
					matrix[y][x] = '.';
				}
				node.setParent(lastnode);
				if(matrix[y][x] != '%') {
					numberSurroundings = checkSurroundings(node);
				}
				if(numberSurroundings == 0) { //if we've reached a dead end.
					if(checkForGoalState(y,x)) {
						goal = (checkForGoalState(y,x)); //check to make see if its the goal.
						matrix[y][x] = '.';
						return;
					}
					else {
						int[] xy = handleWall(node);
						x = xy[0];
						y = xy[1];
					}
				}
				if(numberSurroundings > 1) { //if there are alternate paths at a node, set alternatePaths to true.
					node.setAlternatePath(true);
				}
			}
			else {
				x = ancestorNode.getX();
				y = ancestorNode.getY();
			}
			lastnode = node;
			i++;
		}
	}
	public Node getNextPath(Node node) {//find the closest parent node with another possible path.
		while(!node.hasAlternatePath()) {
			node = nodes.pop();
		}
		return node;
	}
	public int[] handleWall(Node node) {
		int[] xy = new int[2];
		ancestorNode = getNextPath(node);
		int x = ancestorNode.getX();
		int y = ancestorNode.getY();
		xy[0] = x;
		xy[1] = y;
		node = new Node(x,y);
		node.setParent(ancestorNode);
		ancestorNode.setRightChild(node);
		return xy;
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
			node.addPossiblePathY(y);		
		}
		if(matrix[y+1][x] == ' ' && !visited(y+1,x)) {
			counter++;
			node.addPossiblePathX(x); 
			node.addPossiblePathY(y+1);		
		}
		if(matrix[y-1][x] == ' ' && !visited(y-1,x)) {
			counter++;
			node.addPossiblePathX(x); 
			node.addPossiblePathY(y-1);		
		}
		return counter;
	}
	public boolean checkForGoalState(int y, int x) {
		boolean goal = false;
		if(matrix[y][x+1] == '*'){//if we are at the goal
			goal = true;
			pathX.add(x);
			pathY.add(y);
			matrix[y][x+1] = '.';
		}
		else if(matrix[y][x-1] == '*') {
			goal = true;
			pathX.add(x);
			pathY.add(y);
			matrix[y][x-1] = '.';
		}
		else if(matrix[y-1][x] == '*') {
			goal = true;
			pathX.add(x);
			pathY.add(y);
			matrix[y-1][x] = '.';
		}
		else if(matrix[y+1][x] == '*') {
			goal = true;
			pathX.add(x);
			pathY.add(y);
			matrix[y+1][x] = '.';
		}
		return goal;
	}
	public boolean visited(int y, int x) {
		for(int i = 0; i < pathX.size(); i++) {
			if(pathX.get(i) == x && pathY.get(i) == y) { //return true if we have visited a spot.
				return true;
			}
		}
		return false;
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
	public void getPathCost() {
		System.out.println("start: " + "(" + startingY + ", " + startingX + ")");
		System.out.println("total cost: " + nodes.size());
	}
}

