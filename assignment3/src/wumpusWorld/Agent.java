package wumpusWorld;
/*
 * Authors: Carie Pointer, Hugh Jackovich, Matthew Sagen
 * Date:    11/30/18
 * Programming Assignment 3: Wumpus World
 * 
 */
public class Agent {
	private int x,y, performanceMeasure;
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
		if(arrow == true) {
			arrow = false;
			editPerformanceMeasure(-10); //-10 for using arrow
			int arrowX = x;
			int arrowY = y;
			if(direction == "East") {
				while(arrowX < maze.getUpperBound()) {
					arrowX ++;
					if(maze.getNode(arrowX, arrowY).containsWumpus()) {
						wumpus.killWumpus();
						return true;
					}
				}
			}
			else if(direction == "South") {
				while(arrowY < maze.getUpperBound()) {
					arrowY ++;
					if(maze.getNode(arrowX, arrowY).containsWumpus()) {
						wumpus.killWumpus();
						return true;
					}
				}
			}
			else if(direction == "West") {
				while(arrowX >= 0 ) {
					arrowX --;
					if(maze.getNode(arrowX, arrowY).containsWumpus()) {
						wumpus.killWumpus();
						return true;
					}	
				}
			}
			else if(direction == "North") {
				while(arrowY >= 0) {
					arrowY--;
					if(maze.getNode(arrowX, arrowY).containsWumpus()) {
						wumpus.killWumpus();
						return true;
					}
				}
			}
			System.out.println("Arrow hit wall");
		}
		return false;
	}
	public int getX() {//return the x position of the agent
		return x;
	}
	public int getY() {//return the y position of the agent
		return y;
	}
	public char getId() {//return character representation of Agent --> A
		return id;
	}
	public void turnLeft() {
		
	}
	public void turnRight() {
		
	}
	public void moveForward() {//move the agent forward by one if the agent remains within the bounds of the maze.
		
		System.out.println("moving " + this.getDirection());
		
		if (this.getDirection() == "North") {
			x--;
		}
		else if (this.getDirection() == "East") {
			y++;
		}
		else if (this.getDirection() == "South") {
			x++;
		}
		else if (this.getDirection() == "West") {
			y--;
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
}
