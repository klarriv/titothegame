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
		// TODO add the actual images in the help menu
		
		switch (pageNumber) {
		case 0:  g.drawImage(MainFrame.getTl().helpPages[0], 0, 0, getWidth(), getHeight(), null);
				 break;
        case 1:  g.drawImage(MainFrame.getTl().helpPages[1], 0, 0, getWidth(), getHeight(), null);
                 break;/*
        case 2:  g.drawImage(img, x, y, width, height, observer);
                 break;
        case 3:  g.drawImage(img, x, y, width, height, observer);
                 break;
        case 4:  g.drawImage(img, x, y, width, height, observer);
                 break;
        case 5:  g.drawImage(img, x, y, width, height, observer);
                 break;
        case 6:  g.drawImage(img, x, y, width, height, observer);
                 break;
        case 7:  g.drawImage(img, x, y, width, height, observer);
                 break;
        case 8:  g.drawImage(img, x, y, width, height, observer);
                 break;
        case 9:  g.drawImage(img, x, y, width, height, observer);
                 break;
        default: g.drawImage(img, x, y, width, height, observer);
                 break;*/
		}
		g.drawString(pageNumber+"", 50, 50);
	}
}
