
package RunningClasses;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import objects.Cone;


public class BufferedImageLoader {
	
	public BufferedImage loadImage(String pathRelativeToThis) throws IOException{
		File file = new File(pathRelativeToThis);
		URL url = file.toURI().toURL();
		BufferedImage img = ImageIO.read(url);
		return img;
	}

}
