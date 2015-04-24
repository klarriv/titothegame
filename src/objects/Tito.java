package objects;

import java.awt.image.BufferedImage;
import javax.swing.Timer;

import windows.MainFrame;

public class Tito extends Physics{
	private int weight = 9;
	private boolean isVisible = true;
	private boolean isUsed = true;
	private BufferedImage texture;
	private DoublePoint position = new DoublePoint(10,10);
	private double vx = 7;
	private double vy = 10;
	private final double HEIGHT = 0.25;
	private DoublePoint defaultPosition = new DoublePoint(1, 1);
	private DoubleRectangle r;
	
	public Tito(){
		setR(new DoubleRectangle(position, HEIGHT, HEIGHT));
		texture = MainFrame.getTl().titoTexture;
	}
	
	/**
	 * This creates a new instance of Tito
	 * @param x
	 * @param y
	 * @param vx
	 * @param vy
	 * @param t
	 */
	public Tito(double x, double y, double vx, double vy, Timer t) {
		super(x, y, vx, vy, t);
		
		this.position.x = x;
		this.position.y = y;
		setDefaultPosition(position);
		this.vx = vx;
		this.vy = vy;
		this.t = t;
		setR(new DoubleRectangle(position, HEIGHT, HEIGHT));
		texture = MainFrame.getTl().titoTexture;
		
	}

	public void setWeight(int weight) {
		this.weight = weight;
		
	}

	public int getWeight() {
		
		return this.weight;
	}

	public void setVisible(boolean visible) {
		
		this.isVisible = visible;
		
	}

	public boolean getVisible() {
		
		return isVisible;
	}
	
	public void setUsed(boolean used) {
		
		this.isUsed = used;
		
	}

	public boolean getUsed() {
		
		return isUsed;
	}

	public void setPosition(DoublePoint position) {
		
		this.position = position;
		
	}
	
	public void setX(double x){
		
		this.position.x =x;
	}
	
	public void setY(double y){
		
		this.position.y = y;
	}

	public DoublePoint getPosition() {
		
		return this.position;
	}

	public void setVx(double vx) {
		
		this.vx = vx;
		
	}
	public void setVx(){
		this.vx = this.vxi;
	}

	public double getVx() {
		
		return this.vx;
	}

	public void setVy(double vy) {
		
		this.vy = vy;
		
	}
	
	public void setVy(){
		this.vy = this.vyi;
	}

	public double getVy() {
		
		return this.vy;
	}
	
	public int getDelay(){
		return this.t.getDelay();
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isUsed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BufferedImage getTexture() {
		
		return texture;
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
		
		return HEIGHT;
	}

	public DoubleRectangle getR() {
		return r;
	}

	public void setR(DoubleRectangle r) {
		this.r = r;
	}


	
	

}
