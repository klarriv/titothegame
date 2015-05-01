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
		
		switch (pageNumber) {
		case 0:  g.drawImage(MainFrame.getTl().helpPages[0], 0, 0, getWidth(), getHeight(), null);
				 break;
        case 1:  g.drawImage(MainFrame.getTl().helpPages[1], 0, 0, getWidth(), getHeight(), null);
                 break;
        case 2:  g.drawImage(MainFrame.getTl().helpPages[2], 0, 0, getWidth(), getHeight(), null);
                 break;
        case 3:  g.drawImage(MainFrame.getTl().helpPages[3], 0, 0, getWidth(), getHeight(), null);
                 break;
        case 4:  g.drawImage(MainFrame.getTl().helpPages[4], 0, 0, getWidth(), getHeight(), null);
                 break;
        case 5:  g.drawImage(MainFrame.getTl().helpPages[5], 0, 0, getWidth(), getHeight(), null);
                 break;/*
        case 6:  g.drawImage(MainFrame.getTl().helpPages[6], 0, 0, getWidth(), getHeight(), null);
                 break;
        case 7:  g.drawImage(MainFrame.getTl().helpPages[7], 0, 0, getWidth(), getHeight(), null);
                 break;
        case 8:  g.drawImage(MainFrame.getTl().helpPages[8], 0, 0, getWidth(), getHeight(), null);
                 break;
        case 9:  g.drawImage(MainFrame.getTl().helpPages[9], 0, 0, getWidth(), getHeight(), null);
                 break;
        default: g.drawImage(img, x, y, width, height, observer);
                 break;*/
		}
		g.drawString(pageNumber+"", 50, 50);
	}
}
