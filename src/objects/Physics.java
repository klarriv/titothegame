package objects;


import javax.swing.Timer;
/**
 * This class provides a skeletal implementation of ObjectInterface 
 * to create an object which reacts following physics laws.
 * @author CharlesPhilippe
 *
 */
public abstract class Physics implements ObjectInterface{
	
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
	 * The acceleration in x
	 */
	protected double ax;
	/**
	 * The acceleration in y
	 */
	protected double ay;
	/**
	 * The index of the plane the object is on
	 */
	private int plane = -1;
	/**
	 * The property of the plane
	 */
	private int planeVariable = -1;
	/**
	 * The velocity in x of the object
	 */
	public double vx;
	/**
	 * The velocity in y of the object
	 */
	public double vy;
	/**
	 * The weight of the object
	 */
	public int weight;
	/**
	 * The visibility of the object
	 */
	public boolean isVisible;
	/**
	 * The usage of the object
	 */
	public boolean isUsed;
	/**
	 * The current position of the object
	 */
	public DoublePoint position;
	/**
	 * Whether the object is being dragged or not
	 */
	public boolean isMoving;
	
	/**
	 * Constructs a new Physics object
	 */
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
	
	/**
	 * Calculates the position of an object on an incline plane
	 * @param position
	 * @param vx
	 * @param vy
	 * @param delayS
	 * @return
	 */
	public DoublePoint frictionMotion(DoublePoint position, double vx, double vy, int delayS){
		//System.out.println("vx:  " + vx +  " vy : " + vy);
		double delay = ((double)delayS/1000);
		this.vyi = vy;
		this.vxi = vx;
		this.vxi -=  ax * (delay);
		this.vyi +=  ay * (delay);
		position.x += vx*(delay) - (0.5 * ax*((delay) * (delay)));
		position.y -= vy*(delay) + (0.5 * ay*((delay) * (delay)));
		
		//System.out.println( ay + "  "+ ay * (delay)+"  "+vy*(delay) + (0.5 * ay*((delay) * (delay))));
		
		return position;
	}
	/**
	 * Calculates and sets the position of an object on a specified plane
	 * @param p
	 * @param ob1
	 * @param delay
	 */
	public void frictionMotion(Plane p, Physics ob1, double delay){
		ob1.setVy(0);
		double force = ob1.getWeight() * GRAVITY;
		double frictionF;
		if (p.getPlaneVariable() == 1)
			frictionF = force * Math.sin(p.getAngle()) + force* Math.cos(p.getAngle()) * p.getFrictionConstant();
		else
			frictionF = force * Math.sin(p.getAngle()) - force* Math.cos(p.getAngle()) * p.getFrictionConstant();
		double a = frictionF/ob1.getWeight();
		double ax = a * Math.abs(Math.sin(p.getAngle()));
		double ay = a * Math.abs(Math.cos(p.getAngle()));
		//TODO be sure it works every time
	
		ob1.setVx(ob1.getVx() + ax * delay);
		this.vyi = (ob1.getVy() + ay * delay);
		this.vxi = ob1.getVx();
		ob1.getPosition().x += ob1.getVx()*(delay) + (0.5 * ax*((delay) * (delay)));
		ob1.setY(p.getY(ob1.getPosition().x) - ob1.getHeight());
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
		return position;
	}
	
	/**
	 * Sets the velocity of an Object after the launch from a see-saw using kinetic Energy
	 * Given the velocity and the weight of the falling object (v, weight) and the mass of the accelerated object
	 * @param v
	 * @param weight
	 * @param mass
	 */
	public void setEnergyVelocity(double v, double weight, double mass){
		double ek = 0.5 * weight * v * v;
		double velocity = Math.sqrt((2* ek) / mass);
		
		this.vxi = velocity * Math.cos(Math.toRadians(45)); //setting always 45 degrees
		this.vyi = velocity * Math.cos(Math.toRadians(135));
	
	}
	/**
	 * Returns the force of an object using f = m*a
	 * @param mass
	 * @return force
	 */
	public double getForce(int mass){
		if (this.ax != 0 && this.ax > 0 || this.ax < 0)
			return this.ax * mass;
		else
			return GRAVITY * mass;
	}
	
	
	/**
	 * Calculates and returns the acceleration of an object using Newton's second law
	 * @param weight
	 * @param force
	 * @return The acceleration
	 */
	public double setAcceleration( double angle, double weight, double u){
		double force = (weight * GRAVITY);
		double f = Math.abs((force * Math.sin(angle)) + Math.abs(((force * Math.cos(angle)) * u)));
		//if ( f >= 0)
			this.a = f/ weight;
		//else
		//	this.a = 0;
	
		this.ay = -a * Math.sin(Math.PI - angle);
		this.ax = a * Math.cos(Math.PI - angle);
	
		return a;
	}
	

	
	/**
	 * Reflecting an object bouncing on an inclined plane
	 * @param angle
	 * @param vx
	 * @param vy
	 */
	public void matrixMultiplication(double angle, double vx, double vy){
		double[][] T = {{Math.cos(angle), -Math.sin(angle)},
						{Math.sin(angle), Math.cos(angle)}};

		double[][] T2 ={{Math.cos(angle), Math.sin(angle)},
						{-Math.sin(angle),Math.cos(angle)}};
		
		if (vx >= 0){
			if (vx > 0.5)
				vx -= 0.5;
			if (vy > 0.5)
				vy -= 0.5;
			else if (vy < -0.5)
				vy += 0.5;
			
			this.vxi = T[0][0] * vx;
			this.vxi += T[1][0] * vy;
	
			this.vyi = T[0][1] * vx;
			this.vyi += T[1][1] * vy;

		}
		else{
			if (vx < -0.5)
				vx += 0.5;
			if (vy > 0.5)
				vy -= 0.5;
			else if (vy < -0.5)
				vy += 0.5;
			
			this.vxi = T2[0][0] * vx;
			this.vxi += T2[1][0] * vy;
			
			this.vyi = T2[0][1] * vx;
			this.vyi += T2[1][1] * vy;
			
		}
		
	}
	
	public int getPlane() {
		return plane;
	}
	
	public void setPlane(int plane) {
		this.plane = plane;
	}
	
	public void setPlaneVariable(int planeVariable){
		this.planeVariable = planeVariable;
	}
	
	public int getPlaneVariable(){
		return this.planeVariable;
	}

}
