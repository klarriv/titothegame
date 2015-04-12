package objects;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Maison {
	private DoublePoint position = new DoublePoint(1,1);
	private double width;
	private double height;
	private boolean isVisible = false;
	private BufferedImage texture;
	@SuppressWarnings("unused")
	private String which ="1";
	private String pathRelativeToThis;
	
	/**
	 * Creates a Maison with a texture
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param which
	 */
	public Maison(double x, double y, double width, double height, String which){
		isVisible = true;//since user uses this constructor... there is an image else use the other constructor
		this.position.x = x;
		this.position.y = y;
		this.width = width;
		this.height = height;
		
		if (isVisible){
			pathRelativeToThis = "Resources/Objects/Maison" + which +".png";
			loadImage();
		}
		
	}
	/**
	 * Creates a Maison with no texture
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Maison(double x, double y, double width, double height){
		
		this.position.x = x;
		this.position.y = y;
		this.width = width;
		this.height = height;
		
	}
	public boolean colliding(DoublePoint p){
		
		if (p.x >= position.x && p.x <= position.x + width 
				&& p.y >= position.y && p.y <= position.y + height)
			return true;
		
		
		return false;
	}

	public DoublePoint getPosition() {
		return position;
	}

	public void setPosition(DoublePoint position) {
		this.position = position;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}

	public String getPathRelativeToThis() {
		return pathRelativeToThis;
	}

	public void setPathRelativeToThis(String pathRelativeToThis) {
		this.pathRelativeToThis = pathRelativeToThis;
	}
	
	public void loadImage(){
		try{
		File file = new File(pathRelativeToThis);
		texture = ImageIO.read(file);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
