package windows;

import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame{
	
	/**
	 * The level select menu that will be used by the user.
	 */
	private LevelSelectMenu levelSelectMenu = new LevelSelectMenu();
	/**
	 * The main menu that will be used by the user.
	 */
	private MainMenu mainMenu = new MainMenu();
	/**
	 * A JPanel with a cardLayout used to hold all other JPanels.
	 */
	private JPanel cardLayout;
	
	/**
	 * This creates a new 
	 */
	public MainFrame(){
		setSize(new Dimension(1280, 720));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Game: Tito Escapes the Zoo");
		setVisible(true);
		
		cardLayout.setLayout(new CardLayout());
	}

	public LevelSelectMenu getLevelSelectMenu() {
		return levelSelectMenu;
	}

	public void setLevelSelectMenu(LevelSelectMenu levelSelectMenu) {
		this.levelSelectMenu = levelSelectMenu;
	}

	public MainMenu getMainMenu() {
		return mainMenu;
	}

	public void setMainMenu(MainMenu mainMenu) {
		this.mainMenu = mainMenu;
	}
}
