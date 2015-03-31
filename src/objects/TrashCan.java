package objects;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Timer;

import RunningClasses.ObjectInterface;

public class TrashCan extends Physics {
	
	private int weight = 10;
	private boolean isVisible = true;
	private boolean isUsed = true;
	private BufferedImage image = null;
	private DoublePoint position = new DoublePoint(10,10);
	private double vx = 7;
	private double vy = 10;
	/**
	 * Path of the sprite sheet
	 */
	private String pathRelativeToThis = "Resources/Objects/trashCan.png";
	
	public TrashCan(double x, double y){
		
		position = new DoublePoint(x, y);
		
		try {
			this.texture = ImageIO.read(new File("Resources/Objects/trashCan.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		
	}
	
	
	public BufferedImage loadImage() throws IOException{
		File file = new File(pathRelativeToThis);
		URL url = file.toURI().toURL();
		BufferedImage img = ImageIO.read(url);
		return img;
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
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setX(double x) {
		// TODO Auto-generated method stub
		
	}
	
	

}
