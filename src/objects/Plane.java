package objects;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import windows.MainFrame;

/**
 * 
 * @author CharlesPhilippe
 *
 */
public class Plane {
	
	/**
	 * The width/the side adjacent to the angle
	 */
	private double width = 1;
	/**
	 * The height/the side opposite to the angle
	 */
	private double height;
	/**
	 * 
	 */
	private double length;
	/**
	 * The position of the plane
	 */
	private DoublePoint anchor1;
	/**
	 * The position of the other side of the plane
	 */
	private DoublePoint anchor2;
	/**
	 * 
	 */
	private BufferedImage texture;
	/**
	 * 
	 */
	private int isUsed = -1;
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
	private double frictionConstant = 0.5;
	/**
	 * 
	 */
	private boolean isMoving;
	/**
	 * 
	 */
	private DoublePoint defaultPosition = new DoublePoint(1, 1);
	/**
	 * 
	 */
	private int maisonNumber = -1;

	
	/**
	 * Constructs a new plane at a specified position width a specified angle and width
	 * @param x
	 * @param y
	 * @param angle
	 * @param width
	 */
	public Plane(double x, double y, double angle, double width, int maisonNumber){
		this.setMaisonNumber(maisonNumber);
		
		this.width = width;
		this.angle = angle;
		
		if (this.angle >= Math.PI/2 && this.angle <= Math.PI || this.angle >= 3*Math.PI/2)
			setPlaneVariable(0);
		else
			setPlaneVariable(1);
		
		this.anchor1 = new DoublePoint(x, y);
		this.anchor2 = new DoublePoint(x, y);
		
		setDefaultPosition(anchor1);
		setFormula();
		setLength();
		setHeight();
		setAnchor2();
		texture = MainFrame.getTl().planeTexture;
	}
	
	
	/**
	 * Returns the position in y of the specified x value on the plane
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
		
		c = (b * anchor1.y) - (m * anchor1.x);
		
		//System.out.println(m + "x" + " + " + b + "y + (" + c + ") = 0");
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
		double o = angle;//Math.PI -
		
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
	
	
	/**
	 * Returns the the angle of the plane in radians
	 * @return The angle of the plane
	 */
	public double getAngle() {
		return angle;
	}
	/**
	 * Sets to the specified angle
	 * @param angle
	 */
	public void setAngle(double angle) {
		this.angle = angle;
		
	}
	/**
	 * Sets the angle of the plane with the current width and height
	 */
	public void setAngle(){
	//TODO
		this.angle = Math.PI + Math.atan(height/width);
		
	}
	
	/**
	 * Returns the current width
	 * @return
	 */
	public double getWidth(){
		return this.width;
	}
	/**
	 * Sets the width of the plane with the specified value
	 * @param width
	 */
	public void setWidth(double width){
		this.width = width;
		//TODO setAnchor2()??
	}
	/**
	 * Sets the width of the plane with the current angle
	 */
	public void setWidth(){
		this.width = Math.abs(length * Math.cos(angle));
	}
	
	/**
	 * Returns the height
	 * @return
	 */
	public double getHeight(){
		return this.height;
	}
	/**
	 * Sets the height of the plane with the specified value
	 * @param height
	 */
	public void setHeight(double height){
		this.height = height;
	}
	/**
	 * Sets the height of the plane with the current angle
	 */
	public void setHeight(){
		this.height = Math.abs(length * Math.sin(angle));
	}
	/**
	 * Returns the length of the plane/the hypotenuse
	 * @return
	 */
	public double  getLength(){
		return this.length;
	}
	/**
	 * Sets the length of the plane to the specified value
	 * @param length
	 */
	public void setLength(int length){
		this.length = length;
	}
	/**
	 * Sets the length of the plane with the current width and angle
	 */
	public void setLength(){
			this.length = Math.abs((double)this.width/Math.cos(angle));
	}
	
	
	/**
	 * This creates the gradually inclining movement of a plane attached to a rope.
	 * Assuming the movement of Anchor1 in y and the Anchor2's y fixed,
	 *  this sets the new angle,the new formula, the new width and new position for Anchor2.
	 */
	public void mouvement(){
		setHeight(Math.abs(anchor1.y - anchor2.y));
		setWidth(Math.sqrt(length * length - height * height));
		setAngle();
		setFormula();
		setAnchor2X();
		
	}
	
	
	
	
	/**
	 * Returns the position of the plane/the anchor 1
	 * @return
	 */
	public DoublePoint getAnchor1(){
		return this.anchor1;
	}
	/**
	 * Sets the Anchor1 to the specified position
	 * @param anchor1
	 */
	public void setAnchor1(DoublePoint anchor1){
		this.anchor1 = anchor1;
		setFormula();
	}
	/**
	 * Sets the Anchor1 with the current width, height and Anchor2's position
	 */
	public void setAnchor1(){
		
		if (planeVariable == 1){
			this.anchor1.x = this.anchor2.x - width;
			this.anchor1.y = this.anchor2.y + height;
		}
		else {
			this.anchor1.x = this.anchor2.x + width;
			this.anchor1.y = this.anchor2.y - height;
		}
		setFormula();
	}
	
