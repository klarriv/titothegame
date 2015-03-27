package objects;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TrashCan extends Physics {

	public TrashCan(int x, int y){
		
		try {
			this.texture = ImageIO.read(new File("Resources/Objects/trashCan.png"));
			this.x = x;
			this.y = y;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
