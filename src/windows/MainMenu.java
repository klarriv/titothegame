package windows;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.ImageIO;
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
	private JButton playButton, newGameButton;
	
	private Timer t = new Timer(100, this);
	private int yTitle = 20;
	boolean direction = true;
	
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
					playButton.setIcon(new ImageIcon(playButtonHighlightIcon));
				} else if ((JButton) e.getSource() == newGameButton) {
					newGameButton.setIcon(new ImageIcon(newGameHighlightButtonIcon));
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Cursor cursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
				setCursor(cursor);
				if ((JButton) e.getSource() == playButton) {
					playButton.setIcon(new ImageIcon(playButtonIcon));
				} else if ((JButton) e.getSource() == newGameButton) {
					newGameButton.setIcon(new ImageIcon(newGameButtonIcon));
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

		playButton = new JButton(new ImageIcon(playButtonIcon));
		playButton.setBorder(BorderFactory.createEmptyBorder());
		playButton.setContentAreaFilled(false);
		playButton.setBounds(541, 398, 232, 69);
		playButton.addMouseListener(new ButtonListener());
		playButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout = (CardLayout) MainFrame.getMenus().getLayout();
				cardLayout.show(MainFrame.getMenus(), MainFrame.getLevelselectpanel());
			}

		});

		newGameButton = new JButton(new ImageIcon(newGameButtonIcon));
		newGameButton.setBorder(BorderFactory.createEmptyBorder());
		newGameButton.setContentAreaFilled(false);
		newGameButton.setBounds(541, 498, 232, 69);
		newGameButton.addMouseListener(new ButtonListener());
		newGameButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout = (CardLayout) MainFrame.getMenus().getLayout();
				cardLayout.show(MainFrame.getMenus(), MainFrame.getLevelselectpanel());
			}

		});

		add(playButton);
		add(newGameButton);
		t.start();
	}

	/**
	 * Starts the music in the main menu.
	 */
	public void startMenuMusic() {

	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		System.out.println(getSize());
		g.drawImage(backgroundImage, 0, 0, null);
		g.drawImage(titleImage, 81, yTitle, null);
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
