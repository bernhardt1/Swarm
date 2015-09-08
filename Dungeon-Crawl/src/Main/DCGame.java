package Main;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import javax.swing.InputMap;
import javax.swing.ActionMap;
import javax.swing.Timer;

import Droppables.HpDot;
import MapBlocks.MapPiece;
import MapBlocks.RoomTrigger;
import MapBlocks.Wall;
import Maps.Map;
import Maps.SecondMap;
import Maps.StartMap;

public class DCGame {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;

	private Character character;
	private BasicEnemy enemy;
	private BasicEnemy blobSpawnEnemy;
	private List<Enemy> enemies;
	private List<Bullet> bullets;
	private List<MapPiece> map;
	private List<HpDot> hpDots;
	private List<Map> mapList;
	private static final int RANGE = 100;
	private boolean isRandomSpawning;
	private boolean isGameOver;
	private boolean shootingRight;
	private boolean shootingLeft;
	private boolean shootingDown;
	private boolean shootingUp;
	private int spawnTimer = 50;
	private int firingRate = 10;
	private int firingRateI = 0;
	int blobSpawnSize = 20;
	
	// these have to be included to avoid stammering movement.
	boolean movingLeft;
	boolean movingRight;
	boolean movingUp;
	boolean movingDown;

	// Constructs a Dungeon Crawl game.
	public DCGame() {
		setUp();
	}

	public void update() {
		characterAndWallCollision();
		characterMove();
		enemySetSpeed();
		enemyAndWallCollision();
		enemyMove();
		spawnEnemy();
		blobSpawnCheck();
		enemyAndPlayerCollision();
		enemyAndBulletCollision();
		playerAndHpDotCollision();
		enemyDeath();
		moveBullets();
		bulletAndWallCollision();
		checkGameOver();
    //	setMap();
		boundsRemoval();
		characterAndRoomTriggerCollision();
		increaseDifficulty();
		bulletFiringRate();
		bulletDirection();
		
	}

	// the basic enemy size is not a constant and so these methods need to be adjusted if basicEnemy size is changed.
	private void blobSpawn(BlobEnemy e) {
		int playerX = character.getX() + Character.SIZE_X/2;
		int playerY = character.getY() + Character.SIZE_Y/2;
		int blobX = e.getX() + e.getSizeX()/2;
		int blobY = e.getY() + e.getSizeY()/2;
		int xValue = playerX - blobX;
		int yValue = playerY - blobY;
		int spawnX = 0;
		int spawnY = 0;
		if(xValue != 0 && yValue != 0){
			float xToYFraction = (float)xValue/(float)yValue;
			// player is down and right of blob.
			if (xValue > 0 && yValue > 0){
				if (xToYFraction > 1){
					spawnX = blobX + e.getSizeX()/2;
					spawnY = (int) (blobY + Math.round((e.getSizeY()/2)/xToYFraction)); 
				}
				else{
					spawnX = (int) (blobX + Math.round((e.getSizeX()/2)*xToYFraction));
					spawnY = blobY + e.getSizeY()/2;
				}
			}
			// player is up and right of blob
			if (xValue > 0 && yValue < 0){
				if (xToYFraction < -1){
					spawnX = blobX + e.getSizeX()/2;
					spawnY = (int) (blobY - blobSpawnSize/2 - Math.round((e.getSizeY()/2)/-xToYFraction));  
				}
				else{
					spawnX = (int) (blobX + Math.round((e.getSizeX()/2)*-xToYFraction));
					spawnY = blobY - e.getSizeY()/2 - blobSpawnSize;
				}

			}
			// player is down and left
			if (xValue < 0 && yValue > 0){
				if (xToYFraction < -1){
					spawnX = blobX - blobSpawnSize - e.getSizeX()/2;
					spawnY = (int) (blobY + Math.round((e.getSizeY()/2)/-xToYFraction)); 
				}
				else{
					spawnX = (int) (blobX - blobSpawnSize - Math.round((e.getSizeX()/2)*-xToYFraction));
					spawnY = blobY + e.getSizeY()/2;
				}
			}
			//player is up and left
			else if (xValue < 0 && yValue < 0){
				if (xToYFraction > 1){
					spawnX = blobX - blobSpawnSize - e.getSizeX()/2;
					spawnY = (int) (blobY - blobSpawnSize - Math.round((e.getSizeY()/2)/xToYFraction)); 
				}
				else{
					spawnX = (int) (blobX - blobSpawnSize/2 - Math.round((e.getSizeX()/2)*xToYFraction));
					spawnY = blobY - blobSpawnSize - e.getSizeY()/2;
				}

			}
			blobSpawnEnemy = new BasicEnemy(spawnX, spawnY);
			enemies.add(blobSpawnEnemy);
		}
	}

