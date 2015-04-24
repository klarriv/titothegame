package windows;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class HelpMenu extends JPanel{
	
	private static JPanel helpCL;
	private static HelpPage[] helpPages;
	
	public HelpMenu(){
		setLayout(new BorderLayout());
		
		JButton jbtNext = new JButton("Next");
		jbtNext.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cardLayout = (CardLayout) helpCL.getLayout();
				cardLayout.next(helpCL);
			}
			
		});
		JButton jbtPrevious = new JButton("Previous");
		jbtPrevious.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cardLayout = (CardLayout) helpCL.getLayout();
				cardLayout.previous(helpCL);
			}
			
		});
		JButton jbtBackToMenu = new JButton("Back to Main Menu");
		jbtBackToMenu.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cardLayout = (CardLayout) MainFrame.getMenus().getLayout();
				cardLayout.show(MainFrame.getMenus(), MainFrame.getMainmenupanel());
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
		south.add(jbtBackToMenu);
		south.add(jbtPrevious);
		south.add(jbtNext);
		add(south, BorderLayout.SOUTH);
	}
}
