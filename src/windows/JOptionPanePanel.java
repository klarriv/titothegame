package windows;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JOptionPanePanel extends JPanel{
	
	public JOptionPanePanel() {
		JLabel bg = new JLabel(MainFrame.getTl().JOptionPaneBG);		
		add(bg);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.setFont(MainFrame.getTl().fntPlayGame.deriveFont(Font.PLAIN, 15f));
		g.setColor(new Color(204, 213, 187));
		
		g.drawString("Starting a new game will erase any previous save file.", 140, 90);
		g.drawString("Are you sure you want to continue?", 280, 140);
		g.drawImage(MainFrame.getTl().titoWalkingAnimation.getImage(), 385, 200, this);
	}
}
