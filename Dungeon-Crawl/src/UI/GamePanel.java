package UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JPanel;

import Droppables.HpDot;
import Main.BasicEnemy;
import Main.BlobEnemy;
import Main.Bullet;
import Main.DCGame;
import Main.Character;
import Main.Enemy;
import MapBlocks.MapPiece;
import MapBlocks.RoomTrigger;
import MapBlocks.Wall;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {

	private static final String OVER = "Game Over!";
	private static final String REPLAY = "R to replay.";
	private DCGame game;

	// Constructs a game panel
	// effects: sets size and background colour of panel
	public GamePanel(DCGame g) {
		setPreferredSize(new Dimension(DCGame.WIDTH, DCGame.HEIGHT));
		setBackground(Color.LIGHT_GRAY);
		this.game = g;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawGame(g);
	}

	private void drawGame(Graphics g) {
		drawCharacter(g);
		drawHpDots(g);
		drawEnemies(g);
		drawBullets(g);
		drawMap(g);
	}

	private void drawHpDots(Graphics g) {
		List<HpDot> hpDots = game.getHpDots();
		Color hpColor = HpDot.COLOR;
		g.setColor(hpColor);
		for(HpDot h: hpDots){
			g.fillRect(h.getX(), h.getY(), HpDot.SIZE_X, HpDot.SIZE_Y);
		}
		
	}

	private void drawCharacter(Graphics g) {
		Character c = game.getCharacter();
		Color cColor = Character.COLOR;
		g.setColor(cColor);
		g.fillRect(c.getX(), c.getY(), Character.SIZE_X, Character.SIZE_Y);
	}

	private void drawEnemies(Graphics g) {
		List<Enemy> enemies = game.getEnemies();
		Color basicColor = BasicEnemy.COLOR;
		g.setColor(basicColor);
		for (Enemy e : enemies) {
			System.out.println(e.getClass().toString());
			if (e.getClass().toString().equals("class Main.BasicEnemy"))
			g.fillRect(e.getX(), e.getY(), e.getSizeX(), e.getSizeY());
		}
		Color blobColor = BlobEnemy.COLOR;
		g.setColor(blobColor);
		for (Enemy e : enemies) {
			if (e.getClass().toString().equals("class Main.BlobEnemy"))
			g.fillRect(e.getX(), e.getY(), e.getSizeX(), e.getSizeY());
		}

	}

	private void drawBullets(Graphics g) {
		List<Bullet> bullets = game.getBullets();
		Color bColor = Bullet.COLOR;
		g.setColor(bColor);
		for (Bullet b : bullets) {
			g.fillRect(b.getX(), b.getY(), Bullet.SIZE_X, Bullet.SIZE_Y);
		}
	}

	private void drawMap(Graphics g) {
		List<MapPiece> map = game.getMap();
		Color wColor = Wall.COLOR;
		Color tColor = RoomTrigger.COLOR;
		for (MapPiece block : map) {
			if(block.getClass().toString().equals("class MapBlocks.Wall")){
				g.setColor(wColor);
				g.fillRect(block.getX(), block.getY(), Wall.SIZE_X, Wall.SIZE_Y);
			}	else if(block.getClass().toString().equals("class MapBlocks.RoomTrigger")){
				g.setColor(tColor);
				g.fillRect(block.getX(), block.getY(), Wall.SIZE_X, Wall.SIZE_Y);
			}
		}
	}
}
