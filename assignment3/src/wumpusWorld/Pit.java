package wumpusWorld;

public class Pit {
	private int x, y;
	private char id;

	public Pit(int x, int y) {
		this.x = x;
		this.y = y;
		this.id = 'P';
	}
	
	public char getId() {
		return id;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
}
