package objects;

import java.awt.Point;
import java.awt.image.BufferedImage;

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
	
	
	
}
