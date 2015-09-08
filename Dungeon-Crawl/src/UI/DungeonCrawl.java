package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.Timer;

import Main.DCGame;

@SuppressWarnings("serial")
public class DungeonCrawl extends JFrame {
	
	private static final int INTERVAL = 20;
	private DCGame game;
	private GamePanel gp;
	private CharacterPanel cp;
	
	public DungeonCrawl() { 
		super("Dungeon Crawl");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		game = new DCGame();
		gp = new GamePanel(game);
		cp = new CharacterPanel(game);
		add(gp);
		add(cp, BorderLayout.NORTH);
		addKeyListener(new KeyHandlerMovement());
		addKeyListener(new KeyHandlerShooting());
		pack();
		centreOnScreen();
		setVisible(true);
		addTimer();
	}
	
	private void addTimer() {
		Timer t = new Timer(INTERVAL, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae){
				game.update();
				cp.update();
				gp.repaint();
			}
			
		});		
		t.start();
	}
	
	private void centreOnScreen(){
		Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
	}
	
	private class KeyHandlerMovement extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			game.keyPressedMovement(e.getKeyCode());		
		}
		@Override
		public void keyReleased(KeyEvent e){
			game.keyReleasedMovement(e.getKeyCode());
		}
	}
	
	private class KeyHandlerShooting extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			game.keyPressedShooting(e.getKeyCode());		
		}
		@Override
		public void keyReleased(KeyEvent e){
			game.keyReleasedShooting(e.getKeyCode());
		}
	}
	
	public static void main(String[] args){
		new DungeonCrawl();
	}

}
