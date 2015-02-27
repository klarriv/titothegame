package objects;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Timer;

public class Tito extends Physics{
	private String pathRelativeToThis = "Resources/TitoSpriteSheet.png";
	
	public Tito(){
		
	}
	
	public Tito(int x, int y, double vx, double vy, Timer t) {
		super(x, y, vx, vy, t);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
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

	@Override
	public void setWeight(int weight) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getWeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setVisible(boolean visible) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getVisible() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void setUsed(boolean used) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getUsed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setPosition(Point position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Point getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setVx(double vx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getVx() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setVy(double vy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getVy() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BufferedImage loadImage(String pathRelativeToThis)
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
