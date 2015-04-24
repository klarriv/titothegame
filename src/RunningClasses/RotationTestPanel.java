package RunningClasses;

import java.awt.Graphics;

import javax.swing.JPanel;

import objects.Plane;

public class RotationTestPanel extends JPanel{
	private Plane plane = new Plane(1.5, 2, Math.toRadians(165), 1, -1);
	//private JButton
	
	public RotationTestPanel(){
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(plane.getTexture(), (int) (256 * plane.getPosition().x), (int) (256 * plane.getPosition().y), null);
		
	}
}
