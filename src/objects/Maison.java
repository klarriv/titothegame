package objects;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import windows.MainFrame;

public class Maison {
	private DoublePoint position = new DoublePoint(1,1);
	private double width;
	private double height;
	private boolean isVisible = false;
	private BufferedImage texture;
	@SuppressWarnings("unused")
	private String which ="1";
	private String pathRelativeToThis;
	private Plane plane1 = null;
	private Plane plane2 = null;
	private DoubleRectangle r;

	/**
	 * Creates a Maison with a texture
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param which
	 */
	public Maison(double x, double y, double width, double height, int which){
		isVisible = true;//since user uses this constructor... there is an image else use the other constructor
		this.position.x = x;
		this.position.y = y;
		this.width = width;
		this.height = height;
		
		switch (which){
		case 0: setR(new DoubleRectangle(position.x+81*width/MainFrame.getTl().maison[0].getWidth(), position.y+95*height/MainFrame.getTl().maison[0].getHeight(), width-(81*width/MainFrame.getTl().maison[0].getWidth()), height-(95*height/MainFrame.getTl().maison[0].getHeight())));
				break;
		case 1: setR(new DoubleRectangle(position, width, height));
				break;
		case 2: setR(new DoubleRectangle(position.x+55*width/MainFrame.getTl().maison[2].getWidth(), position.y, width-(106*width/MainFrame.getTl().maison[2].getWidth()), height));
				break;
		case 3: setR(new DoubleRectangle(position.x, position.y+453*height/MainFrame.getTl().maison[3].getHeight(), width, height-453*height/MainFrame.getTl().maison[3].getHeight()));
				break;
		case 4: setR(new DoubleRectangle(position.x+104*width/MainFrame.getTl().maison[4].getWidth(), position.y+161*height/MainFrame.getTl().maison[4].getHeight(), width-218*width/MainFrame.getTl().maison[4].getWidth(), height-161*height/MainFrame.getTl().maison[4].getHeight()));
				break;
		case 5: setR(new DoubleRectangle(position.x+236*width/MainFrame.getTl().maison[5].getWidth(), position.y+600*height/MainFrame.getTl().maison[5].getHeight(), width-236*width/MainFrame.getTl().maison[5].getWidth(), height-600*height/MainFrame.getTl().maison[5].getHeight()));
				break;
		}
		
		texture = MainFrame.getTl().maison[which];
		
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
	
	public void addPlane1(Plane plane){
		this.plane1 = plane;
		
	}
	public void addPlane2(Plane plane){
		this.plane2 = plane;
	}
	
	public DoubleRectangle getR() {
		return r;
	}
	
	public void setR(DoubleRectangle r) {
		this.r = r;
	}
	/**
	 * Not suggested
	 * @param plane
	 */
	@Deprecated
	public void addPlane(Plane plane){
		if (this.plane1 == null){
			this.plane1 = plane;
			this.plane1.setAnchor1(this.position);
			this.plane1.setAnchor2X();
			this.plane1.setAnchor2Y();
		}
		else if (this.plane2 == null && this.plane1 !=null){
			this.plane2 = plane;
			this.plane2.setAnchor1(this.plane1.getAnchor2());
			this.plane2.setAnchor2X();
			this.plane2.setAnchor2Y();
		}
	}
	/**
	 * 
	 * @param plane
	 */
	 public void addPlanes(Plane plane){
		 if (this.plane1 == null)
				this.plane1 = plane;
		 
		 else if (this.plane2 == null && this.plane1 !=null)
				this.plane2 = plane;
		 
		 plane.setUsed(0);
	 }

}
