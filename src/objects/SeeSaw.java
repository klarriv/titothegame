package objects;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SeeSaw {
	private BufferedImage texture;
	private String pathRelativeToThis = "Resources/Objects/seeSaw.png";
	private DoublePoint position;

	public SeeSaw(double x, double y){
		
		setPosition(new DoublePoint(x, y));
		loadImage();
	}

	public void loadImage(){
		try{
		File file = new File(pathRelativeToThis );
		texture = ImageIO.read(file);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
