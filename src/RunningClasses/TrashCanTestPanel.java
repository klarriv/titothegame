package RunningClasses;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import objects.Plane;
import objects.Pulley;
import objects.Rope;
import objects.TrashCan;
import RunningClasses.RopesTestPanel.MouseDrag;

public class TrashCanTestPanel extends JPanel {

	private double gUnit;
	private Timer t;
	private TrashCan trash1 = new TrashCan(1, 1.5);
	private Plane plane = new Plane(1,1, Math.toRadians(165), 1);
	///private TrashCan trash2 = new TrashCan(2, 1.5);
//	private Pulley pulley = new Pulley(2,1, false);
	//private Rope rope = new Rope(trash1, pulley, trash2);
	
	
	public TrashCanTestPanel(){
		addMouseMotionListener( new MouseDrag());
		t = new Timer(1000/24, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
				
			}
			
		}
		);
		//t.start();
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		gUnit = getWidth()/5;
		g.drawImage(trash1.getTexture(),(int)(gUnit* trash1.getPosition().x), (int)(gUnit* trash1.getPosition().y), 100, 100, null);
		
		
		g.drawLine((int)(gUnit*(plane.getX()[0])), (int)(gUnit*(plane.getY()[0])), (int)(gUnit*(plane.getX()[1])), (int)(gUnit*plane.getY()[1]));
		
		double y = trash1.projectileMotions(trash1.getWeight(), trash1.getPosition().y, trash1.getVy(), t.getDelay());
		double x = trash1.getPosition().x;
		System.out.println(plane.pointDistance(trash1.getPosition()));
		planeContact(trash1, plane);
		
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
		
		double height = ob1.HEIGHT;
		double width = ob1.WIDTH;
				
		if (y < ty && x > tx && x < (txf-width) && p.pointDistance(ob1.getPosition()) < 1){
			ob1.setY(ty - height);
			if (!ob1.rotated)
				ob1.rotate(p.getAngle());
		}
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
