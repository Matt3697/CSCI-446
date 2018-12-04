package wumpusWorld;

import java.util.ArrayList;

/*
 * Authors: Carie Pointer, Hugh Jackovich, Matthew Sagen
 * Date:    11/30/18
 * Programming Assignment 3: Wumpus World
 * 
 */
public class Agent {
	private int x,y, performanceMeasure, arrowX, arrowY;
	private boolean arrow, gold;
	private String direction;
	private char id;
	private ArrayList<Node> unknown;
	private ArrayList<Node> danger;
	private ArrayList<Node> valid;
	private ArrayList<Node> safe;
	private ArrayList<Node> warning;
	
	
	public Agent(int x, int y) {//x and y coordinates of agent
		this.x = x;
		this.y = y;
		this.arrow = true;
		this.id = 'A';
		this.gold = false;
		unknown = new ArrayList<Node>();
		danger = new ArrayList<Node>();
		valid = new ArrayList<Node>();
		safe = new ArrayList<Node>();
		warning = new ArrayList<Node>();
		
	}
	
	public boolean hasArrow() {//return whether or not the agent currently has it's arrow
		return arrow;
	}
	public boolean shootArrow(Maze maze, Wumpus wumpus) {//if the agent shoots it's arrow, set hasArrow to false. If we hit the wumpus return true
		arrow = false;
		arrowX = x;
		arrowY = y;
		if(direction == "East" && maze.getNode(arrowX,arrowY+1).getGuess() == 'W') {//if we are moving east and the wumpus could be in that dir.
			editPerformanceMeasure(-10); //-10 for using arrow
			if(maze.getNode(arrowX, arrowY+1).containsWumpus()) {//if the arrow is in same square as wumpus, kill wumpus
				wumpus.killWumpus();
				return true;
			}
			else {
				maze.getNode(arrowX, arrowY+1).setGuess('*');//there is no wumpus in that direction.
			}
			valid.add(maze.getNode(arrowX, arrowY+1));
		}
		else if(direction == "South" && maze.getNode(arrowX+1,arrowY).getGuess() == 'W') {//if direction is south
			editPerformanceMeasure(-10); //-10 for using arrow
			if(maze.getNode(arrowX+1, arrowY).containsWumpus()) {//if the arrow is in same square as wumpus, kill wumpus
				wumpus.killWumpus();
				return true;
			}
			else {
				maze.getNode(arrowX+1, arrowY).setGuess('*');//there is no wumpus in that direction.
			}
			valid.add(maze.getNode(arrowX+1, arrowY));
		}
		else if(direction == "West" && maze.getNode(arrowX,arrowY-1).getGuess() == 'W') {//if direction is west
			editPerformanceMeasure(-10); //-10 for using arrow
			if(maze.getNode(arrowX, arrowY-1).containsWumpus()) {//if the arrow is in same square as wumpus, kill wumpus
				wumpus.killWumpus();
				return true;
			}
			else {
				maze.getNode(arrowX, arrowY-1).setGuess('*');//there is no wumpus in that direction.
			}
			valid.add(maze.getNode(arrowX, arrowY-1));
		}
		else if(direction == "North" && maze.getNode(arrowX-1,arrowY).getGuess()  == 'W') {//if direction is north.
			editPerformanceMeasure(-10); //-10 for using arrow
			if(maze.getNode(arrowX-1, arrowY).containsWumpus()) {//if the arrow is in same square as wumpus, kill wumpus
				wumpus.killWumpus();
				return true;
			}
			else {
				maze.getNode(arrowX-1, arrowY).setGuess('*');//there is no wumpus in that direction.
			}
			valid.add(maze.getNode(arrowX-1, arrowY));
		}
		System.out.println("Arrow hit wall");
		return false;
	}
	public int getX() {//return the x position of the agent
		return x;
	}
	public int getY() {//return the y position of the agent
		return y;
	}
	
	// Returns the x position of the arrow that was shot
	public int getArrowX() {
		return arrowX;
	}
	
	// Returns y position of the arrow that was shot
	public int getArrowY() {
		return arrowY;
	}
	
