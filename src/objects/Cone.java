package objects;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Cone extends Physics {
	
	public Cone(int x, int y){
		try {
			this.texture = ImageIO.read(new File("Resources/Objects/cone.png"));
			this.x = x;
			this.y = y;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
