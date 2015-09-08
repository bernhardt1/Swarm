package Main;

import java.awt.Color;

public abstract class Enemy {
	
	private int x;
	private int y;
	private int hp;
	private int speed;
	private int velX;
	private int velY;
	private int sizeX;
	private int sizeY;
	
	public Enemy(int x, int y){	
		this.x = x;
		this.y = y;
	}
	
	public void moveHorizontal() {
		x += getVelX();
	}

	public void moveVertical() {
		y += getVelY();
	}
	
	public int getSpeed(){
		return this.speed;
	}
	
	public void setSpeed(int i){
		this.speed = i;
	}
	
	public int getVelX() {
		return velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelX(int velX){
		this.velX = velX;
	}
	
	public void setVelY(int velY){
		this.velY = velY;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
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

	public int getSizeX() {
		return sizeX;
	}

	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}

}