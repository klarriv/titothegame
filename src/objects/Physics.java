package objects;

import java.awt.Point;

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
	private final double GRAVITY = 0.4;
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
		
		this.vyi = v;
		this.vyi -= 0.5 * GRAVITY *(delay/10);
		position -= v*(delay/10) - (0.5 * GRAVITY*((delay/10) * (delay/10)));
		//System.out.println(position);
		return position;
	}
	public Point frictionMotion(Point position, double vx, double vy, int delay){
		this.vyi = vy;
		this.vxi = vx;
		this.vxi += 0.5 * ax * (delay/10);
		this.vyi += 0.5 * ay* (delay/10);
		position.x -= vx*(delay/10) - (0.5 * ax*((delay/10) * (delay/10)));
		position.y += vy*(delay/10) - (0.5 * ay*((delay/10) * (delay/10)));
		System.out.println("vxi: " + vxi + " vyi: " + vyi);
		return position;
	}
	
	/**
	 * Calculates and returns the position of an object in movement
	 * @param position
	 * @param v
	 * @param delay
	 * @return
	 */
	public int motion(int position, double v, int delay){
		position += v * (delay/10);
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
		double f = (force * Math.cos(angle)) - ((-force * Math.sin(angle)) * u);
		this.a = f/ weight;
		
		this.ay = a * Math.cos(angle);
		this.ax = a * Math.sin(angle);
		
		System.out.println("a: " + this.ax);
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
