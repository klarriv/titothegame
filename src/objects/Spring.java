package objects;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spring extends Physics{
	
	public Spring(double x, double y){
		
		position = new DoublePoint(x, y);
		
		try {
			this.texture = ImageIO.read(new File("Resources/Objects/spring.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
