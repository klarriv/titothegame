package RunningClasses;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.Timer;

import objects.DoublePoint;
import objects.Physics;
import objects.Plane;
import objects.Tito;
import objects.TrashCan;

public class LevelTestPanel extends JPanel{
	
	public static int gUnit;
	private int counter = 0;
	private int i = 0;
	private int j = 0;
	private Timer t;
	
	private BufferedImage spriteSheet;
	private BufferedImage sprite;
	private int[] pattern = { 0, 1, 2, 3, 4, 2, 1 };// tito walking algorithm
	private int[] pattern2x = { 0, 1, 2, 3, 4, 2, 1, 0, 0, 1, 2, 3, 4, 5, 0, 5,
			5, 1, 2, 3, 4, 0 };
	private int[] pattern2y = { 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 0,
			2, 2, 2, 2, 2, 1 };
	private int[] rollingx = { 4, 5, 0, 5, 5 };
	private int[] rollingy = { 1, 1, 2, 0, 2 };
	
	private Tito tito = new Tito(0, 2, 3, 7, t);
	private TrashCan trash = new TrashCan(3,0, 0, 0, t);
	private Plane p1 = new Plane(2, 2.2, Math.toRadians(160), 1);
	private Plane p2 = new Plane(4, 2.2, Math.toRadians(20), -1);
	private Plane p3 = new Plane(2.5, 1, Math.toRadians(135), 0.9);
	
	
	public LevelTestPanel(){
		this.setOpaque(true);
		this.setBackground(Color.cyan);
		try {
			spriteSheet = tito.loadImage();
		} catch (IOException ex) {

		}
		t = new Timer(1000 / 25, new ActionListener(){

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
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		gUnit = getWidth()/5;
		g.setColor(Color.gray);
		g.fillRect(0, (int)(2.2*gUnit), 1280, 200);
		
		g.drawLine((int)(gUnit*(p1.getX()[0])), (int)(gUnit*(p1.getY()[0])), (int)(gUnit*(p1.getX()[1])), (int)(gUnit*p1.getY()[1]));
		
		g.drawLine((int)(gUnit*(p2.getX()[0])), (int)(gUnit*(p2.getY()[0])), (int)(gUnit*(p2.getX()[1])), (int)(gUnit*p2.getY()[1]));
		
		g.drawLine((int)(gUnit*(p3.getX()[0])), (int)(gUnit*(p3.getY()[0])), (int)(gUnit*(p3.getX()[1])), (int)(gUnit*p3.getY()[1]));
		
		g.drawImage(sprite, (int)(tito.getPosition().x * gUnit), (int)(tito.getPosition().y * gUnit), 75, 75, null);
		try{
		g.drawImage(trash.loadImage(), (int)(trash.getPosition().x * gUnit), (int)(trash.getPosition().y * gUnit), 75, 75, null);
		}
		catch(Exception e){
			
		}
		
		setAcceleration(135);
		if ( trash.getPosition().x >= p3.getX()[0])
			frictionMove();
		
		else{
			//System.out.println(trash.getVx() + " " + trash.getVy());
			projectileMotion(trash);
			basicMove(trash);
		}
		
		if (tito.getVy() < 0.5 && tito.getPosition().y >= 2){
		xMove();
		}
		else if (planeColliding(p1)){
			planeCollision(p1);
			projectileMotion(tito);
			xMove();
		}
		else if (planeColliding(p2)){
			planeCollision(p2);
			projectileMotion(tito);
			xMove();
		}
		else if (planeColliding(p3)){
			planeCollision(p3);
			projectileMotion(tito);
			xMove();
		}
		else{
			projectileMotion(tito);
			xMove();
		}
		
		
	
		//planeCollision();
	}
	
	public void planeCollision(Plane plane){
		
		double angle = plane.angleOfContact(tito.getVx(), tito.getVy());
		
		
			if (tito.getVx() >= 0)
				tito.matrixMultiplication(angle * 2, tito.getVx(), tito.getVy());
			else
				tito.matrixMultiplication(angle * 2, tito.getVx(), tito.getVy());
			
			tito.setVx();
			tito.setVy();
			
		
	}
	
	public boolean planeColliding(Plane plane){
		double r = 40.0/gUnit;
		DoublePoint dp = new DoublePoint(tito.getPosition().x + r, tito.getPosition().y + r);
		double d = plane.pointDistance(dp);
		if (plane.getWidth() > 0){
			if (d <=  r && (dp.x) <= plane.getX()[1] && dp.x >= plane.getX()[0] )
				return true;
		}
		else if (plane.getWidth() < 0){
			if (d <= r && dp.x > plane.getX()[1] && dp.x < plane.getX()[0] )
				return true;
		}
		return false;
			
	}
	
	public void basicMove(Physics ob1){
		double x = ob1.motion( ob1.getPosition().x, ob1.getVx(), t.getDelay());
		ob1.setX(x);
	}
	
	public void xMove(){
		double x = tito.motion( tito.getPosition().x, -tito.getVx(), t.getDelay());
		
		if (x <=5 && x>= 0)
			tito.setX(x);
		else if (Math.abs(0-x) <= 0.1){
			tito.setX(0);
		}
		else if (Math.abs(5 - x) <= 0.1 ){
			tito.setX(5);
		}
		else
			tito.setVx(-1*tito.getVx());
	}
	
	public void projectileMotion(Physics ob1){
		double y = ob1.projectileMotions(ob1.getWeight(), ob1.getPosition().y, ob1.getVy(), t.getDelay());
		if (ob1.getVy() < 0 && y >= 2){
			ob1.setY(2);
			ob1.setVy(-1*ob1.getVy() - 1);
			//System.out.println(loader.getPosition().y);
		}
		
		else if (y <= 2){
			ob1.setY(y);
			ob1.setVy();
		}
	}
	
	public void frictionMove(){
		trash.frictionMotion(trash.getPosition(), trash.getVx(), trash.getVy(),  t.getDelay());	
		trash.setVy();
		trash.setVx();
	}
	
	public void setAcceleration(int aa){
		//System.out.println( aa);
		trash.setAcceleration(Math.toRadians(aa), trash.getWeight(), 0.5);
	}

}
