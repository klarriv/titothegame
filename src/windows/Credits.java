package windows;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Credits extends JPanel{
	
	Credits(){
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawString("Tito is a game", 10, getWidth()/2);
		g.drawString("by Charlo and Kevne", 50, getWidth()/2);
		
	}
}