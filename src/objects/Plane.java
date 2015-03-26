package objects;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Plane extends Physics{
	private DoublePoint dp = new DoublePoint(0,0);
	private int[] x;
	private int[] y;
	private double angle;
	private double a;
	private double b = 1;
	private double c;
	
	public Plane(double angle, double x, double y){
		this.angle = angle;
		this.dp.x = x;
		this.dp.y = y;
		setFormula();
	}
	
	public Plane(int[] x, int[] y){
		
	}
	
	public Plane(double a, double b, double c, DoublePoint dp){
		
	}
	/**
	 * Sets the slope in the form ax + by + c = 0
	 */
	public void setFormula(){
		this.a = Math.tan(angle);
		
		c = (b*dp.y)-(a*dp.x);
		/**System.out.println(a + "x" + " + " + b+ "y" + " + " + c +" = 0");
		System.out.println(dp.x + " " + dp.y);
		DoublePoint p = new DoublePoint(4, 1);
		System.out.println("d: " + pointDistance(p) );*/
		
		angleOfContact(5, 5);
		
	}
	
	/**
	 * Returns the distance between a point and a plane
	 * @param point
	 * @return
	 */
	public double pointDistance(DoublePoint point){
		//System.out.println(point.x + " " + point.y);
		//System.out.println("c: " + c);
		return Math.abs(-a * point.x + b * point.y - c)/ Math.sqrt((a * a) + (b * b));
		
	}
	
	public double angleOfContact(double vx, double vy){
		double t;
		double o = Math.PI -angle;
		//System.out.println(Math.toDegrees(t));
		if (vx < 0){
			t = Math.atan(vx/vy);
			//System.out.println("a " + t);
			t += (Math.PI/2);
			//System.out.println("b " + t);
			t -= o;
			//System.out.println("c " + t);
		}
		else{
			t = Math.atan(vy/vx);
			t +=  o;
		}
		//System.out.println(Math.toDegrees(t));
		return t;
	}
	
	public double getY(double x){
		return a*x + c;
	}
	
	
	
	
	public DoublePoint getDp() {
		return dp;
	}

	public void setDp(DoublePoint dp) {
		this.dp = dp;
	}

	public int[] getX() {
		return x;
	}

	public void setX(int[] x) {
		this.x = x;
	}

	public int[] getY() {
		return y;
	}

	public void setY(int[] y) {
		this.y = y;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public double getA() {
		return a;
	}

	public void setA(double a) {
		this.a = a;
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

	/**
	 * Moves the object
	 */
	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setWeight(int weight) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getWeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setVisible(boolean visible) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getVisible() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setUsed(boolean used) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getUsed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BufferedImage loadImage() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPosition(DoublePoint position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DoublePoint getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setVx(double vx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getVx() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setVy(double vy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getVy() {
		// TODO Auto-generated method stub
		return 0;
	}

}
