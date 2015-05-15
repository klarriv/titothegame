package objects;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.swing.Timer;

import windows.MainFrame;
import RunningClasses.SpriteSheet;
/**
 * The TrashCan is a physics object used to make Tito escape.
 * @author CharlesPhilippe
 *
 */
public class TrashCan extends Physics {
	/**
	 * The width of the trash can with no angle
	 */
	public static final double WIDTHFLAT = 0.30;
	/**
	 * The height of the trashCan width no angle
	 */
	public static final double HEIGHTFLAT = 0.40;
	/**
	 * The width of the trash can at an angle
	 */
	public static final double WIDTHATANGLE = 0.4;
	/**
	 * The height of the trash can at an angle
	 */
	public static final double HEIGHTATANGLE = 0.5;
	/**
	 * The weight of the trash can
	 */
	private int weight = 10;
	/**
	 * The visibility of the trash can
	 */
	private boolean isVisible = true;
	/**
	 * The usage of the trash can
	 */
	private boolean isUsed = false;
	/**
	 * The current image of the trash can
	 */
	private BufferedImage texture;
	/**
	 * The sprite sheet of the trash can
	 */
	private BufferedImage spriteSheet;
	/**
	 * The spriteSheet object of the trash can
	 */
	private SpriteSheet ss = new SpriteSheet();
	/**
	 * The current position of the trash can
	 */
	private DoublePoint position;
	/**
	 * The current velocity in x of the trash can
	 */
	private double vx = 7;
	/**
	 * The current velocity in y of the trash can
	 */
	private double vy = 10;
	/**
	 * Changes to 1 if it hit a see saw
	 */
	public int single = 0;
	/**
	 * The hit box of the trash can
	 */
	private DoubleRectangle r;
	/**
	 * Whether the trash can is rotated or not 
	 */
	public boolean rotated = false;
	/**
	 * The default position of the trash can
	 */
	private DoublePoint defaultPosition  = new DoublePoint(1, 1);
	
	//public static final double threshold = 300; da fuck is dis
	/**
	 * The width of the trash can
	 */
	private double width;
	/**
	 * The height of the trash can
	 */
	private double height;
	
	
	/**
	 * Creates a new trashcan at the specified position
	 * @param x
	 * @param y
	 */
	public TrashCan(double x, double y){
		
		position = new DoublePoint(x, y);
		setDefaultPosition(position);
		this.vx = 0;
		this.vy = 0;
		this.width = WIDTHFLAT;
		this.height = HEIGHTFLAT;
		spriteSheet = MainFrame.getTl().trashCanTexture;
		setR(new DoubleRectangle(position, width, height));
	}

	/**
	 * This creates a new instance of TrashCan
	 * @param x
	 * @param y
	 * @param vx
	 * @param vy
	 * @param t
	 */
	public TrashCan(double x, double y, double vx, double vy, Timer t) {
		super(x, y, vx, vy, t);
		
		this.position.x = x;
		this.position.y = y;
		setDefaultPosition(position);
		this.vx = vx;
		this.vy = vy;
		this.width = WIDTHFLAT;
		this.height = HEIGHTFLAT;
		this.t = t;
		texture = MainFrame.getTl().trashCanTexture;
		setR(new DoubleRectangle(position, width, height));
	}
	/**
	 * Should use the sprite sheet technique.
	 * Rotates the image with the specified angle
	 * @param angle
	 */
	@Deprecated
	public void rotate(double angle){
		AffineTransform tx = new AffineTransform();
		
	    tx.rotate(angle - Math.PI);

	    AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
	   	texture = op.filter(texture, null);
	   	rotated = true;
	}
	@Override
	public int getWeight() {
		return weight;
	}

	@Override
	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public boolean isVisible() {
		return isVisible;
	}

	@Override
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	@Override
	public boolean isUsed() {
		
		return isUsed;
	}

	@Override
	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

	@Override
	public DoublePoint getPosition() {
		return position;
	}

	@Override
	public void setPosition(DoublePoint position) {
		this.position = position;
	}

	@Override
	public double getVx() {
		return vx;
	}
	@Override
	public void setVx(){
		this.vx = this.vxi;
	}

	@Override
	public void setVx(double vx) {
		this.vx = vx;
	}

	@Override
	public double getVy() {
		return vy;
	}
	@Override
	public void setVy(){
		this.vy = this.vyi;
	}

	@Override
	public void setVy(double vy) {
		this.vy = vy;
	}


	@Override
	public void setY(double y) {
		this.position.y = y;
		
	}


	@Override
	public void setX(double x) {
		this.position.x = x;
		
	}

	@Override 
	public BufferedImage getTexture() {
		 this.ss.setSpriteSheet(spriteSheet);
		
		if (super.getPlaneVariable() == -1){
			this.width = WIDTHFLAT;
			this.height = HEIGHTFLAT;
			this.r.setWidth(WIDTHFLAT);
			this.r.setHeight(HEIGHTFLAT);
			texture = ss.grabSprite(0, 0, 389, 489);
		}
		else if(super.getPlaneVariable() == 0 || super.getPlaneVariable() == 1){
			this.width = WIDTHATANGLE;
			this.height = HEIGHTATANGLE;
			this.r.setWidth(WIDTHATANGLE);
			this.r.setHeight(HEIGHTATANGLE);
			texture = ss.grabSprite(super.getPlaneVariable() * 530, 600, 530, 600);
			
		}
		else{
			this.width = WIDTHFLAT;
			this.height = HEIGHTFLAT;
			this.r.setWidth(WIDTHFLAT);
			this.r.setHeight(HEIGHTFLAT);
			texture = ss.grabSprite(0, 0, 389, 489);
		}
		
		return texture;
	}
	
	

	public DoubleRectangle getR() {
		return r;
	}

	public void setR(DoubleRectangle r) {
		this.r = r;
	}

	@Override
	public double getHeight() {
		
		return this.height;
	}
	
	@Override
	public double getWidth() {
		
		return this.width;
	}

	/**
	 * Resets the position to the default position.
	 */
	@Override
	public void resetPosition(){
		this.position.x = this.defaultPosition.x;
		this.position.y = this.defaultPosition.y;
	}

	/**
	 * Generates the default position from the initial position of the bench
	 */
	@Override
	public void setDefaultPosition(DoublePoint position) {
		this.defaultPosition.x =this.position.x;
		this.defaultPosition.y = this.position.y;
		
	}

	@Override
	public DoublePoint getDefaultPosition() {
		return defaultPosition;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	

	


	
	
	

}
