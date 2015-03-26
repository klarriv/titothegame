package RunningClasses;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import objects.Tito;

public class VectorTestPanel extends JPanel {
	private JLabel setVx = new JLabel("Set Vx:"); 
	private JLabel setVy = new JLabel("Set Vy:"); 
	private JLabel rot = new JLabel("Set Rotation:"); 
	private JTextField sVx = new JTextField(4);
	private JTextField sVy = new JTextField(4);
	private JTextField sRot = new JTextField(4);
	private JButton rotate = new JButton("Rotate");
	private JPanel p = new JPanel();
	private JLabel answer = new JLabel();
	
	private double vx = 100;
	private double vy = -100;
	private double angle;
	private double vx2;
	private double vy2;
	
	private Tito t2 = new Tito();
	
	public VectorTestPanel(){
		setLayout(new BorderLayout());
		
		rotate.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				vx = Double.parseDouble(sVx.getText());
				vy = Double.parseDouble(sVy.getText());
				angle = Math.toRadians(Double.parseDouble(sRot.getText()));
				
				t2.matrixMultiplication(angle, vx, vy);
				t2.setVx();
				t2.setVy();
				answer.setText("vx: " + t2.getVx() + " vy: " + t2.getVy());
				repaint();
			}
			
		});
		
		
		p.add(setVx);
		p.add(sVx);
		p.add(setVy);
		p.add(sVy);
		p.add(rot);
		p.add(sRot);
		p.add(rotate);
		
		add(p, BorderLayout.NORTH);
		add(answer, BorderLayout.SOUTH);
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		int x = 1280/2;
		int y = 720/2;
		vx2 = t2.getVx();
		vy2 = t2.getVy();
		g.setColor(Color.gray);
		
		g.drawLine(x, y +200, x, y - 200);
		g.drawLine(x -200, y, x + 200, y);
		
		g.setColor(Color.blue);
		
		g.drawLine(x, y, (int)(x + vx), (int)(y + vy));
		g.fillOval((int)(x+vx -5), (int)(y+vy -5), 10, 10);
		
		g.setColor(Color.green);
		g.drawLine(x, y, (int)(x + vx2), (int)(y + vy2));
		g.fillOval((int)(x+vx2 -5), (int)(y+vy2 -5), 10, 10);
		
	}
	

}