	/**
	 * Sets the X coordinate of the Anchor1with the current width and anchor2's position
	 */
	public void setAnchor1X(){
		if (planeVariable == 1)
			this.anchor1.x = this.anchor2.x - width;
		else 
			this.anchor1.x = this.anchor2.x + width;
	}
	/**
	 * Sets the Y coordinate of the Anchor1 with the current width and anchor2's position
	 */
	public void setAnchor1Y(){
		if (planeVariable == 0)
			this.anchor1.y = this.anchor2.y + height;
		else
			this.anchor1.y = this.anchor2.y - height;
	}
	
	
	
	/**
	 * Returns the position of the plane/the anchor2
	 * @return
	 */
	public DoublePoint getAnchor2(){
		return this.anchor2;
	}
	/**
	 * Sets the Anchor2 to the specified position
	 * @param anchor2
	 */
	public void setAnchor2(DoublePoint anchor2){
		this.anchor2 = anchor2;
		setFormula();
	}
	/**
	 * Sets the Anchor2 with the current width, height and Anchor2 position
	 */
	public void setAnchor2(){
		if (planeVariable == 1){
			this.anchor2.x = this.anchor1.x + width;
			this.anchor2.y = this.anchor1.y + height;
		}
		else {
			this.anchor2.x = this.anchor1.x + width;
			this.anchor2.y = this.anchor1.y - height;
		}
		setFormula();
	}
	
	/**
	 * Sets the X coordinate of Anchor2 with the current width and Anchor1's position
	 */
	public void setAnchor2X(){
		if (planeVariable == 1)
			this.anchor2.x = this.anchor1.x + width;
		else 
			this.anchor2.x = this.anchor1.x - width;
	}
	/**
	 * Sets the Y coordinate of Anchor2 with the current width and Anchor1's position
	 */
	public void setAnchor2Y(){
		if (planeVariable == 1)
			this.anchor2.y = this.anchor1.y + height;
		else 
			this.anchor2.y = this.anchor1.y - height;
		
	}
	
	/**
	 *  Getting used 
	 * -1 if not used,
	 * 0 if used by a Maison,
	 * and 1 if used by a rope.
	 * @return
	 */
	public int isUsed() {
		return isUsed;
	}
	
	/**
	 * Setting used 
	 * -1 if not used,
	 * 0 if used by a Maison,
	 * and 1 if used by a rope.
	 * @param isUsed
	 */
	public void setUsed(int isUsed) {
		this.isUsed = isUsed;
	}
	/**
	 * 
	 * @return
	 */
	public boolean isVisible() {
		return isVisible;
	}
	/**
	 * 
	 * @param isVisible
	 */
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
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
		this.setAnchor2();
		setFormula();
	}
	/**
	 * 
	 * @param position
	 */
	public void setDefaultPosition(DoublePoint position) {
		this.defaultPosition.x = position.x;
		this.defaultPosition.y = position.y;
		
	}
	/**
	 * 
	 * @return
	 */
	public DoublePoint getDefaultPosition() {
		
		return defaultPosition;
	}

	public int getMaisonNumber() {
		return maisonNumber;
	}

	public void setMaisonNumber(int maisonNumber) {
		this.maisonNumber = maisonNumber;
	}
	
	public BufferedImage getTexture(){
		return texture;
	}
	
	
	public void rotateImage(){
		AffineTransform tx = new AffineTransform();
		
	    tx.rotate(angle - Math.PI, texture.getWidth()/2, texture.getHeight()/2);

	    AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
	   	texture = op.filter(texture, null);
	   	//rotated = true;
	}



	public double getFrictionConstant() {
		return frictionConstant;
	}



	public void setFrictionConstant(double frictionConstant) {
		this.frictionConstant = frictionConstant;
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


}
