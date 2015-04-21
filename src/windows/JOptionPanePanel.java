package windows;

import java.awt.Graphics;

import javax.swing.JPanel;

public class JOptionPanePanel extends JPanel{
	
	public JOptionPanePanel() {
		
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.drawImage(MainFrame.getTl().JOptionPaneBG.getImage(), 0, 0, this);
		g.drawImage(MainFrame.getTl().titoWalkingAnimation.getImage(), 0, 0, this);
	}
}
