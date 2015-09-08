package UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Main.DCGame;

@SuppressWarnings("serial")
public class CharacterPanel extends JPanel{
	
	private static final String CHAR_HP = "Health: ";
	private static final String CHAR_STRENGTH = "Strength: ";
	private static final String CHAR_ENDURANCE = "Endurance: ";
	private static final String CHAR_SKILL = "Skill: ";
	private static final String CHAR_SPEED = "Speed: ";
	private static final String CHAR_KILLCOUNT = "Kill Count @ ";
	private static final int LBL_WIDTH = 100;
	private static final int LBL_HEIGHT = 30;
	private DCGame game;
	private JLabel charHpLbl;
	private JLabel charStrenLbl;
	private JLabel charEndurLbl;
	private JLabel charSkillLbl;
	private JLabel charSpeedLbl;
	private JLabel charKillLbl;
	
	public CharacterPanel(DCGame g){
		setBackground(new Color(180, 180, 180));
		this.game = g;
		characterHealth();
		characterStrength();
		characterEndurance();
		characterSkill();
		characterSpeed();
		characterKillCount();
	}
	
	private void characterKillCount() {
		charKillLbl = new JLabel(CHAR_KILLCOUNT + Integer.toString(game.getCharacter().getKillCount()));
		charKillLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
		charKillLbl.setAlignmentY(LEFT_ALIGNMENT);
		charKillLbl.setAlignmentX(LEFT_ALIGNMENT);
		add(charKillLbl);		
	}
	
	private void characterSpeed() {
		charSpeedLbl = new JLabel(CHAR_SPEED + Integer.toString(game.getCharacter().getEndurance()));
		charSpeedLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
		charSpeedLbl.setAlignmentY(LEFT_ALIGNMENT);
		charSpeedLbl.setAlignmentX(LEFT_ALIGNMENT);
		add(charSpeedLbl);		
	}

	private void characterSkill() {
		charSkillLbl = new JLabel(CHAR_SKILL + Integer.toString(game.getCharacter().getSkill()));
		charSkillLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
		charSkillLbl.setAlignmentY(LEFT_ALIGNMENT);
		charSkillLbl.setAlignmentX(LEFT_ALIGNMENT);
		add(charSkillLbl);
	}

	private void characterEndurance() {
		charEndurLbl = new JLabel(CHAR_ENDURANCE + Integer.toString(game.getCharacter().getEndurance()));
		charEndurLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
		charEndurLbl.setAlignmentY(LEFT_ALIGNMENT);
		charEndurLbl.setAlignmentX(LEFT_ALIGNMENT);
		add(charEndurLbl);
	}

	public void characterHealth(){
		charHpLbl = new JLabel(CHAR_HP + Integer.toString(game.getCharacter().getHp()));
		charHpLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
		charHpLbl.setAlignmentY(LEFT_ALIGNMENT);
		charHpLbl.setAlignmentX(LEFT_ALIGNMENT);
		add(charHpLbl);
	}
	
	public void characterStrength(){
		charStrenLbl = new JLabel(CHAR_STRENGTH + Integer.toString(game.getCharacter().getStrength()));
		charStrenLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
		charStrenLbl.setAlignmentY(LEFT_ALIGNMENT);
		charStrenLbl.setAlignmentX(LEFT_ALIGNMENT);
		add(charStrenLbl);
	}
	
	
	
	public void update() {
		charHpLbl.setText(CHAR_HP + Integer.toString(game.getCharacter().getHp()));
		charKillLbl.setText(CHAR_KILLCOUNT + Integer.toString(game.getCharacter().getKillCount()));
	}
	
	

}
