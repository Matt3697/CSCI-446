package wumpusWorld;

import java.util.Random;

public class Wumpus {
	
	private int upperBound,x,y;
	private char id;
	
	public Wumpus(int upperBound) {
		this.upperBound = upperBound - 1;
		this.id = 'W';
		setLocation();
	}
	
	public void setLocation() {//generate random location to place wumpus
		Random rnd = new Random();
		x = rnd.nextInt(upperBound + 1);
		y = rnd.nextInt(upperBound + 1);
		//System.out.println(id + " " + x + ": " + y);
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
}
