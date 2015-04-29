package RunningClasses;
import java.awt.image.BufferedImage;

/** c'est un test pour l'animation, si on l'utilise on le changera de place sinon on le deletera*/
public class SpriteSheet {
	
	public BufferedImage spriteSheet;
	
	public SpriteSheet(){
		
	}
	
	public SpriteSheet( BufferedImage ss){
		this.spriteSheet = ss;
		
	}
	
	public  BufferedImage grabSprite(int x, int y, int width, int height){
		BufferedImage sprite = spriteSheet.getSubimage(x, y, width, height);
		return sprite;
	}
	
	public void setSpriteSheet(BufferedImage ss){
		this.spriteSheet = ss;
	}

}
