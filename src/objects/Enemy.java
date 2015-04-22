package objects;

import java.awt.image.BufferedImage;

import windows.MainFrame;

public class Enemy {
	
	public static final double WIDTH = 0.5;
	public static final double HEIGHT = 1;
	private BufferedImage texture;
	private DoublePoint position;
	private DoubleRectangle r;

	public Enemy(double x, double y){
		
		setPosition(new DoublePoint(x, y));
		texture = MainFrame.getTl().enemyTexture;
		setR(new DoubleRectangle(position, WIDTH, HEIGHT));
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public DoublePoint getPosition() {
		return position;
	}

	public void setPosition(DoublePoint position) {
		this.position = position;
	}

	public DoubleRectangle getR() {
		return r;
	}

	public void setR(DoubleRectangle r) {
		this.r = r;
	}
}
