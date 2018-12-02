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
	private boolean isSource, stench, wumpus, pit, gold, glitter, breeze, bump;
	private boolean topWall, bottomWall, leftWall, rightWall;
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
	
	public int getDist(){
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
	
	public int getX() {//return x location of node
		return x;
	}
	
	public int getY() {//return y location of node.
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
	
	public int getCost(){//return cost of node.
		return cost;
	}
	
	public void addValidN(Node n){
		nearbySources.add(n);
	}
	
	public Set<Node> getValidN(){
		return nearbySources;
	}
	public boolean hasStench() {//return whether or not a node has a stench.
		return stench;
	}
	public void setStench() {
		stench = true;
		System.out.println(x + ": " + y + " has stench.");
	}
	public void setWumpus() {//set a pit to have the wumpus.
		wumpus = true;
	}
	public boolean containsWumpus() {//return whether or not a pit contains the wumpus.
		return wumpus;
	}
	public void setPit() {//set a node to have a pit.
		pit = true;
	}
	public boolean containsPit() {//return whether or not a node contains a pit
		return pit;
	}
	public void setGold() {//set a node to have gold
		gold = true;
	}
	public boolean containsGold() {//return whether or not a node contains gold
		return gold;
	}

	public void setGlitter() {//set a node to have glitter.
		// TODO Auto-generated method stub
		glitter = true;
		System.out.println(x + ": " + y + " has glitter.");
	}
	public boolean containsGlitter() {//return whether or not a node has glitter in it.
		return glitter;
	}
	public void setBreeze() {//set a node to have a breeze.
		breeze = true;
		System.out.println(x + ": " + y + " has breeze.");
	}
	public boolean hasBreeze() {//return whether or not a pit has a breeze.
		return breeze;
	}
	

	public void setTopWall() {//there is a wall at the top of the square
		topWall = true;
		System.out.println(x + ": " + y + " has top wall.");
	}

	public void setBottomWall() {//there is a wall at the bottom of the square
		bottomWall = true;
		System.out.println(x + ": " + y + " has bottom wall.");
	}

	public void setLeftWall() {//there is a wall on the left side of the square
		leftWall = true;
		System.out.println(x + ": " + y + " has left wall.");
	}

	public void setRightWall() {//there is a wall on the right side of the square
		rightWall = true;
		System.out.println(x + ": " + y + " has right wall.");
	}
	
}