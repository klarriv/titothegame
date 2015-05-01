package windows;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * This class creates a new main menu so the game can be started.
 * @author Keven-Matthew
 */
public class MainFrame extends JFrame{
	/**
	 * Creates an instance of TextureLoader so the images can be loaded into the game.
	 */
	private static TextureLoader tl = new TextureLoader();
	/**
	 * This is the identifier string for the level select JPanel.
	 */
	private static final String LEVELSELECTPANEL = "LevelSelectPanel";
	/**
	 * This is the identifier string for the main menu JPanel.
	 */
	private static final String MAINMENUPANEL = "MainMenuPanel";
	/**
	 * This is the identifier string for credits JPanel
	 */
	private static final String CREDITSPANEL = "CreditsPanel";
	/**
	 * This is the identifier string the help JPanel
	 */
	private static final String HELPMENUPANEL = "HelpPanel";
	/**
	 * This is the array that contains the 10 level panels
	 */
	private static Level[] levels = new Level[10];
	/**
	 * The level select menu that will be used by the user.
	 */
	private static LevelSelectMenu levelSelectMenu;
	/**
	 * The main menu that will be used by the user.
	 */
	private static MainMenu mainMenu = new MainMenu();
	/**
	 * This is the credits window
	 */
	private static Credits credits = new Credits();
	/**
	 * A JPanel with a cardLayout used to hold all other JPanels.
	 */
	private static JPanel menus;
	/**
	 * This is the help menu panel.
	 */
	private static HelpMenu helpMenu = new HelpMenu();
	/**
	 * The image variable that will be used to set the icon of the JFrame.
	 */
	private Image frameIcon = null;
	/**
	 * Holds the number of the level reached contained in the .sav file
	 */
	private static int levelReached;
	/**
	 * 
	 */
	private static int[] numberOfStartsForLevel = new int[10];
	
	/**
	 * This creates a new main JFrame
	 */
	public MainFrame(){
		setLayout(new BorderLayout());
		getContentPane().setPreferredSize(new Dimension(1280, 720));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Game: Tito Escapes the Zoo");
		setUndecorated(true);
		
		try {
			frameIcon = ImageIO.read(new File("Resources/frameIcon.png"));
			File gameSave =  new File("Resources/gameSave.sav");
			if(!gameSave.exists()){
				levelReached = 0;
				for(int i=0; i<numberOfStartsForLevel.length;i++)
					numberOfStartsForLevel[i] = 0;
			}
			else{
				Scanner reader = new Scanner(gameSave);
				levelReached = reader.nextInt();
				for(int i=0; i<numberOfStartsForLevel.length;i++)
					numberOfStartsForLevel[i] = reader.nextInt();
				reader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setIconImage(frameIcon);
		
		menus = new JPanel(new CardLayout());
		
		for (int i=0; i<levels.length; i++){
			levels[i] = new Level(i);
			menus.add(levels[i], ("LEVEL"+i));
			if(i<MainFrame.getLevelReached()){
				MainFrame.getLevels()[i].setHasBeenCompleted(true);
				MainFrame.getLevels()[i].setNumberOfStars(numberOfStartsForLevel[i]);
			}
			levelSelectMenu = new LevelSelectMenu();
			menus.add(levelSelectMenu, LEVELSELECTPANEL);
			menus.add(mainMenu, MAINMENUPANEL);
		}
		menus.add(credits, CREDITSPANEL);
		menus.add(helpMenu, HELPMENUPANEL);
		
		getContentPane().add(menus, BorderLayout.CENTER);
		
		CardLayout cardLayout = (CardLayout) menus.getLayout();
		cardLayout.show(menus, MAINMENUPANEL);
	}

	public static TextureLoader getTl() {
		return tl;
	}

	public static void setTl(TextureLoader tl) {
		MainFrame.tl = tl;
	}

	public static JPanel getMenus() {
		return menus;
	}

	public void setMenus(JPanel menus) {
		MainFrame.menus = menus;
	}

	public static String getLevelselectpanel() {
		return LEVELSELECTPANEL;
	}

	public static String getMainmenupanel() {
		return MAINMENUPANEL;
	}
	
	public static String getHelpmenupanel(){
		return HELPMENUPANEL;
	}

	public void setLevelSelectMenu(LevelSelectMenu levelSelectMenu) {
		MainFrame.levelSelectMenu = levelSelectMenu;
	}

	public void setMainMenu(MainMenu mainMenu) {
		MainFrame.mainMenu = mainMenu;
	}

	public static LevelSelectMenu getLevelSelectMenu() {
		return levelSelectMenu;
	}

	public static MainMenu getMainMenu() {
		return mainMenu;
	}
	
	public int getHeight(){
		return this.getContentPane().getHeight();
	}
	
	public int getWidth(){
		return this.getContentPane().getWidth();
	}
	
	public static Level[] getLevels() {
		return levels;
	}

	public static void setLevels(Level[] levels) {
		MainFrame.levels = levels;
	}

	public static int getLevelReached() {
		return levelReached;
	}

	public static void setLevelReached(int levelReached) {
		MainFrame.levelReached = levelReached;
	}

	public static HelpMenu getHelpMenu() {
		return helpMenu;
	}

	public static void setHelpMenu(HelpMenu helpMenu) {
		MainFrame.helpMenu = helpMenu;
	}

	public static int[] getNumberOfStartsForLevel() {
		return numberOfStartsForLevel;
	}

	public static void setNumberOfStartsForLevel(
			int[] numberOfStartsForLevel) {
		MainFrame.numberOfStartsForLevel = numberOfStartsForLevel;
	}
	
	

}
