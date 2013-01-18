package periphgeraete.sensor;

import java.awt.Color;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

import werkzeug.BildImplementierung;


/**
 * Die Klasse SensorTeingabe erstellt einen JFrame mit einem Textfeld,
 * das zum Einstellen der Temperatur genutzt wird.
 *
 * @author Niklas Knauer
 */
public class SensorTeingabe extends JFrame {

  private ImageIcon icon;

  private JFrame f;
  private JPanel sensorPanel = new JPanel();
  private JLabel sensorLabel = new JLabel();
  private JTextField eingabe_Tfield = new JTextField();
  private Sensor senor = new Sensor();
  private BildImplementierung bildkonf = new BildImplementierung();

  /**
   * Standardkonstruktor der Klasse SensorJFrame. Liesst den eingetragenden
   * Wert aus und gibt ihn in der Konsole aus.
   */
  public SensorTeingabe(String name) {
	  f = new JFrame(name);

    try {
      icon = bildkonf.getImageIcon("Sensor");
    } catch(IOException ex) {
      ex.printStackTrace();
    }

  	sensorLabel.setBounds(0, 0, 100, 100);

    sensorPanel.setLayout(null);
    sensorPanel.setSize(200, 200);
    sensorPanel.setBackground(Color.GRAY);
    eingabe_Tfield.setBounds(10, 100, 70, 20);

    bildkonf.skalieren(icon);
    sensorLabel.setIcon(icon);
    sensorLabel.setBounds(0, 0, 100, 100);

    sensorPanel.add(eingabe_Tfield);
    sensorPanel.add(sensorLabel);

    f.add(sensorPanel);
    f.setSize(260, 200);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setResizable(false);
    f.setVisible(true);
  }

  public void setKeyListener(KeyListener l) {
    eingabe_Tfield.addKeyListener(l);
  }

  public void setMouseListener(MouseListener l) {
    eingabe_Tfield.addMouseListener(l);
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
          int i = Integer.valueOf(eingabe_Tfield.getText().trim()).intValue();
          senor.setTemperatur(i);
        } catch (NumberFormatException ex) {
          System.out.println("NumberFormatException: " + ex.getMessage());
        }
        senor.printTemp();
	    }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

  	@Override
  	public void mouseClicked(MouseEvent e) {}

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
