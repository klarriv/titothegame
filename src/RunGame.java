import java.awt.Dimension;

import javax.swing.JFrame;

import windows.LevelSelectMenu;
import windows.MainMenu;
import windows.OptionMenu;


/*
 * 
 */

public class RunGame extends JFrame{
	LevelSelectMenu lvlSelectMenu = new LevelSelectMenu();
	MainMenu mainMenu = new MainMenu();
	OptionMenu optionMenu = new OptionMenu();
	
	RunGame(){
		JFrame MainFrame = new JFrame();
		MainFrame.add(mainMenu);
		
	}
	
	public static void main(String[] args){
		RunGame game = new RunGame();
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setResizable(false);
		game.setSize(new Dimension (1280, 720));
		game.setVisible(true);
		
	}
}