package windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class CustomJOptionPane {
	public CustomJOptionPane() {
	    EventQueue.invokeLater(new Runnable() {
	        @Override
	        public void run() {
	            try {
	                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	            } catch (Exception ex) {
	            }
	
	            final JDialog dialog = new JDialog();
	
	            JOptionPane op = new JOptionPane("Starting a new game will erase any previous save file. \nAre you sure you want to continue?", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
	            op.addPropertyChangeListener(new PropertyChangeListener() {
	                @Override
	                public void propertyChange(PropertyChangeEvent evt) {
	                    String name = evt.getPropertyName();
	                    if ("value".equals(name)) {
	                        dialog.dispose();
	
	                    }
	                }
	            });
	
	            dialog.setUndecorated(true);
	            dialog.setLayout(new BorderLayout());
	            dialog.add(op);
	            dialog.pack();
	            dialog.setLocationRelativeTo(null);
	            dialog.setVisible(true);
	            dialog.setAlwaysOnTop(true);
	        }
	    });
	}
}