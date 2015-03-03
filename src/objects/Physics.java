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
	private double vxi;
	/**
	 * The initial velocity in y
	 */
	protected double vyi;
	/**
	 * Timer variable set in the constructor
	 */
	protected Timer t;
	
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
	public Point frictionMotion(int weight, Point position, double angle, double u, int delay){
		//weight should be replaced by GRAVITY * weight to get 
		//the force but since GRAVITY = 0.4 we will work with weight = force
		//and try to find something that works force Newton's law
		double fn = forceY(weight, angle);
		double fy = -fn;
		double fx = forceX(weight, angle);
		double ff = friction(fn, u);
		double a = acceleration(fx - ff, weight);//don't know how it's gonna work out since weight = force...
		
		
		//this.vyi -= 0.5 * GRAVITY *(delay/10);
		double ds = 0;
		ds -= vyi*(delay/10) - (0.5 * a*((delay/10) * (delay/10)));//I don't know why this is working better then
																	//Math.sqrt(Math.pow(vxi, 2.0) + Math.pow(vyi, 2.0))
		this.vxi -= a * Math.cos(angle);
		this.vyi -= a * Math.sin(angle);
		position.x += ds * Math.cos(angle);
		position.y += ds * Math.sin(angle);
		return position;
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
	public double acceleration( double force, double weight){
		return force/ weight;
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
