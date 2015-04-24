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
import objects.Plane;
import objects.Pulley;
import objects.Rope;
import objects.TrashCan;

public class PlaneIncliningTestPanel extends JPanel{
	private double gUnit;
	private Timer t;
	private Plane plane = new Plane(1.6, 2, Math.toRadians(175), 1);
	private TrashCan trash = new TrashCan(1, 1.5);
	private Pulley pulley = new Pulley(2, 0.1, true);
	private Rope rope = new Rope(3,2);
	private Button start = new Button("Start");
	
	public PlaneIncliningTestPanel(){
		setLayout(new BorderLayout());
		addMouseMotionListener( new MouseDrag());
		start.addActionListener(new ActionListener(){
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
		
		add(start, BorderLayout.NORTH);
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
