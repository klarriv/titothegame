package objects;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Timer;

import RunningClasses.RunGame;

public class TrashCan extends Physics {
	
	private int weight = 20;
	private boolean isVisible = true;
	private boolean isUsed = true;
	private BufferedImage texture;
	private DoublePoint position = new DoublePoint(10,10);
	private double vx = 7;
	private double vy = 10;
	public int single = 0;
	public DoubleRectangle r;
	/**
	 * Path of the sprite sheet
	 */
	private String pathRelativeToThis = "Resources/Objects/trashCan.png";
	
	public TrashCan(double x, double y){
		
		this.position.x = x;
		this.position.y = y;
		this.vx = 0;
		this.vy = 0;
		loadImage();
		r = new DoubleRectangle(position, 0.4, 0.5);
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
		loadImage();
		r = new DoubleRectangle(position, 0.4, 0.5);
	}
	
	
	public void loadImage(){
		try{
		File file = new File(pathRelativeToThis);
		texture = ImageIO.read(file);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	
	

}
