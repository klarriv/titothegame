package objects;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import objects.DoubleRectangle;
import windows.MainFrame;

public class Bench extends Physics {
	
	public static final double WIDTH = 0.4;
	public static final double HEIGHT = 0.7;
	private BufferedImage texture;
	private DoubleRectangle r;
	private DoublePoint position;
	
	public Bench(double x, double y){
		
		position = new DoublePoint(x, y);
		texture = MainFrame.getTl().benchTexture;
		setR(new DoubleRectangle(position, WIDTH, HEIGHT));
	}

	@Override
	public int getWeight() {
		return weight;
	}

	@Override
	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public boolean isVisible() {
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
		return position;
	}

	@Override
	public void setPosition(DoublePoint position) {
		this.position = position;
	}

	@Override
	public double getVx() {
		return vx;
	}
	
	@Override
	public void setVx(double vx) {
		this.vx = vx;
	}

	@Override
	public double getVy() {
		return vy;
	}
	
	@Override
	public void setVy(double vy) {
		this.vy = vy;
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
	public void setHeight(double height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setX(double x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVx() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setY(double y) {
		// TODO Auto-generated method stub
		
	}
}