	private void blobSpawnCheck() {
		Random generator = new Random();
		// Can't modify the list of enemies while it's open in the for loop. Count number of blobs to spawn and call blobSpawn for the total afterwards.
		List<BlobEnemy> spawningBlobs = new ArrayList<BlobEnemy>();
		for (Enemy e: enemies){
			if (e.getClass().toString().equals("class Main.BlobEnemy")){
				int onePerThreeSeconds = generator.nextInt(180);
				if (onePerThreeSeconds == 5){
					spawningBlobs.add((BlobEnemy) e);
				}
			}
		}
		if (!spawningBlobs.isEmpty()){
			for (BlobEnemy e: spawningBlobs){
				blobSpawn(e);
			}
		}
		spawningBlobs.clear();
	}

	private void placeEnemy() {
		BlobEnemy blob1 = new BlobEnemy(35, 35);
		BlobEnemy blob2 = new BlobEnemy(735, 35);
		BlobEnemy blob3 = new BlobEnemy(735, 535);
		BlobEnemy blob4 = new BlobEnemy(35, 535);
		enemies.add(blob1);
		enemies.add(blob2);
		enemies.add(blob3);
		enemies.add(blob4);
	}

	private void playerAndHpDotCollision() {
		int charX = character.getX();
		int charY = character.getY();
		Rectangle characterBox = new Rectangle();
		characterBox.setBounds(charX, charY, Character.SIZE_X, Character.SIZE_Y);
		for (int i = 0; i < hpDots.size() ; i++){
			int hpDotX = hpDots.get(i).getX();
			int hpDotY = hpDots.get(i).getY();
			Rectangle hpDotBox = new Rectangle();
			hpDotBox.setBounds(hpDotX, hpDotY, HpDot.SIZE_X, HpDot.SIZE_Y);
			if (characterBox.contains(hpDotBox)){
				hpDots.remove(i);
				character.setHp(character.getHp() + 1);
			}
		}
		
	}

	public void enemyDeath() {
		Random generator = new Random();
		for (int z = 0; z < enemies.size(); z++) {
			int hpDotX = enemies.get(z).getX() + enemies.get(z).getSizeX()/2;
			int hpDotY = enemies.get(z).getY() + enemies.get(z).getSizeY()/2;
			if(enemies.get(z).getHp() == 0){
				enemies.remove(z);
				character.setKillCount(character.getKillCount() + 1);
				if (generator.nextInt(2) == 1){
					HpDot hpDot = new HpDot(hpDotX, hpDotY);
					hpDots.add(hpDot);
				}
			}
		}
	}
	
