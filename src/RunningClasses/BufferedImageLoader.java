
package RunningClasses;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import objects.Cone;

/**
 * Loads a BufferedImage with the specific path
 * @author CharlesPhilippe
 *
 */
public class BufferedImageLoader {
	/**
	 * Loads a new BufferedImage
	 * @param pathRelativeToThis
	 * @return
	 * @throws IOException
	 */
	public BufferedImage loadImage(String pathRelativeToThis) throws IOException{
		File file = new File(pathRelativeToThis);
		URL url = file.toURI().toURL();
		BufferedImage img = ImageIO.read(url);
		return img;
	}

}
