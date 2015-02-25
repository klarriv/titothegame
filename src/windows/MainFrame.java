package windows;

import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame{
	private LevelSelectMenu levelSelectMenu = new LevelSelectMenu();
	private MainMenu mainMenu = new MainMenu();
	private JPanel cardLayout;
	
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