	public void characterAndRoomTriggerCollision(){
		int charX = character.getX();
		int charY = character.getY();
		Rectangle characterBox = new Rectangle();
		characterBox.setBounds(charX, charY, Character.SIZE_X, Character.SIZE_Y);
		List<MapPiece> triggers = new ArrayList<MapPiece>();
		for(int i = 0 ; i < map.size() ; i++){
			if(map.get(i).getClass().toString().equals("class MapBlocks.RoomTrigger")){
				triggers.add(map.get(i));
			}
		}
		for(int z = 0 ; z < triggers.size() ; z++){
			int trigX = triggers.get(z).getX();
			int trigY = triggers.get(z).getY();
			Rectangle trigBox = new Rectangle();
			trigBox.setBounds(trigX, trigY, MapPiece.SIZE_X, MapPiece.SIZE_Y);
			if (characterBox.intersects(trigBox)){
				if (enemies.isEmpty()){
				Map secondMap = new SecondMap();
				enemies.clear();
				map = secondMap.getMap();
				placeEnemy();
				isRandomSpawning = true;
				}
			}
		}
		
		
	}
	
	// Removes Bullets if they go out of bounds of the game window.
	public void boundsRemoval(){
		for(int i = 0 ; i < bullets.size() ; i++){
			if (bullets.get(i).getX() < 0 || bullets.get(i).getX() > WIDTH){
				bullets.remove(i);
			} else if (bullets.get(i).getY() < 0 || bullets.get(i).getY() > HEIGHT){
				bullets.remove(i);
			}
			
		}
	}
	
	// sets the map to PlusMap when player kill count gets to 5.
	public void setMap(){
		if (character.getKillCount() == 5){
			Map plusMap = new SecondMap();
			map = plusMap.getMap();
		}
	}

	public void characterMove() {
		character.moveHorizontal();
		character.moveVertical();
	}

	public void checkGameOver() {
		if (character.getHp() <= 0)
			isGameOver = true;
		if (isGameOver) {
			enemies.clear();
		}
	}

	public void moveBullets() {
		for (Bullet b : bullets) {
			b.moveBullets();
		}
	}

	// this method causes the list of enemies to move towards the main character
	// at their speed if the enemy is within RANGE of the character.
	public void enemySetSpeed() {
		for (Enemy e : enemies) {
			if (withinRange(character, e)) {
				if (above(character, e))
					e.setVelY(e.getSpeed());
				if (below(character, e))
					e.setVelY(-e.getSpeed());
				if (left(character, e))
					e.setVelX(e.getSpeed());
				if (right(character, e))
					e.setVelX(-e.getSpeed());
			} else {
				e.setVelX(0);
				e.setVelY(0);
			}
		}
	}

	public void enemyAndWallCollision() {
		for (int i = 0; i < enemies.size(); i++) {
			int enemyFutureXMovingX = enemies.get(i).getX() + enemies.get(i).getVelX();
			int enemyFutureYMovingX = enemies.get(i).getY();
			int enemyFutureXMovingY = enemies.get(i).getX();
			int enemyFutureYMovingY = enemies.get(i).getY() + enemies.get(i).getVelY();
			Rectangle enemyFutureBoxMovingX = new Rectangle();
			Rectangle enemyFutureBoxMovingY = new Rectangle();
			enemyFutureBoxMovingX.setBounds(enemyFutureXMovingX, enemyFutureYMovingX, Character.SIZE_X, Character.SIZE_Y);
			enemyFutureBoxMovingY.setBounds(enemyFutureXMovingY, enemyFutureYMovingY, Character.SIZE_X, Character.SIZE_Y);
			for (int z = 0; z < map.size(); z++) {
				int pieceMinX = map.get(z).getX();
				int pieceMinY = map.get(z).getY();
				Rectangle mapPieceBox = new Rectangle();
				mapPieceBox.setBounds(pieceMinX, pieceMinY, MapPiece.SIZE_X, MapPiece.SIZE_Y);
				if (enemyFutureBoxMovingX.intersects(mapPieceBox)) {
					enemies.get(i).setVelX(0);
				}
				if (enemyFutureBoxMovingY.intersects(mapPieceBox)) {
					enemies.get(i).setVelY(0);
				}
			}
		}
	}

	public void enemyMove() {
		for (Enemy e : enemies) {
			e.moveHorizontal();
			e.moveVertical();
		}
	}

