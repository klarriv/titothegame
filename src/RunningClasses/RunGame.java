package RunningClasses;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import jSplashScreen.JSplash;

import windows.*;

/**
 * This is the main class that runs the actual game. 
 * @author Keven-Matthew
 *
 */
public class RunGame{
	
	private static double gUnit = 1280/5;
	private static MainFrame frame;
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		
		try{
			URL imgURL = new File("Resources/Menus/SplashScreen/SplashTito.gif").toURI().toURL();
			
			JSplash splash = new JSplash(imgURL, true, true, false, "V1.0", null, Color.BLACK, Color.BLACK);
			
			splash.splashOn();
			splash.setProgress(20, "Loading Files");
			frame = new MainFrame();
			Thread.sleep(3500);
			splash.setProgress(40, "Creating Levels");
			Thread.sleep(3000);
			splash.setProgress(100, "Done");
			splash.splashOff();
			frame.setVisible(true);
			frame.pack();
			frame.setLocationRelativeTo(null);
			RunGame.getFrame().getMainMenu().startMenuMusic();
			
		}catch(Exception e){
			
		}
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