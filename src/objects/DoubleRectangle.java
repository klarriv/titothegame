package objects;

import java.awt.Point;

public class DoubleRectangle {
	
	private DoublePoint position;
	private double x, y, width, height;
	
	public DoubleRectangle(DoublePoint position, double width, double height){
		this.position = position;
		this.width = width;
		this.height = height;
	}
	
	public boolean contains(Point p){
		if(p.getX()>x && p.getX()<x+width && p.getY()>y && p.getY()<y+height)
			return true;
		else
			return false;
		
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
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
