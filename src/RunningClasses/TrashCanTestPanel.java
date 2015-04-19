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

import objects.Physics;
import objects.Plane;
import objects.Pulley;
import objects.Rope;
import objects.SeeSaw;
import objects.TrashCan;
import RunningClasses.RopesTestPanel.MouseDrag;

public class TrashCanTestPanel extends JPanel {

	private double gUnit;
	private Timer t;
	private TrashCan trash1 = new TrashCan(1, 1.5);
	private Plane plane = new Plane(2,1, Math.toRadians(-155), 1);
	private Button start = new Button("Start");
	private SeeSaw seesaw = new SeeSaw(0.5, 2);
	
	
	
	public TrashCanTestPanel(){
		setLayout(new BorderLayout());
		addMouseMotionListener( new MouseDrag());
		t = new Timer(1000/24, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
				
			}
			
		}
		);
		
		start.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				t.start();
				seesaw.setUsed(2);
			}
		});
		add(start, BorderLayout.NORTH);
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		gUnit = getWidth()/5;
		g.drawImage(trash1.getTexture(),(int)(gUnit* trash1.getPosition().x), (int)(gUnit* trash1.getPosition().y), (int)(gUnit * trash1.getWidth()), (int)(gUnit * trash1.getHeight()), null);
		g.drawImage(seesaw.getTexture(),(int)(gUnit * seesaw.getPosition().x), (int)(gUnit * seesaw.getPosition().y), (int)(gUnit * 1.1),(int)(gUnit * 0.33), null);
		
		g.drawLine((int)(gUnit*(plane.getX()[0])), (int)(gUnit*(plane.getY()[0])), (int)(gUnit*(plane.getX()[1])), (int)(gUnit*plane.getY()[1]));
		
		double y = trash1.projectileMotions(trash1.getWeight(), trash1.getPosition().y, trash1.getVy(), t.getDelay());
		double x = trash1.getPosition().x;
		//System.out.println(plane.pointDistance(trash1.getPosition()));
		planeContact(trash1, plane);
		if (seesaw.getContact(trash1)){
			trash1.setPlaneVariable(1);
			trash1.setUsed(true);
			}
		
		/*if (trash1.isUsed() && trash1.getPlaneVariable() > -1 && t.isRunning()){
			frictionMove(trash1);
		}
		/**if (!rope.isMaxed()){
			trash1.setY(y);
			trash1.setVy();
			rope.pulleyMove(trash1.getPosition().x, y);
		}*/
		
	}
	
	public void planeContact(TrashCan ob1, Plane p){
		double x = ob1.getPosition().x;
		double y = ob1.getPosition().y;
		double tx = p.getX()[0];
		double txf = p.getX()[1];
		double ty = p.getY(x);
		
		double height = ob1.getHeight();
		double width = ob1.getWidth();
		//System.out.println((y > ty )+ " " + (x < tx ) + " " +( x > (txf-width)));
		if (y < ty && x > tx && x < (txf-width) && p.pointDistance(ob1.getPosition()) < 1){
			
			ob1.setUsed(true);
			ob1.setY(ty - height);
			ob1.setPlaneVariable(p.getPlaneVariable());
			setAcceleration(ob1, p);
		}
		else{
			ob1.setUsed(false);
			ob1.setPlaneVariable(-1);
			ob1.setAcceleration(0,0,0);
		}
	}
	
	public void setAcceleration(Physics ob1, Plane p){
		   
		  ob1.setAcceleration(p.getAngle(), ob1.getWeight(), 0.5); 
	  }
	
	public void frictionMove(Physics ob1){
		 ob1.frictionMotion(ob1.getPosition(),ob1.getVx(), ob1.getVy(), t.getDelay());
		 ob1.setVy();
		 ob1.setVx();
		 
		 }
	
	
	
	
	
	
	
	
	
	
	
	
	
	class MouseDrag implements MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent e) {
			double x = (double)e.getX()/gUnit;
			double y = (double)e.getY()/gUnit;
			double t1x = trash1.getPosition().x;
			double t1y = trash1.getPosition().y;
			
			
			
			if ( x >= t1x && x <= t1x +(100/gUnit) && y >= t1y && y <= t1y + (100/gUnit) ){
				
				t1x = x - (50/gUnit);
				t1y = y - (50/gUnit);
				
				trash1.setX(t1x);
				trash1.setY(t1y);
				//System.out.println( x);
			}
			repaint();
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		
		
	}

	
}
