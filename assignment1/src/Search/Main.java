package Search;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) {
		create_mazes("medium_maze");
		create_mazes("large_maze");
		create_mazes("open_maze");
		
	}
	
	public static void create_mazes(String mazeType) { //read in the mazes into double arrays.
		Scanner mazeScanner = null;
		char[][] maze = null;
		if(mazeType == "medium") {
			maze = new char[62][22];
		}
		else if(mazeType == "large") {
			maze = new char[81][30];
		}
		else if(mazeType == "open") {
			maze = new char[37][19];
		}
		
		//Opens file location
        try{
        		mazeScanner = new Scanner(new File("Search/" + mazeType + "/.txt"));
        }
        //throws error if the file can't be located.
        catch(Exception e){
            System.out.println("File not found.");
            System.exit(0);
        }
        //add data to maze list.
        for(int i = 0; i < maze[0].length; i++) {
        		for(int y = 0; y < maze.length; y++) {
        			maze[i][y] = (char) mazeScanner.nextByte();
        		}
        }
   }
}