	public void characterAndWallCollision() {
		int charFutureXMovingX = character.getX() + character.getVelX();
		int charFutureYMovingX = character.getY();
		int charFutureXMovingY = character.getX();
		int charFutureYMovingY = character.getY() + character.getVelY();
		Rectangle characterFutureBoxMovingX = new Rectangle();
		Rectangle characterFutureBoxMovingY = new Rectangle();
		characterFutureBoxMovingX.setBounds(charFutureXMovingX, charFutureYMovingX, Character.SIZE_X, Character.SIZE_Y);
		characterFutureBoxMovingY.setBounds(charFutureXMovingY, charFutureYMovingY, Character.SIZE_X, Character.SIZE_Y);
		List<MapPiece> walls = new ArrayList<MapPiece>();
		for (int y = 0; y < map.size(); y++){
			if(map.get(y).getClass().toString().equals("class MapBlocks.Wall")){
				walls.add(map.get(y));
			}
		}
		for (int i = 0; i < walls.size(); i++) {
			int pieceMinX = walls.get(i).getX();
			int pieceMinY = walls.get(i).getY();
			Rectangle mapPieceBox = new Rectangle();
			mapPieceBox.setBounds(pieceMinX, pieceMinY, MapPiece.SIZE_X, MapPiece.SIZE_Y);
			if (characterFutureBoxMovingX.intersects(mapPieceBox)) {
				character.setVelX(0);
			}
			if (characterFutureBoxMovingY.intersects(mapPieceBox)) {
				character.setVelY(0);
			}
		}
	}

	// currently this checks for a collision between bullets and ALL MapPieces.
	public void bulletAndWallCollision() {
		for (int i = 0; i < bullets.size(); i++) {
			int bulletMinX = bullets.get(i).getX();
			int bulletMinY = bullets.get(i).getY();
			Rectangle bulletBox = new Rectangle();
			bulletBox.setBounds(bulletMinX, bulletMinY, Bullet.SIZE_X, Bullet.SIZE_Y);
			List<MapPiece> walls = new ArrayList<MapPiece>();
			for (int y = 0; y < map.size(); y++){
				if(map.get(y).getClass().toString().equals("class MapBlocks.Wall")){
					walls.add(map.get(y));
				}
			}
			for (int z = 0; z < walls.size(); z++) {
				int mapPieceMinX = walls.get(z).getX();
				int mapPieceMinY = walls.get(z).getY();
				Rectangle mapPieceBox = new Rectangle();
				mapPieceBox.setBounds(mapPieceMinX, mapPieceMinY, MapPiece.SIZE_X, MapPiece.SIZE_Y);
				if (mapPieceBox.intersects(bulletBox)) {
					if (!bullets.isEmpty()){
						bullets.remove(i);
					}
				}
			}
		}
	}

	public void enemyAndPlayerCollision() {
		int charMinX = character.getX();
		int charMinY = character.getY();
		Rectangle characterBox = new Rectangle();
		characterBox.setBounds(charMinX, charMinY, Character.SIZE_X, Character.SIZE_Y);
		for (int i = 0; i < enemies.size(); i++) {
			int enemyMinX = enemies.get(i).getX();
			int enemyMinY = enemies.get(i).getY();
			Rectangle enemyBox = new Rectangle();
			enemyBox.setBounds(enemyMinX, enemyMinY, enemies.get(i).getSizeX(), enemies.get(i).getSizeY());
			if (enemyBox.intersects(characterBox)) {
				enemies.remove(i);
				character.setHp(character.getHp() - 1);
			}
		}
	}

