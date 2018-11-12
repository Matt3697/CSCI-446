package flowFree;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Maze {
	public String mazeType;
	public char[][] maze = null;
	public char[][] DFSmaze = null;
	public ArrayList<Node> startNodes = new ArrayList<Node>();
	private Node[][] nodeMaze;
	private Node goalNode;
	
	public Maze(String mazeType) { //constructor for Maze
		this.mazeType = mazeType;
		create_maze(mazeType);
	}
	
	public void create_maze(String mazeType) { //reads from different text files to create mazes.
		Scanner mazeScanner = null;
		String[] rows = null;			//holds the initial lines from the text file
		char[] columns = null;			//holds each index of a row.
		if(mazeType == "5x5maze") {	//if the maze type is a medium maze cater to its specific dimensions.
			maze = new char[5][5];		//[rows][columns]
			nodeMaze = new Node[5][5];
			rows = new String[5];
			columns = new char[5];
		}
		else if(mazeType == "7x7maze") {//else if it is a large maze...
			maze = new char[7][7];		  //[rows][columns]
			nodeMaze = new Node[7][7];
			rows = new String[7];
			columns = new char[7];
		}
		else if(mazeType == "8x8maze") {//else if it is an open maze...
			maze = new char[8][8];		 //[rows][columns]
			nodeMaze = new Node[8][8];
			rows = new String[8];
			columns = new char[8];
		}
		else if(mazeType == "9x9maze") {//else if it is an open maze...
			maze = new char[9][9];		 //[rows][columns]
			nodeMaze = new Node[9][9];
			rows = new String[9];
			columns = new char[9];
		}
		else if(mazeType == "10x10maze") {//else if it is an open maze...
			maze = new char[10][10];		 //[rows][columns]
			nodeMaze = new Node[10][10];
			rows = new String[10];
			columns = new char[10];
		}
		else if(mazeType == "12x12maze") {//else if it is an open maze...
			maze = new char[12][12];		 //[rows][columns]
			nodeMaze = new Node[12][12];
			rows = new String[12];
			columns = new char[12];
		}
	
        try{ //Try to open file location
        		mazeScanner = new Scanner(new File("src/assets/" + mazeType + ".txt"));
        }
        catch(Exception e){//throw error if the file can't be located.
            System.out.println("File not found.");
            System.exit(0);
        }
        for(int i = 0; i < rows.length; i++) {//get the initial rows from the text file
        		rows[i] = mazeScanner.nextLine();
        }
        for(int i = 0; i < rows.length; i++) {//loop through each row
        	columns = rows[i].toCharArray();  //convert row to a char array, this represents an index of each column in a row.
			for(int y = 0; y < columns.length; y++) {
				maze[i][y] = columns[y];		 //add each column index to its corresponding column in each row.	
			}
        }
        makeNodeMatrix();
   }
	public void printMaze() {
		System.out.println(mazeType);
		System.out.println("-------------");
		for(int i = 0; i < maze.length; i++) {//loops through the maze and print out its contents. stdout
			for(int y = 0; y < maze[0].length; y++) {
				System.out.print(maze[i][y]);
     		}
     		System.out.println();
		}
		System.out.println();
	}

	public char[][] getMatrix(){//returns the maze
		return maze;
	}
	
	public Node[][] getNodeMatrix() {
		return nodeMaze;
	}
	/*
	public Node getStartingPoint() {
		return startNode;
	}
	*/
	public Node getGoalNode() {
		return goalNode;
	}
	
	// Method for updating matrix values to track visited nodes
	public void updateValue(int x, int y, char c) {
		maze[x][y] = c;
	}
	
	// Create a matrix where each item is a Node
	public void makeNodeMatrix() {
		HashSet<Character> vals = new HashSet<Character>();
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				Node n = new Node(i, j, maze[i][j]);
				nodeMaze[i][j] = n;
				if(!vals.contains(maze[i][j]) && maze[i][j] != '_') { //add all the starting nodes to a list
					startNodes.add(n);
					vals.add(maze[i][j]);
				}
			}
		}
		setNeighbors();
	}
	
	public void printNodeMatrix() {
		for (int i = 0; i < nodeMaze.length; i++) {
			for (int j = 0; j < nodeMaze[0].length; j++) {
				System.out.print(nodeMaze[i][j].getValue());
			}
			System.out.println();
		}
	}
	
	// Set all neighbors of all nodes
	public void setNeighbors() {
		Node n;
		for(int i = 1; i < nodeMaze.length -1; i++) {
			for(int j = 1; j < nodeMaze[0].length -1; j++) {	
				n = nodeMaze[i][j];
				n.addNeighbor(nodeMaze[i+1][j]); // South neighbor
				n.addNeighbor(nodeMaze[i-1][j]); // North neighbor
				n.addNeighbor(nodeMaze[i][j+1]); // East  neighbor
				n.addNeighbor(nodeMaze[i][j-1]); // West  neighbor
			}
			
		}
		
	}
	public ArrayList<Node> getStartNodes() {
		return startNodes;
	}
	public String getMazeType() {
		return mazeType;
	}
}
