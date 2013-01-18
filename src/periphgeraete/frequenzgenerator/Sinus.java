package periphgeraete.frequenzgenerator;

import java.awt.*;

import javax.swing.JFrame;


public class Sinus  {

  private JFrame f;

  public Sinus(int amplitude, int frequenz, int phasenverschiebung) {
    f = new JFrame("Sinuskurve");

    SinusPanel sinuspanel = new SinusPanel(amplitude,
                                           frequenz,
                                           phasenverschiebung);
    sinuspanel.setPreferredSize(new Dimension(400,300));

    // Get screen dimensions
    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension screenSize = tk.getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;

    f.add(sinuspanel);
    f.pack();

    Dimension dim = f.getSize();
    f.setLocation(screenWidth - dim.width, 0);

    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setBackground(Color.GRAY);
    f.setResizable(false);
    f.setVisible(true);
   }
}
