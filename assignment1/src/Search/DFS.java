package Search;
/*
 * DFS (Depth First Search)
 */
public class DFS {
	int startingX;
	int startingY;
	
	public DFS() {
		
	}
	
	public void solve(Maze maze) {
		char[][] matrix = maze.getMatrix();
		getStartingPoint(matrix);
		System.out.println(startingX + ":" + startingY);
		for(int i = 0; i < matrix.length; i++) {
			for(int y = 0; y < matrix[0].length; i++) {
				
			}
		}
	}
	
	public void getStartingPoint(char[][] matrix) { //helper method to get starting point.
		for(int i = 0; i < matrix.length; i++) {
			for(int y = 0; y < matrix[0].length; i++) {
				if(matrix[i][y] == 'P') {
					startingX = i;
					startingY = y;
				}
			}
		}
	}
}

