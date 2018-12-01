package wumpusWorld;
/*
 * Authors: Carie Pointer, Hugh Jackovich, Matthew Sagen
 * Date:    11/30/18
 * Programming Assignment 3: Wumpus World
 * 
 */
public class Agent {
	public int x,y;
	public boolean arrow, gold;
	public char id;
	
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
	public void shootArrow() {//if the agent shoots it's arrow, set hasArrow to false.
		arrow = false;
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
	public void moveForward(int upperBound) {//move the agent forward by one if the agent remains within the bounds of the maze.
		if((x < upperBound - 1 && y < upperBound - 1)) {
			x++;
			y++;
		}
		else {
			System.out.println("Can't move forward from here.");
		}
	}
	public void grab() {//lets the agent grab the gold from a square
		gold = true;
	}
	public boolean hasGold() {//return whether or not agent has the gold
		return gold;
	}
}
