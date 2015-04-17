package objects;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import windows.MainFrame;

public class Pulley extends Physics{
	private DoublePoint position = new DoublePoint(1,1);
	private boolean isVisible;
	private BufferedImage texture;
	private boolean isUsed = false;
	private DoublePoint defaultPosition = new DoublePoint(1, 1);
	
	public Pulley(DoublePoint position, boolean visible){
		this.position = position;
		setDefaultPosition(position);
		this.isVisible = visible;
		
		if (isVisible)
			texture = MainFrame.getTl().pulleyTexture;
	}
	
	public Pulley(double x, double y, boolean visible){
		this.position.x = x;
		this.position.y = y;
		setDefaultPosition(position);
		this.isVisible = visible;
		
		if (isVisible)
			texture = MainFrame.getTl().pulleyTexture;
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
		
		return isUsed;
	}

	@Override
	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
		
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


	

}
