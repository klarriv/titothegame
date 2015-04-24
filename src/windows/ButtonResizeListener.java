package windows;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

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
		MainFrame.getTl().changeButtonImage(MainMenu.getHelpButton(), MainFrame.getTl().mainMenuHelpMenuButtonTexture);
		
		// LEVEL SELECT BUTTONS
		for(int i=0; i<LevelSelectMenu.getLvlButtons().length; i++){
			if(LevelSelectMenu.getLvlButtons()[i].getSize().width != 0 && LevelSelectMenu.getLvlButtons()[i].getSize().height != 0)
				MainFrame.getTl().changeButtonImage(LevelSelectMenu.getLvlButtons()[i], MainFrame.getTl().levelSelectLevelTexture[i]);
		}
		MainFrame.getTl().changeButtonImage(LevelSelectMenu.getBackButton(), MainFrame.getTl().levelSelectBackButtonTexture);
		
		// LEVEL BUTTONS
		for(int i=0; i<MainFrame.getLevels().length; i++){
			MainFrame.getTl().changeButtonImage(MainFrame.getLevels()[i].getJbtPlay(), MainFrame.getTl().levelPlayTexture);
			MainFrame.getTl().changeButtonImage(MainFrame.getLevels()[i].getJbtRestart(), MainFrame.getTl().levelRestartTexture);
			MainFrame.getTl().changeButtonImage(MainFrame.getLevels()[i].getJbtPause(), MainFrame.getTl().levelPauseTexture);
			MainFrame.getTl().changeButtonImage(MainFrame.getLevels()[i].getJbtBackToGame(), MainFrame.getTl().pauseMenuBackToGameTexture);
			MainFrame.getTl().changeButtonImage(MainFrame.getLevels()[i].getJbtBackToLevelSelect(), MainFrame.getTl().pauseMenuLevelSelectionTexture);
			MainFrame.getTl().changeButtonImage(MainFrame.getLevels()[i].getJbtExitGame(), MainFrame.getTl().pauseMenuExitGameTexture);
		}

	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
	}
}