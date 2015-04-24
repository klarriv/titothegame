package RunningClasses;

import java.awt.Graphics;

import javax.swing.JPanel;

import objects.Plane;

public class RotationTestPanel extends JPanel{
	private Plane plane = new Plane(1, 1, Math.toRadians(165), 1, -1);
	//private JButton
	
	public RotationTestPanel(){
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		plane.rotateImage();
		g.drawImage(plane.getTexture(), (int) (256 * plane.getPosition().x), (int) (256 * plane.getPosition().y), null);
		g.drawLine((int)(256 * plane.getAnchor1().x), (int)(256 * plane.getAnchor1().y), (int)(256 * plane.getAnchor2().x), (int)(256 * plane.getAnchor2().y));
	}
}
