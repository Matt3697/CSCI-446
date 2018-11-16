package flowFree;

/*
 * Authors: Carie Pointer, Hugh Jackovich, Matthew Sagen
 * Date:    11/13/18
 * Artificial Intelligence: Assignment 2
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Node {
	private int x,y;
	private char value;
	private boolean isSource;
	private ArrayList<Node> neighbors = new ArrayList<>();
	private int[] otherSource;
	private int dist, cost;
	private ArrayList<Path> domain;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Node(int x, int y, char c) {
		this.x = x;
		this.y = y;
		value = c;
		isSource = false;
		domain = new ArrayList<Path>();
	}
	
	public void setAsSource() {//sets the node as a source node
		isSource = true;
	}
	
	public void setOtherSource(int[] loc)
	{
		otherSource = loc;
	}
	
	public int[] getOtherSource()
	{
		return otherSource;
	}
	
	public void setDist(int n)
	{
		dist = n;
	}
	
	public int getDist()
	{
		return dist;
	}
	
	public void addPath(Path p)
	{
		domain.add(p);
	}
	
	public void removePath()
	{
		domain.remove(domain.size()-1);
	}
	
	public Path getPath()
	{
		return domain.get(domain.size()-1);
	}
	
	public ArrayList<Path> getPaths()
	{
		return domain;
	}
	
	public void sortPaths()
	{		
		Path tempI;
		for(int i = 0; i < domain.size(); i++)
		{
			for(int j = i + 1; j < domain.size(); j++)
			{
				int first = domain.get(i).getPath().size();
				int second = domain.get(j).getPath().size();
				
				if(first < second)
				{
	                tempI = domain.get(j);
	                domain.set(i, domain.get(j));
	                domain.set(j,tempI);
				}
			}
		}
	}
	
	public boolean isSource() {
		return isSource;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void addNeighbor(Node n) {
		neighbors.add(n);
	}
	
	public ArrayList<Node> getNeighbors() {
		return neighbors;
	}
	
	public void setValue(char c) {
		value = c;
	}
	
	public char getValue() {
		return value;
	}
	
	public void setCost(int n)
	{
		cost = n;
	}
	
	public int getCost()
	{
		return cost;
	}
}


