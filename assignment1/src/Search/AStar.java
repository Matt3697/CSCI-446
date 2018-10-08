package Search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
/*
 * Astar (A* Search)
 */
public class AStar {
	
	int curCost, estimation, expanded;
	boolean finished = false;
	int[] goalState;
	int[] curPosition;
	char[][] puzzle = null;
	ArrayList<Node> path;
	ArrayList<Integer[]> visited;
	Frontier frontier;
	

	public AStar() {
		frontier = new Frontier();
		visited = new ArrayList<Integer[]>();
	}
	
	public void initPuzzle(Maze puzzle) {
		this.puzzle = puzzle.getMatrix();
		goalState = findGoalState();
		curPosition = findInitialState();
		estimation = estimateCost();
		
		while(!isFinished()) {
			curPosition = findGoalStateAStar(curPosition);
		}
		
		System.out.println("Cost: " + curCost);
		System.out.println("Nodes Expanded: " + expanded);
	}

	public int[] findGoalState() {
		int[] goalLocation = new int[2];
		char goalstate = '*';
		
		for(int i = 0; i < puzzle.length; i++) {
			for(int j = 0; j < puzzle[i].length; j++) {
				if(puzzle[i][j] == goalstate) {
					goalLocation[0] = i;	//i = row
					goalLocation[1] = j;	//j = column
					return goalLocation;
				}
			}
		}
		System.out.println("Goal state not found");
		return goalLocation;
	}
	
	public int[] findInitialState() {
		int[] initialLocation = new int[2];
		char goalstate = 'P';
		
		for(int i = 0; i < puzzle.length; i++) {
			for(int j = 0; j < puzzle[i].length; j++) {
				if(puzzle[i][j] == goalstate) {
					initialLocation[0] = i;	//i = row
					initialLocation[1] = j;	//j = column
					return initialLocation;
				}
			}
		}
		System.out.println("Goal state not found");
		return initialLocation;
	}
	
	public int[] findGoalStateAStar(int[] curPosition) {
		curCost ++;
		addToVisited(curPosition);
		
		if(puzzle[curPosition[0]][curPosition[1]] == '*') {
			setCompleted();
			return curPosition;
		}
		
		expandFrontier(curPosition);
		updatePuzzle(curPosition);
		printMaze();
		return findNextSmallestCost();
	}
	
	public int[] findNextSmallestCost() {
		int[] newPos = new int[2];
		Node nextMove = frontier.findSmallestCost();
		
		newPos[0] = nextMove.getX();
		newPos[1] = nextMove.getY();
		
		return newPos;
	}
	
	public void expandFrontier(int[] curPos) {
		int x, y;
		for(int i = 0; i < 4; i++)
		{
			switch (i) {
			case 0:
				x = curPos[0];
				y = curPos[1] - 1;
				if(!checkVisited(x, y)) {
					try{
						if(puzzle[x][y] != '%') {
							Node newNode = new Node(x, y);
							newNode.setCost(estimateNodeCost(curPos, x, y));
							frontier.addNode(newNode);
							expanded++;
						}
					}
					catch(Exception e){
						//Out of Bounds
					}
				}
				break;

			case 1:
				x = curPos[0];
				y = curPos[1] + 1;
				if(!checkVisited(x, y)) {
					try{
						if(puzzle[x][y] != '%') {
							Node newNode = new Node(x, y);
							newNode.setCost(estimateNodeCost(curPos, x, y));
							frontier.addNode(newNode);
							expanded++;
						}
					}
					catch(Exception e){
						//Out of Bounds
					}
				}
				break;

			case 2:
				x = curPos[0] - 1;
				y = curPos[1];
				if(!checkVisited(x, y)) {
					try{
						if(puzzle[x][y] != '%') {
							Node newNode = new Node(x, y);
							newNode.setCost(estimateNodeCost(curPos, x, y));
							frontier.addNode(newNode);
							expanded++;
						}
					}
					catch(Exception e){
						//Out of Bounds
					}
				}
				break;

			case 3:
				x = curPos[0] + 1;
				y = curPos[1];
				if(!checkVisited(x, y)) {
					try{
						if(puzzle[x][y] != '%') {
							Node newNode = new Node(x, y);
							newNode.setCost(estimateNodeCost(curPos, x, y));
							frontier.addNode(newNode);
							expanded++;
						}
					}
					catch(Exception e){
						//Out of Bounds
					}
				}
				break;

			default:
				break;
			}
		}
	}
	
	public boolean checkVisited(int x, int y) {
		Integer[] pos = {x, y};
//		System.out.println("WTF: " + visited.size());
//		if(visited.size() != 0) {
			for(int i = visited.size() - 1; i >= 0; i--) {
//				int xcomp = visited.get(i)[0];
//				int ycomp = visited.get(i)[1];
//				System.out.println("TEST: " + x + " " + y + " " + xcomp + " " + ycomp);
				if(Arrays.equals(pos, visited.get(i))) {
//				if(xcomp == x && ycomp == y) {
//					System.out.println("MAYBEWORKING?");
					return true;
				}
			}
//		}
		return false;
	}
	
	public void addToVisited(int[] curPos) {
		Integer[] toAdd = new Integer[2];
		toAdd[0] = curPos[0];
		toAdd[1] = curPos[1];
		visited.add(toAdd);
	}
	
	public void setCompleted() {
		finished = true;
		System.out.println("Finished");
	}
	
	public boolean isFinished() {
		return finished;
	}
	
	public int estimateNodeCost(int[] curPosition, int newX, int newY) {
		int cost, x, y;
//		x = Math.abs(curPosition[0] - newX);
//		y = Math.abs(curPosition[1] - newY);
		x = Math.abs(newX - goalState[0]);
		y = Math.abs(newY - goalState[1]);
		cost = x + y;
		return cost;
	}
	
	
	public int estimateCost(int[] curPosition) {
		int cost, x, y;
		x = Math.abs(curPosition[0] - goalState[0]);
		y = Math.abs(curPosition[1] - goalState[1]);
		cost = x + y;
		return cost;
	}
	
	public int estimateCost() {
		int cost, x, y;
		x = Math.abs(curPosition[0] - goalState[0]);
		y = Math.abs(curPosition[1] - goalState[1]);
		cost = x + y;
		return cost;
	}
	
	public void updatePuzzle(int[] curPos) {
		puzzle[curPos[0]][curPos[1]] = '.';
	}
	
	public void printMaze() {
		for(int i = 0; i < puzzle.length; i++) { //looping through columns
			for(int y = 0; y < puzzle[0].length; y++) { //looping through rows
     			System.out.print(puzzle[i][y]);
     		}
			System.out.println();
		}
	}
}
