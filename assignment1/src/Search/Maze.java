package Search;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Maze {
	public String mazeType;
	public char[][] maze = null;
	
	public Maze(String mazeType) {
		this.mazeType = mazeType;
		create_maze(mazeType);
	}
	
	public void create_maze(String mazeType) { //read in a maze into "maze".
		Scanner mazeScanner = null;
		String[] rows = null;
		char[] columns = null;
		if(mazeType == "medium_maze") {
			maze = new char[23][62];//[rows][columns]
			rows = new String[23];
			columns = new char[62];
			
		}
		else if(mazeType == "large_maze") {
			maze = new char[31][81];//[rows][columns]
			rows = new String[31];
			columns = new char[81];
		}
		else if(mazeType == "open_maze") {
			maze = new char[20][37];//[rows][columns]
			rows = new String[20];
			columns = new char[37];
		}
		
		//Opens file location
        try{
        		mazeScanner = new Scanner(new File("src/Search/" + mazeType + ".txt"));
        }
        //throws error if the file can't be located.
        catch(Exception e){
            System.out.println("File not found.");
            System.exit(0);
        }
        //add data to maze list.
        System.out.println(maze.length + ":" + maze[0].length);
        for(int i = 0; i < rows.length; i++) {
        		rows[i] = mazeScanner.nextLine();
        }
        for(int i = 0; i < rows.length; i++) {//loop through each row and add each index as an index of a column
        		columns = rows[i].toCharArray();
			for(int y = 0; y < columns.length; y++) {
				maze[i][y] = columns[y];
			}
        }
       
      
   }
	public void printMaze() {
		 for(int i = 0; i < maze.length; i++) {
     		for(int y = 0; y < maze[0].length; y++) {
     			System.out.print(maze[i][y]);;
     		}
     		System.out.println();
     }
		 System.out.println();
		 System.out.println();
	}
}
