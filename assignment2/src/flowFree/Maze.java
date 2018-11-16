package flowFree;
/*
 * Authors: Carie Pointer, Hugh Jackovich, Matthew Sagen
 * Date:    11/13/18
 * Artificial Intelligence: Assignment 2
 */
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class Maze {
	public String mazeType;
	public char[][] maze = null;
	public char[][] DFSmaze = null;
	public ArrayList<Node> startNodes = new ArrayList<Node>();
	private ArrayList<Node> varList = new ArrayList<Node>();
	private Node[][] nodeMaze;
	private int size;
	
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
			size = 25;
		}
		else if(mazeType == "7x7maze") {//else if it is a large maze...
			maze = new char[7][7];		  //[rows][columns]
			nodeMaze = new Node[7][7];
			rows = new String[7];
			columns = new char[7];
			size = 49;
		}
		else if(mazeType == "8x8maze") {//else if it is an open maze...
			maze = new char[8][8];		 //[rows][columns]
			nodeMaze = new Node[8][8];
			rows = new String[8];
			columns = new char[8];
			size = 64;
		}
		else if(mazeType == "9x9maze") {//else if it is an open maze...
			maze = new char[9][9];		 //[rows][columns]
			nodeMaze = new Node[9][9];
			rows = new String[9];
			columns = new char[9];
			size = 81;
		}
		else if(mazeType == "10x10maze") {//else if it is an open maze...
			maze = new char[10][10];		 //[rows][columns]
			nodeMaze = new Node[10][10];
			rows = new String[10];
			columns = new char[10];
			size = 100;
		}
		else if(mazeType == "12x12maze") {//else if it is an open maze...
			maze = new char[12][12];		 //[rows][columns]
			nodeMaze = new Node[12][12];
			rows = new String[12];
			columns = new char[12];
			size = 144;
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
	
	public int getSize() {
		return size;
	}
	
	// Method for updating matrix values to track visited nodes
	public void updateValue(int x, int y, char c) {
		maze[x][y] = c;
	}
	
	// Create a matrix where each item is a Node
	public void makeNodeMatrix() {
		HashMap<Character, int[]> vals = new HashMap<Character, int[]>();
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				int[] temp = {i, j};
				Node n = new Node(i, j, maze[i][j]);
				nodeMaze[i][j] = n;
				varList.add(n);
				if(n.getValue() != '_') {
					n.setAsSource(); // If the value is not empty, it is a source node
					if(!vals.containsKey(n.getValue())) { //add all the starting nodes to a list
//						startNodes.add(n);
						vals.put(n.getValue(), temp);
					}
					else
					{
						startNodes.add(n);
						n.setOtherSource(vals.get(n.getValue()));
					}
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
		for(int i = 0; i < nodeMaze.length; i++) {
			for(int j = 0; j < nodeMaze[0].length; j++) {	
				n = nodeMaze[i][j];
				if (i+1 <= nodeMaze.length-1)
					n.addNeighbor(nodeMaze[i+1][j]); // South neighbor
				if (i-1 >= 0)
					n.addNeighbor(nodeMaze[i-1][j]); // North neighbor
				if (j+1 <= nodeMaze[0].length-1)
					n.addNeighbor(nodeMaze[i][j+1]); // East  neighbor
				if (j-1 >= 0)
					n.addNeighbor(nodeMaze[i][j-1]); // West  neighbor
			}
		}
	}

	public ArrayList<Node> getStartNodes() {
		return startNodes;
	}
	
	public ArrayList<Node> getVarList() {
		return varList;
	}
	public String getMazeType() {
		return mazeType;
	}
	
	public Node getNode(int x, int y)
	{
		return nodeMaze[x][y];
	}
}
