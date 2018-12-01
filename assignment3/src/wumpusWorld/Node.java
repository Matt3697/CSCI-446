package wumpusWorld;
/*
 * Authors: Carie Pointer, Hugh Jackovich, Matthew Sagen
 * Date:    11/30/18
 * Programming Assignment 3: Wumpus World
 * 
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Node {
	private int x,y;
	private char value;
	private boolean isSource, stench, wumpus, pit, gold, glitter,breeze;
	private ArrayList<Node> neighbors = new ArrayList<>();
	private int[] otherSource;
	private int dist, cost;
	//private ArrayList<Path> domain;
	private ArrayList<Node> validNeighbors;
	Set<Node> nearbySources;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Node(int x, int y, char c) {
		this.x = x;
		this.y = y;
		value = c;
		isSource = false;
		//domain = new ArrayList<Path>();
		validNeighbors = new ArrayList<Node>();
		nearbySources = new HashSet<Node>();
	}
	
	public int getDist()
	{
		return dist;
	}
	/*
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
	*/
	
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
	
	public void setCost(int n){
		cost = n;
	}
	
	public int getCost(){
		return cost;
	}
	
	public void addValidN(Node n){
		nearbySources.add(n);
	}
	
	public Set<Node> getValidN(){
		return nearbySources;
	}
	public boolean hasStench() {
		return stench;
	}
	public void setWumpus() {
		wumpus = true;
	}
	public boolean containsWumpus() {
		return wumpus;
	}
	public void setPit() {
		pit = true;
	}
	public boolean containsPit() {
		return pit;
	}
	public void setGold() {
		gold = true;
	}
	public boolean containsGold() {
		return gold;
	}

	public void containsGlitter() {
		// TODO Auto-generated method stub
		glitter = true;
		
	}

	public void hasBreeze() {
		// TODO Auto-generated method stub
		breeze = true;
	}
}