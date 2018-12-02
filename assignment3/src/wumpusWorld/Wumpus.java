package wumpusWorld;

import java.util.Random;

public class Wumpus {
	
	private int upperBound,x,y;
	private char id;
	private boolean alive, scream;
	
	public Wumpus(int upperBound) {
		this.upperBound = upperBound - 1;
		this.id = 'W';
		this.alive = true;
		this.scream = false;
		setLocation();
	}
	
	public void setLocation() {//generate random location to place wumpus
		Random rnd = new Random();
		x = rnd.nextInt(upperBound + 1);
		y = rnd.nextInt(upperBound + 1);
		//System.out.println(id + " " + x + ": " + y);
	}
	
	public boolean isValidLocation() {//wumpus cannot be spawned at 0,0, the starting point of the agent.
		if(x == 0 && y == 0) {
			return false;
		}
		else {
			return true;
		}
	}
	public int getX() {//return x location of wumpus
		return x;
	}
	public int getY() {//return y location of wumpus
		return y;
	}
	public char getId() {//return character representation of wumpus --> W
		return id;
	}
	public void killWumpus() {//kill the wumpus
		alive = false;
		scream = true;
	}
	public boolean isAlive() {//return whether or not wumpus is alive.
		return alive;
	}
	public boolean didScream() {//return whether or not the wumpus screamed when the arrow was shot
		return scream;
	}
}
