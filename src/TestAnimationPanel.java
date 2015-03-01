import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.Timer;

import objects.Tito;

public class TestAnimationPanel extends JPanel {
	private Tito loader = new Tito();
	private BufferedImage spriteSheet;
	private BufferedImage sprite;
	private int[] pattern = { 0, 1, 2, 3, 4, 2, 1 };// tito walking algorithm
	private int[] pattern2x = { 0, 1, 2, 3, 4, 2, 1, 0, 0, 1, 2, 3, 4, 5, 0, 5,
			5, 1, 2, 3, 4, 0 };
	private int[] pattern2y = { 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 0,
			2, 2, 2, 2, 2, 1 };
	private int[] rollingx = { 4, 5, 0, 5, 5 };
	private int[] rollingy = { 1, 1, 2, 0, 2 };

	public TestAnimationPanel() {
		this.setOpaque(true);
		this.setBackground(Color.cyan);
		try {
			spriteSheet = loader.loadImage();
		} catch (IOException ex) {

		}
		t.start();

	}

	private int i = 0;// index loader
	private int j = 0;
	private int x = -50; // position
	private boolean direction; // true=to the right, false= to the left.

	Timer t = new Timer(1000 / 10, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			SpriteSheet ss = new SpriteSheet(spriteSheet);
			if (i < rollingx.length) {

				// sprite width: 300 height: 250 and 27 pixel from paws to bottom
				sprite = ss.grabSprite(pattern2x[i]*300, pattern2y[j]*250, 289, 250);
				//sprite = ss.grabSprite(rollingx[i] * 300, rollingy[j] * 250, 289, 250);
				i++;
				j++;
			} else {
				i = 0;
				j = 0;
				// sprite width: 300 height: 250 and 27 pixel from paws to bottom
				sprite = ss.grabSprite(pattern2x[i]*300, pattern2y[j]*250, 289, 250);
				// sprite = ss.grabSprite(rollingx[i] * 300, rollingy[j] * 250, 289, 250);
				i++;
				j++;
			}
			if (direction)
				x += 7;
			else if (!direction)
				x -= 7;
			if (x > 1280 || x < -50) {
				direction = !direction;
			}

			repaint();
		}

	});

	public static BufferedImage getFlippedImage(BufferedImage bi) {
		BufferedImage flipped = new BufferedImage(bi.getWidth(),
				bi.getHeight(), bi.getType());
		AffineTransform tran = AffineTransform.getTranslateInstance(
				bi.getWidth(), 0);
		AffineTransform flip = AffineTransform.getScaleInstance(-1d, 1d);
		tran.concatenate(flip);

		Graphics2D g = flipped.createGraphics();
		g.setTransform(tran);
		g.drawImage(bi, 0, 0, null);
		g.dispose();

		return flipped;
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		
		g.setColor(Color.gray);
		g.fillRect(0, 630, 1280, 90);
		g.setColor(Color.black);
		g.drawLine(0, 630, 1280, 630);
		if (direction)
			g.drawImage(sprite, x,550, 100, 100, null);
		else if(!direction)
			g.drawImage(getFlippedImage(sprite), x,550, 100, 100, null);
		
	}
}
