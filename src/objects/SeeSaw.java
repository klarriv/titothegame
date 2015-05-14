package objects;

import java.awt.image.BufferedImage;
import RunningClasses.SpriteSheet;
import windows.MainFrame;

/**
 * The SeeSaw object is used as a mean to make Tito escape
 * @author CharlesPhilippe
 *
 */
public class SeeSaw {
	/**
	 * The width of the see saw
	 */
	public static final double WIDTH = 1.1;
	/**
	 * The height of the see saw
	 */
	public static final double HEIGHT = 0.33;
	/**
	 * The usage of the see saw
	 */
	private int isUsed = 0;
	/**
	 * The current image of the see saw
	 */
	private BufferedImage texture;
	/**
	 * The BufferedImage of the see saw
	 */
	private BufferedImage spriteSheet;
	/**
	 * The SpriteSheet object for the see saw
	 */
	SpriteSheet ss = new SpriteSheet(spriteSheet);
	/**
	 * The current position of the see saw
	 */
	private DoublePoint position;
	
	/**
	 * Creates a new see-saw at the specified position
	 * @param x
	 * @param y
	 */
	public SeeSaw(double x, double y){
		
		setPosition(new DoublePoint(x, y));
		spriteSheet = MainFrame.getTl().seesawTexture;
	}

	/**
	 * Returns the BufferedImage for the see-saw
	 * @return
	 */
	public BufferedImage getTexture() {
		this.ss = new SpriteSheet(spriteSheet);
		if (isUsed == 0)
			texture = ss.grabSprite(0, 170, 559, 170);
			
		else if (isUsed == 1)
			texture = ss.grabSprite(0, 0, 559, 170);
		
		else if (isUsed == 2)
			texture = ss.grabSprite(559, 170, 559, 170);
		else
			texture = ss.grabSprite(0, 170, 559, 170);
			
		return texture;
	}
	/**
	 * Returns whether the specified physics object is on the see-saw or not
	 * @param ob1
	 * @return Whether the object is on the see-saw or not
	 */
	public boolean getContact(Physics ob1){
		double x = ob1.getPosition().x;
		double y = ob1.getPosition().y;
		double width = ob1.getWidth();
		double height = ob1.getHeight();
		double middleX = this.position.x + (WIDTH/2);
		double endX = this.position.x + WIDTH;
		
		if (x >= middleX && (x + width) <= endX && (y + height) >= this.position.y ){
			setUsed(2);
			return true;
		}
		
		else
			return false;
	}
	/**
	 * Returns whether the specified physics object is on the see-saw or not
	 * @param ob1
	 * @return Whether the object is on the see-saw or not
	 */
	public boolean objectOn(Physics ob1){
		double x = ob1.getPosition().x;
		double y = ob1.getPosition().y;
		double width = ob1.getWidth();
		double height = ob1.getHeight();
		double middleX = this.position.x + (WIDTH/2);
		
		if ((x + width/2) >= this.position.x && (x + width) <= middleX && (y + height) >= this.position.y)
			return true;
		
		else
			return false;
	}

	public DoublePoint getPosition() {
		return position;
	}

	public void setPosition(DoublePoint position) {
		this.position = position;
	}
	
	public double getHeight(){
		return HEIGHT;
	}
	public double getWidth(){
		return WIDTH;
	}

	public int isUsed() {
		return isUsed;
	}

	public void setUsed(int isUsed) {
		this.isUsed = isUsed;
	}

}
