package windows;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class HelpMenu extends JPanel{
	
	private static JPanel helpCL;
	private static HelpPage[] helpPages;
	
	public HelpMenu(){
		setLayout(new BorderLayout());
		
		JButton next = new JButton("Next");
		next.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		JButton previous = new JButton("Previous");
		previous.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		helpPages = new HelpPage[10];
		helpCL = new JPanel(new CardLayout());
		
		for(int i = 0; i<helpPages.length; i++){
			helpPages[i] = new HelpPage(i);
			helpCL.add(helpPages[i], ("HELP"+i));
		}
		add(helpCL, BorderLayout.CENTER);
		JPanel south = new JPanel();
		south.add(previous);
		south.add(next);
		add(south, BorderLayout.SOUTH);
	}
}
