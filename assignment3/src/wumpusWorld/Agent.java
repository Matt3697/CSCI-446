package wumpusWorld;
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
	
	
	public Agent(int x, int y) {//x and y coordinates of agent
		this.x = x;
		this.y = y;
		this.arrow = true;
		this.id = 'A';
		this.gold = false;
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
		
		if (this.getDirection() == "North") {
			if(maze.getNodeMatrix()[x-1][y].isVisited()) {
				nextDirection();
			}
			else {
				x--;
			}
		}
		else if (this.getDirection() == "East") {
			if(maze.getNodeMatrix()[x][y+1].isVisited()) {
				nextDirection();
			}
			else {
				y++;
			}
		}
		else if (this.getDirection() == "South") {
			if(maze.getNodeMatrix()[x+1][y].isVisited()) {
				nextDirection();
			}
			else {
				x++;
			}
		}
		else if (this.getDirection() == "West") {
			if(maze.getNodeMatrix()[x][y-1].isVisited()) {
				nextDirection();
			}
			else {
				y--;
			}
		}
		editPerformanceMeasure(-1); //-1 for taking action
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
		editPerformanceMeasure(-1);
	}
}
