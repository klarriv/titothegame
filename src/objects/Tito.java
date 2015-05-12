package objects;

import java.awt.image.BufferedImage;

import javax.swing.Timer;

import RunningClasses.SpriteSheet;
import windows.MainFrame;
/**
 * Tito
 * @author CharlesPhilippe
 *
 */
public class Tito extends Physics{
	private int weight = 9;
	private boolean isVisible = true;
	private boolean isUsed = true;
	private boolean onPlane = false;
	private BufferedImage texture;
	private BufferedImage spriteSheet;
	private SpriteSheet ss = new SpriteSheet();
	private int counter = 1;
	private int titoXSprite = 0;
	private int titoYSprite = 0;
	/**
	 * 
	 */
	private int[] rollingx = { 4, 5, 0, 5, 5 };
	/**
	 * 
	 */
	private int[] rollingy = { 1, 1, 2, 0, 2 };
	
	
	private DoublePoint position = new DoublePoint(10,10);
	private double vx = 7;
	private double vy = 10;
	private final double HEIGHT = 0.25;
	private DoublePoint defaultPosition = new DoublePoint(1, 1);
	private DoubleRectangle r;
	/**
	 * This creates a new Tito
	 */
	public Tito(){
		setR(new DoubleRectangle(position, HEIGHT-0.09, HEIGHT-0.03));
		spriteSheet = MainFrame.getTl().titoTexture;
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
		setR(new DoubleRectangle(position, HEIGHT-0.09, HEIGHT-0.03));
		spriteSheet = MainFrame.getTl().titoTexture;
		
	}
	@Override
	public void setWeight(int weight) {
		this.weight = weight;
		
	}
	@Override
	public int getWeight() {
		
		return this.weight;
	}
	@Override
	public void setVisible(boolean visible) {
		
		this.isVisible = visible;
		
	}
	@Override
	public boolean isVisible() {
		
		return isVisible;
	}
	@Override
	public void setUsed(boolean used) {
		
		this.isUsed = used;
		
	}
	@Override
	public boolean isUsed() {
		
		return isUsed;
	}
	@Override
	public void setPosition(DoublePoint position) {
		
		this.position = position;
		
	}
	@Override
	public void setX(double x){
		
		this.position.x =x;
	}
	@Override
	public void setY(double y){
		
		this.position.y = y;
	}
	@Override
	public DoublePoint getPosition() {
		
		return this.position;
	}
	@Override
	public void setVx(double vx) {
		
		this.vx = vx;
		
	}
	@Override
	public void setVx(){
		this.vx = this.vxi;
	}
	@Override
	public double getVx() {
		
		return this.vx;
	}
	@Override
	public void setVy(double vy) {
		
		this.vy = vy;
		
	}
	@Override
	public void setVy(){
		this.vy = this.vyi;
	}
	@Override
	public double getVy() {
		
		return this.vy;
	}
	
	public int getDelay(){
		return this.t.getDelay();
	}

	
	@Override
	public BufferedImage getTexture() {
		this.ss.setSpriteSheet(spriteSheet);
		if (titoXSprite < rollingx.length) {
			texture = ss.grabSprite(rollingx[titoXSprite] * 300, rollingy[titoYSprite] * 250, 289, 250);
			if (counter % 2 == 0) {
				titoXSprite++;
				titoYSprite++;
			}
		} else {
			titoXSprite = 0;
			titoYSprite = 0;
			texture = ss.grabSprite(rollingx[titoXSprite] * 300, rollingy[titoYSprite] * 250, 289, 250);
			titoXSprite++;
			titoYSprite++;
		}
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

	public boolean isOnPlane() {
		return onPlane;
	}

	public void setOnPlane(boolean onPlane) {
		this.onPlane = onPlane;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
}
