package Search;
/*
 * Authors: Hugh Jackovich, Carie Pointer, Matthew Sagen
 * Date: 10/02/2018
 * Assignment1: Search
 * 
 * This is the Main class for "Search". It creates the three different Maze objects...
 * 
 */
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


public class Main {
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter("Output.txt", "UTF-8"); //outputs to directory Assignment1
		
		Maze mediumMaze = new Maze("medium_maze");
		Maze largeMaze = new Maze("large_maze");
		Maze openMaze = new Maze("open_maze");
		BFS bfs = new BFS();
		bfs.solve(mediumMaze);
		
		/*Maze mediumMaze1 = new Maze("medium_maze");//mazes for Depth First Search.
		Maze largeMaze1 = new Maze("large_maze");
		Maze openMaze1 = new Maze("open_maze");*/
		DFS depthFirstSearch = new DFS();
		depthFirstSearch.solve(mediumMaze);
		depthFirstSearch.solve(largeMaze);
		depthFirstSearch.solve(openMaze);
		
		/*Maze mediumMaze2 = new Maze("medium_maze");
		Maze largeMaze2 = new Maze("large_maze");
		Maze openMaze2 = new Maze("open_maze");*/
		AStar AStarSearch = new AStar();
		AStarSearch.initPuzzle(mediumMaze);
		
		/*Maze mediumMaze3 = new Maze("medium_maze");
		Maze largeMaze3 = new Maze("large_maze");
		Maze openMaze3 = new Maze("open_maze");*/
		GBFS gbfs = new GBFS();
		gbfs.initPuzzle(mediumMaze);
		//printResults(writer, depthFirstSearch.getStats());
		writer.close();
	}
	public static void printResults(PrintWriter writer, ArrayList<String> dfsStats) {
		for(int i = 0; i < dfsStats.size();i++) {
			writer.println(dfsStats.get(i));
		}
	}
	
}