	private void enemyAndBulletCollision() {
			for (int z = 0; z < enemies.size(); z++) {
				int enemyMinX = enemies.get(z).getX();
				int enemyMinY = enemies.get(z).getY();
				Rectangle enemyBox = new Rectangle();
				enemyBox.setBounds(enemyMinX, enemyMinY, enemies.get(z).getSizeX(), enemies.get(z).getSizeY());
				for (int i = 0; i < bullets.size(); i++) {
					int bulletMinX = bullets.get(i).getX();
					int bulletMinY = bullets.get(i).getY();
					Rectangle bulletBox = new Rectangle();
					bulletBox.setBounds(bulletMinX, bulletMinY, Bullet.SIZE_X, Bullet.SIZE_Y);
				if (enemyBox.intersects(bulletBox)) {
					bullets.remove(i);
					enemies.get(z).setHp(enemies.get(z).getHp() - 1);
				}
			}
		}
	}

	public boolean withinRange(Character c, Enemy e) {
		int CEY = c.getY() - e.getY() - Character.SIZE_Y / 2;
		int CEX = c.getX() - e.getX() - Character.SIZE_X / 2;
		if (-RANGE < CEX && CEX < RANGE && -RANGE < CEY && CEY < RANGE)
			return true;
		else if (e.getHp() < 5)
			return true;
		else
			return false;
	}

	public boolean above(Character c, Enemy e) {
		int CEY = c.getY() - e.getY();
		if (0 < CEY)
			return true;
		else
			return false;
	}

	public boolean below(Character c, Enemy e) {
		int CEY = c.getY() - e.getY();
		if (CEY < 0)
			return true;
		else
			return false;
	}

	public boolean right(Character c, Enemy e) {
		int CEX = c.getX() - e.getX();
		if (CEX < 0)
			return true;
		else
			return false;
	}

	public boolean left(Character c, Enemy e) {
		int CEX = c.getX() - e.getX();
		if (0 < CEX)
			return true;
		else
			return false;
	}

	private void setUp() {
		character = new Character(WIDTH / 2, HEIGHT / 2);
		enemies = new ArrayList<Enemy>();
		bullets = new ArrayList<Bullet>();
		hpDots = new ArrayList<HpDot>();
		this.isRandomSpawning = false;
		Map currMap = new StartMap();
		map = currMap.getMap();
		placeEnemy();
		this.isGameOver = false;
	}
	
	private void increaseDifficulty(){
		if (character.getKillCount() == 15){
			spawnTimer = 40;
		}
		if (character.getKillCount() == 25){
			spawnTimer = 30;
		}
		if (character.getKillCount() == 35){
			spawnTimer = 20;
		}
		if (character.getKillCount() == 40){
			spawnTimer = 10;
		}
		if (character.getKillCount() == 55){
			spawnTimer = 5;
		}
		if (character.getKillCount() == 57){
			spawnTimer = 4;
		}
		else if (character.getKillCount() == 59){
			spawnTimer = 3;
		}
	}


	private void spawnEnemy() {
		if (isRandomSpawning == true){
		Random generator = new Random();
		int oneToHundred = generator.nextInt(spawnTimer);
		if (oneToHundred == 1) {
			int xHigh = WIDTH - Wall.SIZE_X * 3;
			int xLow = Wall.SIZE_X;
			int yHigh = HEIGHT - Wall.SIZE_Y * 3;
			int yLow = Wall.SIZE_Y;
			int x = generator.nextInt(xHigh) + xLow;
			int y = generator.nextInt(yHigh) + yLow;
			int charX = character.getX() + Character.SIZE_X/2;
			int charY = character.getY() + Character.SIZE_Y/2;
			while ((charX - x < 30 && charX - x > -30) || (charX - x > 100 || charX - x < -100)){
				x = generator.nextInt(xHigh) + xLow;
			}
			while ((charY - y < 30 && charY - y > -30) || (charY - y > 100 || charY - y < -100)){
				y = generator.nextInt(yHigh) + yLow;
			}
			enemy = new BasicEnemy(x, y);
			enemies.add(enemy);	
		}
		}
	}

	// getters and setters
	public Character getCharacter() {
		return character;
	}

	public List<Enemy> getEnemies() {
		return enemies;
	}
	
	public List<HpDot> getHpDots() {
		return hpDots;
	}

