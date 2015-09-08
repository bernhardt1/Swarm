package Main;

import java.awt.Color;

public class BlobEnemy extends Enemy{
	
	public static Color COLOR = new Color(120, 120, 0);

	public BlobEnemy(int x, int y) {
		super(x, y);
		setHp(5);
		setSpeed(1);
		setSizeX(30);
		setSizeY(30);
	}

}
