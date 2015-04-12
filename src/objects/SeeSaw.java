package objects;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import windows.MainFrame;

public class SeeSaw {
	private BufferedImage texture;
	private DoublePoint position;

	public SeeSaw(double x, double y){
		
		setPosition(new DoublePoint(x, y));
		//loadImage();
		texture = MainFrame.getTl().seesawTexture;
	}

	public BufferedImage getTexture() {
		// TODO Auto-generated method stub
		return texture;
	}

	public DoublePoint getPosition() {
		return position;
	}

	public void setPosition(DoublePoint position) {
		this.position = position;
	}

}
