package objects;

import javax.swing.Timer;
/**
 * This class provides a skeletal implementation of ObjectInterface 
 * to create an object which reacts following physics laws.
 * @author CharlesPhilippe
 *
 */
public abstract class Physics implements ObjectInterface {
	
	/**
	 * The gravity constant
	 */
	private final double GRAVITY = 9.8;//probably not....
	/**
	 * The initial position in x on the screen of the object
	 */
	private int xi;
	/**
	 * The initial position in y on the screen of the object
	 */
	private int yi;
	/**
	 * The initial velocity in x
	 */
	private double vxi;
	/**
	 * The initial velocity in y
	 */
	private double vyi;
	/**
	 * Timer variable set in the constructor
	 */
	private Timer t;
	
	public Physics(){
		
	}
	
	/**
	 * Constructs a new Physics object with specified position and velocity in x and y
	 * @param x
	 * @param y
	 * @param vx
	 * @param vy
	 * @param t
	 */
	public Physics(int x, int  y, double vx, double vy, Timer t){
		this.xi = x;
		this.yi = y;
		this.vxi = vx;
		this.vyi = vy;
		this.t = t;
	}
	
	/**
	 * Calculates and returns the position of an object subject to acceleration
	 * @param weight
	 * @param position
	 * @param v
	 * @param delay
	 * @return
	 */
	public int projectileMotions(int weight, int position, double v, int delay){
		
	}
	
	/**
	 * Calculates and returns the position of an object in movement
	 * @param weight
	 * @param position
	 * @param v
	 * @param delay
	 * @return
	 */
	public int motion(int weight, int position, double v, int delay){
		
	}
	
	/**
	 * Calculates and returns the force on/of an object using Newton's second law
	 * @param weight
	 * @param acceleration
	 * @return
	 */
	public int force(int weight, double acceleration){
		
	}
	
	/**
	 * Calculates and returns the acceleration of an object using Newton's second law
	 * @param weight
	 * @param force
	 * @return
	 */
	public double acceleration( int weight, int force){
		
	}
	
	/**
	 * Calculates and returns the force applied by the spring
	 * @param k
	 * @param dx
	 * @return
	 */
	public int spring(double k, int dx){
		
	}
	
	/**Inverts the force*/
	public void pulley(){
		
	}

}
