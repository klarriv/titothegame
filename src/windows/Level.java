package windows;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.Timer;

/**
 * This class loads the level file and creates the GUI with the objects that are
 * passed with the .lvl file.
 * @author Keven-Matthew
 */
public class Level extends JPanel implements ActionListener {
	
	/**
	 * This is a variable that holds the level number
	 */
	public int levelNumber;
	/**
	 * The timer used to repaint and used for the delay in the physics formulas
	 */
	public Timer t;
	/**
	 * List of objects read from the level file. 
	 */
	private ArrayList objectList;
	/**
	 * The song used in the levels
	 */
	private Clip levelSong;
	/**
	 * Contains the location of the level files
	 */
	private final String LEVELDIRECTORY = "Resources/Levels/";
	
	/**
	 * This creates a new instance of level
	 */
	public Level(int levelNumber) {
		this.levelNumber = levelNumber;
		
		try {
			FileInputStream reader = new FileInputStream(LEVELDIRECTORY + "/level" + levelNumber + ".lvl");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This starts the timer
	 */
	public void start() {
		t.start();
	}

	/**
	 * This stops the timer
	 */
	public void stop() {
		t.stop();
	}

	/**
	 * This starts the music in the levels
	 */
	public void startLevelMusic() {
		levelSong.start();
	}

	/**
	 * This repaints the level and calls the calculations
	 * 
	 * @param e
	 *            ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
