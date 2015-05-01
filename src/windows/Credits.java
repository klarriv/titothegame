package windows;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Credits extends JPanel{
	
	private JButton exit;
	private final Font FONT = MainFrame.getTl().fntPlayGame.deriveFont(Font.PLAIN, 18f);
	
	Credits(){
		
		exit = new JButton("Exit to Main Menu");
		exit.setFont(FONT);
		exit.setBorder(BorderFactory.createRaisedBevelBorder());
		exit.setContentAreaFilled(false);
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout = (CardLayout) MainFrame.getMenus().getLayout();
				cardLayout.show(MainFrame.getMenus(), MainFrame.getMainmenupanel());
				
			}
		});
		
		setLayout(new BorderLayout());
		background bg = new background();
		add(bg, BorderLayout.CENTER);
		add(exit, BorderLayout.SOUTH);
	
	}
	
	private class background extends JPanel{
		background(){
			
		}
		
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			
			g.drawImage(MainFrame.getTl().creditsBg, 0, 0, getWidth(), getHeight(), null);
		}
	}
}
