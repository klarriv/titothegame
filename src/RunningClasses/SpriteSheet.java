package RunningClasses;
import java.awt.image.BufferedImage;

/**
 * The SpriteSheet class loads a BufferedImage and 
 * crops it to get the current image for the the object
 * @author CharlesPhilippe
 *
 */
public class SpriteSheet {
	/**
	 * The cropped image
	 */
	BufferedImage sprite;
	/**
	 * The SpriteSheet
	 */
	public BufferedImage spriteSheet;
	/**
	 * Creates a new image
	 */
	public SpriteSheet(){
		
	}
	/**
	 * Creates a new image with a specified BufferedImage
	 * @param ss
	 */
	public SpriteSheet( BufferedImage ss){
		this.spriteSheet = ss;
		
	}
	/**
	 * Takes a part of the sprite sheet and returns it as another image
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return The image
	 */
	public  BufferedImage grabSprite(int x, int y, int width, int height){
		this.sprite = spriteSheet.getSubimage(x, y, width, height);
		return sprite;
	}
	/**
	 * Sets the Sprite sheet to the specified BufferedImage
	 * @param ss
	 */
	public void setSpriteSheet(BufferedImage ss){
		this.spriteSheet = ss;
	}

}
