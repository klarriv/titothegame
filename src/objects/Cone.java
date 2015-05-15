package objects;

import java.awt.image.BufferedImage;
import windows.MainFrame;
/**
 * The Bench object is used to add weight to a TrashCan.
 * @author CharlesPhilippe
 *
 */
public class Cone extends Physics {
	/**
	 * The width of the cone
	 */
	public static final double WIDTH = 0.2;
	/**
	 * The height of the cone
	 */
	public static final double HEIGHT = 0.2;
	/**
	 * The BufferedImage for the cone
	 */
	private BufferedImage texture;
	/**
	 * The current position
	 */
	private DoublePoint position;
	/**
	 * The hit box of the cone
	 */
	private DoubleRectangle r;
	/**
	 * The weight of the cone
	 */
	private int weight = 5;
	/**
	 * The visibility of the cone
	 */
	private boolean isVisible;
	/**
	 * The usage of the cone
	 */
	private boolean isUsed = false;
	/**
	 * The default position of the cone
	 */
	private DoublePoint defaultPosition = new DoublePoint(1, 1);
	
	/**
	 * Creates a new Cone at specified position
	 * @param x
	 * @param y
	 */
	public Cone(double x, double y){
		
		position = new DoublePoint(x,y);
		setDefaultPosition(position);
		
		texture = MainFrame.getTl().coneTexture;
		isVisible = true;
		setR(new DoubleRectangle(position, WIDTH, HEIGHT));
	}

	@Override
	public int getWeight() {
		return weight;
	}

	@Override
	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public boolean isVisible() {
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
		return position;
	}

	@Override
	public void setPosition(DoublePoint position) {
		this.position = position;
	}

	@Override
	public double getVx() {
		return vx;
	}
	
	@Override
	public void setVx(double vx) {
		this.vx = vx;
	}

	@Override
	public double getVy() {
		return vy;
	}
	
	@Override
	public void setVy(double vy) {
		this.vy = vy;
	}

	@Override
	public BufferedImage getTexture() {
		return texture;
	}

	public DoubleRectangle getR() {
		return r;
	}

	public void setR(DoubleRectangle r) {
		this.r = r;
	}


	@Override
	public void setX(double x) {
		this.position.x =x;
		
	}

	@Override
	public void setY(double y) {
		this.position.y = y;
		
	}

	@Override
	public void setVx() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getHeight() {
		return HEIGHT;
	}

	public DoublePoint getDefaultPosition() {
		return defaultPosition;
	}
	
	/**
	 * Generates the default position from the initial position of the bench
	 */
	public void setDefaultPosition(DoublePoint defaultPosition) {
		this.defaultPosition.x = defaultPosition.x;
		this.defaultPosition.y = defaultPosition.y;
	}
	
	/**
	 * Resets the position to the default position.
	 */
	public void resetPosition(){
		this.position.x = this.defaultPosition.x;
		this.position.y = this.defaultPosition.y;
	}

	@Override
	public double getWidth() {
		
		return WIDTH;
	}

}
