package RunningClasses;

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

public class PhysicsTestPanel extends JPanel{
	private int a = 135;
	public static int gUnit;
	private int counter = 0;
	private int i = 0;
	private int j = 0;
	private Timer t;
	private Tito loader = new Tito(0, 2.2, 2, 0, t);
	private BufferedImage spriteSheet;
	private BufferedImage sprite;
	private int[] pattern = { 0, 1, 2, 3, 4, 2, 1 };// tito walking algorithm
	private int[] pattern2x = { 0, 1, 2, 3, 4, 2, 1, 0, 0, 1, 2, 3, 4, 5, 0, 5,
			5, 1, 2, 3, 4, 0 };
	private int[] pattern2y = { 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 0,
			2, 2, 2, 2, 2, 1 };
	private int[] rollingx = { 4, 5, 0, 5, 5 };
	private int[] rollingy = { 1, 1, 2, 0, 2 };
	
	
	public PhysicsTestPanel(){
		this.setOpaque(true);
		this.setBackground(Color.cyan);
		try {
			spriteSheet = loader.loadImage();
		} catch (IOException ex) {

		}
		
		
		this.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				
				loader.setVy(-5);
				
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
		
		
		t.start();
		

		
	}
	
	private boolean b = true;
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		gUnit = getWidth()/5;
		g.setColor(Color.gray);
		g.fillRect(0, (int)(2.4*gUnit), 1280, 90);
		int[] x = {(int)(4 * gUnit), (int)(5 * gUnit), (int)(5 * gUnit)};
		int[] y = {(int)(2.4 * gUnit), (int)((2.4 - Math.tan(Math.toRadians(45)))*gUnit), (int)(2.4 * gUnit)};
		
		g.fillPolygon(x, y, 3);
		g.drawImage(sprite, (int)(loader.getPosition().x * gUnit), (int)(loader.getPosition().y * gUnit), 75, 75, null);
		
		moving();
	}
	
	public void moving(){
		if ( loader.getPosition().x <0)
			loader.setVx(-1*loader.getVx());
		if (loader.getPosition().x < 4){
			projectileMotion();
			setAcceleration(0,0);
			loader.setVy(0);
			b = true;
		}
		//double x = loader.motion( loader.getPosition().x, loader.getVx(), t.getDelay());
		else if (loader.getPosition().x >= 4){
			setAcceleration(a, 0.5);
			if (b){
				loader.setVy(loader.getVx()*Math.cos(Math.toRadians(a)));
				loader.setVx(loader.getVx()*Math.sin(Math.toRadians(a)));
				//System.out.println(loader.getVx()*Math.cos(Math.toRadians(a)) + " " + loader.getVx()*Math.sin(Math.toRadians(a)));
			}
			b = false;
			//frictionMove();
		}
		//else{
			//System.out.println(x);
			frictionMove();
			
		//}
		
		
	}
	
	
	public void setAcceleration(int aa, double u){
		//System.out.println();
		loader.setAcceleration(Math.toRadians(aa), loader.getWeight(), u);
	}
	
	public void frictionMove(){
		//System.out.println(loader.getVy());
		loader.frictionMotion(loader.getPosition(), loader.getVx(), loader.getVy(),  t.getDelay());	
		loader.setVy();
		loader.setVx();
	}
	
	public void xMove(double x){
		
		
		if (x < 5 && x > 0)
			loader.setX(x);
		else 
			loader.setVx(-1 * loader.getVx());
		
		
	}
	public void projectileMotion(){
		double y = loader.projectileMotions(loader.getWeight(), loader.getPosition().y, loader.getVy(),t.getDelay());
		if (loader.getVy() < 0 && y >= 2.2){
			loader.setY(2.2);
			loader.setVy((-1 * loader.getVy()) - 0.7);
			//System.out.println(loader.getPosition().y);
		}
		
		else if (y <= 2.2){
			loader.setY(y);
			loader.setVy();
		}
	}

}