	public char getId() {//return character representation of Agent --> A
		return id;
	}
	public void turnLeft() {
		
	}
	public void turnRight() {
		
	}
	public void moveForward(Maze maze) {//move the agent forward by one if the agent remains within the bounds of the maze.
		
		System.out.println("moving " + this.getDirection());
		boolean flag = true;
		
		System.out.println("TEST :" + valid.size() + " || " + unknown.size() + " || " + safe.size());
		
		if (this.getDirection() == "North") {
			try {
				Node poss = maze.getNode(x-1, y);
				
				if(valid.contains(poss))
				{
					valid.remove(poss);
					x--;
					flag = false;
				}
				else {nextDirection();}
			}catch(Exception e) {}
//			if(maze.getNodeMatrix()[x-1][y].isVisited()) {
//				nextDirection();
//			}
//			else {
//				x--;
//			}
		}
		else if (this.getDirection() == "East") {
			try {
				Node poss = maze.getNode(x, y+1);
				
				if(valid.contains(poss))
				{
					valid.remove(poss);
					y++;
					flag = false;
				}
				else {nextDirection();}
			}catch(Exception e) {}
//			if(maze.getNodeMatrix()[x][y+1].isVisited()) {
//				nextDirection();
//			}
//			else {
//				y++;
//			}
		}
		else if (this.getDirection() == "South") {
			try {
				Node poss = maze.getNode(x+1, y);
				
				if(valid.contains(poss))
				{
					valid.remove(poss);
					x++;
					flag = false;
				}
				else {nextDirection();}
			}catch(Exception e) {}
//			if(maze.getNodeMatrix()[x+1][y].isVisited()) {
//				nextDirection();
//			}
//			else {
//				x++;
//			}
		}
		else if (this.getDirection() == "West") {
			try {
				Node poss = maze.getNode(x, y-1);
				
				if(valid.contains(poss))
				{
					valid.remove(poss);
					y--;
					flag = false;
				}
				else {nextDirection();}
			}catch(Exception e) {}
//			if(maze.getNodeMatrix()[x][y-1].isVisited()) {
//				nextDirection();
//			}
//			else {
//				y--;
//			}
		}
		if(!flag) {
			editPerformanceMeasure(-1); //-1 for taking action
		}
		//try other valids
		else
		{
			if(!valid.isEmpty())
			{
				//greedy to find path? for now we can cheese it.
				editPerformanceMeasure(-2); //-2 for 180
				Node nextMove = valid.get(valid.size()-1); //DFS like
				
				//Manhattan temporarily to calculate cost to move to new spot
				x = Math.abs(x - nextMove.getX());
				y = Math.abs(y - nextMove.getY());
				editPerformanceMeasure(x + y);
				
				x = nextMove.getX();
				y = nextMove.getY();
				valid.remove(valid.size()-1);
				flag = false;
			}
		}
		
		//try unknowns
		if(flag && !unknown.isEmpty())
		{
			//try diagonals of danger?
			//For now
			Node nextMove = unknown.get(unknown.size()-1);
			
			//Manhattan temporarily to calculate cost to move to new spot
			x = Math.abs(x - nextMove.getX());
			y = Math.abs(y - nextMove.getY());
			editPerformanceMeasure(x + y);
			
			x = nextMove.getX();
			y = nextMove.getY();
			unknown.remove(nextMove);
			flag = false;
			
		}
	}
	public void grab() {//lets the agent grab the gold from a square
		gold = true;
		editPerformanceMeasure(-1); //-1 for taking action
	}
	public boolean hasGold() {//return whether or not agent has the gold
		return gold;
	}
	public int getPerformanceMeasure() {//return the agents performance measure
		return performanceMeasure;
	}
	public void editPerformanceMeasure(int measure) {
		performanceMeasure += measure;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public String getDirection() {
		return direction;
	}
	public void setX(int x) {//update agent x location
		this.x = x;
	}
	public void setY(int y) {//update agent y location
		this.y = y;
	}
	public void nextDirection() {
		if (direction == "North") {
			direction = "East";
		}
		else if (direction == "East") {
			direction = "South";
		}
		else if (direction == "South") {
			direction = "West";
		}
		else if (direction == "West") {
			direction = "North";
		}
	}
	
	public void addUnknown(Node n)
	{
		if(valid.contains(n) || safe.contains(n))
		{
			return;
		}
		for(Node neighbor: n.getNeighbors())
		{
			if(safe.contains(neighbor))
			{
				if(!warning.contains(n))
				{
					valid.add(n);
				}
//				valid.add(n);
				try {
					unknown.remove(n);
					danger.remove(n);
				}
				catch(Exception e){}
				return;
			}
		}
		if(!unknown.contains(n) && !danger.contains(n))
		{
			unknown.add(n);
		}
		else if(!danger.contains(n))
		{
			danger.add(n);
			unknown.remove(n);
		}
	}
	
	public void addDanger(Node n)
	{
		if(!danger.contains(n))
		{
			danger.add(n);
		}
	}
	
	public void addSafe(Node n)
	{
		if(!safe.contains(n)) {
			safe.add(n);
		}
		if(valid.contains(n))
		{
			valid.remove(n);
		}
	}
	
	public void addValid(Node n)
	{
		if(!valid.contains(n) && !safe.contains(n)) {
			valid.add(n);
		}
	}
	
	//This is sorta temporarily, would be nice to
	//add a way to know if pit/wumpus
	public void findDanger(Node n)
	{
		ArrayList<Integer> removal = new ArrayList<Integer>();
		for(Node poss : unknown)
		{
			int count = 0;
			
			for(Node neighbor: poss.getNeighbors())
			{
				if(warning.contains(neighbor))
				{
					count++;
				}
				if (count >= poss.getNeighbors().size() - 1)
				{
					addDanger(poss);
					removal.add(unknown.indexOf(poss));
					break;
				}
			}
		}
		for(Integer index : removal)
		{
			unknown.remove(index);
		}
	}
	
	public void addWarningNode(Node n)
	{
		warning.add(n);
	}
}
