package periphgeraete.motor;

import java.awt.*;

import javax.swing.JPanel;

import werkzeug.BildImplementierung;

/**
 * @author KingLui
 */
public class MotorPanel extends JPanel implements Runnable {
  private Thread animation;
  private Image arImg[];
  private int index;
  private MediaTracker mt;
  private Toolkit tk;

  public MotorPanel() {
    setPreferredSize(new Dimension(400, 300));
    setSize(200, 150);
    setBackground(Color.BLUE);
    startAnimation();
  }

  public void startAnimation() {
    animation = new Thread(this);
    animation.start();
  }

  @Override
  public void run() {
    arImg = new Image[2];
    mt = new MediaTracker(this);
    tk = getToolkit();
    for (int i = 0; i < 2; i++) {
      arImg[i] = tk.getImage("/images/propeller" + i + ".gif");
      mt.addImage(arImg[i], 1);

      // System.out.println("armIMg" + arImg[i]);

      try {
        mt.waitForID(1);
      } catch (InterruptedException ex) {
        System.out.println("Fehler beim warten");
      }
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
        if (index >= arImg.length) {
          index = 0;
          // System.out.println("index 0: " + index);
        }
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

    // TODO

    g2.dispose();
  }
}
