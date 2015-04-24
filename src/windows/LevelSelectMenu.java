package windows;

import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

/**
 * This class creates a new level select menu so the player can navigate through
 * the different levels
 * 
 * @author Keven-Matthew
 */
public class LevelSelectMenu extends JPanel {
	
	/**
	 * These are the level buttons.
	 */
	private static JButton[] lvlButtons = new JButton[10];

	/**
	 * This is the back button in the lower left corner.
	 */
	private static JButton backButton;
	
	/**
	 * This creates a new level select menu
	 */
	public LevelSelectMenu(){
		setLayout(null);
		
		for(int i = 0; i<10; i++){
			lvlButtons[i] = new JButton(new ImageIcon(MainFrame.getTl().levelSelectLevelTexture[i]));
			lvlButtons[i].setBorder(BorderFactory.createEmptyBorder());
			lvlButtons[i].setContentAreaFilled(false);
			lvlButtons[i].addMouseListener(new ButtonListener());
			lvlButtons[i].addComponentListener(new ButtonResizeListener());
			add(lvlButtons[i]);
			if(i>MainFrame.getLevelReached()){
				lvlButtons[i].setEnabled(false);
			}
		}
		
		backButton = new JButton(new ImageIcon(MainFrame.getTl().levelSelectBackButtonTexture));
		backButton.setBorder(BorderFactory.createEmptyBorder());
		backButton.setContentAreaFilled(false);
		backButton.addMouseListener(new ButtonListener());
		add(backButton);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int x1 = 261*getWidth()/1280;
		int x2 = 729*getWidth()/1280;
		int width = 294*getWidth()/1280;
		int height = 86*getHeight()/720;
		
		g.drawImage(MainFrame.getTl().levelSelectBackgroundTexture, 0, 0, getWidth(), getHeight(), null);
		
		lvlButtons[0].setBounds(x1, 73*getHeight()/720, width, height);
		lvlButtons[1].setBounds(x1, 189*getHeight()/720, width, height);
		lvlButtons[2].setBounds(x1, 305*getHeight()/720, width, height);
		lvlButtons[3].setBounds(x1, 421*getHeight()/720, width, height);
		lvlButtons[4].setBounds(x1, 537*getHeight()/720, width, height);
		
		lvlButtons[5].setBounds(x2, 73*getHeight()/720, width, height);
		lvlButtons[6].setBounds(x2, 189*getHeight()/720, width, height);
		lvlButtons[7].setBounds(x2, 305*getHeight()/720, width, height);
		lvlButtons[8].setBounds(x2, 421*getHeight()/720, width, height);
		lvlButtons[9].setBounds(x2, 537*getHeight()/720, width, height);
		
		backButton.setBounds(0, 617*getHeight()/720, 260*getWidth()/1280, 105*getHeight()/720);
	}
	
	public static JButton[] getLvlButtons() {
		return lvlButtons;
	}
	
	public static JButton getBackButton() {
		return backButton;
	}
}
