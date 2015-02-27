package objects;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;

public interface ObjectInterface {
	public int weight = 0;
	public boolean isVisible = false;
	public boolean isUsed = false;
	public BufferedImage image = null;
	public Point position = null;
	public double vx = 0;
	public double vy = 0;
	
	/** Changing the position of the using a mouse listener*/
	public void move();
	
	public void setWeight( int weight);
	
	public int getWeight();
	
	public void setVisible(boolean visible);
	
	public boolean getVisible();
	
	public void setUsed(boolean used);
	
	public boolean getUsed();
	
	public BufferedImage loadImage(String pathRelativeToThis) throws IOException;//something like that
	
	public void setPosition(Point position);
	
	public Point getPosition();
	
	public void setVx(double vx);
	
	public double getVx();
	
	public void setVy(double vy);
	
	public double getVy();
	
	
	
	
}
