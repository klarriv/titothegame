package windows;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class TextureLoader {

	private final String PATHFOFOBJECTS = "Resources/Objects/";
	public BufferedImage titoTexture, treeTexture, benchTexture, coneTexture, planeTexture, ropeTexture, seesawTexture, springTexture, trashCanTexture, pulleyTexture, enemyTexture;
	public BufferedImage[] maison, helpPages;
	public BufferedImage levelBackgroundTexture, levelPauseHeaderTexture, levelRestartTexture, levelPlayTexture, levelPauseTexture;
	public BufferedImage mainMenuBackgroundTexture, mainMenuPlayButtonTexture, mainMenuPlayButtonHighlightTexture, mainMenuNewGameButtonTexture, mainMenuNewGameButtonHighlightTexture, mainMenuTitleTexture, mainMenuMusicButtonTexture, mainMenuMusicButtonTexture2, mainMenuHelpMenuButtonTexture;
	public BufferedImage levelSelectBackgroundTexture, levelSelectBackButtonTexture, levelSelectBackButtonGlowTexture;
	public BufferedImage[] levelSelectLevelTexture, levelSelectLevelGlowTexture;
	public BufferedImage pauseMenuBackToGameTexture, pauseMenuExitGameTexture, pauseMenuLevelSelectionTexture;
	public BufferedImage level0star, level1star, level2stars, level3stars;
	public ImageIcon titoWalkingAnimation, JOptionPaneBG;
	public Font fntPlayGame;
	
	public String bouncingSound, addObjectTrashcanSound, heySound, attachingRopeSound, ropeSnap, levelChange, nonono;
	
	public TextureLoader() {
		
		try {
			
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Resources/BlackCarrot.ttf")));
			fntPlayGame = Font.createFont(Font.TRUETYPE_FONT, new File("Resources/BlackCarrot.ttf")).deriveFont(Font.PLAIN, 22f);
			
			titoWalkingAnimation = new ImageIcon("Resources/titoWalking.png");
			JOptionPaneBG = new ImageIcon("Resources/brickBG.png");
			
			// loads Tito
			titoTexture = ImageIO.read(new File("Resources/TitoSpriteSheet.png"));
			
			// loads objects texture
			treeTexture = ImageIO.read(new File(PATHFOFOBJECTS+"tree.png"));
			benchTexture = ImageIO.read(new File(PATHFOFOBJECTS+"bench.png"));
			coneTexture = ImageIO.read(new File(PATHFOFOBJECTS+"cone.png"));
			planeTexture = ImageIO.read(new File(PATHFOFOBJECTS+"plane.png"));
			ropeTexture = ImageIO.read(new File(PATHFOFOBJECTS+"rope.png"));
			seesawTexture = ImageIO.read(new File(PATHFOFOBJECTS+"seeSawSprite.png"));
			trashCanTexture = ImageIO.read(new File(PATHFOFOBJECTS+"trashCanSprite.png"));
			pulleyTexture = ImageIO.read(new File(PATHFOFOBJECTS+"pulley.png"));
			enemyTexture = ImageIO.read(new File(PATHFOFOBJECTS+"jasonGerard.png"));
			
			// load les maisons
			maison = new BufferedImage[6];
			for (int i = 0; i<maison.length; i++){
				maison[i] = ImageIO.read(new File(PATHFOFOBJECTS+"Maison"+i+".png"));
			}
			
			// loads level buttons
			levelBackgroundTexture = ImageIO.read(new File("Resources/Background.png"));
			levelPauseHeaderTexture = ImageIO.read(new File("Resources/Menus/PauseMenu/pauseTitle.png"));
			levelPlayTexture = ImageIO.read(new File("Resources/play.png"));
			levelRestartTexture = ImageIO.read(new File("Resources/restart.png"));
			levelPauseTexture = ImageIO.read(new File("Resources/pause.png"));
			
			// loads mainmenu textures
			mainMenuBackgroundTexture = ImageIO.read(new File("Resources/Menus/MainMenu/mainmenu.png"));
			mainMenuPlayButtonTexture = ImageIO.read(new File("Resources/Menus/MainMenu/playButton.png"));
			mainMenuPlayButtonHighlightTexture = ImageIO.read(new File("Resources/Menus/MainMenu/playHighlightButton.png"));
			mainMenuNewGameButtonTexture = ImageIO.read(new File("Resources/Menus/MainMenu/newGameButton.png"));
			mainMenuNewGameButtonHighlightTexture = ImageIO.read(new File("Resources/Menus/MainMenu/newGameHighlightButton.png"));
			mainMenuTitleTexture = ImageIO.read(new File("Resources/Menus/MainMenu/title.png"));
			mainMenuMusicButtonTexture = ImageIO.read(new File("Resources/Menus/MainMenu/musicButton.png"));
			mainMenuMusicButtonTexture2 = ImageIO.read(new File("Resources/Menus/MainMenu/musicButton2.png"));
			mainMenuHelpMenuButtonTexture = ImageIO.read(new File("Resources/Menus/MainMenu/helpMenuButton.png"));
			
			//loads level select texture
			levelSelectBackgroundTexture = ImageIO.read(new File("Resources/Menus/LevelSelectMenu/levelSelectionMenu.png"));
			levelSelectBackButtonTexture = ImageIO.read(new File("Resources/Menus/LevelSelectMenu/backButton.png"));
			levelSelectBackButtonGlowTexture = ImageIO.read(new File("Resources/Menus/LevelSelectMenu/backButtonGlow.png"));
			levelSelectLevelTexture = new BufferedImage[10];
			levelSelectLevelGlowTexture = new BufferedImage[10];
			
			for(int i=0; i<10; i++){
				levelSelectLevelTexture[i] = ImageIO.read(new File("Resources/Menus/LevelSelectMenu/lvl" + (i+1) + ".png"));
				levelSelectLevelGlowTexture[i] = ImageIO.read(new File("Resources/Menus/LevelSelectMenu/lvl" + (i+1) + "Glow.png"));
			}
			
			// loads the stars
			level0star = ImageIO.read(new File("Resources/Menus/Level/0star.png"));
			level1star = ImageIO.read(new File("Resources/Menus/Level/1star.png"));
			level2stars = ImageIO.read(new File("Resources/Menus/Level/2stars.png"));
			level3stars = ImageIO.read(new File("Resources/Menus/Level/3stars.png"));
			
			// loads the buttons for the pause menu
			pauseMenuBackToGameTexture = ImageIO.read(new File("Resources/Menus/PauseMenu/backToGameButton.png"));
			pauseMenuExitGameTexture = ImageIO.read(new File("Resources/Menus/PauseMenu/exitGameButton.png"));
			pauseMenuLevelSelectionTexture = ImageIO.read(new File("Resources/Menus/PauseMenu/levelSelectionButton.png"));
			
			helpPages = new BufferedImage[6];
			for(int i=0; i<helpPages.length; i++){
				helpPages[i] = ImageIO.read(new File("Resources/Menus/HelpMenu/help"+i+".png"));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		bouncingSound = "Resources/Sounds/bouncingSound.wav";
		addObjectTrashcanSound = "Resources/Sounds/addObjectTrashcan.wav";
		heySound = "Resources/Sounds/heySound.wav";
		attachingRopeSound = "Resources/Sounds/attachingRope.wav";
		ropeSnap = "Resources/Sounds/ropeSnap.wav";
		levelChange = "Resources/Sounds/levelChange.wav";
		nonono = "Resources/Sounds/nonono.wav";
		
	}
	
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
        if(size.width != 0 && size.getHeight() != 0){
        	Image scaled = img.getScaledInstance(size.width, size.height, java.awt.Image.SCALE_SMOOTH);
        	jbutton.setIcon(new ImageIcon(scaled));
        }
	}
	
	public void playSound(String string) {
		try {
			Clip sound = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File(string).getAbsoluteFile());
			sound.open(ais);
			sound.start();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}

