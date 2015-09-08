package Droppables;

import java.awt.Color;

public class HpDot {
	
	public final static int SIZE_X = 2;
	public final static int SIZE_Y = 2;
	public final static Color COLOR = new Color(0, 240, 0);
	
	private int x;
	private int y;
	
	public HpDot(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	// getters and setters
	
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	

}
