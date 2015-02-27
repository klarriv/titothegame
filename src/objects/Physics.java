package objects;

import javax.swing.Timer;

public abstract class Physics implements ObjectInterface {
	private final double GRAVITY = 9.8;//probably not....
	private int xi;
	private int yi;
	private double vxi;
	private double vyi;
	private Timer t;
	
	/** Constructs a new Physics object with specified position and velocity in x and y*/
	public Physics(int x, int  y, double vx, double vy, Timer t){
		this.xi = x;
		this.yi = y;
		this.vxi = vx;
		this.vyi = vy;
		this.t = t;
	}
	
	/**Calculates and returns the position of an object subject to acceleration*/
	public int projectileMotions(int weight, int position, double v, int delay){
		
	}
	
	/**Calculates and returns the position of an object in movement*/
	public int motion(int weight, int position, double v, int delay){
		
	}
	
	/**Calculates and returns the force on/of an object using Newton's second law*/
	public int force(int weight, double acceleration){
		
	}
	
	/**Calculates and returns the acceleration of an object using Newton's second law*/
	public double acceleration( int weight, int force){
		
	}
	
	/**Calculates and returns the force applied by the spring*/
	public int spring(double k, int dx){
		
	}
	
	/**Inverts the force*/
	public void pulley(){
		
	}

}
