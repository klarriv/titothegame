package windows;

import java.awt.*;
import javax.swing.*;

/**
 * This class creates a new main menu so the game can be started.
 * @author Keven-Matthew
 */
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
	 * This creates a new main JFrame
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

	public MainMenu getMainMenu() {
		return mainMenu;
	}
}
