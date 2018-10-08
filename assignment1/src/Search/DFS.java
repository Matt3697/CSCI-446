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
		getInitialState(matrix);
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
		maze.printMaze();
		Node[][] nodeMaze;
		Node currNode = maze.getStartingPoint();
		nodeMaze = maze.getNodeMatrix(); // Get maze as a 2D array of Nodes
		//System.out.println("start: (" + currNode.getX() + ", " + currNode.getY() + ")");  
		int explored = 0;
		nodes.push(currNode);
		while(!nodes.isEmpty()) {
			
			currNode = nodes.pop();
			if (currNode.getValue() == '*') { // End has been found
				System.out.println("total cost: " + currNode.getCost());
				System.out.println("total explored: " + explored);
				while (currNode.getValue() != 'P') { // Update visual path of least cost in the maze, represented with 'o' char
					maze.updateValue(currNode.getX(), currNode.getY(), 'o');
					currNode = currNode.getPrev();
				}
				maze.updateValue(currNode.getX(), currNode.getY(), 'o'); 
				maze.printMaze();
			}
			
			else {
				for (Node n : currNode.getNeighbors()) {
					if (!n.isVisited()) { // If neighbor has not been visited, add it to queue and update cost
						n.setVisited();
						n.setCost(currNode.getCost() + 1); // Cost is current node cost + 1 for the neighbor
						n.setPrev(currNode); // Previous node of neighbor is current node
						maze.updateValue(n.getX(), n.getY(), '.'); // (optional) Update char maze to represent nodes that have been visited
						nodes.push(n);
						explored++;
					}
				}
				
			}
			/*int numberSurroundings = 0;
			System.out.println(x + ":" + y);
			if(!visited(y, x+1) && matrix[y][x+1] != '%') { //if we have not visited the space to the right.
				x += 1;
				node = new Node(x,y);
				nodes.push(node);
				pathX.add(x);
				pathY.add(y);
				if(matrix[y][x] == ' ') {
					lastnode.setLeftChild(node);	
					matrix[y][x] = '.';
				}
				node.setParent(lastnode);
				if(matrix[y][x] != '%') {
					numberSurroundings = checkSurroundings(node);
				}
				if(numberSurroundings >= 1) { //if there are alternate paths at a node, set alternatePaths to true.
					node.setAlternatePath(true);
				}
				if(numberSurroundings <= 1 && matrix[y][x+1] == '%') { //if we've reached a wall
					if(checkForGoalState(y,x)) {
						goal = (checkForGoalState(y,x)); //check to make sure its not the goal
						printMaze();
						return;
					}
					else {
						node = handleWall(node);
						x = node.getX();
						y = node.getY();
					}
				}	
			}
			else if(!visited(y-1, x)&& matrix[y-1][x] != '%') {//if we have not visited the space above.
				y -= 1;
				node = new Node(x,y);
				nodes.push(node);
				pathX.add(x);
				pathY.add(y);
				if(matrix[y][x] == ' ') {
					lastnode.setLeftChild(node);	
					matrix[y][x] = '.';
				}
				node.setParent(lastnode);
				if(matrix[y][x] != '%') {
					numberSurroundings = checkSurroundings(node);
				}
				if(numberSurroundings >= 1) { //if there are alternate paths at a node, set alternatePaths to true.
					node.setAlternatePath(true);
				}
				if(numberSurroundings <= 1 && matrix[y-1][x] == '%') { //if we've reached a wall
					if(checkForGoalState(y,x)) {
						goal = (checkForGoalState(y,x)); //check to make sure its not the goal
						printMaze();
						return;
					}
					else {
						node = handleWall(node);
						x = node.getX();
						y = node.getY();
					}
				}			
			}
			else if(!visited(y, x-1)&& matrix[y][x-1] != '%') { //if we have not visited the space to the left.
				x -= 1;
				node = new Node(x,y);
				nodes.push(node);
				pathX.add(x);
				pathY.add(y);
				if(matrix[y][x] == ' ') {
					lastnode.setLeftChild(node);	
					matrix[y][x] = '.';
				}
				node.setParent(lastnode);
				if(matrix[y][x] != '%') {
					numberSurroundings = checkSurroundings(node);
				}
				if(numberSurroundings >= 1) { //if there are alternate paths at a node, set alternatePaths to true.
					node.setAlternatePath(true);
				}
				if(numberSurroundings <= 1 && matrix[y][x-1] == '%') { //if we've reached a wall
					if(checkForGoalState(y,x)) {
						goal = (checkForGoalState(y,x)); //check to make sure its not the goal
						printMaze();
						return;
					}
					else {
						node = handleWall(node);
						x = node.getX();
						y = node.getY();
					}
				}	
			}
			else if(!visited(y+1,x)&& matrix[y+1][x] != '%') {//if we have not visited the space below.
				y += 1;
				node = new Node(x,y);
				nodes.push(node);
				pathX.add(x);
				pathY.add(y);
				if(matrix[y][x] == ' ') {
					lastnode.setLeftChild(node);	
					matrix[y][x] = '.';
				}
				node.setParent(lastnode);
				if(matrix[y][x] != '%') {
					numberSurroundings = checkSurroundings(node);
				}
				if(numberSurroundings >= 1) { //if there are alternate paths at a node, set alternatePaths to true.
					node.setAlternatePath(true);
				}
				if(numberSurroundings <= 1 && matrix[y+1][x] == '%') { //if we've reached a wall
					if(checkForGoalState(y,x)) {
						goal = (checkForGoalState(y,x)); //check to make sure its not the goal
						printMaze();
						return;
					}
					else {
						node = handleWall(node);
						x = node.getX();
						y = node.getY();
					}
				}	
			}
			else {
				node = handleWall(node);
				x = node.getX();
				y = node.getY();
				//x = ancestorNode.getX();
				//y = ancestorNode.getY();
			}
			lastnode = node;
			i++;
			printMaze();*/
		}
	}
	public Node getNextPath(Node node) {//find the closest parent node with another possible path.
		while(!node.hasAlternatePath()) {
			node = nodes.pop();
		}
		return node;
	}
	public Node handleWall(Node node) {
		int[] xy = new int[2];
		ancestorNode = getNextPath(node);
		int x = ancestorNode.getX();
		int y = ancestorNode.getY();
		xy[0] = x;
		xy[1] = y;
		node = new Node(x,y);
		node.setParent(ancestorNode);
		ancestorNode.setRightChild(node);
		return node;
	}
	public int checkSurroundings(Node node) {//checks to see if there are any alternate paths at this node.
		int counter = 0;
		int y = node.getY();
		int x = node.getX();
		if(matrix[y][x+1] == ' ' && !visited(y,x+1)) {
			Node childnode = new Node(y,x+1);
			if(counter == 0) {
				node.setLeftChild(childnode);
			}
			else {
				node.setRightChild(childnode);
			}
			node.addPossiblePathX(x+1); 
			node.addPossiblePathY(y);
			counter++;
		}
		if(matrix[y][x-1] == ' ' && !visited(y,x-1)) {
			
			Node childnode = new Node(y,x-1);
			if(counter == 0) {
				node.setLeftChild(childnode);
			}
			else {
				node.setRightChild(childnode);
			}
			node.addPossiblePathX(x-1); 
			node.addPossiblePathY(y);	
			counter++;
		}
		if(matrix[y+1][x] == ' ' && !visited(y+1,x)) {
			
			Node childnode = new Node(y+1,x);
			if(counter == 0) {
				node.setLeftChild(childnode);
			}
			else {
				node.setRightChild(childnode);
			}
			node.addPossiblePathX(x); 
			node.addPossiblePathY(y+1);	
			counter++;
		}
		if(matrix[y-1][x] == ' ' && !visited(y-1,x)) {
			Node childnode = new Node(y-1,x);
			if(counter == 0) {
				node.setLeftChild(childnode);
			}
			else {
				node.setRightChild(childnode);
			}		
			node.addPossiblePathX(x); 
			node.addPossiblePathY(y-1);
			counter++;
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

