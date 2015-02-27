package windows;

import java.awt.event.*;
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
	 * This creates a new instance of level
	 */
	public Level() {
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