	public List<Bullet> getBullets() {
		return bullets;
	}

	public List<MapPiece> getMap() {
		return map;
	}

	public boolean getGameOver() {
		return isGameOver;
	}

	// Character Movement basic WASD;
	public void keyPressedMovement(int keyCode) {
		if (keyCode == KeyEvent.VK_A){
			movingRight = false;
			movingLeft = true;
			character.setVelX(-character.getDX());
		}
		else if (keyCode == KeyEvent.VK_D){
			movingLeft = false;
			movingRight = true;
			character.setVelX(character.getDX());
		}
		if (keyCode == KeyEvent.VK_W){
			movingDown = false;
			movingUp = true;
			character.setVelY(-character.getDY());
		}
		else if (keyCode == KeyEvent.VK_S){
			movingUp = false;
			movingDown = true;
			character.setVelY(character.getDY());
		}
	}

	public void keyReleasedMovement(int keyCode) {
		if (keyCode == KeyEvent.VK_A){
			if (!movingRight)
				character.setVelX(0);
		}
		else if (keyCode == KeyEvent.VK_D){
			if (!movingLeft)
				character.setVelX(0);
		}
		if (keyCode == KeyEvent.VK_W){
			if (!movingDown)
			character.setVelY(0);
		}
		else if (keyCode == KeyEvent.VK_S){
			if (!movingUp)
			character.setVelY(0);
		}
	}
	
	public void keyReleasedShooting(int keyCode) {
		if (keyCode == KeyEvent.VK_LEFT)
			shootingLeft = false;
		else if (keyCode == KeyEvent.VK_RIGHT)
			shootingRight = false;
		if (keyCode == KeyEvent.VK_UP)
			shootingUp = false;
		else if (keyCode == KeyEvent.VK_DOWN)
			shootingDown = false;
	}
	
	public void keyPressedShooting(int keyCode) {
		if (keyCode == KeyEvent.VK_LEFT) {
			shootingRight = false;
			shootingDown = false;
			shootingUp = false;
			shootingLeft = true;
		}
		if (keyCode == KeyEvent.VK_RIGHT) {
			shootingRight = true;
			shootingUp = false;
			shootingLeft = false;
		}
		if (keyCode == KeyEvent.VK_UP) {
			shootingRight = false;
			shootingDown = false;
			shootingUp = true;
			shootingLeft = false;
		}
		if (keyCode == KeyEvent.VK_DOWN) {
			shootingRight = false;
			shootingDown = true;
			shootingUp = false;
			shootingLeft = false;
		}
	}
	
	private void bulletDirection() {
		if (shootingLeft){
			shootLeft();
		}
		if (shootingRight){
			shootRight();
		}
		if (shootingUp){
			shootUp();
		}
		if(shootingDown){
			shootDown();
		}
	}
	
	private void bulletFiringRate() {
		if (firingRateI != 0){
			firingRateI -= 1;
		}
	}

	private void shootLeft() {
		if (firingRateI == 0){
			bullets.add(new Bullet(character.getX(), character.getY() + Character.SIZE_Y / 2, -Bullet.SPEED, 0));
			firingRateI = 10;
		}
	}

	private void shootRight() {
		if (firingRateI == 0){
			bullets.add(new Bullet(character.getX() + Character.SIZE_X, character.getY() + Character.SIZE_Y / 2, Bullet.SPEED, 0));
			firingRateI = 10;
		}
	}

	private void shootUp() {
		if (firingRateI == 0){
			bullets.add(new Bullet(character.getX() + Character.SIZE_X / 2, character.getY(), 0, -Bullet.SPEED));
			firingRateI = 10;
		}
	}

	private void shootDown() {
		if (firingRateI == 0){
			bullets.add(new Bullet(character.getX() + Character.SIZE_X / 2, character.getY() + Character.SIZE_Y, 0, Bullet.SPEED));
			firingRateI = 10;
		}
	}

}
