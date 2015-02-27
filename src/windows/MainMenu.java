package windows;

import java.awt.Image;

import javax.sound.sampled.Clip;
import javax.swing.*;

/**
 * This class creates a new main menu so the game can be started. 
 * @author Keven-Matthew
 */
public class MainMenu extends JPanel{
	
	/**
	 * Holds the background image for the main menu.
	 */
	private final Image BACKGROUND_IMAGE;
	/**
	 * Determines if the music should be playing or not.
	 */
	private JCheckBox jCheckMusic;
	/**
	 * The song used in the main menu.
	 */
	private Clip menuSong;
	
	/**
	 * Creates a new main menu. 
	 */
	public MainMenu(){
		BACKGROUND_IMAGE = null;
	}
	
	/**
	 * Starts the music in the main menu. 
	 */
	public void startMenuMusic(){
		
	}
	
	
}
