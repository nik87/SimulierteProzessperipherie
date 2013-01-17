package periphgeraete.sensor;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import werkzeug.BildImplementierung;


/**
 * Die Klasse SensorTeingabe erstellt einen JFrame mit einem Textfeld,
 * das zum Einstellen der Temperatur genutzt wird.
 *
 * @author Niklas Knauer
 */
public class SensorTeingabe extends JFrame {

  private ImageIcon icon;
  private JFrame sensorFrame = new JFrame("SensorTextfeld");
  private JPanel sensorPanel = new JPanel();
  private JLabel sensorLabel = new JLabel();
  private JTextField sensorEingabe = new JTextField("TempWert?");
  BildImplementierung bildkonf = new BildImplementierung();
  SensorRechnen senorR = new SensorRechnen();

  /**
   * Standardkonstruktor der Klasse SensorJFrame. Liesst den eingetragenden
   * Wert aus und gibt ihn in der Konsole aus.
   */

  public SensorTeingabe() {
    try {
      // Quelle des Bildes:
	    // http://www.pce-instruments.com/deutsch/messtechnik-im-online-handel/messgeraete-fuer-alle-parameter/handtachometer-wachendorff-prozesstechnik-gmbh-laser-handtachometer-pce-155-det_11639.htm
	    icon = bildkonf.getImageIcon("Sensor");
    } catch (IOException e) {
	    e.printStackTrace();
    }

  	sensorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  	sensorFrame.setSize(260, 200);
  	sensorPanel.setLayout(null);
  	sensorPanel.setSize(200, 200);
  	sensorPanel.setBackground(Color.GRAY);
  	sensorEingabe.setBounds(10, 100, 70, 20);
  	bildkonf.skalieren(icon);
  	sensorLabel.setBounds(0, 0, 100, 100);
  	sensorLabel.setIcon(icon);
  	sensorPanel.add(sensorEingabe);
  	sensorPanel.add(sensorLabel);
  	sensorFrame.add(sensorPanel);
  	sensorFrame.setVisible(true);
  }

  public void setKeyListener(KeyListener l) {
    sensorEingabe.addKeyListener(l);
  }

  public void setMouseListener(MouseListener m) {
    sensorEingabe.addMouseListener(m);
  }

  public void print() {
    setKeyListener(new ActionListenerForBedienElement());
    setMouseListener(new ActionListenerForBedienElement());
  }


  class ActionListenerForBedienElement implements KeyListener, MouseListener {

    @Override
    public void keyPressed(KeyEvent e) {
	    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        try {
          int i = Integer.valueOf(sensorEingabe.getText().trim()).intValue();
          senorR.setTemperatur(i);
        } catch (NumberFormatException nfe) {
          System.out.println("NumberFormatException: " + nfe.getMessage());
        }
        senorR.printTemp();
	    }
    }

  	@Override
  	public void keyReleased(KeyEvent e) {}

  	@Override
  	public void keyTyped(KeyEvent e) {}

  	@Override
  	public void mouseClicked(MouseEvent e) {
      if (sensorEingabe.getText().equals("TempWert?"))
  		sensorEingabe.setText("");
  	}

  	@Override
  	public void mouseEntered(MouseEvent e) {}

  	@Override
  	public void mouseExited(MouseEvent e) {}

  	@Override
  	public void mousePressed(MouseEvent e) {}

  	@Override
  	public void mouseReleased(MouseEvent e) {}
  }
}
