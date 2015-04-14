package objects;

public class DoublePoint {
	public double x = 0;
	public double y = 0;
	
	public DoublePoint(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Returns the distance between the point and the specified point
	 * @param point
	 * @return
	 */
	public double distance(DoublePoint point){
		return Math.sqrt(Math.pow(point.x - x, 2.0) + Math.pow(point.y - y, 2.0));
	}

}
