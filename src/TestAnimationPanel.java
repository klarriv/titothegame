import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.Timer;

import objects.Tito;


public class TestAnimationPanel extends JPanel {
	private Tito loader = new Tito();
	private BufferedImage spriteSheet;
	private BufferedImage sprite;
	private int[] pattern = {0, 1, 2, 3, 4, 2, 1};//tito walking algorithm
	private int[] pattern2x = {0, 1, 2, 3, 4, 2, 1, 0, 0, 1, 2, 3, 4 , 5, 0, 5, 5, 1, 2, 3, 4, 0};
	private int[] pattern2y = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 0, 2, 2, 2, 2, 2, 1};
	private int[] rollingx ={4, 5, 0, 5, 5};
	private int[] rollingy ={1, 1, 2, 0, 2};
	
	public TestAnimationPanel(){
		this.setBackground(Color.cyan);
		try{
			spriteSheet = loader.loadImage();
		} catch(IOException ex){
			
		}
		t.start();
		
		
	}
	private int i = 0;//index loader
	private int j = 0;
	private int x = 0; //position
	Timer t = new Timer(1000/10, new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			
			SpriteSheet ss = new SpriteSheet(spriteSheet);
			if (i < rollingx.length){
				
				//sprite width: 300 height: 250 and 27 pixel from paws to bottom
				sprite = ss.grabSprite(pattern2x[i]*300, pattern2y[j]*250, 289, 250);
				i++;
				j++;
			}
			else{
				i = 0;
				j = 0;
				//sprite width: 300 height: 250 and 27 pixel from paws to bottom
				sprite = ss.grabSprite(pattern2x[i]*300, pattern2y[j]*250, 289, 250);
				i++;
				j++;
			}
			
			x+= 5;
			if (x > 1280)
				x =-300;
			repaint();
		}
		
	});
	@Override
	public void paintComponent(Graphics g){
		g.setColor(Color.gray);
		g.fillRect(0, 630, 1280, 90);
		g.setColor(Color.black);
		g.drawLine(0, 630, 1280, 630);
		
		g.drawImage(sprite, x,550, 100, 100, null);
		
	}

}
