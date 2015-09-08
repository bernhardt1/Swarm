package Main;

import java.awt.Color;

public class Bullet {
	
	public static final int SIZE_X = 4;
	public static final int SIZE_Y = 4;
	public static final int SPEED = 5;
	public static final Color COLOR = new Color(0, 0, 0);
	
	private int x;
	private int y;
	private int speedX;
	private int speedY;
	
	public Bullet(int x, int y, int speedX, int speedY){
		this.x = x;
		this.y = y;
		this.speedX = speedX;
		this.speedY = speedY;
	}
	
	public void moveBullets(){
		 x += speedX;
		 y += speedY;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
	

}
