package werkzeug;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class BildImplementierung {

	public ImageIcon getImageIcon(String filename) throws IOException {
		URL rurl = getClass().getResource("/images/" + filename + ".gif");
		Image image = ImageIO.read(rurl);
		ImageIcon icon = new ImageIcon(image);

		return icon;
	}

	public void skalieren(ImageIcon icon) {
		icon.setImage(icon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
	}
}
