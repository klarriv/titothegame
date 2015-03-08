


import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.Timer;

import objects.Tito;

public class PlaneTestPanel extends JPanel {
	private int counter = 0;
	private int i = 0;
	private int j = 0;
	private Timer t;
	private Tito loader = new Tito(0, 50, 0, 0, t);
	private BufferedImage spriteSheet;
	private BufferedImage sprite;
	private int[] pattern = { 0, 1, 2, 3, 4, 2, 1 };// tito walking algorithm
	private int[] pattern2x = { 0, 1, 2, 3, 4, 2, 1, 0, 0, 1, 2, 3, 4, 5, 0, 5, 5, 1, 2, 3, 4, 0 };
	private int[] pattern2y = { 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 0, 2, 2, 2, 2, 2, 1 };
	private int[] rollingx = { 4, 5, 0, 5, 5 };
	private int[] rollingy = { 1, 1, 2, 0, 2 };
	
	
	public PlaneTestPanel() {
		this.setOpaque(true);
		this.setBackground(Color.cyan);
		try {
			spriteSheet = loader.loadImage();
		} catch (IOException ex) {

		}
		
		
		this.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				
				loader.setVy(7);
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		t = new Timer(1000 / 24, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				SpriteSheet ss = new SpriteSheet(spriteSheet);
				
				if (i < rollingx.length) {

					// sprite width: 300 height: 250 and 27 pixel from paws to bottom
					//sprite = ss.grabSprite(pattern2x[i]*300, pattern2y[j]*250, 289, 250);
					sprite = ss.grabSprite(rollingx[i] * 300, rollingy[j] * 250, 289, 250);
					if (counter % 2 == 0){
					i++;
					j++;
					}
				} else {
					i = 0;
					j = 0;
					// sprite width: 300 height: 250 and 27 pixel from paws to bottom
					//sprite = ss.grabSprite(pattern2x[i]*300, pattern2y[j]*250, 289, 250);
					sprite = ss.grabSprite(rollingx[i] * 300, rollingy[j] * 250, 289, 250);
					i++;
					j++;
				}
				
				counter++;
				repaint();
				
			}
			
		});
		loader.setAcceleration(Math.toRadians(320), loader.getWeight(), 1);
		
		
		t.start();
		

	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.gray);
		g.fillRect(0, 630, 1280, 90);
		int[] x = {0, 422,0};
		int[] y = {110, 630, 630};
		
		g.fillPolygon(x, y, 3);
		
		g.drawImage(sprite, loader.getPosition().x, loader.getPosition().y, 100, 100, null);
		
		if (loader.getPosition().x < 422 && loader.getPosition().y < 550)
			frictionMove();
		else {
			//System.out.println(loader.getPosition().y);
			loader.setY(550);//we have to find something better than that
			xMove();
		}
		
		
	}
	
	public void frictionMove(){
		loader.frictionMotion(loader.getPosition(), loader.getVx(), loader.getVy(),  t.getDelay());	
		loader.setVy();
		loader.setVx();
	}
	
	public void xMove(){
		int x = loader.motion( loader.getPosition().x, -loader.getVx(), t.getDelay());
		if (x < 422){
			loader.frictionMotion(loader.getPosition(), -loader.getVx() * Math.sin(Math.toRadians(320)),
					-loader.getVx()* Math.cos(Math.toRadians(320)),  t.getDelay());	
			loader.setVy();
			loader.setVx();
		}
		
		if (x < 1280)
			loader.setX(x);
		else 
			loader.setVx(-1 * loader.getVx());
		
		
	}

}
