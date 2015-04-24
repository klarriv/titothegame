package windows;

import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class ButtonListener implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		JButton source = (JButton)e.getSource();
		CardLayout cardLayout = (CardLayout) MainFrame.getMenus().getLayout();
		if(((JButton) e.getComponent()).getParent().equals(MainFrame.getMainMenu())){
			if(source == MainMenu.getPlayButton()){
				cardLayout.show(MainFrame.getMenus(), MainFrame.getLevelselectpanel());
			}
			else if(source == MainMenu.getHelpButton()){
				cardLayout.show(MainFrame.getMenus(), MainFrame.getHelpmenupanel());
			}
		}
		else if(((JButton) e.getComponent()).getParent().equals(MainFrame.getLevelSelectMenu())){
			if((source).isEnabled()){
				if (source == LevelSelectMenu.getLvlButtons()[0]) {
					cardLayout.show(MainFrame.getMenus(), "LEVEL0");
				} else if (source == LevelSelectMenu.getLvlButtons()[1]) {
					cardLayout.show(MainFrame.getMenus(), "LEVEL1");
				} else if (source == LevelSelectMenu.getLvlButtons()[2]) {
					cardLayout.show(MainFrame.getMenus(), "LEVEL2");
				} else if (source == LevelSelectMenu.getLvlButtons()[3]) {
					cardLayout.show(MainFrame.getMenus(), "LEVEL3");
				} else if (source == LevelSelectMenu.getLvlButtons()[4]) {
					cardLayout.show(MainFrame.getMenus(), "LEVEL4");
				} else if (source == LevelSelectMenu.getLvlButtons()[5]) {
					cardLayout.show(MainFrame.getMenus(), "LEVEL5");
				} else if (source == LevelSelectMenu.getLvlButtons()[6]) {
					cardLayout.show(MainFrame.getMenus(), "LEVEL6");
				} else if (source == LevelSelectMenu.getLvlButtons()[7]) {
					cardLayout.show(MainFrame.getMenus(), "LEVEL7");
				} else if (source == LevelSelectMenu.getLvlButtons()[8]) {
					cardLayout.show(MainFrame.getMenus(), "LEVEL8");
				} else if (source == LevelSelectMenu.getLvlButtons()[9]) {
					cardLayout.show(MainFrame.getMenus(), "LEVEL9");
				} else if (source == LevelSelectMenu.getBackButton()){
					cardLayout.show(MainFrame.getMenus(), MainFrame.getMainmenupanel());
				}
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
		JButton source = (JButton)e.getSource();
		if(((JButton) e.getComponent()).getParent().equals(MainFrame.getMainMenu())){
			MainFrame.getMainMenu().setCursor(cursor);
			if (source == MainMenu.getPlayButton()) {
				MainFrame.getTl().changeButtonImage(MainMenu.getPlayButton(), MainFrame.getTl().mainMenuPlayButtonHighlightTexture);
			} else if (source == MainMenu.getNewGameButton()) {
				MainFrame.getTl().changeButtonImage(MainMenu.getNewGameButton(), MainFrame.getTl().mainMenuNewGameButtonHighlightTexture);
			}
		}
		else if(((JButton) e.getComponent()).getParent().equals(MainFrame.getLevelSelectMenu())){
			if((source).isEnabled()){
				MainFrame.getLevelSelectMenu().setCursor(cursor);
				if (source == LevelSelectMenu.getLvlButtons()[0]) {
					MainFrame.getTl().changeButtonImage(LevelSelectMenu.getLvlButtons()[0], MainFrame.getTl().levelSelectLevelGlowTexture[0]);
				} else if (source == LevelSelectMenu.getLvlButtons()[1]) {
					MainFrame.getTl().changeButtonImage(LevelSelectMenu.getLvlButtons()[1], MainFrame.getTl().levelSelectLevelGlowTexture[1]);
				} else if (source == LevelSelectMenu.getLvlButtons()[2]) {
					MainFrame.getTl().changeButtonImage(LevelSelectMenu.getLvlButtons()[2], MainFrame.getTl().levelSelectLevelGlowTexture[2]);
				} else if (source == LevelSelectMenu.getLvlButtons()[3]) {
					MainFrame.getTl().changeButtonImage(LevelSelectMenu.getLvlButtons()[3], MainFrame.getTl().levelSelectLevelGlowTexture[3]);
				} else if (source == LevelSelectMenu.getLvlButtons()[4]) {
					MainFrame.getTl().changeButtonImage(LevelSelectMenu.getLvlButtons()[4], MainFrame.getTl().levelSelectLevelGlowTexture[4]);
				} else if (source == LevelSelectMenu.getLvlButtons()[5]) {
					MainFrame.getTl().changeButtonImage(LevelSelectMenu.getLvlButtons()[5], MainFrame.getTl().levelSelectLevelGlowTexture[5]);
				} else if (source == LevelSelectMenu.getLvlButtons()[6]) {
					MainFrame.getTl().changeButtonImage(LevelSelectMenu.getLvlButtons()[6], MainFrame.getTl().levelSelectLevelGlowTexture[6]);
				} else if (source == LevelSelectMenu.getLvlButtons()[7]) {
					MainFrame.getTl().changeButtonImage(LevelSelectMenu.getLvlButtons()[7], MainFrame.getTl().levelSelectLevelGlowTexture[7]);
				} else if (source == LevelSelectMenu.getLvlButtons()[8]) {
					MainFrame.getTl().changeButtonImage(LevelSelectMenu.getLvlButtons()[8], MainFrame.getTl().levelSelectLevelGlowTexture[8]);
				} else if (source == LevelSelectMenu.getLvlButtons()[9]) {
					MainFrame.getTl().changeButtonImage(LevelSelectMenu.getLvlButtons()[9], MainFrame.getTl().levelSelectLevelGlowTexture[9]);
				} else if (source == LevelSelectMenu.getBackButton()) {
					MainFrame.getTl().changeButtonImage(LevelSelectMenu.getBackButton(), MainFrame.getTl().levelSelectBackButtonGlowTexture);
				}
			}
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Cursor cursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
		JButton source = (JButton)e.getSource();
		if(((JButton) e.getComponent()).getParent().equals(MainFrame.getMainMenu())){
			MainFrame.getMainMenu().setCursor(cursor);
			if (source == MainMenu.getPlayButton()) {
				MainFrame.getTl().changeButtonImage(MainMenu.getPlayButton(), MainFrame.getTl().mainMenuPlayButtonTexture);
			} else if (source == MainMenu.getNewGameButton()) {
				MainFrame.getTl().changeButtonImage(MainMenu.getNewGameButton(), MainFrame.getTl().mainMenuNewGameButtonTexture);
			}
		}
		else if(((JButton) e.getComponent()).getParent().equals(MainFrame.getLevelSelectMenu())){
			if((source).isEnabled()){
				MainFrame.getLevelSelectMenu().setCursor(cursor);
				if (source == LevelSelectMenu.getLvlButtons()[0]) {
					MainFrame.getTl().changeButtonImage(LevelSelectMenu.getLvlButtons()[0], MainFrame.getTl().levelSelectLevelTexture[0]);
				} else if (source == LevelSelectMenu.getLvlButtons()[1]) {
					MainFrame.getTl().changeButtonImage(LevelSelectMenu.getLvlButtons()[1], MainFrame.getTl().levelSelectLevelTexture[1]);
				} else if (source == LevelSelectMenu.getLvlButtons()[2]) {
					MainFrame.getTl().changeButtonImage(LevelSelectMenu.getLvlButtons()[2], MainFrame.getTl().levelSelectLevelTexture[2]);
				} else if (source == LevelSelectMenu.getLvlButtons()[3]) {
					MainFrame.getTl().changeButtonImage(LevelSelectMenu.getLvlButtons()[3], MainFrame.getTl().levelSelectLevelTexture[3]);
				} else if (source == LevelSelectMenu.getLvlButtons()[4]) {
					MainFrame.getTl().changeButtonImage(LevelSelectMenu.getLvlButtons()[4], MainFrame.getTl().levelSelectLevelTexture[4]);
				} else if (source == LevelSelectMenu.getLvlButtons()[5]) {
					MainFrame.getTl().changeButtonImage(LevelSelectMenu.getLvlButtons()[5], MainFrame.getTl().levelSelectLevelTexture[5]);
				} else if (source == LevelSelectMenu.getLvlButtons()[6]) {
					MainFrame.getTl().changeButtonImage(LevelSelectMenu.getLvlButtons()[6], MainFrame.getTl().levelSelectLevelTexture[6]);
				} else if (source == LevelSelectMenu.getLvlButtons()[7]) {
					MainFrame.getTl().changeButtonImage(LevelSelectMenu.getLvlButtons()[7], MainFrame.getTl().levelSelectLevelTexture[7]);
				} else if (source == LevelSelectMenu.getLvlButtons()[8]) {
					MainFrame.getTl().changeButtonImage(LevelSelectMenu.getLvlButtons()[8], MainFrame.getTl().levelSelectLevelTexture[8]);
				} else if (source == LevelSelectMenu.getLvlButtons()[9]) {
					MainFrame.getTl().changeButtonImage(LevelSelectMenu.getLvlButtons()[9], MainFrame.getTl().levelSelectLevelTexture[9]);
				} else if (source == LevelSelectMenu.getBackButton()) {
					MainFrame.getTl().changeButtonImage(LevelSelectMenu.getBackButton(), MainFrame.getTl().levelSelectBackButtonTexture);
				}
			}
		}
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
