package periphgeraete.motor;

import java.awt.*;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import werkzeug.BildImplementierung;

/**
 * @author KingLui
 */
public class MotorPanel extends JPanel implements Runnable {
  private Thread animation;
  private Image images[];
  private int index;

  public MotorPanel() {
    setPreferredSize(new Dimension(350, 350));
    setSize(200, 150);
    setBackground(Color.BLUE);

    images = new Image[4];

    startAnimation();
  }

  public void startAnimation() {
    animation = new Thread(this);
    animation.start();
  }

  @Override
  public void run() {

    for (int i = 0; i < 4; i++) {
      URL url = getClass().getResource("/images/propeller" + i + ".jpg");
      try {
        images[i] = ImageIO.read(url);
      } catch(IOException ex) {
        ex.printStackTrace();
      }
      // System.out.println("url: " + url.toString());
    }

    // Animation beginnen
    Thread me = Thread.currentThread();
    while (animation == me) {
      try {
        Thread.sleep(50);
      } catch (InterruptedException ex) {
        break;
      }
      synchronized (this) {
        index++;
        // System.out.println("index: " + index);
        if (index >= images.length) index = 0;
      }
      repaint();
    }
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    // provide a better graphics class
    Graphics2D g2 = (Graphics2D) g.create();
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

    g2.drawImage(images[index], 0, 0, null);

    g2.dispose();
  }
}
