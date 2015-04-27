package RunningClasses;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import objects.DoublePoint;
import objects.Physics;
import objects.Plane;
import objects.Pulley;
import objects.Rope;
import objects.Tito;
import objects.TrashCan;

public class PlaneIncliningTestPanel extends JPanel{
	private double gUnit;
	private Timer t;
	private Tito tito = new Tito(1.8, 1.7, 0, 0, t);
	private Plane plane = new Plane(1.6, 2, Math.toRadians(0), 1, -1);
	private TrashCan trash = new TrashCan(1, 1.5);
	private Pulley pulley = new Pulley(2, 0.1, true);
	private Rope rope = new Rope(3,2);
	private Button place = new Button("Place");
	private Button start = new Button("Start");
	
	public PlaneIncliningTestPanel(){
		setLayout(new BorderLayout());
		addMouseMotionListener( new MouseDrag());
		
		
		t = new Timer(1000/24, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (planeColliding(plane)){
					
					tito.frictionMotion(plane, tito, (double)t.getDelay()/1000);
				}
					
				
				
				
				
				rope.setXAnchored();
				
				//projectile motion of a trashcan attached to a pulley and another trashcan
				if (rope.isUsed() == 2 || rope.isUsed() == 4 && rope.getOb1().getPosition().y <= 2) {
					double y = rope.getOb1().projectileMotions(rope.getOb1().getWeight(), rope.getOb1().getPosition().y, rope.getOb1().getVy(), t.getDelay());

					if (!rope.isMaxed() && t.isRunning()) {
						rope.getOb1().setY(y);
						rope.getOb1().setVy();

						rope.pulleyMove(rope.getOb1().getPosition().x, y);
					}

				}
				rope.setTotalForce();
				repaint();
			}
				
			
		});
		
		
		
		
		
		
		
		
		start.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				t.start();
				
			}
			
		});
		
		
		
		
		
		place.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				rope.setPulley(pulley);
				rope.setOb1(trash);
				trash.setUsed(true);
				rope.setXAnchored();
				
				rope.setPlane(plane);
				rope.setXAnchored();
				rope.setLength3();
				//System.out.println(rope.getPlane().getAnchor1().x + " " + rope.isUsed());
				repaint();
				
			}
			
		});
		
		add(place, BorderLayout.NORTH);
		add(start, BorderLayout.SOUTH);
	}
	/**
	 * Determines if Tito is colliding with a plane
	 * @param plane
	 * @return
	 */
	public boolean planeColliding(Plane plane) {
		double r = 40.0 / gUnit;
		DoublePoint dp = new DoublePoint(tito.getPosition().x + r, tito.getPosition().y + r);
		double d = plane.pointDistance(dp);
		if (plane.getPlaneVariable() == 0) {
			if (d <= r && (dp.x) <= plane.getAnchor2().x && dp.x >= plane.getAnchor1().x)
				return true;
		} else if (plane.getPlaneVariable() == 1) {
			if (d <= r && dp.x > plane.getAnchor2().x && dp.x < plane.getAnchor1().x)
				return true;
		}
		return false;

	}
	/**
	 * Makes the object move with friction
	 * @param ob1
	 */
	 public void frictionMove(Physics ob1){
		 ob1.frictionMotion(ob1.getPosition(),ob1.getVx(), ob1.getVy(), t.getDelay());
		 ob1.setVy();
		 ob1.setVx();
		 
		 }
	  /**
	   * Sets the acceleration of an object on a plane
	   * @param ob1
	   * @param p
	   */
	  public void setAcceleration(Physics ob1, Plane p){
		  ob1.setAcceleration(p.getAngle(), ob1.getWeight(), 0.5); 
	  }
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		gUnit = getWidth()/5;
		g.drawImage(trash.getTexture(),(int)(gUnit* trash.getPosition().x), (int)(gUnit* trash.getPosition().y), 100, 100, null);
	
		if (rope.isUsed() == -1){
			g.fillRect((int)(gUnit*rope.getAnchor2().x), (int)(gUnit*rope.getAnchor2().y), 20, 20);
		}
		else if (rope.isUsed() == 1 || rope.isUsed() == 3){
			int[] xPoints = {(int)(gUnit*rope.getAnchor1().x) + 50, (int)(gUnit*rope.getAnchor2().x) + 50};
			int[] yPoints = {(int)(gUnit*rope.getAnchor1().y), (int)(gUnit*rope.getAnchor2().y)};
			g.drawPolyline(xPoints, yPoints, 2);
		}
		else if (rope.isUsed() == 2 || rope.isUsed() == 4){
			int[] xPoints = {(int)(gUnit*rope.getAnchor1().x) + 50, (int)(gUnit*rope.getAnchor2().x) + 50, (int)(gUnit*rope.getAnchor3().x) + 50};
			if (rope.isUsed() == 4){
				plane.getPosition().x += (50/gUnit);
				plane.setAnchor2X();
				xPoints[2] = (int)(gUnit * rope.getAnchor3().x);
			}
			
			int[] yPoints = {(int)(gUnit*rope.getAnchor1().y), (int)(gUnit*rope.getAnchor2().y), (int)(gUnit*rope.getAnchor3().y)};
			g.drawPolyline(xPoints, yPoints, 3);
		}
		else if (rope.isUsed() == -2){
			g.drawLine((int)(gUnit*rope.getAnchor2().x) + 50, (int)(gUnit*rope.getAnchor2().y), (int)(gUnit*rope.getAnchor2().x) + 50 , (int)(gUnit*rope.getAnchor2().y) + 75);
			
		}
		if(pulley.isVisible())
			g.drawImage(pulley.getTexture(), (int)(gUnit*pulley.getPosition().x) + 15 , (int)(gUnit*pulley.getPosition().y), null);
		//g.drawLine((int)(gUnit*rope.getAnchor1().x ) + 50, (int)(gUnit*rope.getAnchor1().y)+ 50, (int)(gUnit*rope.getAnchor2().x)+ 50, (int)(gUnit*rope.getAnchor2().y)+ 50);
		
		g.drawLine((int)(gUnit * plane.getAnchor1().x), (int)(gUnit * plane.getAnchor1().y), (int)(gUnit * plane.getAnchor2().x), (int)(gUnit * plane.getAnchor2().y));
		
		g.drawImage(tito.getTexture(), (int)(256*tito.getPosition().x), (int)(256*tito.getPosition().y), 75, 75, null);
		rope.setOb1(trash);
		rope.setXAnchored();
		
	}
	
	class MouseDrag implements MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent e) {
			double x = (double)e.getX()/gUnit;
			double y = (double)e.getY()/gUnit;
			DoublePoint dp = new DoublePoint(x, y);
			double t1x = trash.getPosition().x;
			double t1y = trash.getPosition().y;
			double px = plane.getPosition().x;
			double p2x = plane.getAnchor2().x;
			
		
			
				if ( x >= t1x && x <= t1x +(100/gUnit) && y >= t1y && y <= t1y + (100/gUnit) ){
					
					t1x = x - (50/gUnit);
					t1y = y - (50/gUnit);
					//rope.pulleyMove(t1x, t1y);
					trash.setX(t1x);
					trash.setY(t1y);
					
					rope.pulleyMove(rope.getOb1().getPosition().x, y);
					plane.setAngle();
				}
				else if( x >= px && x <= p2x && plane.pointDistance(dp) <= 0.3 ){
					x = x - plane.getWidth()/2;
					//y = plane.getY(x);
					plane.getAnchor1().x = x;
					plane.getAnchor1().y = y;
					plane.setAnchor2X();
					plane.setAnchor2Y();
				}
				repaint();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}

}
