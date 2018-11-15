package flowFree;

import java.util.ArrayList;

public class Path
{
	int x, y;
	int[] goal;
	char c;
	ArrayList<Integer> path;
	
	Path(Path old)
	{
		this.c = old.c;
		this.goal = old.goal;
		this.x = old.x;
		this.y = old.y;
		
		path = new ArrayList<Integer>();
		for(int n : old.path)
		{
			path.add(n);
		}
	}
	Path()
	{
		path = new ArrayList<Integer>();
	}
	
	public void setStart(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int getStartX()
	{
		return x;
	}
	
	public int getStartY()
	{
		return y;
	}
	
	public void setGoal(int[] n)
	{
		goal = n;
	}
	
	public void setColor(char c)
	{
		this.c = c;
	}
	
	public char getColor()
	{
		return c;
	}
	
	public void nextMove(int n)
	{
		path.add(n);
	}
	public void removeLastMove()
	{
		path.remove(path.size()-1);
	}
	
	public ArrayList<Integer> getPath()
	{
		return path;
	}
}
