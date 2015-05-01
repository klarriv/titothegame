package windows;

import RunningClasses.RunGame;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

/**
 * This class creates a new main menu so the game can be started.
 * 
 * @author Keven-Matthew
 */
public class MainMenu extends JPanel implements ActionListener{

	/**
	 * The song used in the main menu.
	 */
	private Clip menuSong;

	/**
	 * These are the variables for the "play" and "new game" buttons.
	 */
	private static JButton playButton, newGameButton, exitButton, fullScreenButton, musicButton, helpButton;

	private Timer t = new Timer(100, this);
	private int yTitle = 20;
	boolean direction = true;
	boolean isFullScreen = false;
	
	/**
	 * Creates a new main menu.
	 */
	public MainMenu() {
		
		setLayout(null);

		playButton = new JButton(new ImageIcon(MainFrame.getTl().mainMenuPlayButtonTexture));
		playButton.setBorder(BorderFactory.createEmptyBorder());
		playButton.setContentAreaFilled(false);
		playButton.addMouseListener(new ButtonListener());
		playButton.addComponentListener(new ButtonResizeListener());

		newGameButton = new JButton(new ImageIcon(MainFrame.getTl().mainMenuNewGameButtonTexture));
		newGameButton.setBorder(BorderFactory.createEmptyBorder());
		newGameButton.setContentAreaFilled(false);
		newGameButton.addMouseListener(new ButtonListener());
		newGameButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UIManager.put("OptionPane.background", Color.BLACK);
				UIManager.put("OptionPane.messagebackground", Color.BLACK);
				UIManager.put("Panel.background", Color.BLACK);
				int reply = JOptionPane.showConfirmDialog(null, new JOptionPanePanel(), "WARNING!", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
				if(reply == JOptionPane.YES_OPTION){
					writeToSaveFile(0);
					MainFrame.setLevelReached(0);
					for(int i=1; i<MainFrame.getLevels().length; i++){
						MainFrame.getLevels()[i].setHasBeenCompleted(false);
					}
					for(int i=1; i<LevelSelectMenu.getLvlButtons().length; i++){
						LevelSelectMenu.getLvlButtons()[i].setEnabled(false);
					}
					CardLayout cardLayout = (CardLayout) MainFrame.getMenus().getLayout();
					cardLayout.show(MainFrame.getMenus(), MainFrame.getLevelselectpanel());
				}else if(reply == JOptionPane.NO_OPTION){
					
				}	
			}
			
		});
		newGameButton.addComponentListener(new ButtonResizeListener());

		exitButton = new JButton();
		exitButton.setBorder(BorderFactory.createEmptyBorder());
		exitButton.setContentAreaFilled(false);
		exitButton.addMouseListener(new ButtonListener());
		exitButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					PrintWriter writer = new PrintWriter(new File("Resources/gameSave.sav"));
					for (int i = MainFrame.getLevels().length - 1; i >= 0; i--) {
						if (MainFrame.getLevels()[i].hasBeenCompleted()) {
							writer.println((i+1));
							break;
						}
					}
					for(int i = 0; i < MainFrame.getLevels().length; i++){
						writer.println(MainFrame.getLevels()[i].getNumberOfStars());
					}
					writer.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				System.exit(0);		
			}
		});
		
		
		
		fullScreenButton = new JButton();
		fullScreenButton.setBorder(BorderFactory.createEmptyBorder());
		fullScreenButton.setContentAreaFilled(false);
		fullScreenButton.addMouseListener(new ButtonListener());
		fullScreenButton.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(!isFullScreen){
					RunGame.getFrame().dispose();
					RunGame.getFrame().setExtendedState(Frame.MAXIMIZED_BOTH);
					RunGame.getFrame().setVisible(true);
					isFullScreen = true;
					RunGame.setgUnit(RunGame.getFrame().getWidth()/5);
				}
					
				else{
					RunGame.getFrame().dispose();
					RunGame.getFrame().setVisible(true);
					RunGame.getFrame().setSize(new Dimension(1280,720));
					RunGame.getFrame().setLocationRelativeTo(null);
					isFullScreen = false;
					RunGame.setgUnit(RunGame.getFrame().getWidth()/5);
				}
			}
		});
		
		helpButton = new JButton(new ImageIcon(MainFrame.getTl().mainMenuHelpMenuButtonTexture));
		helpButton.setBorder(BorderFactory.createEmptyBorder());
		helpButton.setContentAreaFilled(false);
		helpButton.addMouseListener(new ButtonListener());
		helpButton.addComponentListener(new ButtonResizeListener());
		
		musicButton = new JButton(new ImageIcon(MainFrame.getTl().mainMenuMusicButtonTexture));
		musicButton.setBorder(BorderFactory.createEmptyBorder());
		musicButton.setContentAreaFilled(false);
		musicButton.addMouseListener(new ButtonListener());
		musicButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(menuSong.isActive()){
					MainFrame.getTl().changeButtonImage(musicButton, MainFrame.getTl().mainMenuMusicButtonTexture2);
					menuSong.close();
				}
				else{
					MainFrame.getTl().changeButtonImage(musicButton, MainFrame.getTl().mainMenuMusicButtonTexture);
					startMenuMusic();
				}
			}
		});
		musicButton.addComponentListener(new ButtonResizeListener());
		
		add(playButton);
		add(newGameButton);
		add(exitButton);
		add(fullScreenButton);
		add(musicButton);
		add(helpButton);
		t.start();
	}

	/**
	 * Starts the music in the main menu.
	 */
	public void startMenuMusic() {
		try {
			menuSong = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("Resources/Music/callToAdventure.wav").getAbsoluteFile());
			menuSong.open(ais);
			menuSong.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void writeToSaveFile(int i){
		PrintWriter writer;
		try {
			writer = new PrintWriter(new File("Resources/gameSave.sav"));
			writer.print(i);
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(MainFrame.getTl().mainMenuBackgroundTexture, 0, 0, getWidth(), getHeight(), null);
		g.drawImage(MainFrame.getTl().mainMenuTitleTexture, 81*getWidth()/1280, yTitle*getHeight()/720, MainFrame.getTl().mainMenuTitleTexture.getWidth()*getWidth()/1280, MainFrame.getTl().mainMenuTitleTexture.getHeight()*getHeight()/720, null);
		playButton.setBounds(541*getWidth()/1280, 398*getHeight()/720, 232*getWidth()/1280, 69*getHeight()/720);
		newGameButton.setBounds(541*getWidth()/1280, 498*getHeight()/720, 232*getWidth()/1280, 69*getHeight()/720);
		exitButton.setBounds(1256*getWidth()/1280, 10*getHeight()/720, 14*getWidth()/1280, 14*getHeight()/720);
		fullScreenButton.setBounds(1235*getWidth()/1280, 10*getHeight()/720, 14*getWidth()/1280, 14*getHeight()/720);
		musicButton.setBounds(40*getWidth()/1280, 10*getHeight()/720, 25*getWidth()/1280, 25*getHeight()/720);
		helpButton.setBounds(10*getWidth()/1280, 10*getHeight()/720, 25*getWidth()/1280, 25*getHeight()/720);
		
		g.setColor(new Color(204, 213, 187));
		// draws the exit button
		g.drawLine(1270*getWidth()/1280, 10*getHeight()/720, 1256*getWidth()/1280, 24*getHeight()/720);
		g.drawLine(1256*getWidth()/1280, 10*getHeight()/720, 1270*getWidth()/1280, 24*getHeight()/720);
		
		//draws the fullscreen button
		g.fillRect(1235*getWidth()/1280, 10*getHeight()/720, 14*getWidth()/1280, 14*getHeight()/720);
		
		g.setColor(new Color(255, 0, 0, 128));
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {	
		
		if(direction){
			yTitle++;
		}
		else {
			yTitle--;
		}
		if(yTitle == 20 || yTitle == 30){
			direction =!direction;
		}
		
		repaint();
	}
	
	public static JButton getPlayButton() {
		return playButton;
	}

	public static JButton getNewGameButton() {
		return newGameButton;
	}
	
	public static JButton getMusicButton(){
		return musicButton;
	}
	
	public static JButton getHelpButton(){
		return helpButton;
	}
}
