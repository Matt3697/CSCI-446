package flowFree;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class GDFS
{
	int totalCost, estimation, expanded, smallestCost;
	boolean finished = false;
	int[] goalState;
	int[] curPosition;
	int[] initialPosition;
	char[][] puzzle = null;
	ArrayList<Node> path;
	ArrayList<Integer[]> visited;
	Frontier frontier;
	

	public GDFS() {
	}
	
	public boolean initSearch(char[][] puzzle, int[] ini, int[] goal, char c) {
		initialPosition = ini;
		goalState = goal;
		curPosition = ini;
		frontier = new Frontier();
		visited = new ArrayList<Integer[]>();
		this.puzzle = puzzle;
		estimation = estimateCost();
		
		while(!isFinished()) {
			curPosition = GDFSearch(curPosition, c);
		}
		return true;
	}
	
	public void resetVariables() {
		totalCost = 0;
		estimation = 0;
		expanded = 0;
		smallestCost = 0;
	}
	
	public int[] GDFSearch(int[] curPosition, char c) {
		
		if(puzzle[curPosition[0]][curPosition[1]] == c) {
			setCompleted();
			return curPosition;
		}
		
		expandFrontier(curPosition, c);
		return findNextSmallestCost();
	}
	
	public int[] findNextSmallestCost() {
		int[] newPos = new int[2];
		Node nextMove = frontier.findSmallestCost();
		
		newPos[0] = nextMove.getX();
		newPos[1] = nextMove.getY();
		
		return newPos;
	}
	
	public void expandFrontier(int[] curPos, char c) {
		int x, y;
		
		for(int i = 0; i < 4; i++)
		{
			switch (i) {
			case 0:
				x = curPos[0];
				y = curPos[1] - 1;
				if(!checkVisited(x, y)) {
					try{
						if(puzzle[x][y] == '_' || puzzle[x][y] == c) {
							Node newNode = new Node(x, y);
							newNode.setCost(estimateNodeCost(x, y));
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
						if(puzzle[x][y] == '_' || puzzle[x][y] == c){
							Node newNode = new Node(x, y);
							newNode.setCost(estimateNodeCost(x, y));
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
						if(puzzle[x][y] == '_' || puzzle[x][y] == c) {
							Node newNode = new Node(x, y);
							newNode.setCost(estimateNodeCost(x, y));
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
							if(puzzle[x][y] == '_' || puzzle[x][y] == c);
							newNode.setCost(estimateNodeCost(x, y));
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
			for(int i = visited.size() - 1; i >= 0; i--) {
				if(Arrays.equals(pos, visited.get(i))) {
					return true;
				}
			}
		visited.add(pos);
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
	}
	
	public boolean isFinished() {
		return finished;
	}
	
	public int estimateNodeCost(int newX, int newY) {
		int cost, x, y;
		x = Math.abs(newX - goalState[0]);
		y = Math.abs(newY - goalState[1]);
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

	public class Frontier {
		public Stack<Integer> xLoc = new Stack<Integer>();
		public Stack<Integer> yLoc = new Stack<Integer>();
		public Stack<Node> nodes = new Stack<Node>();
		public ArrayList<Node> nodeList;
		public Node curNode;

		public Frontier() {
			nodeList = new ArrayList<Node>();
		}
		
		public void push(Node node) {
			nodes.push(node);
		}
		
		public void pop() {
			nodes.pop();
		}
		
		public void addNode(Node node) {
			nodeList.add(node);
		}
		
		public Node getCurNode() {
			return curNode;
		}
		
		public void setNode(Node n) {
			curNode = n;
		}
		
		public Node findSmallestCost() {
			int pos = 0;
			int cost = nodeList.get(0).getCost();
			curNode = nodeList.get(0);
			
			for(int i = 1; i < nodeList.size(); i++) {
				if(nodeList.get(i).getCost() < cost) {
					cost = nodeList.get(i).getCost();
					curNode = nodeList.get(i);
					pos = i;
				}
			}
			nodeList.remove(pos);
			return curNode;
		}
		
		public ArrayList<Integer> getStackX() {
			ArrayList<Integer> x = new ArrayList<Integer>();
			while(!xLoc.isEmpty()) {
				x.add(xLoc.pop());
			}
			return x;
		}
		public ArrayList<Integer> getStackY() {
			ArrayList<Integer> y = new ArrayList<Integer>();
			while(!yLoc.isEmpty()) {
				y.add(yLoc.pop());
			}
			return y;
		}
		
	}

}
