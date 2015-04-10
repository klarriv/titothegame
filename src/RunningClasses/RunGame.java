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
	private static double gUnit;
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		
		frame.setVisible(true);
		frame.pack();
		frame.setLocationRelativeTo(null);
		gUnit = (frame.getWidth()/5);
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