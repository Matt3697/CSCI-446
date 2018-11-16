package flowFree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class CSP
{
	ArrayList<Node> var;
	Maze maze;
	Node[][] nodeMaze;
	char[][] charMaze;
	ArrayList<Path> temp_domain;
	public ArrayList<String> stats = new ArrayList<String>();
	int counter = 0;
	
	public CSP(Maze maze)
	{
		this.maze = maze;
		nodeMaze = maze.getNodeMatrix();
		charMaze = maze.getMatrix();
		ArrayList<Node> starters = maze.getStartNodes();
		var = orderVariables(starters);
		
		run();
	}
	
	public void run()
	{	
		double start = System.currentTimeMillis();
		
		
		if(solvePuzzle(var.get(0), 1))
		{
			System.out.println("Solved!");
		}
		double end = System.currentTimeMillis();
		String str = "Total nodes colored: "+ counter;
		stats.add(str);
		// Calculate the time it took for the search to execute and add to our list of stats
		double timeTook = end - start;
		String s = "Execution time: " + timeTook + "ms";
		stats.add(s);
	}
	
	public boolean solvePuzzle(Node curSource, int j)
	{
		buildPaths(curSource);

		while(curSource.getPaths().size() != 0)
		{
			applyPath(curSource.getPath());

			if(forwardChecking(j))
			{
				for(int i = j; i < var.size(); i++)
				{
					Node nextSource = var.get(i);
					if(solvePuzzle(nextSource, i+1))
					{
						break;
					}
				}
				
				if(boardFilled())
				{
					return true;
				}
			}
			
			removePath(curSource.getPath());
			curSource.removePath();
		}
		return false;
	}
	
	//Assumes starting Node
	public void buildPaths(Node n)
	{
		Path cur_path = new Path();
		cur_path.setStart(n.getX(), n.getY());
		cur_path.setGoal(n.getOtherSource());
		cur_path.setColor(n.getValue());
		
		findPath(n.getX(), n.getY(), cur_path, n);
	}
	
	public void findPath(int x, int y, Path path, Node sourceNode)
	{
		if(x == sourceNode.getOtherSource()[0] && y == sourceNode.getOtherSource()[1])
		{
			sourceNode.addPath(new Path(path));
			return;
		}
//		if(sourceNode.getPaths().size() > 1000000)
//		{
//			return;
//		}
//		if(path.getPath().size() > 30)
//		{
//			return;
//		}
		
		char c = path.getColor();
		charMaze[x][y] = c;

		if(neighborTest(x, y, c, true))
		{
			try{
				if(charMaze[x+1][y] == '_' || (y - sourceNode.getOtherSource()[1] == 0 && x+1 == sourceNode.getOtherSource()[0]))
				{
					path.nextMove(1);
					findPath(x+1, y, path, sourceNode);
					path.removeLastMove();
				}
			}catch(Exception e){}
			
			try{
				if(charMaze[x-1][y] == '_' || (y - sourceNode.getOtherSource()[1] == 0 && x-1 == sourceNode.getOtherSource()[0]))
				{
					path.nextMove(2);
					findPath(x-1, y, path, sourceNode);
					path.removeLastMove();
				}
			}catch(Exception e){}
			
			try{
				if(charMaze[x][y+1] == '_' || (x - sourceNode.getOtherSource()[0] == 0 && y+1 == sourceNode.getOtherSource()[1]))
				{
					path.nextMove(3);
					findPath(x, y+1, path, sourceNode);
					path.removeLastMove();
				}
			}catch(Exception e){}
			
			try{
				if(charMaze[x][y-1] == '_' || (x - sourceNode.getOtherSource()[0] == 0 && y-1 == sourceNode.getOtherSource()[1]))
				{
					path.nextMove(4);
					findPath(x, y-1, path, sourceNode);
					path.removeLastMove();
				}
			}catch(Exception e){}
		}

		if(sourceNode.getX() != x || sourceNode.getY() != y)
		{
			charMaze[x][y] = '_';
		}
	}
	
	public boolean neighborTest(int x, int y, char c, boolean flag)
	{
		int count = 0;
		
		try{
			if(charMaze[x+1][y] == c)
			{
				count++;
				
				if(flag)
				{
					if(!neighborTest(x+1, y, c, false))
					{
						return false;
					}
				}
			}
		}catch(Exception e){}
		
		try{
			if(charMaze[x-1][y] == c)
			{
				count++;
				
				if(flag)
				{
					if(!neighborTest(x-1, y, c, false))
					{
						return false;
					}
				}
			}
		}catch(Exception e){}
		
		try{
			if(charMaze[x][y+1] == c)
			{
				count++;
				
				if(flag)
				{
					if(!neighborTest(x, y+1, c, false))
					{
						return false;
					}
				}
			}
		}catch(Exception e){}
		
		try{
			if(charMaze[x][y-1] == c)
			{
				count++;
				if(flag)
				{
					if(!neighborTest(x, y-1, c, false))
					{
						return false;
					}
				}
			}
		}catch(Exception e){}
		
		if(count > 2)
		{
			return false;
		}
		return true;
	}
	
	public boolean forwardChecking(int j)
	{
		for(int i = j; i < var.size(); i++)
		{
			GDFS search = new GDFS();
			Node cur = var.get(i);
			int[] ini = {cur.getX(), cur.getY()};
			int[] goal = cur.getOtherSource();
			if(search.initSearch(nodeMaze, ini, goal, cur.getValue()))
			{}
			else
			{
				return false;
			}
		}
		return true;
	}
	
	public void applyPath(Path path)
	{
		int x = path.getStartX();
		int y = path.getStartY();

		
		for(int n : path.getPath())
		{
			switch(n)
			{
			case 1: charMaze[x+1][y] = path.getColor();
					nodeMaze[x+1][y].setValue(path.getColor());
					x++;
					break;
			case 2: charMaze[x-1][y] = path.getColor();
					nodeMaze[x-1][y].setValue(path.getColor());
					x--;
					break;
			case 3: charMaze[x][y+1] = path.getColor();
					nodeMaze[x][y+1].setValue(path.getColor());
					y++;
					break;
			case 4: charMaze[x][y-1] = path.getColor();
					nodeMaze[x][y-1].setValue(path.getColor());
					y--;
					break;
			}
			
			counter++; //increment counter by one.
			
		}
	}
	
	public void removePath(Path path)
	{
		int x = path.getStartX();
		int y = path.getStartY();
		ArrayList<Integer> modified_path = path.getPath();
		modified_path.remove(modified_path.size()-1);
		
		for(int n : path.getPath())
		{
			switch(n)
			{
			case 1: charMaze[x+1][y] = '_';
					nodeMaze[x+1][y].setValue('_');
					x++;
					break;
			case 2: charMaze[x-1][y] = '_';
					nodeMaze[x-1][y].setValue('_');
					x--;
					break;
			case 3: charMaze[x][y+1] = '_';
					nodeMaze[x][y+1].setValue('_');
					y++;
					break;
			case 4: charMaze[x][y-1] = '_';
					nodeMaze[x][y-1].setValue('_');
					y--;
					break;
			}
			
		}
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
	
	public boolean boardFilled()
	{
		for(int i = 0; i < charMaze.length; i++)
		{
			for(int k = 0; k < charMaze[i].length; k++)
			{
				if(charMaze[i][k] == '_')
				{
					return false;
				}
			}
		}
		return true;
	}
	public ArrayList<String> getStats(){
		return stats;
	}
}
