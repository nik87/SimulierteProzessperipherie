package werkzeug;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class BildImplementierung {

	public ImageIcon getImageIcon(String name) throws IOException{

	java.net.URL rurl = getClass().getResource("/Bilder/" + name + ".gif");

	Image image = ImageIO.read(rurl); 

	ImageIcon icon = new ImageIcon(image);

	return icon;

	}
	
	public void skalieren(ImageIcon icon){
		icon.setImage(icon.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));
	}

}
