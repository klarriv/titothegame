package objects;

import java.awt.image.BufferedImage;
import windows.MainFrame;

public class Cone extends Physics {
	
	public static final double WIDTH = 0.2;
	public static final double HEIGHT = 0.2;
	private BufferedImage texture;
	private DoublePoint position;
	private DoubleRectangle r;
	private int weight = 5;
	private boolean isVisible;
	private boolean isUsed = false;
	private DoublePoint defaultPosition = new DoublePoint(1, 1);
	
	public Cone(double x, double y){
		
		position = new DoublePoint(x,y);
		setDefaultPosition(position);
		
		texture = MainFrame.getTl().coneTexture;
		isVisible = true;
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
	public void setX(double x) {
		this.position.x =x;
		
	}

	@Override
	public void setY(double y) {
		this.position.y = y;
		
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
		return HEIGHT;
	}

	public DoublePoint getDefaultPosition() {
		return defaultPosition;
	}

	public void setDefaultPosition(DoublePoint defaultPosition) {
		this.defaultPosition.x = defaultPosition.x;
		this.defaultPosition.y = defaultPosition.y;
	}
	
	public void resetPosition(){
		this.position.x = this.defaultPosition.x;
		this.position.y = this.defaultPosition.y;
	}

	@Override
	public double getWidth() {
		
		return WIDTH;
	}

}
