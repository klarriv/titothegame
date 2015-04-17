package objects;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Rope {
	public final static double WIDTH = 0.15;
	public final static double HEIGHT = 0.15;
	public boolean isMoving;
	private DoublePoint anchor1;
	private DoublePoint anchor2;
	private DoublePoint anchor3;
	private double length;
	private Physics ob1;
	private Physics ob2;
	private Pulley pulley;
	private boolean maxed = false;
	private double force = 0;
	private boolean broken = false;
	private DoubleRectangle r;
	
	
	
	public Rope(double x, double y){
		this.anchor2 = new DoublePoint(x, y);
		System.out.println(x);
		System.out.println(y);
		setR(new DoubleRectangle(anchor2.x, anchor2.y, WIDTH, HEIGHT));
	}
	
	/**
	 * Constructs a new Rope attached to two Physics objects
	 * @param ob1
	 * @param pulley
	 */
	public Rope(Physics ob1, Pulley pulley){
		this.ob1 = ob1;
		this.pulley = pulley;
		
		this.anchor1 = ob1.getPosition();
		this.anchor2 = pulley.getPosition();
		
		setR(new DoubleRectangle(anchor2.x, anchor2.y, WIDTH, HEIGHT));
		
		setLength2();
	}
	
	/**
	 * Constructs a new Rope attached to a pulley and an object
	 * @param ob1
	 * @param pulley
	 * @param ob2
	 */
	public Rope(Physics ob1, Pulley pulley, Physics ob2){
		this.ob1 = ob1;
		this.pulley = pulley;
		if (ob2 != ob1)
			this.ob2 = ob2;
		
		this.anchor1 = ob1.getPosition();
		this.anchor2 = pulley.getPosition();
		this.anchor3 = ob2.getPosition();
		
		setR(new DoubleRectangle(anchor2.x, anchor2.y, WIDTH, HEIGHT));
		
		setLength3();
	}
	
	/**
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public Rope(double x1, double y1, double x2, double y2){
		
		anchor1.x = x1;
		anchor1.y = y1;
		anchor2.x = x2;
		anchor2.y = y2;
		
		setR(new DoubleRectangle(anchor2.x, anchor2.y, WIDTH, HEIGHT));
		
		setLength2();

	}
	
	/**
	 * 
	 * @param anchor1
	 * @param anchor2
	 */
	public Rope(DoublePoint anchor1, DoublePoint anchor2){
		
		this.anchor1 = anchor1;
		this.anchor2 = anchor2;
		
		setR(new DoubleRectangle(anchor2.x, anchor2.y, WIDTH, HEIGHT));
	}
	
	public void setLength3(){
		length = Math.sqrt(Math.pow(anchor1.x - anchor2.x, 2) + Math.pow(anchor1.y - anchor2.y, 2));
		length +=  Math.sqrt(Math.pow(anchor3.x - anchor2.x, 2) + Math.pow(anchor3.y - anchor2.y, 2));
		System.out.println(length);
	}
	
	public void setLength2(){
		
		length = Math.sqrt(Math.pow(anchor1.x - anchor2.x, 2) + Math.pow(anchor1.y - anchor2.y, 2));
		//System.out.println(anchor1.x - anchor2.x);
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public void move(double x, double y){
		double dx = x - anchor1.x;
		double dy = y - anchor1.y;
		double distance = Math.sqrt(Math.pow((anchor1.x + dx) - anchor2.x, 2.0) + Math.pow((anchor1.y + dy) - anchor2.y, 2.0));
		
		if (distance >= length){
			anchor2.x += dx;
			anchor2.y += dy;
		}
		//System.out.println(" d: " + distance + " l: " + length);
	}
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public void pulleyMove(double x, double y){
		
		if (isUsed() == 2){
			double dx = x - anchor1.x;
			double dy = y - anchor1.y;
			double distance = Math.sqrt(Math.pow((anchor1.x + dx) - anchor2.x, 2.0) + Math.pow((anchor1.y + dy) - anchor2.y, 2.0));
			double distance2 = length - distance;
			
			if (distance <= length && distance2 >= 0)
				maxed = false;
			else 
				maxed = true;
			
			if (!maxed)
				anchor3.y = anchor2.y + distance2;
		}
		
		
	}
	public boolean isMaxed(){
		return maxed;
	}
	/**
	 * Returns -2 if the rope broke,
	 *  -1 if it is not used,
	 * 0 if it is attached to a pulley,
	 * 1 if it is attached to a pulley and a TrashCan,
	 * and 2 if it is attached to a pulley and two TrashCans
	 * @return
	 */
	public int isUsed(){
		boolean a = ob1 == null;
		boolean b = pulley == null;
		boolean c = ob2 == null;
		
		if (broken)
			return -2;
		
		else if (a && !b && c)
			return 0;
		
		else if (!a && !b && c)
			return 1;
		
		else if (!a && !b && !c)
			return 2;
		
		else 
			return -1;
			
	}
	/**
	 * Sets the total force applied on the rope
	 */
	public void setTotalForce(){
		
		if (isUsed() > 0 )
			force = ob1.getForce(ob1.getWeight());
		
		if (isUsed() > 1  )
			force = ob1.getForce(ob1.getWeight()) + ob2.getForce(ob2.getWeight());
		
		if (force >= 300){
			if (ob1 != null)
				ob1.setUsed(false);
			if (ob2 != null)
				ob2.setUsed(false);
			ob1 = null;
			ob2 = null;
			broken = true;
		}
		else
			broken = false;
		
			
	}

	public DoublePoint getAnchor1() {
		return anchor1;
	}

	public void setAnchor1(DoublePoint anchor1) {
		this.anchor1 = anchor1;
	}

	public DoublePoint getAnchor2() {
		return anchor2;
	}

	public void setAnchor2(DoublePoint anchor2) {
		this.anchor2 = anchor2;
	}

	public DoublePoint getAnchor3() {
		// TODO Auto-generated method stub
		return anchor3;
	}
	
	public void setAnchor3(DoublePoint anchor3){
		this.anchor3 = anchor3;
		
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public Physics getOb1() {
		return ob1;
	}

	public void setOb1(Physics ob1) {
		this.ob1 = ob1;
		this.anchor1 = ob1.getPosition();
	}

	public Physics getOb2() {
		return ob2;
	}

	public void setOb2(Physics ob2) {
		if (ob2 != ob1){
			this.ob2 = ob2;
			this.anchor3 = ob2.getPosition();
		}
	}

	public Pulley getPulley() {
		return pulley;
	}

	public void setPulley(Pulley pulley) {
		this.pulley = pulley;
		this.anchor2 = pulley.getPosition();
	}

	public void setMaxed(boolean maxed) {
		this.maxed = maxed;
	}
	
	public void setXAnchored(){
		int u = isUsed();
		if (u == 1)
			this.anchor1.x = anchor2.x;
		
		if(u==2){
			this.anchor1.x = anchor2.x;
			this.anchor3.x = anchor2.x;
		}
	}

	public boolean isBroken() {
		return broken;
	}

	public void setBroken(boolean broken) {
		this.broken = broken;
	}

	public DoubleRectangle getR() {
		return r;
	}

	public void setR(DoubleRectangle r) {
		this.r = r;
	}
	
	
}

	
