package RunningClasses;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import objects.Pulley;
import objects.Rope;
import objects.TrashCan;

public class RopesTestPanel extends JPanel {
	
	private double gUnit;
	private Timer t;
	private TrashCan trash1 = new TrashCan(1, 1.5);
	private TrashCan trash2 = new TrashCan(2, 1.5);
	private Pulley pulley = new Pulley(2,1, false);
	private Rope rope = new Rope(trash1, pulley, trash2);
	
	
	public RopesTestPanel(){
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
		g.drawImage(trash2.getTexture(),(int)(gUnit* trash2.getPosition().x), (int)(gUnit* trash2.getPosition().y), 100, 100, null);
		
		int[] xPoints = {(int)(gUnit*rope.getAnchor1().x) + 50, (int)(gUnit*rope.getAnchor2().x) + 50, (int)(gUnit*rope.getAnchor3().x) + 50};
		int[] yPoints = {(int)(gUnit*rope.getAnchor1().y) + 50, (int)(gUnit*rope.getAnchor2().y) + 50, (int)(gUnit*rope.getAnchor3().y) + 50};
		g.drawPolyline(xPoints, yPoints, 3);
		//g.drawLine((int)(gUnit*rope.getAnchor1().x ) + 50, (int)(gUnit*rope.getAnchor1().y)+ 50, (int)(gUnit*rope.getAnchor2().x)+ 50, (int)(gUnit*rope.getAnchor2().y)+ 50);
		double y = trash1.projectileMotions(trash1.getWeight(), trash1.getPosition().y, trash1.getVy(), t.getDelay());
		
		/**if (!rope.isMaxed()){
			trash1.setY(y);
			trash1.setVy();
			rope.pulleyMove(trash1.getPosition().x, y);
		}*/
		
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
				rope.pulleyMove(t1x, t1y);
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

