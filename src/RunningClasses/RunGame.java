package RunningClasses;
import java.awt.Color;
import java.io.File;
import java.net.URL;
import jSplashScreen.JSplash;
import windows.*;

/**
 * This is the main class that runs the actual game. 
 * @author Keven-Matthew
 *
 */
public class RunGame{
	
	/**
	 * The initial value of a gUnit.
	 */
	private static double gUnit = 1280/5;
	/**
	 * The frame that holds the game.
	 */
	private static MainFrame frame;
	
	/**
	 * Main class that starts the game.
	 * @param args
	 */
	public static void main(String[] args){
		
		try{
			URL imgURL = new File("Resources/Menus/SplashScreen/SplashTito.gif").toURI().toURL();
			
			JSplash splash = new JSplash(imgURL, true, true, false, "V1.0", null, Color.BLACK, Color.BLACK);
			
			
			splash.splashOn();
			splash.setProgress(20, "Loading Files");
			frame = new MainFrame();
			splash.setProgress(30, "Loading Files");
			Thread.sleep(1000);
			splash.setProgress(40, "Creating Levels");
			Thread.sleep(1000);
			splash.setProgress(50, "Creating Levels");
			Thread.sleep(1000);
			splash.setProgress(60, "Finalising Levels");
			Thread.sleep(1000);
			splash.setProgress(70, "Finalising Levels");
			Thread.sleep(1000);
			splash.setProgress(80, "Waking up Tito");
			Thread.sleep(900);
			splash.setProgress(90, "Waking up Tito");
			Thread.sleep(500);
			splash.setProgress(100, "Done");
			splash.splashOff();
			//frame = new MainFrame(); // To remove when we put the splash screen back
			frame.setVisible(true);
			frame.pack();
			frame.setLocationRelativeTo(null);
			MainFrame.getMainMenu().startMenuMusic();
			
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