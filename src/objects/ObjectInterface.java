package objects;

import java.awt.image.BufferedImage;
import java.io.IOException;
/**
 * 
 * @author CharlesPhilippe
 *
 */
public interface ObjectInterface {
	
	/**
	 * Returns the texture of the object
	 * @return
	 */
	public BufferedImage getTexture();

	/**
	 * Returns the weight of the object
	 * @return
	 */
	public int getWeight();

	/**
	 * Sets the weight of the object
	 * @param weight
	 */
	public void setWeight(int weight);

	/**
	 * Returns the visibility status of the object
	 * @return
	 */
	public boolean isVisible();

	/**
	 * Sets the visibility status of the object
	 * @param isVisible
	 */
	public void setVisible(boolean isVisible);

	/**
	 * Returns true if the object was used by the player
	 * @return
	 */
	public boolean isUsed();

	/**
	 * Sets its truth-value
	 * @param isUsed
	 */
	public void setUsed(boolean isUsed);

	/**
	 * Returns the position in game units of the object
	 * @return
	 */
	public DoublePoint getPosition();

	/**
	 * Sets the position in game units of the object
	 * @param position
	 */
	public void setPosition(DoublePoint position);

	/**
	 * Sets the specified y position in game units
	 * @param y
	 */
	public void setY(double y);
	
	/**
	 * Sets the specified x position in game units
	 * @param x
	 */
	public void setX(double x);

	/**
	 * Returns the velocity in x of the object
	 * @return
	 */
	public double getVx();
	
	/**
	 * Sets the velocity of the object to the same velocity of the object in the Physics class
	 */
	public void setVx();

	/**
	 * Sets the specified velocity in x
	 * @param vx
	 */
	public void setVx(double vx);

	/**
	 * Returns the velocity in y of the object
	 * @return
	 */
	public double getVy();
	
	/**
	 * Sets the velocity of the object to the same velocity of the object in the Physics class
	 */
	public void setVy();

	/**
	 * Sets the specified velocity in y
	 * @param vy
	 */
	public void setVy(double vy);
	
	/**
	 * Returns the height of the object from the its Position to the bottom of its image
	 * @return
	 */
	public double getHeight();
	
	/**
	 * Resets to the default position
	 */
	public void resetPosition();
	
	/**
	 * Sets the default position
	 * @param position
	 */
	public void setDefaultPosition(DoublePoint position);
	/**
	 * Gets the default position
	 * @return
	 */
	public DoublePoint getDefaultPosition();
}
