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
		MainFrame.getTl().changeButtonImage(MainMenu.getPlayButton(), MainFrame.getTl().mainMenuPlayButtonTexture);
		MainFrame.getTl().changeButtonImage(MainMenu.getNewGameButton(), MainFrame.getTl().mainMenuNewGameButtonTexture);
		MainFrame.getTl().changeButtonImage(MainMenu.getMusicButton(), MainFrame.getTl().mainMenuMusicButtonTexture);
		
		// TODO change the size of the levelSelectMenu buttons
		//MainFrame.getTl().changeButtonImage(jbutton, img);
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
	}
}