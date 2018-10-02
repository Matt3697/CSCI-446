package Search;

import java.util.HashMap;

/*
 * DFS (Depth First Search)
 */
public class DFS {
	int startingX;
	int startingY;
	HashMap<Integer, Integer> xy = new HashMap<Integer,Integer>();
	
	public DFS() {
		
	}
	
	public void solve(Maze maze) {
		char[][] matrix = maze.getMatrix();
		computeStartingPoint(matrix);
		for(int i = startingY; i < matrix.length; i++) {		  //looping through columns
			for(int y = startingX; y < matrix[0].length; i++) {//looping through rows
				if(startingX == 1 && startingY == 1) {		  //top left corner
					break;
				}
				else if(startingX == 1 && startingY == matrix.length - 2) { //bottom left corner
					System.out.println("HI");
					break;
				}
				else if(startingX == matrix.length - 1 && startingY == matrix[0].length - 2) { // bottom right corner
					break;
				}
				else if(startingX == matrix.length - 2 && startingY == 1) { //top right corner
					break;
				}
				else {
					break;
				}
			}
		}
	}
	
	public void computeStartingPoint(char[][] matrix) { //helper method to get starting point.
		for(int i = 0; i < matrix.length; i++) { //looping through columns
			for(int y = 0; y < matrix[0].length; y++) { //looping through rows
     			if(matrix[i][y] == 'P') {
     				startingX = y;
     				startingY = i;
     				break;
     			}
     		}
		}
	}
}

