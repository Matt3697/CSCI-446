package wumpusWorld;

import java.util.Random;

public class Gold {
	private int x,y,upperBound;
	private char id;
	
	public Gold(int upperBound) {
		this.upperBound = upperBound - 1;
		this.id = 'G';
		setLocation();
	}
	
	public void setLocation() {//generate random location within maze to place gold
		Random rnd = new Random();
		x = rnd.nextInt(upperBound + 1);
		y = rnd.nextInt(upperBound + 1);
		//System.out.println(id + " " + x + ": " + y);
	}
	public boolean isValidLocation() {//gold cannot be spawned at 0,0, the starting point of the agent.
		if(x == 0 && y == 0) {
			return false;
		}
		else {
			return true;
		}
	}
	public int getX() {//return x position of gold
		return x;
	}
	
	public int getY() {//return y position of gold
		return y;
	}
	public char getId() {//return character representation of gold --> G
		return id;
	}
}
