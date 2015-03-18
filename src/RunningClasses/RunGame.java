package RunningClasses;
import java.awt.Dimension;

import javax.swing.JFrame;

import windows.*;

/**
 * This is the main class that runs the actual game. 
 * @author Keven-Matthew
 *
 */
public class RunGame{
	
	private static MainFrame frame = new MainFrame();
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		
		frame.setVisible(true);
		frame.pack();
		frame.setLocationRelativeTo(null);
	}
	
	public static MainFrame getFrame() {
		return frame;
	}

}