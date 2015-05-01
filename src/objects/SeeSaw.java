package objects;

import java.awt.image.BufferedImage;
import RunningClasses.SpriteSheet;
import windows.MainFrame;

public class SeeSaw {
	public static final double WIDTH = 1.1;
	public static final double HEIGHT = 0.33;
	private int isUsed = 0;
	private BufferedImage texture;
	private BufferedImage spriteSheet;
	SpriteSheet ss = new SpriteSheet(spriteSheet);
	private DoublePoint position;

	public SeeSaw(double x, double y){
		
		setPosition(new DoublePoint(x, y));
		spriteSheet = MainFrame.getTl().seesawTexture;
	}

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
