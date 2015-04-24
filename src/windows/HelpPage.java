package windows;

import java.awt.Graphics;

import javax.swing.JPanel;

public class HelpPage extends JPanel{

	private int pageNumber;
	
	HelpPage(int pageNumber){
		this.pageNumber = pageNumber;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(MainFrame.getTl().JOptionPaneBG.getImage(), 0, 0, getWidth(), getHeight(), null);
		g.drawString("pageNumber", 50, 50);
	}
}
