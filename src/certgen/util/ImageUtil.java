package certgen.util;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Class for work with images.
 * 
 * @author Dražen Đanić
 */
public class ImageUtil {
	
	/**
	 * Returns loaded image from given URL.
	 * 
	 * @param url - URL to image
	 * @return loaded image or <code>null</code> if an error occurred
	 */
	public static Image loadImage(URL url) {
		Image image = null;
		
		try {
			image = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return image;
	}

	/**
	 * Returns loaded image from given URL and set its width and height to given width and height.
	 * 
	 * @param url - URL to image
	 * @param width - the width to which to scale the image
	 * @param height - the height to which to scale the image
	 * @return loaded and scaled version of the image or <code>null</code> if an error occurred
	 */
	public static Image loadImage(URL url, int width, int height) {
		Image image = null;
		
		try {
			image = ImageIO.read(url).getScaledInstance(width, height, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return image;
	}
	
	/**
	 * Returns loaded icon image from given URL and set its width and height to given width and height.
	 * 
	 * @param url - URL to image
	 * @param width - the width to which to scale the image
	 * @param height - the height to which to scale the image
	 * @return loaded and scaled version of the icon image or <code>null</code> if an error occurred
	 */
	public static ImageIcon loadImageIcon(URL url, int width, int height) {
		ImageIcon imageIcon = null;
		
		imageIcon = new ImageIcon(loadImage(url, width, height));
		
		return imageIcon;
	}
	
}
