package objects;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pulley extends Physics{
	private DoublePoint position = new DoublePoint(1,1);
	private boolean isVisible;
	private BufferedImage texture;
	private String pathRelativeToThis;
	
	public Pulley(DoublePoint position, boolean visible){
		this.position = position;
		this.isVisible = visible;
		
		if (isVisible)
			loadImage();
	}
	
	public Pulley(double x, double y, boolean visible){
		this.position.x = x;
		this.position.y = y;
		this.isVisible = visible;
		
		if (isVisible)
			loadImage();
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
		return isVisible;
	}

	@Override
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
		
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
		return position;
	}

	@Override
	public void setPosition(DoublePoint position) {
		this.position = position;
		
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
	public double getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setHeight(double height) {
		// TODO Auto-generated method stub
		
	}

}
