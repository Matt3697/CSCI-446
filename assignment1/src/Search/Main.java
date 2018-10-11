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

public class Main {
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter("Output.txt", "UTF-8"); //outputs to directory Assignment1
		
		Maze mediumMaze = new Maze("medium_maze");
		Maze largeMaze = new Maze("large_maze");
		Maze openMaze = new Maze("open_maze");
		BFS bfs = new BFS();
		bfs.solve(mediumMaze);
		bfs.solve(openMaze);
		bfs.solve(largeMaze);
	
		writer.println("-------BFS-------");
		for (String s : bfs.getStats()) {
			System.out.println(s);
			writer.println(s);
		}
		
		Maze mediumMaze1 = new Maze("medium_maze");//mazes for Depth First Search.
		Maze largeMaze1 = new Maze("large_maze");
		Maze openMaze1 = new Maze("open_maze");
		DFS depthFirstSearch = new DFS();
		depthFirstSearch.solve(mediumMaze1);
		depthFirstSearch.solve(largeMaze1);
		depthFirstSearch.solve(openMaze1);
		
		writer.println("-------DFS-------");
		for (String s : depthFirstSearch.getStats()) {
			System.out.println(s);
			writer.println(s);
		}
            
		Maze mediumMaze2 = new Maze("medium_maze");
		Maze largeMaze2 = new Maze("large_maze");
		Maze openMaze2 = new Maze("open_maze");
		AStar AStarSearch = new AStar();
		System.out.println("-------AStarSearch-------");
		AStarSearch.initPuzzle(mediumMaze2);
		AStarSearch.initPuzzle(largeMaze2);
		AStarSearch.initPuzzle(openMaze2);
		
		writer.println("-------AStarSearch-------");
		for (String s : AStarSearch.getStats()) {
			System.out.println(s);
			writer.println(s);
		}
		
		Maze mediumMaze3 = new Maze("medium_maze");
		Maze largeMaze3 = new Maze("large_maze");
		Maze openMaze3 = new Maze("open_maze");
		GBFS gbfs = new GBFS();
		System.out.println("-------GDFS-------");
		gbfs.initPuzzle(mediumMaze3);
		gbfs.initPuzzle(largeMaze3);
		gbfs.initPuzzle(openMaze3);
		
		writer.println("-------GBFS-------");
		for (String s : gbfs.getStats()) {
			System.out.println(s);
			writer.println(s);
		}
		
		writer.close();
	}
	
}