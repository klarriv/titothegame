package RunningClasses;

import java.awt.BorderLayout;
import java.awt.Button;
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
	private Pulley pulley = new Pulley(2,0.1, true);
	private Rope rope = new Rope(2, 2);
	private Button start = new Button("Start");
	
	
	public RopesTestPanel(){
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
			public void actionPerformed(ActionEvent e) {
				start();
				
			}
			
		});
		add(start, BorderLayout.NORTH);
		
	}
	public void start(){
		rope.setLength3();
		t.start();
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		gUnit = getWidth()/5;
		g.drawImage(trash1.getTexture(),(int)(gUnit* trash1.getPosition().x), (int)(gUnit* trash1.getPosition().y), 100, 100, null);
		g.drawImage(trash2.getTexture(),(int)(gUnit* trash2.getPosition().x), (int)(gUnit* trash2.getPosition().y), 100, 100, null);
		if (rope.isUsed() == -1){
			g.fillRect((int)(gUnit*rope.getAnchor2().x), (int)(gUnit*rope.getAnchor2().y), 20, 20);
		}
		else if (rope.isUsed() == 1){
			int[] xPoints = {(int)(gUnit*rope.getAnchor1().x) + 50, (int)(gUnit*rope.getAnchor2().x) + 50};
			int[] yPoints = {(int)(gUnit*rope.getAnchor1().y), (int)(gUnit*rope.getAnchor2().y)};
			g.drawPolyline(xPoints, yPoints, 2);
		}
		else if (rope.isUsed() == 2){
			int[] xPoints = {(int)(gUnit*rope.getAnchor1().x) + 50, (int)(gUnit*rope.getAnchor2().x) + 50, (int)(gUnit*rope.getAnchor3().x) + 50};
			int[] yPoints = {(int)(gUnit*rope.getAnchor1().y), (int)(gUnit*rope.getAnchor2().y), (int)(gUnit*rope.getAnchor3().y)};
			g.drawPolyline(xPoints, yPoints, 3);
		}
		//g.drawLine((int)(gUnit*rope.getAnchor1().x ) + 50, (int)(gUnit*rope.getAnchor1().y)+ 50, (int)(gUnit*rope.getAnchor2().x)+ 50, (int)(gUnit*rope.getAnchor2().y)+ 50);
		double y = trash1.projectileMotions(trash1.getWeight(), trash1.getPosition().y, trash1.getVy(), t.getDelay());
		if(pulley.isVisible())
			g.drawImage(pulley.getTexture(), (int)(gUnit*pulley.getPosition().x) + 15, (int)(gUnit*pulley.getPosition().y), null);
		
		if (!rope.isMaxed() && t.isRunning()){
			trash1.setY(y);
			trash1.setVy();
			rope.pulleyMove(trash1.getPosition().x, y);
		}
		
	}
	
	class MouseDrag implements MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent e) {
			double x = (double)e.getX()/gUnit;
			double y = (double)e.getY()/gUnit;
			double t1x = trash1.getPosition().x;
			double t1y = trash1.getPosition().y;
			
			if (!t.isRunning()){
			
			if ( x >= t1x && x <= t1x +(100/gUnit) && y >= t1y && y <= t1y + (100/gUnit) ){
				
				t1x = x - (50/gUnit);
				t1y = y - (50/gUnit);
				//rope.pulleyMove(t1x, t1y);
				trash1.setX(t1x);
				trash1.setY(t1y);
				
				if (trash1.getPosition().distance(rope.getAnchor2()) <= 0.3 && rope.isUsed() < 1){
					if (rope.isUsed() == 0){
						rope.setOb1(trash1);
						rope.setOb2(trash2);
					}
					else if (rope.isUsed() == 1)
						rope.setOb2(trash1);
				}
				//System.out.println( x);
			}
			
			if (x >= rope.getAnchor2().x && x <= rope.getAnchor2().x +(20/gUnit) && y >= rope.getAnchor2().y && y <= rope.getAnchor2().y + (20/gUnit)
					&& rope.isUsed() == -1){
				rope.getAnchor2().x = x - (10/gUnit);
				rope.getAnchor2().y = y - (10/gUnit);
				if (rope.getAnchor2().distance(pulley.getPosition()) <= 0.3)
					rope.setPulley(pulley);
			}
			
			if (x >= trash2.getPosition().x && x <= trash2.getPosition().x +(100/gUnit) && y >= trash2.getPosition().y && y <= trash2.getPosition().y + (100/gUnit)){
				trash2.getPosition().x = x - (50/gUnit);
				trash2.getPosition().y = y - (50/gUnit);
				if (rope.getAnchor2().distance(trash2.getPosition()) <= 0.3){
					if (rope.isUsed() == 0){
						rope.setOb1(trash1);
						rope.setOb2(trash2);
					}
					else if (rope.isUsed() == 1)
						rope.setOb2(trash1);
				}
					
			}
			repaint();
			}
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		
		
	}

	

}

