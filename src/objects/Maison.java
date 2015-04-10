package objects;

public class Maison {
	private DoublePoint position = new DoublePoint(1,1);
	private double width;
	private double height;
	
	public Maison(double x, double y, double width, double height){
		this.position.x = x;
		this.position.y = y;
		this.width = width;
		this.height = height;
		
	}
	
	public boolean colliding(DoublePoint p){
		if (p.x >= position.x && p.x <= position.x + width 
				&& p.y >= position.y && p.y <= position.y + height)
			return true;
		
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
