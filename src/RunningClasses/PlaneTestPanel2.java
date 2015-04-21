package RunningClasses;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import objects.Plane;

public class PlaneTestPanel2 extends JPanel{
	private double gUnit;
	private Button move = new Button("Move Up");
	private Plane plane = new Plane(1, 2, Math.toRadians(165), 1);

	public PlaneTestPanel2(){
		setLayout(new BorderLayout());
		
		move.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				plane.getAnchor1().y -= 0.1;
				plane.setAngle();
				//System.out.println(plane.getAngle() + " " + plane.getAnchor1().x + " " + plane.getAnchor1().y + " "  + plane.getAnchor2().x + " " + plane.getAnchor2().y);
				//System.out.println(plane.getM());
				repaint();
				
			}
			
		});
		
		add(move, BorderLayout.NORTH);
	}
	
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		gUnit = getWidth()/5;
		
		g.drawLine((int)(gUnit * plane.getAnchor1().x), (int)(gUnit * plane.getAnchor1().y), (int)(gUnit * plane.getAnchor2().x), (int)(gUnit * plane.getAnchor2().y));
	}
}
