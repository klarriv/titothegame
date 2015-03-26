package objects;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.Timer;

public class Tito extends Physics{
	private int weight = 9;
	private boolean isVisible = true;
	private boolean isUsed = true;
	private BufferedImage image = null;
	private DoublePoint position = new DoublePoint(10,10);
	private double vx = 7;
	private double vy = 10;
	/**
	 * Path of the sprite sheet
	 */
	private String pathRelativeToThis = "Resources/TitoSpriteSheet.png";
	
	public Tito(){
		
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
		this.vx = vx;
		this.vy = vy;
		this.t = t;
		
	}
	
	/**
	 * Loads the buffered image
	 * @return
	 * @throws IOException
	 */
	public BufferedImage loadImage() throws IOException{
		File file = new File(pathRelativeToThis);
		URL url = file.toURI().toURL();
		BufferedImage img = ImageIO.read(url);
		return img;
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
	

}
