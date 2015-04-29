package RunningClasses;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import objects.Bench;
import objects.Cone;
import objects.DoublePoint;
import objects.Jason;
import objects.Maison;
import objects.Physics;
import objects.Plane;
import objects.Pulley;
import objects.Rope;
import objects.SeeSaw;
import objects.Tito;
import objects.TrashCan;
import objects.Tree;

@SuppressWarnings("serial")
public class EngineTestPanel extends JPanel{
	/**
	 * This holds the object that represents Tito in the game.
	 */
	private Tito tito;
	/**
	 * This creates the nemesis: Jason Gerard the zookeeper
	 */
	private Jason jason;
	/**
	 * This is a variable that holds the level number
	 */
	public int levelNumber;
	/**
	 * The timer used to repaint and used for the delay in the physics formulas
	 */
	public Timer t;
	/**
	 * List of trees read from the level file.
	 */
	private ArrayList<Tree> treeList = new ArrayList<Tree>();
	/**
	 * List of the benches read from the level file.
	 */
	private ArrayList<Bench> benchList = new ArrayList<Bench>();
	/**
	 * List of cones read from the level file.
	 */
	private ArrayList<Cone> coneList = new ArrayList<Cone>();
	/**
	 * List of plane read from the level file.
	 */
	private ArrayList<Plane> planeList = new ArrayList<Plane>();
	/**
	 * List of rope read from the level file.
	 */
	private ArrayList<Rope> ropeList = new ArrayList<Rope>();
	/**
	 * List of seesaw read from the level file.
	 */
	private ArrayList<SeeSaw> seesawList = new ArrayList<SeeSaw>();
	/**
	 * List of trashcans read from the level file.
	 */
	private ArrayList<TrashCan> trashCanList = new ArrayList<TrashCan>();
	/**
	 * List of pulleys read from the level file
	 */
	private ArrayList<Pulley> pulleyList = new ArrayList<Pulley>();
	/**
	 * List of houses in the level read from the level file
	 */
	private ArrayList<Maison> maisonList = new ArrayList<Maison>();
	
	
	public void engine(){
		
	}
	
	
	
	/*
	 *  Physics moving and colliding methods
	 */
	
	/**
	 * Makes Tito bounce on a plane according to the angle relative to the plane
	 * @param plane
	 */
	public void planeCollision(Plane plane) {

		double angle = plane.angleOfContact(tito.getVx(), tito.getVy());

		if (tito.getVx() >= 0)
			tito.matrixMultiplication(angle * 2, tito.getVx(), tito.getVy());
		else
			tito.matrixMultiplication(angle * 2, tito.getVx(), tito.getVy());

		tito.setVx();
		tito.setVy();

	}
	
	
	//TODO Check whether this can be improved
	/**
	 * Determines if Tito is colliding with a plane
	 * @param plane
	 * @return
	 */
	public boolean planeColliding(Plane plane) {
		double r = 40.0 / gUnit;
		
		DoublePoint dp = new DoublePoint(tito.getPosition().x + r, tito.getPosition().y + r);
		
		double d = plane.pointDistance(dp);
		
		if (plane.getWidth() > 0) {
			if (d <= r && (dp.x) <= plane.getAnchor2().x && dp.x >= plane.getAnchor1().x)
				return true;
		} 
		else if (plane.getWidth() < 0) {
			if (d <= r && dp.x > plane.getAnchor2().x && dp.x < plane.getAnchor1().x)
				return true;
		}
		return false;

	}
	
	/**
	 * Makes an object move with no friction and collide with the frame
	 */
	public void xMove() {
		double x = tito.motion(tito.getPosition().x, -tito.getVx(), t.getDelay());

		if (x <= 5 && x >= 0)
			tito.setX(x);
		
		else if (Math.abs(0 - x) <= 0.1) {
			tito.setX(0);
			
		} 
		else if (Math.abs(5 - x) <= 0.1) {
			tito.setX(5);
		} 
		else
			tito.setVx(-1 * tito.getVx());
	}
	
	/**
	 * Projectile motion of an object falling and hitting the ground
	 * @param ob1
	 */
	public void projectileMotion(Physics ob1) {
		double y = ob1.projectileMotions(ob1.getWeight(), ob1.getPosition().y, ob1.getVy(), t.getDelay());
		
		if (ob1.getVy() < 0 && y >= 2.5 - ob1.getHeight()) {

			ob1.setY(2.5 - ob1.getHeight());
			ob1.setVy(-1 * ob1.getVy() - 1.5);
			// //(loader.getPosition().y);
		}

		else if (y <= 2.5 - ob1.getHeight()) {
			ob1.setY(y);
			ob1.setVy();
		}
	}
	
	
	/**
	 * Puts the trashcan on the plane if it is near enough
	 * @param ob1
	 * @param p
	 */
	public void planeContact(TrashCan ob1, Plane p){
		double x = ob1.getPosition().x;
		double ty = p.getY(x);
		
		double height = ob1.getHeight();
		
		if (isOnPlane(ob1, p)){
			ob1.setUsed(true);
			ob1.setY(ty - height);
			ob1.setPlaneVariable(p.getPlaneVariable());
			setAcceleration(ob1, p);
		}
		else{
			ob1.setUsed(false);
			ob1.setPlaneVariable(-1);
			ob1.setAcceleration(0, 0, 0);
		}
	}
		
	/**
	 * Determines whether an object is on the plane or not
	 * @param ob1
	 * @param p
	 * @return
	 */
	public boolean isOnPlane(Physics ob1, Plane p){
		double x = ob1.getPosition().x;
		double y = ob1.getPosition().y;
		double tx1 = p.getAnchor1().x;
		double tx2 = p.getAnchor2().x;
		double ty = p.getY(x);
		
		//TODO sss
		double width = Math.abs((ob1.getWidth()/2.0) * Math.cos(p.getAngle()));
		
		if (y < ty && (x + width) > (tx1) && (x + width) < (tx2) && p.pointDistance(ob1.getPosition()) < 1)
			return true;
		else
			return false;
	}
	
	/**
	   * Sets the acceleration of an object on a plane
	   * @param ob1
	   * @param p
	   */
	  public void setAcceleration(Physics ob1, Plane p){
		  ob1.setAcceleration(p.getAngle(), ob1.getWeight(), 0.5); 
	  }
}
