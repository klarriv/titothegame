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
	
	private static double gUnit = 1280/5;
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

	public static double getgUnit() {
		return gUnit;
	}

	public static void setgUnit(double gUnit) {
		RunGame.gUnit = gUnit;
	}

}