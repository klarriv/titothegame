package objects;

import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Timer;

import windows.MainFrame;
import RunningClasses.RunGame;

public class TrashCan extends Physics {
	
	public static final double WIDTH = 0.4;
	public static final double HEIGHT = 0.5;
	private int weight = 10;
	private boolean isVisible = true;
	private boolean isUsed = true;
	private BufferedImage texture;
	private DoublePoint position;
	private double vx = 7;
	private double vy = 10;
	public int single = 0;
	private DoubleRectangle r;
	public boolean rotated = false;
	public static final double threshold = 300;
	
	
	public TrashCan(double x, double y){
		
		position = new DoublePoint(x, y);
		this.vx = 0;
		this.vy = 0;
		//loadImage();
		texture = MainFrame.getTl().trashCanTexture;
		setR(new DoubleRectangle(position, WIDTH, HEIGHT));
	}

	/**
	 * This creates a new instance of TrashCan
	 * @param x
	 * @param y
	 * @param vx
	 * @param vy
	 * @param t
	 */
	public TrashCan(double x, double y, double vx, double vy, Timer t) {
		super(x, y, vx, vy, t);
		
		this.position.x = x;
		this.position.y = y;
		this.vx = vx;
		this.vy = vy;
		this.t = t;
		texture = MainFrame.getTl().trashCanTexture;
		setR(new DoubleRectangle(position, WIDTH, HEIGHT));
	}
	
	public void rotate(double angle){
		AffineTransform tx = new AffineTransform();
		
	    tx.rotate(angle - Math.PI);

	    AffineTransformOp op = new AffineTransformOp(tx,
	        AffineTransformOp.TYPE_BILINEAR);
	   	texture = op.filter(texture, null);
	   	rotated = true;
	}
	
	public int getWeight() {
		return weight;
	}


	public void setWeight(int weight) {
		this.weight = weight;
	}


	public boolean isVisible() {
		return isVisible;
	}


	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}


	public boolean isUsed() {
		return isUsed;
	}


	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}


	public DoublePoint getPosition() {
		return position;
	}


	public void setPosition(DoublePoint position) {
		this.position = position;
	}


	public double getVx() {
		return vx;
	}
	
	public void setVx(){
		this.vx = this.vxi;
	}


	public void setVx(double vx) {
		this.vx = vx;
	}


	public double getVy() {
		return vy;
	}
	
	public void setVy(){
		this.vy = this.vyi;
	}


	public void setVy(double vy) {
		this.vy = vy;
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
	public double getHeight() {
		
		return HEIGHT;
	}

	
	
	

}
