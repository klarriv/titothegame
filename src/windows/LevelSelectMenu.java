package windows;

import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
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
		
		/**
		 * This is the mouse listener to make the buttons change when the mouse hovers over them.
		 * @author Keven-Matthew
		 *
		 */
		class ButtonListener implements MouseListener {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(((JButton) e.getSource()).isEnabled()){
					if ((JButton) e.getSource() == lvlButtons[0]) {
						CardLayout cardLayout = (CardLayout) MainFrame.getMenus().getLayout();
						cardLayout.show(MainFrame.getMenus(), "LEVEL0");
					} else if ((JButton) e.getSource() == lvlButtons[1]) {
						CardLayout cardLayout = (CardLayout) MainFrame.getMenus().getLayout();
						cardLayout.show(MainFrame.getMenus(), "LEVEL1");
					} else if ((JButton) e.getSource() == lvlButtons[2]) {
						CardLayout cardLayout = (CardLayout) MainFrame.getMenus().getLayout();
						cardLayout.show(MainFrame.getMenus(), "LEVEL2");
					} else if ((JButton) e.getSource() == lvlButtons[3]) {
						CardLayout cardLayout = (CardLayout) MainFrame.getMenus().getLayout();
						cardLayout.show(MainFrame.getMenus(), "LEVEL3");
					} else if ((JButton) e.getSource() == lvlButtons[4]) {
						CardLayout cardLayout = (CardLayout) MainFrame.getMenus().getLayout();
						cardLayout.show(MainFrame.getMenus(), "LEVEL4");
					} else if ((JButton) e.getSource() == lvlButtons[5]) {
						CardLayout cardLayout = (CardLayout) MainFrame.getMenus().getLayout();
						cardLayout.show(MainFrame.getMenus(), "LEVEL5");
					} else if ((JButton) e.getSource() == lvlButtons[6]) {
						CardLayout cardLayout = (CardLayout) MainFrame.getMenus().getLayout();
						cardLayout.show(MainFrame.getMenus(), "LEVEL6");
					} else if ((JButton) e.getSource() == lvlButtons[7]) {
						CardLayout cardLayout = (CardLayout) MainFrame.getMenus().getLayout();
						cardLayout.show(MainFrame.getMenus(), "LEVEL7");
					} else if ((JButton) e.getSource() == lvlButtons[8]) {
						CardLayout cardLayout = (CardLayout) MainFrame.getMenus().getLayout();
						cardLayout.show(MainFrame.getMenus(), "LEVEL8");
					} else if ((JButton) e.getSource() == lvlButtons[9]) {
						CardLayout cardLayout = (CardLayout) MainFrame.getMenus().getLayout();
						cardLayout.show(MainFrame.getMenus(), "LEVEL9");
					}
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if(((JButton) e.getSource()).isEnabled()){
					Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
					setCursor(cursor);
					if ((JButton) e.getSource() == lvlButtons[0]) {
						MainFrame.getTl().changeButtonImage(lvlButtons[0], MainFrame.getTl().levelSelectLevelGlowTexture[0]);
					} else if ((JButton) e.getSource() == lvlButtons[1]) {
						MainFrame.getTl().changeButtonImage(lvlButtons[1], MainFrame.getTl().levelSelectLevelGlowTexture[1]);
					} else if ((JButton) e.getSource() == lvlButtons[2]) {
						MainFrame.getTl().changeButtonImage(lvlButtons[2], MainFrame.getTl().levelSelectLevelGlowTexture[2]);
					} else if ((JButton) e.getSource() == lvlButtons[3]) {
						MainFrame.getTl().changeButtonImage(lvlButtons[3], MainFrame.getTl().levelSelectLevelGlowTexture[3]);
					} else if ((JButton) e.getSource() == lvlButtons[4]) {
						MainFrame.getTl().changeButtonImage(lvlButtons[4], MainFrame.getTl().levelSelectLevelGlowTexture[4]);
					} else if ((JButton) e.getSource() == lvlButtons[5]) {
						MainFrame.getTl().changeButtonImage(lvlButtons[5], MainFrame.getTl().levelSelectLevelGlowTexture[5]);
					} else if ((JButton) e.getSource() == lvlButtons[6]) {
						MainFrame.getTl().changeButtonImage(lvlButtons[6], MainFrame.getTl().levelSelectLevelGlowTexture[6]);
					} else if ((JButton) e.getSource() == lvlButtons[7]) {
						MainFrame.getTl().changeButtonImage(lvlButtons[7], MainFrame.getTl().levelSelectLevelGlowTexture[7]);
					} else if ((JButton) e.getSource() == lvlButtons[8]) {
						MainFrame.getTl().changeButtonImage(lvlButtons[8], MainFrame.getTl().levelSelectLevelGlowTexture[8]);
					} else if ((JButton) e.getSource() == lvlButtons[9]) {
						MainFrame.getTl().changeButtonImage(lvlButtons[9], MainFrame.getTl().levelSelectLevelGlowTexture[9]);
					} else if ((JButton) e.getSource() == backButton) {
						MainFrame.getTl().changeButtonImage(backButton, MainFrame.getTl().levelSelectBackButtonGlowTexture);
					}
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if(((JButton) e.getSource()).isEnabled()){
					Cursor cursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
					setCursor(cursor);
					if ((JButton) e.getSource() == lvlButtons[0]) {
						MainFrame.getTl().changeButtonImage(lvlButtons[0], MainFrame.getTl().levelSelectLevelTexture[0]);
					} else if ((JButton) e.getSource() == lvlButtons[1]) {
						MainFrame.getTl().changeButtonImage(lvlButtons[1], MainFrame.getTl().levelSelectLevelTexture[1]);
					} else if ((JButton) e.getSource() == lvlButtons[2]) {
						MainFrame.getTl().changeButtonImage(lvlButtons[2], MainFrame.getTl().levelSelectLevelTexture[2]);
					} else if ((JButton) e.getSource() == lvlButtons[3]) {
						MainFrame.getTl().changeButtonImage(lvlButtons[3], MainFrame.getTl().levelSelectLevelTexture[3]);
					} else if ((JButton) e.getSource() == lvlButtons[4]) {
						MainFrame.getTl().changeButtonImage(lvlButtons[4], MainFrame.getTl().levelSelectLevelTexture[4]);
					} else if ((JButton) e.getSource() == lvlButtons[5]) {
						MainFrame.getTl().changeButtonImage(lvlButtons[5], MainFrame.getTl().levelSelectLevelTexture[5]);
					} else if ((JButton) e.getSource() == lvlButtons[6]) {
						MainFrame.getTl().changeButtonImage(lvlButtons[6], MainFrame.getTl().levelSelectLevelTexture[6]);
					} else if ((JButton) e.getSource() == lvlButtons[7]) {
						MainFrame.getTl().changeButtonImage(lvlButtons[7], MainFrame.getTl().levelSelectLevelTexture[7]);
					} else if ((JButton) e.getSource() == lvlButtons[8]) {
						MainFrame.getTl().changeButtonImage(lvlButtons[8], MainFrame.getTl().levelSelectLevelTexture[8]);
					} else if ((JButton) e.getSource() == lvlButtons[9]) {
						MainFrame.getTl().changeButtonImage(lvlButtons[9], MainFrame.getTl().levelSelectLevelTexture[9]);
					} else if ((JButton) e.getSource() == backButton) {
						MainFrame.getTl().changeButtonImage(backButton, MainFrame.getTl().levelSelectBackButtonTexture);
					}
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		}
		
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
		backButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout = (CardLayout) MainFrame.getMenus().getLayout();
				cardLayout.show(MainFrame.getMenus(), MainFrame.getMainmenupanel());
			}
			
		});
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
