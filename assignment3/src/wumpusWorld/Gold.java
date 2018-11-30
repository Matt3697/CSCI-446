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
