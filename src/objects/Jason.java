package objects;

import java.awt.image.BufferedImage;

import windows.MainFrame;

public class Jason {
	/**
	 * The width of Jason
	 */
	public static final double WIDTH = 0.5;
	/**
	 * The height of Jason
	 */
	public static final double HEIGHT = 1;
	/**
	 * The  BufferedImage of Jason
	 */
	private BufferedImage texture;
	/**
	 * The current position of Jason
	 */
	private DoublePoint position;
	/**
	 * The hit box of Jason
	 */
	private DoubleRectangle r;

	/**
	 * Creates a new Jason at specified position
	 * @param x
	 * @param y
	 */
	public Jason(double x, double y){
		
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
