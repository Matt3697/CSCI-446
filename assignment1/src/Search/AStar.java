package Search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
/*
 * Astar (A* Search)
 */
public class AStar {
	
	int curCost, estimation;
	boolean finished = false;
	int[] goalState;
	int[] curPosition;
	char[][] puzzle = null;
	ArrayList<Node> path;
	ArrayList<Integer[]> visited;
	Frontier frontier;
	

	public AStar() {
		frontier = new Frontier();
	}
	
	public void solve(Maze puzzle) {
		this.puzzle = puzzle.getMatrix();
		goalState = findGoalState();
		curPosition = findInitialState();
		
		
		estimation = estimateCost();
		while(isFinished()) {
			curPosition = findGoalStateAStar(curPosition);
		}
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
		
		if(puzzle[curPosition[0]][curPosition[1]] == '*') {
			setCompleted();
			return curPosition;
		}
		
		expandFrontier(curPosition);
		return findNextShortestCost();
	}
	
	public int[] indNextShortestCost() {
		
	}
	
	public void expandFrontier(int[] curPos) {
		int x, y;
		for(int i = 0; i < 4; i++)
		{
			switch (i) {
			case 0:
				x = curPos[0];
				y = curPos[1] - 1;
				if(checkVisited(x, y)) {
					Node newNode = new Node(x, y);
					newNode.setCost(estimateNodeCost(curPos, x, y));
					frontier.push(newNode);
				}
				break;

			case 1:
				x = curPos[0];
				y = curPos[1] + 1;
				if(checkVisited(x, y)) {
					Node newNode = new Node(x, y);
					newNode.setCost(estimateNodeCost(curPos, x, y));
					frontier.push(newNode);
				}
				break;

			case 2:
				x = curPos[0] - 1;
				y = curPos[1];
				if(checkVisited(x, y)) {
					Node newNode = new Node(x, y);
					newNode.setCost(estimateNodeCost(curPos, x, y));
					frontier.push(newNode);
				}
				break;

			case 3:
				x = curPos[0] + 1;
				y = curPos[1];
				if(checkVisited(x, y)) {
					Node newNode = new Node(x, y);
					newNode.setCost(estimateNodeCost(curPos, x, y));
					frontier.push(newNode);
				}
				break;

			default:
				break;
			}
		}
	}
	
	public boolean checkVisited(int x, int y) {
		Integer[] pos = {x, y};
		
		for(int i = visited.size() - 1; i >= 0; i--) {
			if(Arrays.equals(pos, visited.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	public void setCompleted() {
		finished = true;
	}
	
	public boolean isFinished() {
		return finished;
	}
	
	public int estimateNodeCost(int[] curPosition, int newX, int newY) {
		int cost, x, y;
		x = Math.abs(curPosition[0] - newX);
		y = Math.abs(curPosition[1] - newY);
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
}
