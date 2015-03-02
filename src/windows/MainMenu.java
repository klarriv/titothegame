package windows;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import javax.swing.*;

/**
 * This class creates a new main menu so the game can be started. 
 * @author Keven-Matthew
 */
public class MainMenu extends JPanel{
	
	/**
	 * This holds the image for the play button.
	 */
	private BufferedImage playButtonIcon;
	
	/**
	 * This holds the image for the new game button.
	 */
	private BufferedImage newGameButtonIcon;
	
	/**
	 * Holds the background image for the main menu.
	 */
	private Image BACKGROUND_IMAGE = null;
	
	/**
	 * Determines if the music should be playing or not.
	 */
	private JCheckBox jCheckMusic;
	
	/**
	 * The song used in the main menu.
	 */
	private Clip menuSong;
	
	
	
	/**
	 * Creates a new main menu. 
	 */
	public MainMenu(){
		setLayout(null);
		try {
			BACKGROUND_IMAGE = ImageIO.read(new File("Resources/Background_Images/MainMenu/mainmenu.png"));
			playButtonIcon = ImageIO.read(new File("Resources/BackGround_Images/MainMenu/playButton.png"));
			newGameButtonIcon = ImageIO.read(new File("Resources/BackGround_Images/MainMenu/newGameButton.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JButton playButton = new JButton(new ImageIcon(playButtonIcon));
		playButton.setBorder(BorderFactory.createEmptyBorder());
		playButton.setContentAreaFilled(false);
		playButton.setBounds(541, 398, 232, 69);
		playButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("play pressed");
			}
			
		});
		
		JButton newGameButton = new JButton(new ImageIcon(newGameButtonIcon));
		newGameButton.setBorder(BorderFactory.createEmptyBorder());
		newGameButton.setContentAreaFilled(false);
		newGameButton.setBounds(541, 498, 232, 69);
		newGameButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("new game pressed");				
			}
			
		});
		
		add(playButton);
		add(newGameButton);
	}
	
	/**
	 * Starts the music in the main menu. 
	 */
	public void startMenuMusic(){
		
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);

		g.drawImage(BACKGROUND_IMAGE, 0, 0, null);
	}
	
	
}
