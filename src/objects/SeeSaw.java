package objects;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SeeSaw extends Physics{

	public SeeSaw(int x, int y){
		
		try {
			this.texture = ImageIO.read(new File("Resources/Objects/seeSaw.png"));
			this.x = x;
			this.y = y;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
