package Search;
/*
 * 
 */
import java.io.File;
import java.util.Scanner;

public class Maze {
	public String mazeType;
	public char[][] maze = null;
	public char[][] DFSmaze = null;
	private Node[][] nodeMaze;
	private Node startNode;
	
	public Maze(String mazeType) { //constructor for Maze
		this.mazeType = mazeType;
		create_maze(mazeType);
	}
	
	public void create_maze(String mazeType) { //reads from different text files to create mazes.
		Scanner mazeScanner = null;
		String[] rows = null;			//holds the initial lines from the text file
		char[] columns = null;			//holds each index of a row.
		
		if(mazeType == "medium_maze") {	//if the maze type is a medium maze cater to its specific dimensions.
			maze = new char[23][62];		//[rows][columns]
			nodeMaze = new Node[23][62];
			DFSmaze = new char[23][62];
			rows = new String[23];
			columns = new char[62];
		}
		else if(mazeType == "large_maze") {//else if it is a large maze...
			maze = new char[31][81];		  //[rows][columns]
			nodeMaze = new Node[31][81];
			DFSmaze = new char[31][81];
			rows = new String[31];
			columns = new char[81];
		}
		else if(mazeType == "open_maze") {//else if it is an open maze...
			maze = new char[20][37];		 //[rows][columns]
			nodeMaze = new Node[20][37];
			DFSmaze = new char[20][37];
			rows = new String[20];
			columns = new char[37];
		}
		
        try{	//Try to open file location
        		mazeScanner = new Scanner(new File("src/Search/" + mazeType + ".txt"));
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
				DFSmaze[i][y] = columns[y];
			}
        }
        makeNodeMatrix();
   }
	public void printMaze() {
		System.out.println(mazeType);
		for(int i = 0; i < maze.length; i++) {//loops through the maze and print out its contents. stdout
			for(int y = 0; y < maze[0].length; y++) {
     			System.out.print(maze[i][y]);
     		}
     		System.out.println();
		}
		System.out.println();
		System.out.println();
	}

	public char[][] getMatrix(){//returns the maze
		return DFSmaze;
	}
	
	public Node getStartingPoint() {
		return startNode;
	}
	
	// Method for updating matrix values to track visited nodes
	public void updateValue(int x, int y, char c) {
		maze[x][y] = c;
	}
	
	// Create a matrix where each item is a Node
	public void makeNodeMatrix() {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				Node n = new Node(i, j, maze[i][j]);
				nodeMaze[i][j] = n;	
				if (n.getValue() == 'P') { //set starting point
					startNode = n;
				}
				if (n.getValue() == '%') //ignore walls
					n.setVisited();		
			}
		}
		setNeighbors();
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
	public Node[][] getNodeMatrix() {
		return nodeMaze;
	}
	public String getMazeType() {
		return mazeType;
	}
}

