package objects;

import java.awt.image.BufferedImage;
import windows.MainFrame;
/**
 * The Tree object 
 * @author CharlesPhilippe
 *
 */
public class Tree {
	/**
	 * The BufferedImage of the tree
	 */
	private BufferedImage texture;
	/**
	 * The current position of the tree
	 */
	private DoublePoint position;
	/**
	 * Creates a new Tree at the specified position
	 * @param x
	 * @param y
	 */
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
