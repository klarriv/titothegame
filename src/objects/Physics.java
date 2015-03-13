package objects;
import java.awt.Point;

import javax.swing.Timer;

import RunningClasses.PlaneTestPanel;
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
	private final double GRAVITY = 9.8;
	/**
	 * The initial position in x on the screen of the object
	 */
	private double xi;
	/**
	 * The initial position in y on the screen of the object
	 */
	private double yi;
	/**
	 * The initial velocity in x
	 */
	protected double vxi;
	/**
	 * The initial velocity in y
	 */
	protected double vyi;
	/**
	 * Timer variable set in the constructor
	 */
	protected Timer t;
	/**
	 * The acceleration of the object on an incline plane
	 */
	protected double a = 0;
	/**
	 * 
	 */
	protected double ax = 0;
	/**
	 * 
	 */
	protected double ay = 0;
	
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
	public Physics(double x, double  y, double vx, double vy, Timer t){
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
	public double projectileMotions(int weight, double position, double v, int delayS){
		double delay = ((double)delayS/1000);
		this.vyi = v;
		this.vyi -= GRAVITY *(delay);
		position -= v*(delay) - (0.5 * GRAVITY*((delay) * (delay)));
		//System.out.println(position);
		return position;
	}
	public DoublePoint frictionMotion(DoublePoint position, double vx, double vy, int delayS){
		double delay = ((double)delayS/1000);
		this.vyi = vy;
		this.vxi = vx;
		this.vxi +=  ax * (delay);
		this.vyi +=  ay * (delay);
		position.x += vx*(delay) - (0.5 * ax*((delay) * (delay)));
		position.y += vy*(delay) - (0.5 * ay*((delay) * (delay)));
		
		System.out.println("x: " + position.x + " y: " + position.y);
		//System.out.println("vx:  " + vx +  " vy : " + vy);
		return position;
	}
	
	/**
	 * Calculates and returns the position of an object in movement
	 * @param position
	 * @param v
	 * @param delay
	 * @return
	 */
	public double motion(double position, double v, int delay){
		position += v * ((double)delay/1000);
		//System.out.println("x: " + position + " v: " +  v*((double)delay/1000));
		return position;
	}
	
	/**
	 * Calculates and returns the force in x on/of an object using geometry
	 * @param weight
	 * @param a
	 * @return
	 */
	public double forceX(int force, double a){
		
		return (force * GRAVITY) * Math.cos(a);
	}
	/**
	 * Calculates and returns the force in y on/of an object using Newton's second law
	 * @param weight
	 * @param a
	 * @return
	 */
	public double forceY(int force, double a){
		
		return (force * GRAVITY) * Math.sin(a);
	}
	/**
	 * Calculates the friction force of an object with a constant u and a normal force n
	 * @param n
	 * @param u
	 * @return
	 */
	public double friction(double n, double u){
		return (n * u);
	}
	
	/**
	 * Calculates and returns the acceleration of an object using Newton's second law
	 * @param weight
	 * @param force
	 * @return
	 */
	public double setAcceleration( double angle, double weight, double u){
		double force = (weight * GRAVITY);
		double f = (force * Math.sin(angle)) - ((force * Math.cos(angle)) * u);
		if ( f >= 0)
			this.a = f/ weight;
		else
			this.a = 0;
		
		this.ay = a * Math.sin(angle);
		this.ax = a * Math.cos(angle);
		
		System.out.println("ax: " + this.ax + " ay: " + this.ay);
		System.out.println("force: "+ force + " friction: " + f + " a "   + a);
		return a;
	}
	
	/**
	 * Calculates and returns the force applied by the spring
	 * @param k
	 * @param dx
	 * @return
	 */
	public int spring(double k, int dx){
		return 0;
	}
	
	/**Inverts the force*/
	public void pulley(){
		
	}

}
