package objects;
/**
 * 
 * @author CharlesPhilippe
 *
 */
public class Plane {
	/**
	 * The position of the plane
	 */
	private DoublePoint position;
	/**
	 * The width/ the side adjacent to the angle
	 */
	private double width = 1;
	/**
	 * 
	 */
	private double length;
	/**
	 * 
	 */
	private DoublePoint anchor1;
	/**
	 * 
	 */
	private DoublePoint anchor2;
	/**
	 * 
	 */
	private boolean isUsed = false;
	/**
	 * 
	 */
	private boolean isVisible = false;
	/**
	 * 
	 */
	private double angle;
	/**
	 * The slope of the plane
	 */
	private double m;
	/**
	 * 
	 */
	private double b = 1;
	/**
	 * 
	 */
	private double c;
	/**
	 * Constant that determines easily the where the plane is facing
	 */
	private int planeVariable;
	/**
	 * 
	 */
	private boolean isMoving;
	/**
	 * 
	 */
	private DoublePoint defaultPosition = new DoublePoint(1, 1);
	
	
	/**
	 * Constructs a new plane at a specified position width a specified angle and width
	 * @param x
	 * @param y
	 * @param angle
	 * @param width
	 */
	public Plane(double x, double y, double angle, double width){
		this.width = width;
		this.angle = angle;
		if (this.angle >= Math.PI/2 && this.angle <= 3*Math.PI/2)
			setPlaneVariable(0);
		else
			setPlaneVariable(1);
		
		this.position = new DoublePoint(x, y);
		setFormula();
		setAnchors();
		setLength();
	}
	
	/**
	 * Sets the anchors
	 */
	public void setAnchors(){
		this.anchor1 = this.position;
		this.anchor2 = new DoublePoint(position.x + width, getY(this.position.x + width));
	}
	
	/**
	 * Returns the position in y of the specified x value
	 * @param x
	 * @return
	 */
	public double getY(double x){
		return m*x + c;
	}
	
	/**
	 * Sets the slope in the form mX + bY + c = 0
	 */
	public void setFormula(){
		this.m = Math.tan(angle);
		
		c = (b*position.y)-(m*position.x);
	}
	
	/**
	 * Returns the distance between a point and a plane
	 * @param point
	 * @return
	 */
	public double pointDistance(DoublePoint point){
		
		return Math.abs(-m * point.x + b * point.y - c)/ Math.sqrt((m * m) + (b * b));
		
	}
	
	/**
	 * Returns the angle of contact of a vector hitting the plane
	 * @param vx
	 * @param vy
	 * @return
	 */
	public double angleOfContact(double vx, double vy){
		double t;
		double o = Math.PI -angle;
		
		if (vx < 0){
			t = Math.atan(vx/vy);
			
			t += (Math.PI/2);
			
			t -= o;
			
		}
		else{
			t = Math.atan(vy/vx);
			t +=  o;
		}
		
		return t;
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
	public void setWidth(){
		this.width = Math.abs(length * Math.cos(angle));
		System.out.println(width);
	}
	public DoublePoint getAnchor1() {
		return anchor1;
	}
	public void setAnchor1(DoublePoint anchor1) {
		this.anchor1 = anchor1;
		setAngle();
		setWidth();
	}
	public DoublePoint getAnchor2() {
		return anchor2;
	}
	public void setAnchor2(DoublePoint anchor2) {
		this.anchor2 = anchor2;
		setAngle();
		setWidth();
	}
	public void setAnchor2X(){
		this.anchor2.x = this.anchor1.x + width;
		//this.anchor2.y = getY(this.anchor1.x + width);
	}
	public void setAnchor2Y(){
		double dy = Math.sqrt((length * length) - (width * width));
		if (planeVariable == 1)
			this.anchor2.y = this.anchor1.y + dy;
		else 
			this.anchor2.y = this.anchor1.y - dy;
	}
	public boolean isUsed() {
		return isUsed;
	}
	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}
	public boolean isVisible() {
		return isVisible;
	}
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	public double getAngle() {
		return angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}
	public void setAngle(){
		double dx = Math.abs(anchor1.x - anchor2.x);
		double dy = Math.abs(anchor1.y - anchor2.y);
		this.angle = Math.PI - Math.atan(dy/dx);
		setFormula();
		setWidth();
		setAnchor2X();
	}
	public double getM() {
		return m;
	}
	public void setM(double m) {
		this.m = m;
	}
	public double getB() {
		return b;
	}
	public void setB(double b) {
		this.b = b;
	}
	public double getC() {
		return c;
	}
	public void setC(double c) {
		this.c = c;
	}
	public int getPlaneVariable() {
		return planeVariable;
	}
	public void setPlaneVariable(int planeVariable) {
		this.planeVariable = planeVariable;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}
	public void setLength(){
		this.length = Math.abs(this.width/Math.cos(angle));
	}

	public boolean isMoving() {
		return isMoving;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}
	/**
	 * 
	 */
	public void resetPosition(){
		this.anchor1.x = this.defaultPosition.x;
		this.anchor1.y = this.defaultPosition.y;
		this.setAnchor2X();
		this.setAnchor2Y();
	}
	/**
	 * 
	 * @param position
	 */
	public void setDefaultPosition(DoublePoint position) {
		this.defaultPosition .x =this.anchor1.x;
		this.defaultPosition.y = this.anchor1.y;
		
	}
	/**
	 * 
	 * @return
	 */
	public DoublePoint getDefaultPosition() {
		
		return defaultPosition;
	}

 
}
