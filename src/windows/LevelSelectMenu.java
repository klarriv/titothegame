package windows;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * This class creates a new level select menu so the player can navigate through the different levels 
 * @author Keven-Matthew
 */
public class LevelSelectMenu extends JPanel{
	/**
	 * Holds the background image for the level select menu.
	 */
	private Image backgroundImage = null;
	
	/**
	 * This holds the image for the back button.
	 */
	private BufferedImage backButtonIcon;
	
	/**
	 * This holds the ten buttons icons for the levels.
	 */
	private BufferedImage[] lvlIcons = new BufferedImage[10];
	
	/**
	 * This holds the ten images for when the mouse is over a level icon.
	 */
	private BufferedImage[] lvlGlowIcons = new BufferedImage[10];
	
	/**
	 * This creates a new level select menu
	 */
	public LevelSelectMenu(){
		setLayout(null);
		try {
			backgroundImage = ImageIO.read(new File("Resources/Menus/LevelSelectMenu/levelSelectionMenu.png"));
			
			for(int i=0; i<10; i++){
				lvlIcons[i] = ImageIO.read(new File("Resources/Menus/LevelSelectMenu/lvl" + i+1 + ".png"));
				lvlGlowIcons[i] = ImageIO.read(new File("Resources/Menus/LevelSelectMenu/lvl" + i+1 + "Glow.png"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.drawImage(backgroundImage, 0, 0, null);
	}
}
