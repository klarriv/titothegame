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
import RunningClasses.SpriteSheet;

public class TrashCan extends Physics {
	
	public static final double WIDTHFLAT = 0.30;
	public static final double HEIGHTFLAT = 0.40;
	public static final double WIDTHATANGLE = 0.4;
	public static final double HEIGHTATANGLE = 0.5;
	private int weight = 10;
	private boolean isVisible = true;
	private boolean isUsed = false;
	private int planeVariable = -1;
	private BufferedImage texture;
	private BufferedImage spriteSheet;
	private DoublePoint position;
	private double vx = 7;
	private double vy = 10;
	public int single = 0;
	private DoubleRectangle r;
	public boolean rotated = false;
	private DoublePoint defaultPosition  = new DoublePoint(1, 1);
	public static final double threshold = 300;
	private double width;
	private double height;
	
	
	public TrashCan(double x, double y){
		
		position = new DoublePoint(x, y);
		setDefaultPosition(position);
		this.vx = 0;
		this.vy = 0;
		this.width = WIDTHFLAT;
		this.height = HEIGHTFLAT;
		spriteSheet = MainFrame.getTl().trashCanTexture;
		setR(new DoubleRectangle(position, width, height));
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
		setDefaultPosition(position);
		this.vx = vx;
		this.vy = vy;
		this.width = WIDTHFLAT;
		this.height = HEIGHTFLAT;
		this.t = t;
		texture = MainFrame.getTl().trashCanTexture;
		setR(new DoubleRectangle(position, width, height));
	}
	
	public void rotate(double angle){
		AffineTransform tx = new AffineTransform();
		
	    tx.rotate(angle - Math.PI);

	    AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
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
		SpriteSheet ss = new SpriteSheet(spriteSheet);
		
		if (planeVariable == -1){
			this.width = WIDTHFLAT;
			this.height = HEIGHTFLAT;
			this.r.setWidth(WIDTHFLAT);
			this.r.setHeight(HEIGHTFLAT);
			texture = ss.grabSprite(0, 0, 389, 489);
		}
		else if(planeVariable == 0 || planeVariable == 1){
			this.width = WIDTHATANGLE;
			this.height = HEIGHTATANGLE;
			this.r.setWidth(WIDTHATANGLE);
			this.r.setHeight(HEIGHTATANGLE);
			texture = ss.grabSprite(planeVariable * 530, 600, 530, 600);
			
		}
		else{
			this.width = WIDTHFLAT;
			this.height = HEIGHTFLAT;
			this.r.setWidth(WIDTHFLAT);
			this.r.setHeight(HEIGHTFLAT);
			texture = ss.grabSprite(0, 0, 389, 489);
		}
		
		return texture;
	}
	
	public void setPlaneVariable(int planeVariable){
		this.planeVariable = planeVariable;
	}
	
	public int getPlaneVariable(){
		return this.planeVariable;
	}

	public DoubleRectangle getR() {
		return r;
	}

	public void setR(DoubleRectangle r) {
		this.r = r;
	}

	@Override
	public double getHeight() {
		
		return this.height;
	}
	
	@Override
	public double getWidth() {
		
		return this.width;
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

	public void setWidth(double width) {
		this.width = width;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	


	
	
	

}
