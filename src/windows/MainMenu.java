package windows;

import RunningClasses.RunGame;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.ImageIO;
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
	 * This holds the image for the specific buttons.
	 */
	private BufferedImage playButtonIcon, playButtonHighlightIcon, newGameButtonIcon, newGameHighlightButtonIcon, titleImage;

	/**
	 * Holds the background image for the main menu.
	 */
	private Image backgroundImage = null;

	/**
	 * Determines if the music should be playing or not.
	 */
	private JCheckBox jCheckMusic;

	/**
	 * The song used in the main menu.
	 */
	private Clip menuSong;

	/**
	 * These are the variables for the "play" and "new game" buttons.
	 */
	private JButton playButton, newGameButton, exitButton, fullScreenButton;
	
	private Timer t = new Timer(100, this);
	private int yTitle = 20;
	boolean direction = true;
	boolean isFullScreen = false;
	
	/**
	 * Creates a new main menu.
	 */
	public MainMenu() {
		
		setLayout(null);
		try {
			backgroundImage = ImageIO.read(new File("Resources/Menus/MainMenu/mainmenu.png"));
			playButtonIcon = ImageIO.read(new File("Resources/Menus/MainMenu/playButton.png"));
			playButtonHighlightIcon = ImageIO.read(new File("Resources/Menus/MainMenu/playHighlightButton.png"));
			newGameButtonIcon = ImageIO.read(new File("Resources/Menus/MainMenu/newGameButton.png"));
			newGameHighlightButtonIcon = ImageIO.read(new File("Resources/Menus/MainMenu/newGameHighlightButton.png"));
			titleImage = ImageIO.read(new File("Resources/Menus/MainMenu/title.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/**
		 * This is the mouse listener to make the buttons change when the mouse hovers over them.
		 * @author Keven-Matthew
		 *
		 */
		class ButtonListener implements MouseListener {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
				setCursor(cursor);
				if ((JButton) e.getSource() == playButton) {
					changeButtonImage(playButton, playButtonHighlightIcon);
				} else if ((JButton) e.getSource() == newGameButton) {
					changeButtonImage(newGameButton, newGameHighlightButtonIcon);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Cursor cursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
				setCursor(cursor);
				if ((JButton) e.getSource() == playButton) {
					changeButtonImage(playButton, playButtonIcon);
				} else if ((JButton) e.getSource() == newGameButton) {
					changeButtonImage(newGameButton, newGameButtonIcon);
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
		
		class ButtonResizeListener implements ComponentListener{
			
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
				changeButtonImage(playButton, playButtonIcon);
				changeButtonImage(newGameButton, newGameButtonIcon);
			}

			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub
			}
		}
		
		playButton = new JButton(new ImageIcon(playButtonIcon));
		playButton.setBorder(BorderFactory.createEmptyBorder());
		playButton.setContentAreaFilled(false);
		playButton.addMouseListener(new ButtonListener());
		playButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout = (CardLayout) MainFrame.getMenus().getLayout();
				cardLayout.show(MainFrame.getMenus(), MainFrame.getLevelselectpanel());
			}
		});
		playButton.addComponentListener(new ButtonResizeListener());

		newGameButton = new JButton(new ImageIcon(newGameButtonIcon));
		newGameButton.setBorder(BorderFactory.createEmptyBorder());
		newGameButton.setContentAreaFilled(false);
		newGameButton.addMouseListener(new ButtonListener());
		newGameButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout = (CardLayout) MainFrame.getMenus().getLayout();
				cardLayout.show(MainFrame.getMenus(), MainFrame.getLevelselectpanel());
			}

		});
		newGameButton.addComponentListener(new ButtonResizeListener());

		exitButton = new JButton();
		exitButton.setBorder(BorderFactory.createEmptyBorder());
		exitButton.setContentAreaFilled(false);
		exitButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);				
			}
		});
		
		
		
		fullScreenButton = new JButton();
		fullScreenButton.setBorder(BorderFactory.createEmptyBorder());
		fullScreenButton.setContentAreaFilled(false);
		fullScreenButton.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(!isFullScreen){
					RunGame.getFrame().dispose();
					RunGame.getFrame().setExtendedState(Frame.MAXIMIZED_BOTH);
					RunGame.getFrame().setVisible(true);
					isFullScreen = true;
				}
					
				else{
					RunGame.getFrame().dispose();
					RunGame.getFrame().setVisible(true);
					RunGame.getFrame().setSize(new Dimension(1280,720));
					RunGame.getFrame().setLocationRelativeTo(null);
					isFullScreen = false;
					
				}
			}
		});
		
		add(playButton);
		add(newGameButton);
		add(exitButton);
		add(fullScreenButton);
		startMenuMusic();
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
	
	/**
	 * This method changes the size of the image for a specific button. 
	 * @param jbutton
	 * @param img
	 */
	public void changeButtonImage(JButton jbutton, Image img){
        Dimension size = jbutton.getSize();
        Insets insets = jbutton.getInsets();
        size.width -= insets.left + insets.right;
        size.height -= insets.top + insets.bottom;
        if (size.width > size.height) {
            size.width = -1;
        } else {
            size.height = -1;
        }
        Image scaled = img.getScaledInstance(size.width, size.height, java.awt.Image.SCALE_SMOOTH);
        jbutton.setIcon(new ImageIcon(scaled));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
		g.drawImage(titleImage, 81*getWidth()/1280, yTitle*getHeight()/720, titleImage.getWidth()*getWidth()/1280, titleImage.getHeight()*getHeight()/720, null);
		playButton.setBounds(541*getWidth()/1280, 398*getHeight()/720, 232*getWidth()/1280, 69*getHeight()/720);
		newGameButton.setBounds(541*getWidth()/1280, 498*getHeight()/720, 232*getWidth()/1280, 69*getHeight()/720);
		exitButton.setBounds(1256*getWidth()/1280, 10*getHeight()/720, 14*getWidth()/1280, 14*getHeight()/720);
		fullScreenButton.setBounds(1235*getWidth()/1280, 10*getHeight()/720, 14*getWidth()/1280, 14*getHeight()/720);
		
		g.setColor(new Color(204, 213, 187));
		// draws the exit button
		g.drawLine(1270*getWidth()/1280, 10*getHeight()/720, 1256*getWidth()/1280, 24*getHeight()/720);
		g.drawLine(1256*getWidth()/1280, 10*getHeight()/720, 1270*getWidth()/1280, 24*getHeight()/720);
		
		//draws the fullscreen button
		g.fillRect(1235*getWidth()/1280, 10*getHeight()/720, 14*getWidth()/1280, 14*getHeight()/720);
		
		g.setColor(new Color(255, 0, 0, 128));
		
		//g.fillRect(1250, 10, 10, 20);
	
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
}
