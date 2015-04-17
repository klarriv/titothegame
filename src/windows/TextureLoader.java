package windows;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class TextureLoader {

	private final String PATHFOROBJECTS = "Resources/Objects/";
	public BufferedImage titoTexture, treeTexture, benchTexture, coneTexture, planeTexture, ropeTexture, seesawTexture, springTexture, trashCanTexture, pulleyTexture;
	public BufferedImage levelBackgroundTexture, levelPauseHeaderTexture, levelRestartTexture, levelPlayTexture;
	public BufferedImage mainMenuBackgroundTexture, mainMenuPlayButtonTexture, mainMenuPlayButtonHighlightTexture, mainMenuNewGameButtonTexture, mainMenuNewGameButtonHighlightTexture, mainMenuTitleTexture;
	public BufferedImage levelSelectBackgroundTexture;
	
	public TextureLoader() {
		
		try {
			// loads Tito
			titoTexture = ImageIO.read(new File("Resources/TitoSpriteSheet.png"));
			
			// loads objects texture
			treeTexture = ImageIO.read(new File(PATHFOROBJECTS+"tree.png"));
			benchTexture = ImageIO.read(new File(PATHFOROBJECTS+"bench.png"));
			coneTexture = ImageIO.read(new File(PATHFOROBJECTS+"cone.png"));
			planeTexture = ImageIO.read(new File(PATHFOROBJECTS+"plane.png"));
			ropeTexture = ImageIO.read(new File(PATHFOROBJECTS+"rope.png"));
			seesawTexture = ImageIO.read(new File(PATHFOROBJECTS+"seesaw.png"));
			springTexture = ImageIO.read(new File(PATHFOROBJECTS+"spring.png"));
			trashCanTexture = ImageIO.read(new File(PATHFOROBJECTS+"trashCan.png"));
			pulleyTexture = ImageIO.read(new File(PATHFOROBJECTS+"pulley.png"));
			
			// loads level buttons
			levelBackgroundTexture = ImageIO.read(new File("Resources/Background.png"));
			levelPauseHeaderTexture = ImageIO.read(new File("Resources/Menus/PauseMenu/pauseTitle.png"));
			levelPlayTexture = ImageIO.read(new File("Resources/play.png"));
			levelRestartTexture = ImageIO.read(new File("Resources/restart.png"));
			
			// laods mainmenu textures
			mainMenuBackgroundTexture = ImageIO.read(new File("Resources/Menus/MainMenu/mainmenu.png"));
			mainMenuPlayButtonTexture = ImageIO.read(new File("Resources/Menus/MainMenu/playButton.png"));
			mainMenuPlayButtonHighlightTexture = ImageIO.read(new File("Resources/Menus/MainMenu/playHighlightButton.png"));
			mainMenuNewGameButtonTexture = ImageIO.read(new File("Resources/Menus/MainMenu/newGameButton.png"));
			mainMenuNewGameButtonHighlightTexture = ImageIO.read(new File("Resources/Menus/MainMenu/newGameHighlightButton.png"));
			mainMenuTitleTexture = ImageIO.read(new File("Resources/Menus/MainMenu/title.png"));
			
			//loads level select texture
			levelSelectBackgroundTexture = ImageIO.read(new File("Resources/Menus/LevelSelectMenu/levelSelectionMenu.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
        Image scaled = img.getScaledInstance(size.width, size.height, java.awt.Image.SCALE_SMOOTH);
        jbutton.setIcon(new ImageIcon(scaled));
	}
}
