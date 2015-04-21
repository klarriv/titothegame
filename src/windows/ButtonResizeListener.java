package windows;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import RunningClasses.RunGame;

public class ButtonResizeListener implements ComponentListener{
	
	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent e) {
		//MAIN MENU BUTTONS
		MainFrame.getTl().changeButtonImage(MainMenu.getPlayButton(), MainFrame.getTl().mainMenuPlayButtonTexture);
		MainFrame.getTl().changeButtonImage(MainMenu.getNewGameButton(), MainFrame.getTl().mainMenuNewGameButtonTexture);
		MainFrame.getTl().changeButtonImage(MainMenu.getMusicButton(), MainFrame.getTl().mainMenuMusicButtonTexture);
		
		// LEVEL SELECT BUTTONS
		for(int i=0; i<LevelSelectMenu.getLvlButtons().length; i++){
			if(LevelSelectMenu.getLvlButtons()[i].getSize().width != 0 && LevelSelectMenu.getLvlButtons()[i].getSize().height != 0)
				MainFrame.getTl().changeButtonImage(LevelSelectMenu.getLvlButtons()[i], MainFrame.getTl().levelSelectLevelTexture[i]);
		}
		MainFrame.getTl().changeButtonImage(LevelSelectMenu.getBackButton(), MainFrame.getTl().levelSelectBackButtonTexture);
		
		// LEVEL BUTTONS
		//MainFrame.getTl().changeButtonImage(Level.getJbtPlay(), MainFrame.getTl().levelPlayTexture);
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
	}
}