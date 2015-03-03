package windows;

import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
	 * Holds the background image for the level select menu.
	 */
	private Image backgroundImage = null;

	/**
	 * These hold the images for the back button.
	 */
	private BufferedImage backButtonIcon, backButtonGlowIcon;

	/**
	 * This holds the ten buttons icons for the levels.
	 */
	private BufferedImage[] lvlIcons = new BufferedImage[10];

	/**
	 * This holds the ten images for when the mouse is over a level icon.
	 */
	private BufferedImage[] lvlGlowIcons = new BufferedImage[10];
	
	/**
	 * These are the level buttons.
	 */
	private JButton[] lvlButtons = new JButton[10];

	/**
	 * This is the back button in the lower left corner.
	 */
	private JButton backButton;
	
	/**
	 * This creates a new level select menu
	 */
	public LevelSelectMenu(){
		setLayout(null);
		try {
			backgroundImage = ImageIO.read(new File("Resources/Menus/LevelSelectMenu/levelSelectionMenu.png"));
			
			for(int i=0; i<10; i++){
				lvlIcons[i] = ImageIO.read(new File("Resources/Menus/LevelSelectMenu/lvl" + (i+1) + ".png"));
				lvlGlowIcons[i] = ImageIO.read(new File("Resources/Menus/LevelSelectMenu/lvl" + (i+1) + "Glow.png"));
				backButtonIcon = ImageIO.read(new File("Resources/Menus/LevelSelectMenu/backButton.png"));
				backButtonGlowIcon = ImageIO.read(new File("Resources/Menus/LevelSelectMenu/backButtonGlow.png"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/**
		 * This is the mouse listener to make the buttons change when the mouse hovers over them.
		 * @author Keven-Matthew
		 *
		 */
		class ButtonListener implements MouseListener {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
				setCursor(cursor);
				if ((JButton) e.getSource() == lvlButtons[0]) {
					lvlButtons[0].setIcon(new ImageIcon(lvlGlowIcons[0]));
				} else if ((JButton) e.getSource() == lvlButtons[1]) {
					lvlButtons[1].setIcon(new ImageIcon(lvlGlowIcons[1]));
				} else if ((JButton) e.getSource() == lvlButtons[2]) {
					lvlButtons[2].setIcon(new ImageIcon(lvlGlowIcons[2]));
				} else if ((JButton) e.getSource() == lvlButtons[3]) {
					lvlButtons[3].setIcon(new ImageIcon(lvlGlowIcons[3]));
				} else if ((JButton) e.getSource() == lvlButtons[4]) {
					lvlButtons[4].setIcon(new ImageIcon(lvlGlowIcons[4]));
				} else if ((JButton) e.getSource() == lvlButtons[5]) {
					lvlButtons[5].setIcon(new ImageIcon(lvlGlowIcons[5]));
				} else if ((JButton) e.getSource() == lvlButtons[6]) {
					lvlButtons[6].setIcon(new ImageIcon(lvlGlowIcons[6]));
				} else if ((JButton) e.getSource() == lvlButtons[7]) {
					lvlButtons[7].setIcon(new ImageIcon(lvlGlowIcons[7]));
				} else if ((JButton) e.getSource() == lvlButtons[8]) {
					lvlButtons[8].setIcon(new ImageIcon(lvlGlowIcons[8]));
				} else if ((JButton) e.getSource() == lvlButtons[9]) {
					lvlButtons[9].setIcon(new ImageIcon(lvlGlowIcons[9]));
				} else if ((JButton) e.getSource() == backButton) {
					backButton.setIcon(new ImageIcon(backButtonGlowIcon));
				}
				
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Cursor cursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
				setCursor(cursor);
				if ((JButton) e.getSource() == lvlButtons[0]) {
					lvlButtons[0].setIcon(new ImageIcon(lvlIcons[0]));
				} else if ((JButton) e.getSource() == lvlButtons[1]) {
					lvlButtons[1].setIcon(new ImageIcon(lvlIcons[1]));
				} else if ((JButton) e.getSource() == lvlButtons[2]) {
					lvlButtons[2].setIcon(new ImageIcon(lvlIcons[2]));
				} else if ((JButton) e.getSource() == lvlButtons[3]) {
					lvlButtons[3].setIcon(new ImageIcon(lvlIcons[3]));
				} else if ((JButton) e.getSource() == lvlButtons[4]) {
					lvlButtons[4].setIcon(new ImageIcon(lvlIcons[4]));
				} else if ((JButton) e.getSource() == lvlButtons[5]) {
					lvlButtons[5].setIcon(new ImageIcon(lvlIcons[5]));
				} else if ((JButton) e.getSource() == lvlButtons[6]) {
					lvlButtons[6].setIcon(new ImageIcon(lvlIcons[6]));
				} else if ((JButton) e.getSource() == lvlButtons[7]) {
					lvlButtons[7].setIcon(new ImageIcon(lvlIcons[7]));
				} else if ((JButton) e.getSource() == lvlButtons[8]) {
					lvlButtons[8].setIcon(new ImageIcon(lvlIcons[8]));
				} else if ((JButton) e.getSource() == lvlButtons[9]) {
					lvlButtons[9].setIcon(new ImageIcon(lvlIcons[9]));
				} else if ((JButton) e.getSource() == backButton) {
					backButton.setIcon(new ImageIcon(backButtonIcon));
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
			lvlButtons[i] = new JButton(new ImageIcon(lvlIcons[i]));
			lvlButtons[i].setBorder(BorderFactory.createEmptyBorder());
			lvlButtons[i].setContentAreaFilled(false);
			lvlButtons[i].addMouseListener(new ButtonListener());
			add(lvlButtons[i]);
		}
		lvlButtons[0].setBounds(261, 73, 294, 86);
		lvlButtons[1].setBounds(261, 189, 294, 86);
		lvlButtons[2].setBounds(261, 305, 294, 86);
		lvlButtons[3].setBounds(261, 421, 294, 86);
		lvlButtons[4].setBounds(261, 537, 294, 86);
		
		lvlButtons[5].setBounds(729, 73, 294, 86);
		lvlButtons[6].setBounds(729, 189, 294, 86);
		lvlButtons[7].setBounds(729, 305, 294, 86);
		lvlButtons[8].setBounds(729, 421, 294, 86);
		lvlButtons[9].setBounds(729, 537, 294, 86);
		
		backButton = new JButton(new ImageIcon(backButtonIcon));
		backButton.setBorder(BorderFactory.createEmptyBorder());
		backButton.setContentAreaFilled(false);
		backButton.addMouseListener(new ButtonListener());
		backButton.setBounds(0, 617, 260, 105);
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

		g.drawImage(backgroundImage, 0, 0, null);
	}
}
