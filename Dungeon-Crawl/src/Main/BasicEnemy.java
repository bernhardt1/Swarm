package Main;

import java.awt.Color;

public class BasicEnemy extends Enemy{
	
	public static Color COLOR = new Color(0, 120, 120);
	
	public BasicEnemy(int x, int y){	
		super(x, y);
		setHp(3);
		setSpeed(1);
		setSizeX(20);
		setSizeY(20);
	}
	
	
}
