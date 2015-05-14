package objects;

import java.awt.image.BufferedImage;

import windows.MainFrame;
/**
 * The Rope object is used to attach TrashCans to Pulleys
 * @author CharlesPhilippe
 *
 */
public class Rope {
	/**
	 * The width of an unused rope
	 */
	public final static double WIDTH = 0.3;
	/**
	 * The height of an unused rope.
	 */
	public final static double HEIGHT = 0.15;
	/**
	 * Whether the rope is being dragged or not.
	 */
	public boolean isMoving;
	/**
	 * The position of the first attached object.
	 */
	private DoublePoint anchor1;
	/**
	 * The position of the pulley
	 */
	private DoublePoint anchor2;
	/**
	 * The position of the second attached object.
	 */
	private DoublePoint anchor3;
	/**
	 * The length of the rope
	 */
	private double length;
	/**
	 * The first attached object.
	 */
	private Physics ob1;
	/**
	 * The second object attached to the rope.
	 */
	private Physics ob2;
	/**
	 * The plane attached to the rope.
	 */
	private Plane plane;
	/**
	 * The pulley attached to the rope.
	 */
	private Pulley pulley;
	/**
	 * Whether the rope is stretched to its maximum.
	 */
	private boolean maxed = false;
	/**
	 * The force applied to the rope.
	 */
	private double force = 0;
	/**
	 * If the rope is broken.
	 */
	private boolean broken = false;
	/**
	 * The hit box of an unused rope.
	 */
	private DoubleRectangle r;
	/**
	 * The default position of the rope.
	 */
	private DoublePoint defaultPosition = new DoublePoint(1, 1);
	/**
	 * The BufferedImage of an unused rope.
	 */
	private BufferedImage texture;
	
	/**
	 * Creates an unused rope at specified position
	 * @param x
	 * @param y
	 */
	public Rope(double x, double y){
		this.anchor2 = new DoublePoint(x, y);
		setDefaultPosition(anchor2);
		setR(new DoubleRectangle(anchor2.x, anchor2.y, WIDTH, HEIGHT));
		
		texture = MainFrame.getTl().ropeTexture;
	}
	
	/**
	 * Constructs a new Rope attached to a Physics object and a pulley
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
	 * Constructs a new Rope attached to a pulley and an two physics object
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
	 * Creates a new rope with specified anchors 1 and 2
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
	 * Creates a new rope with specified anchors 1 and 2
	 * @param anchor1
	 * @param anchor2
	 */
	public Rope(DoublePoint anchor1, DoublePoint anchor2){
		
		this.anchor1 = anchor1;
		this.anchor2 = anchor2;
		
		setR(new DoubleRectangle(anchor2.x, anchor2.y, WIDTH, HEIGHT));
	}
	/**
	 * Sets the length of the rope when there are two objects attached to the pulley
	 */
	public void setLength3(){
		length = Math.sqrt(Math.pow(anchor1.x - anchor2.x, 2) + Math.pow(anchor1.y - anchor2.y, 2));
		length +=  Math.sqrt(Math.pow(anchor3.x - anchor2.x, 2) + Math.pow(anchor3.y - anchor2.y, 2));
		
	}
	/**
	 * Sets the length of the rope when there is only one object attached to the pulley
	 */
	public void setLength2(){
		
		length = Math.sqrt(Math.pow(anchor1.x - anchor2.x, 2) + Math.pow(anchor1.y - anchor2.y, 2));
	
	}
	
	/**
	 * This makes the objects move together without a puller
	 * @param x
	 * @param y
	 */
	@Deprecated
	public void move(double x, double y){
		double dx = x - anchor1.x;
		double dy = y - anchor1.y;
		double distance = Math.sqrt(Math.pow((anchor1.x + dx) - anchor2.x, 2.0) + Math.pow((anchor1.y + dy) - anchor2.y, 2.0));
		
		if (distance >= length){
			anchor2.x += dx;
			anchor2.y += dy;
		}
		
	}
	/**
	 * Makes the anchors move relatively to each other on a pulley.
	 * @param x
	 * @param y
	 */
	public void pulleyMove(double x, double y){
		
		if ((anchor1 != null) && anchor2 != null){
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
			
			if (isUsed() == 4)
				plane.mouvement();
		}
		
		
	}
	public boolean isMaxed(){
		return maxed;
	}
	/**
	 * Returns -2 if the rope is broken,
	 *  -1 if it is not used,
	 * 0 if it is attached to a pulley,
	 * 1 if it is attached to a pulley and a TrashCan,
	 * and 2 if it is attached to a pulley and two TrashCans
	 * @return The usage
	 */
	public int isUsed(){
		boolean a = ob1 == null;
		boolean b = pulley == null;
		boolean c = ob2 == null;
		boolean d = plane == null;
		
		if (broken)
			return -2;
		
		else if (a && !b && c && d)
			return 0;
		
		else if (!a && !b && c && d)
			return 1;
		
		else if (!a && !b && !c && d)
			return 2;
		else if (a && !d && !b && c)
			return 3;
		else if (!a && !d && !b && c)
			return 4;
		
		else 
			return -1;
			
	}
	
	/**
	 * Sets the total force applied on the rope.
	 * If the force is greater than 200, the rope breaks.
	 */
	public void setTotalForce(){
		
		if (isUsed() == 1 )
			force = ob1.getForce(ob1.getWeight());

		
		if (isUsed() == 2  )
			force = ob1.getForce(ob1.getWeight()) + ob2.getForce(ob2.getWeight());
		
		if (force >= 200){
			MainFrame.getTl().playSound(MainFrame.getTl().ropeSnap);
			if (ob1 != null)
				ob1.setUsed(false);
			if (ob2 != null)
				ob2.setUsed(false);
			ob1 = null;
			ob2 = null;
			broken = true;
			force = 0;
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
	
	public Plane getPlane() {
		return plane;
	}

	public void setPlane(Plane plane) {
		this.plane = plane;
		/**if (isUsed() == 0)
			this.anchor1 = plane.getAnchor1();
		else if(isUsed() == 1)*/
			this.anchor3 = plane.getAnchor1();
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
		if (u == 1 || u == 3)
			this.anchor1.x = anchor2.x;
		
		if(u==2 || u == 4){
			this.anchor1.x = anchor2.x;
			this.anchor3.x = anchor2.x;
		}
		if (u == 4)
			plane.setAnchor2();
		
		
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
	
	public void setX(double x){
		if (isUsed() == -1)
			anchor2.x = x;
		
	}
	public void setY(double y){
		if (isUsed() == -1)
			anchor2.y = y;
	}
	

	public void resetPosition(){
		this.anchor2.x = this.defaultPosition.x;
		this.anchor2.y = this.defaultPosition.y;
	}

	public void setDefaultPosition(DoublePoint position) {
		this.defaultPosition.x =this.anchor2.x;
		this.defaultPosition.y = this.anchor2.y;
		
	}

	public DoublePoint getDefaultPosition() {
		// TODO Auto-generated method stub
		return defaultPosition;
	}
	
	public BufferedImage getTexture(){
		return texture;
	}
	
	
	
}

	
