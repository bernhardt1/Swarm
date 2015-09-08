package Main;

import java.awt.Color;

public class Character {

	private int hp;
	private int strength;
	private int skill;
	private int endurance;
	private int speed;
	private int killCount;

	// SIZE_X and SIZE_Y will become a function of the endurance attribute.
	public static final int SIZE_X = 20;
	public static final int SIZE_Y = 20;

	// DX and DY will become a function of speed attribute.
	public int DX = 3;
	public int DY = 3;
	public int velX = 0;
	public int velY = 0;

	// Color will become a function of attributes combined
	// RED scales based on strength.
	// GREEN scales based on skill.
	// BLUE scales based on endurance.
	public static Color COLOR = new Color(200, 50, 0);

	private int x;
	private int y;

	public Character(int x, int y) {
		this.x = x;
		this.y = y;
		setHp(3);
		setStrength(1);
		setEndurance(1);
		setSkill(1);
		setSpeed(1);
		setKillCount(0);
	}
	
	

	public int getKillCount() {
		return killCount;
	}
	
	public void setKillCount(int killCount) {
		this.killCount = killCount;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getSkill() {
		return skill;
	}

	public void setSkill(int skill) {
		this.skill = skill;
	}

	public int getEndurance() {
		return endurance;
	}

	public void setEndurance(int endurance) {
		this.endurance = endurance;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
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

	public void moveHorizontal() {
		x += velX;
	}

	public void moveVertical() {
		y += velY;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public int getVelX() {
		return velX;
	}

	public int getVelY() {
		return velY;
	}

	public int getDX() {
		return DX;
	}

	public void setDX(int dX) {
		DX = dX;
	}

	public int getDY() {
		return DY;
	}

	public void setDY(int dY) {
		DY = dY;
	}

}
