package flowFree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class smartCSP
{
	Maze maze;
	Node[][] nodeMaze;
	ArrayList<Node> starters;
	public int x,y,counter;
	private HashSet<Character> colors;
	private ArrayList<Node> startNodes;
	private ArrayList<Node> varList;
	public ArrayList<String> stats = new ArrayList<String>();
	private int colorAttempts = 0;
	
	public smartCSP(Maze maze)
	{
		this.maze = maze;
		this.varList = maze.getVarList();
		this.nodeMaze = maze.getNodeMatrix();
	}
	
	public void run()
	{
		long start, end, timeTook;
		colors = new HashSet<Character>();
		startNodes = maze.getStartNodes();
		starters = orderVariables(startNodes);
		
		// Add possible colors to the domain, which will consist of the colors in the startNodes list
		for (Node n : startNodes) {
			colors.add(n.getValue());	
		}

		Node[][] assignment = maze.getNodeMatrix();
		// start from most constrained starting node
		Node n = starters.get(0);

		// Start with first color in list of possible colors
		ArrayList<Node> adj = n.getNeighbors();
		Node cur_node = adj.get(x);
		while(cur_node.getValue() != '_') {
			++x;
			cur_node = adj.get(x);
		}
		cur_node.setValue(n.getValue());
		
		start = System.currentTimeMillis();
		if (solvePuzzle(assignment, cur_node) == false)
			System.out.println("no solution");
		end = System.currentTimeMillis();
		
		// Calculate the time it took for the search to execute and add to our list of stats
		timeTook = end - start;
		String s = "Execution time: " + timeTook + "ms";
		stats.add(s);
	}
	
	public boolean solvePuzzle(Node[][] assignment, Node prev_node)
	{
		int x = 0;
		if (assignmentComplete(assignment)) {
			System.out.println("Solution:");
			printAssignment(assignment);
			String str = "Total nodes colored: "+ colorAttempts;
			stats.add(str);
			return true;
		}
		
		// start from most constrained starting node
		Node n = starters.get(0);
		char c = n.getValue();

		// Start with first color in list of possible colors
		ArrayList<Node> adj = n.getNeighbors();
		Node cur_node = adj.get(x);
		while(cur_node.getValue() != '_') {
			++x;
			cur_node = adj.get(x);
		}
		cur_node.setValue(cur_node.getValue());
		for(Node neighbor : cur_node.getValidN())
		{
			colorAttempts++;
				
				// Check if this color assignment is valid
			if (canMakeAssignment(neighbor, cur_node.getValue(), assignment)) {
				
				// Recursive call to function if previous assignment is valid
				if (solvePuzzle(assignment, cur_node))
				{
					return true;
				}
				
				// If recursive call fails, change assignment back to a blank
				n.setValue('_');
			}
			else
			{
				n.setValue('_');
			}
		}
			return false;
	}
		
	/* Checks if current node has a valid assignment by checking each of its neighbors. 
	 * Returns false if one of the neighbors invalidates a constraint, or true on success 
	 */
	public boolean canMakeAssignment(Node n, char c, Node[][] assignment) {
		for (Node neighbor : n.getNeighbors()) {
			if(!checkNeighborConstraints(neighbor, c, assignment))
				return false;
		}
		
		return true;
	}
	
	/* Checks all neighbors of a given node n to ensure no constraints are broken 
	 * Returns true on success, or false if one of the constraints is broken
	 */
	public boolean checkNeighborConstraints(Node n, char c, Node[][] assignment) {
		int sameColor = 0;
		int blanks = 0;
		ArrayList<Node> nearbySource = new ArrayList<Node>();

		// Count number of neighbors that are unassigned or the same color as n
		for (Node neighbor : n.getNeighbors()) {
			if (neighbor.getValue() == '_')
			{
				blanks++;
				n.addValidN(neighbor);
			}
			else if (neighbor.getValue() == n.getValue()) {
				sameColor++;
			}
			else if(neighbor.isSource())
			{
				nearbySource.add(neighbor);
			}
		}
		
		// Source cannot have more than one neighbor of the same color 
		// and non-source nodes cannot have more than two neighbors of the same color
		if ((sameColor > 1 && n.isSource()) || (sameColor > 2 && !n.isSource())) {
			return false;
		}
			
		// Source nodes must have one neighbor of the same color
		// and non-source nodes must have two neighbors of same color.
		// Unassigned nodes are potential neighbors of same color.
		if (((sameColor + blanks < 1) && n.isSource()) || ((sameColor + blanks < 2) && !n.isSource())) {
			return false;
		}
		
		if(nearbySource.size() > 0)
		{
			for(Node source : nearbySource)
			{
				int validity = 4;
				for(Node neighbor : source.getNeighbors())
				{
					if(neighbor.isSource())
					{
						validity --;
					}
					else if(neighbor.getValue() != source.getValue() && neighbor.getValue() != '_')
					{
						validity --;
					}
				}
				if(validity <= 0)
				{
					return false;
				}
					
			}
		}
		
		
		
		return true;
	}
	
	public ArrayList<Node> orderVariables(ArrayList<Node> start)
	{
		ArrayList<Node> orderedVar = new ArrayList<Node>();
		HashMap<Integer, Node> tempMap = new HashMap<Integer, Node>();
		int tempI;

		for(Node n: start)
		{
			if (n.getOtherSource() != null)
			{
				int x = Math.abs(n.getX() - n.getOtherSource()[0]);
				int y = Math.abs(n.getY() - n.getOtherSource()[1]);
				int u = x + y;
				n.setDist(u);
				
				while(true)
				{
					if(tempMap.containsKey(u))
					{
						u++;
						continue;
					}
					else
					{
						tempMap.put(u, n);
						break;
					}
				}
			}
		}
		
		//Orders largest to Smallest
		int size = tempMap.keySet().size();
		Integer[] temp = new Integer[size];
		tempMap.keySet().toArray(temp);
		
        for (int i = 0; i < size; i++) 
        {
            for (int j = i + 1; j < size; j++) 
            {
                if (temp[i] < temp[j]) 
                {
                    tempI = temp[i];
                    temp[i] = temp[j];
                    temp[j] = tempI;  
                }
            }
        }
        
        for(int i : temp)
        {
        	orderedVar.add(tempMap.get(i));
        }
        
		return orderedVar;
	}
	
	public boolean assignmentComplete(Node[][] assignment)
	{
		for(int i = 0; i < assignment.length; i++)
		{
			for(int k = 0; k < assignment[i].length; k++)
			{
				if(assignment[i][k].getValue() == '_')
				{
					return false;
				}
			}
		}
		return true;
	}
	
	/* Print method for the assignment */
	public void printAssignment(Node[][] assignment) {
		for (int i = 0; i < assignment.length; i++) {
			for (int j = 0; j < assignment[0].length; j++) {
				System.out.print(assignment[i][j].getValue());
			}
			System.out.println();
		}
		System.out.println();	
	}
}
