package windows;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * This class creates a new main menu so the game can be started.
 * @author Keven-Matthew
 */
public class MainFrame extends JFrame{
	/**
	 * This is the identifier string for the level select JPanel.
	 */
	private static final String LEVELSELECTPANEL = "LevelSelectPanel";
	/**
	 * This is the identifier string for the main menu JPanel.
	 */
	private static final String MAINMENUPANEL = "MainMenuPanel";
	/**
	 * This is the identifier string for the option JPanel
	 */
	private static final String OPTIONPANEL = "OptionPanel";
	/**
	 * The level select menu that will be used by the user.
	 */
	private static LevelSelectMenu levelSelectMenu = new LevelSelectMenu();
	/**
	 * The main menu that will be used by the user.
	 */
	private static MainMenu mainMenu = new MainMenu();
	/**
	 * The option menu that will be used by the user.
	 */
	private static OptionMenu optionMenu = new OptionMenu();
	/**
	 * A JPanel with a cardLayout used to hold all other JPanels.
	 */
	private static JPanel menus;
	/**
	 * The image variable that will be used to set the icon of the JFrame.
	 */
	private Image frameIcon = null;
	
	/**
	 * This creates a new main JFrame
	 */
	public MainFrame(){
		setLayout(new BorderLayout());
		getContentPane().setPreferredSize(new Dimension(1280, 720));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Game: Tito Escapes the Zoo");
		setVisible(true);
		
		try {
			frameIcon = ImageIO.read(new File("Resources/frameIcon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setIconImage(frameIcon);
		pack();
		
		menus = new JPanel(new CardLayout());
		menus.add(levelSelectMenu, LEVELSELECTPANEL);
		menus.add(mainMenu, MAINMENUPANEL);
		menus.add(optionMenu, OPTIONPANEL);
		
		add(menus, BorderLayout.CENTER);
		
		CardLayout cardLayout = (CardLayout) menus.getLayout();
		cardLayout.show(menus, MAINMENUPANEL);
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

	public void setLevelSelectMenu(LevelSelectMenu levelSelectMenu) {
		MainFrame.levelSelectMenu = levelSelectMenu;
	}

	public void setMainMenu(MainMenu mainMenu) {
		MainFrame.mainMenu = mainMenu;
	}

	public LevelSelectMenu getLevelSelectMenu() {
		return levelSelectMenu;
	}

	public static MainMenu getMainMenu() {
		return mainMenu;
	}
	
	public void setOptionMenu(OptionMenu optionMenu) {
		MainFrame.optionMenu = optionMenu;
	}

	public OptionMenu getOptionMenu() {
		return optionMenu;
	}

}
