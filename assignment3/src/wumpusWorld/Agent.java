package wumpusWorld;
/*
 * Authors: Carie Pointer, Hugh Jackovich, Matthew Sagen
 * Date:    11/30/18
 * Programming Assignment 3: Wumpus World
 * 
 */
public class Agent {
	public int x,y;
	public boolean arrow;
	public char id;
	
	public Agent(int x, int y) {//x and y coordinates of agent
		this.x = x;
		this.y = y;
		this.arrow = true;
		this.id = 'A';
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
}
