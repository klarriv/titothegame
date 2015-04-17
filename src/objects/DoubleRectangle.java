package objects;

import java.awt.Point;

public class DoubleRectangle {
	
	private DoublePoint position;
	private double width, height;
	
	public DoubleRectangle(double x, double y, double width, double height){
		this.position = new DoublePoint(x, y);
		this.width = width;
		this.height = height;
	}
	
	public DoubleRectangle(DoublePoint position, double width, double height){
		this.position = position;
		this.width = width;
		this.height = height;
	}
	
	public boolean contains(DoublePoint p){
		if(p.x>position.x && p.x<position.x+width && p.y>position.y && p.y<position.y+height)
			return true;
		else
			return false;
		
	}
	
	public boolean contains(DoubleRectangle r){
		if (r.getPosition().x < position.x+width && r.getPosition().x+r.getWidth() > position.x && r.getPosition().y < position.y+height && r.getPosition().y+r.getHeight() > position.y){
			return true;
		}
			
		else
			return false;
	}

	public DoublePoint getPosition() {
		return position;
	}

	public void setPosition(DoublePoint position) {
		this.position = position;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	
	
}
