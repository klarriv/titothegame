package windows;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TextureLoader {

	private final String PATHFOROBJECTS = "Resources/Objects/";
	public BufferedImage titoTexture, treeTexture, benchTexture, coneTexture, planeTexture, ropeTexture, seesawTexture, springTexture, trashCanTexture, pulleyTexture, levelBackgroundTexture, levelPauseHeaderTexture;
	
	public TextureLoader() {
		
		try {
			titoTexture = ImageIO.read(new File("Resources/TitoSpriteSheet.png"));
			
			treeTexture = ImageIO.read(new File(PATHFOROBJECTS+"tree.png"));
			benchTexture = ImageIO.read(new File(PATHFOROBJECTS+"bench.png"));
			coneTexture = ImageIO.read(new File(PATHFOROBJECTS+"cone.png"));
			planeTexture = ImageIO.read(new File(PATHFOROBJECTS+"plane.png"));
			ropeTexture = ImageIO.read(new File(PATHFOROBJECTS+"rope.png"));
			seesawTexture = ImageIO.read(new File(PATHFOROBJECTS+"seesaw.png"));
			springTexture = ImageIO.read(new File(PATHFOROBJECTS+"spring.png"));
			trashCanTexture = ImageIO.read(new File(PATHFOROBJECTS+"trashCan.png"));
			pulleyTexture = ImageIO.read(new File(PATHFOROBJECTS+"pulley.png"));
			
			levelBackgroundTexture = ImageIO.read(new File("Resources/Background.png"));
			levelPauseHeaderTexture = ImageIO.read(new File("Resources/Menus/PauseMenu/pauseTitle.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
