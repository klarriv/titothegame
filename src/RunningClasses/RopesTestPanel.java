package RunningClasses;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import objects.Rope;
import objects.TrashCan;

public class RopesTestPanel extends JPanel {
	
	private double gUnit;
	private Timer t;
	private TrashCan trash1 = new TrashCan(1, 1);
	private TrashCan trash2 = new TrashCan(2, 1);
	private Rope rope = new Rope(trash1, trash2);
	
	
	public RopesTestPanel(){
		addMouseMotionListener( new MouseDrag());
		t = new Timer(1000/24, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
				
			}
			
		}
		);
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		gUnit = getWidth()/5;
		g.drawImage(trash1.getTexture(),(int)(gUnit* trash1.getPosition().x), (int)(gUnit* trash1.getPosition().y), 100, 100, null);
		g.drawImage(trash2.getTexture(),(int)(gUnit* trash2.getPosition().x), (int)(gUnit* trash2.getPosition().y), 100, 100, null);
		
		g.drawLine((int)(gUnit*rope.getAnchor1().x), (int)(gUnit*rope.getAnchor1().y), (int)(gUnit*rope.getAnchor2().x), (int)(gUnit*rope.getAnchor2().y));
	}
	
	class MouseDrag implements MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent e) {
			double x = (double)e.getX()/gUnit;
			double y = (double)e.getY()/gUnit;
			double t1x = trash1.getPosition().x;
			double t1y = trash1.getPosition().y;
			double t2x = trash2.getPosition().x;
			double t2y = trash2.getPosition().y;
			
			
			if ( x >= t1x && x <= t1x +(100/gUnit) && y >= t1y && y <= t1y + (100/gUnit) ){
				
				t1x = x- (50/gUnit);
				t1y = y- (50/gUnit);
				rope.move(t1x, t1y);
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

