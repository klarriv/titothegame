package objects;

import java.awt.image.BufferedImage;
import objects.DoubleRectangle;
import windows.MainFrame;
/**
 * The Bench object used to add weight to a TrashCan.
 * Extends physics for future use.
 * @author CharlesPhilippe
 *
 */
public class Bench extends Physics {
	/**
	 * The width of the bench
	 */
	public static final double WIDTH = 0.4;
	/**
	 * The width of the bench
	 */
	public static final double HEIGHT = 0.7;
	/**
	 * The BufferedImage of the bench
	 */
	private BufferedImage texture;
	/**
	 * The hit box of the bench
	 */
	private DoubleRectangle r;
	/**
	 * The current position of the bench
	 */
	private DoublePoint position;
	/**
	 * The usage of the bench
	 */
	private boolean isUsed = false;
	/**
	 * The default position of the bench
	 */
	private DoublePoint defaultPosition = new DoublePoint(1, 1);
	/**
	 * the weight of the bench
	 */
	private int weight = 35;
	
	/**
	 * Creates a new Bench at specified psoition.
	 * @param x
	 * @param y
	 */
	public Bench(double x, double y){
		
		position = new DoublePoint(x, y);
		setDefaultPosition(position);
		texture = MainFrame.getTl().benchTexture;
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
		this.position.x = x;
		
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
	
	@Override
	public void resetPosition(){
		this.position.x = this.defaultPosition.x;
		this.position.y = this.defaultPosition.y;
	}

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
	public double getWidth() {
		
		return WIDTH;
	}

	
}
