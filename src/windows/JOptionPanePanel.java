package windows;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class JOptionPanePanel extends JPanel{
	
	public JOptionPanePanel() {
		JLabel bg = new JLabel(MainFrame.getTl().JOptionPaneBG);		
		add(bg);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		Font fntPlayGame;
		try {
			fntPlayGame = Font.createFont(Font.TRUETYPE_FONT, new File("Resources/BlackCarrot.ttf")).deriveFont(Font.PLAIN, 15f);
			GraphicsEnvironment gc = GraphicsEnvironment.getLocalGraphicsEnvironment();
			gc.registerFont(fntPlayGame);
			g.setFont(fntPlayGame);
			g.setColor(new Color(204, 213, 187));
		} catch (FontFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		g.drawString("Starting a new game will erase any previous save file.", 140, 90);
		g.drawString("Are you sure you want to continue?", 280, 140);
		g.drawImage(MainFrame.getTl().titoWalkingAnimation.getImage(), 385, 200, this);
	}
}
