package objects;

import java.awt.image.BufferedImage;
import windows.MainFrame;

/**
 * The Pulley objects attaches a rope and with TrashCan.
 * Extends physics for future use
 * @author CharlesPhilippe
 *
 */
public class Pulley extends Physics{
	/**
	 * The current positon of the pulley.
	 */
	private DoublePoint position = new DoublePoint(1,1);
	/**
	 * The visibility of the pulley.
	 */
	private boolean isVisible;
	/**
	 * The BufferedImage of the pulley.
	 */
	private BufferedImage texture;
	/**
	 * The usage of the pulley.
	 */
	private boolean isUsed = false;
	/**
	 * The default position of the pulley.
	 */
	private DoublePoint defaultPosition = new DoublePoint(1, 1);
	/**
	 * The height of the pulley.
	 */
	public static final double HEIGHT = 0.16;
	/**
	 * The width of the pulley.
	 */
	public static final double WIDTH = 0.28;		
	
	/**
	 * Creates a pulley at the specified position with the specified visibility
	 * @param position
	 * @param visible
	 */
	public Pulley(DoublePoint position, boolean visible){
		this.position = position;
		setDefaultPosition(position);
		this.isVisible = visible;
		
		if (isVisible)
			texture = MainFrame.getTl().pulleyTexture;
	}
	
	/**
	 * Creates a pulley at the specified position with the specified visibility
	 * @param x
	 * @param y
	 * @param visible
	 */
	public Pulley(double x, double y, boolean visible){
		this.position.x = x;
		this.position.y = y;
		setDefaultPosition(position);
		this.isVisible = visible;
		
		if (isVisible)
			texture = MainFrame.getTl().pulleyTexture;
	}

	@Override
	public BufferedImage getTexture() {
		// TODO Auto-generated method stub
		return texture;
	}

	@Override
	public int getWeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setWeight(int weight) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return isVisible;
	}

	@Override
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
		
	}

	@Override
	public boolean isUsed() {
		
		return isUsed;
	}

	@Override
	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
		
	}

	@Override
	public DoublePoint getPosition() {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public void setPosition(DoublePoint position) {
		this.position = position;
		
	}

	@Override
	public void setY(double y) {
		this.position.y = y;
		
	}

	@Override
	public void setX(double x) {
		this.position.x = x;
		
	}

	@Override
	public double getVx() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setVx() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVx(double vx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getVy() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setVy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVy(double vy) {
		// TODO Auto-generated method stub
		
	}

	
	/**
	 * Resets the position to the default position.
	 */
	@Override
	public void resetPosition(){
		this.position.x = this.defaultPosition.x;
		this.position.y = this.defaultPosition.y;
	}
	
	/**
	 * Generates the default position from the initial position of the bench
	 */
	@Override
	public void setDefaultPosition(DoublePoint position) {
		this.defaultPosition.x =this.position.x;
		this.defaultPosition.y = this.position.y;
		
	}

	@Override
	public DoublePoint getDefaultPosition() {
		// TODO Auto-generated method stub
		return defaultPosition;
	}
	@Override
	public double getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getWidth() {
		
		return 0;
	}

	public double getHEIGHT() {
		return HEIGHT;
	}

	public double getWIDTH() {
		return WIDTH;
	}


	

}
