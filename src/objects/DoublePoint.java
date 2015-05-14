package objects;

/**
 * The DoublePoint object is used like a Point using doubles instead of integers.
 * @author CharlesPhilippe
 *
 */
public class DoublePoint {
	/**
	 * The position in x in game units
	 */
	public double x = 0;
	/** 
	 * The position in y in game units
	 */
	public double y = 0;
	/**
	 * Creates a  new point at the specified double position.
	 * @param x
	 * @param y
	 */
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
