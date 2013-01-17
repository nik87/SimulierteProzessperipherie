package periphgeraete.motor;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * @author KingLui
 */
public class Motor {
  private String linksDrehen;
  private String rechtsDrehen;
  private boolean an  = true;
  private boolean aus = false;

  private JFrame f;
  private MotorPanel mp;

  public Motor() {
    this.mp = new MotorPanel();
    this.f  = new JFrame("Motor");

    // Get screen dimensions
    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension screenSize = tk.getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;

    f.add(mp);
    f.pack();

    Dimension dim = f.getSize();
    f.setLocation(screenWidth - dim.width, screenHeight - dim.height);

    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setResizable(false);
    f.setVisible(true);
  }

  public  void setDrehrichtungLinks(String linksDrehen) {
    this.linksDrehen = linksDrehen;
  }

  public void setDrehrichtungRechts(String rechtsDrehen) {
    this.rechtsDrehen = rechtsDrehen;
  }

  public void setAn(boolean an) {
    this.an = an;
  }

  public void setAus(boolean aus) {
    this.aus = aus;
  }

  public String getDrehrichtungLinks() {
    return linksDrehen;
  }

  public String getDrehrichtungRechts() {
    return rechtsDrehen;
  }

  public boolean getAn(){
    return an;
  }

  public boolean getAus(){
    return aus;
  }
}
