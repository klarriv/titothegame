package objects;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Cone extends Physics {
	private String pathRelativeToThis = "Resources/Objects/bench.png";
	private BufferedImage texture;
	private DoublePoint position;
	private DoubleRectangle r;
	
	public Cone(double x, double y){
		
		position = new DoublePoint(x,y);
		setR(new DoubleRectangle(position, 0.2, 0.2));
		loadImage();
	}

	@Override
	public int getWeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setWeight(int weight) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setVisible(boolean isVisible) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isUsed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setUsed(boolean isUsed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DoublePoint getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPosition(DoublePoint position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setY(double y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setX(double x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getVx() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setVx() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVx(double vx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getVy() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setVy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVy(double vy) {
		// TODO Auto-generated method stub
		
	}

	@Override
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

	@Override
	public BufferedImage getTexture() {
		// TODO Auto-generated method stub
		return texture;
	}

	public DoubleRectangle getR() {
		return r;
	}

	public void setR(DoubleRectangle r) {
		this.r = r;
	}
	

}
