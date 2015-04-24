package objects;

import java.awt.image.BufferedImage;
import windows.MainFrame;

public class Tree {
	
	private BufferedImage texture;
	private DoublePoint position;

	public Tree(double x, double y){
		
		setPosition(new DoublePoint(x, y));
		texture = MainFrame.getTl().treeTexture;
		
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public DoublePoint getPosition() {
		return position;
	}

	public void setPosition(DoublePoint position) {
		this.position = position;
	}
}
