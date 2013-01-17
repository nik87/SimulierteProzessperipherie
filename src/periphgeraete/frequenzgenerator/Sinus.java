package periphgeraete.frequenzgenerator;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;


public class Sinus  {
	//SinusPanel sinuspanel = new SinusPanel();
	JFrame frame = new JFrame("Sinuskurve");
   public Sinus(int amplitude, int frequenz, int phasenverschiebung) {
      SinusPanel sinuspanel = new SinusPanel(amplitude,frequenz,phasenverschiebung);
      frame.setLocationRelativeTo(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      sinuspanel.setPreferredSize(new Dimension(400,300));
      frame.add(sinuspanel);
      frame.setBackground(Color.gray);
      frame.setResizable(false);
      frame.pack();
      frame.setVisible(true);
   }


   
}
