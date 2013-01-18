package periphgeraete.sensor;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.*;
import java.io.IOException;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import werkzeug.BildImplementierung;


/**
 * Die Klasse SensorSlider erstellt einen JFrame mit einem JSlider,
 * der zum Einstellen der Temperatur genutzt wird.
 *
 * @author Niklas Knauer
 */
public class SensorSlider extends JFrame implements MouseListener {

  private int minimum;
  private int maximum;

  private ImageIcon icon;

  private JFrame f;
  private JPanel sensorPanel = new JPanel();
  private JLabel sensorLabel = new JLabel();
  private JLabel sensorLabel1 = new JLabel("min");
  private JLabel sensorLabel2 = new JLabel("max");
  private JLabel sensorLabel3 = new JLabel("aktuelle Temperatur");
  private JSlider sensorSlider = new JSlider();
  private JTextField aktuelleTemp = new JTextField();
  private JTextField setSliderMinTemp = new JTextField();
  private JTextField setSliderMaxTemp = new JTextField();
  private ChangeListener listener;

  BildImplementierung bildkonf = new BildImplementierung();
  SensorRechnen senorR = new SensorRechnen();

  /**
   * Der Standardkonstruktor der Klasse SensorJSlider,
   * bietet die Moeglichkeit den Wertebereich des JSliders
   * in der GUI ein zu stellen.
   */
  public SensorSlider() {
    f = new JFrame("SensorSlider");

    try {
      icon = bildkonf.getImageIcon("Sensor");
    } catch(IOException ex) {
      ex.printStackTrace();
    }

    sensorPanel.setLayout(null);
    sensorPanel.setSize(250, 200);
    sensorPanel.setBackground(Color.GRAY);
    aktuelleTemp.setBounds(100, 115, 25, 20);

    setSliderMinTemp.setBounds(100, 80, 30, 25);
    setSliderMinTemp.setBackground(Color.green);
    setSliderMinTemp.addMouseListener(this);
    setSliderMaxTemp.setBounds(140, 80, 30, 25);
    setSliderMaxTemp.setBackground(Color.RED);
    setSliderMaxTemp.addMouseListener(this);

    listener = new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent event) {
        // Textfeld aktualisieren, wenn sich Schieberegler aendert

        JSlider source = (JSlider) event.getSource();
        aktuelleTemp.setText(String.valueOf(source.getValue()));
        minimum = Integer.parseInt(setSliderMinTemp.getText());
        maximum = Integer.parseInt(setSliderMaxTemp.getText());

        sensorSlider.setMinimum(minimum);
        sensorSlider.setMaximum(maximum);

        senorR.setTemperatur(source.getValue());
        senorR.printTemp();
      }
    };

    // Teilstriche werden angezeigt
    sensorSlider.setPaintTicks(true);

    // setzt Teilstriche bei Vielfachen der Einheit
    sensorSlider.setMajorTickSpacing(20);
    sensorSlider.setMinorTickSpacing(5);

    sensorSlider.setBounds(0, 101, 100, 50);
    sensorSlider.addChangeListener(listener);

    bildkonf.skalieren(icon);
    sensorLabel.setIcon(icon);
    sensorLabel.setBounds(0, 0, 100, 100);
    sensorLabel1.setBounds(100, 60, 70, 20);
    sensorLabel2.setBounds(140,60,70,20);
    sensorLabel3.setBounds(130, 115, 120, 20);

    sensorPanel.add(setSliderMinTemp);
    sensorPanel.add(setSliderMaxTemp);
    sensorPanel.add(aktuelleTemp);
    sensorPanel.add(sensorLabel);
    sensorPanel.add(sensorLabel1);
    sensorPanel.add(sensorLabel2);
    sensorPanel.add(sensorLabel3);
    sensorPanel.add(sensorSlider);

    // Get screen dimensions
    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension screenSize = tk.getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;

    f.add(sensorPanel);
    f.setSize(265, 200);

    Dimension dim = f.getSize();
    f.setLocation(0, screenHeight/2 - dim.height);

    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setResizable(false);
    f.setVisible(true);
  }

  /**
   * Der Konstruktor mit Uebergabeparameter bietet die Moeglichkeit an
   * den Wertebereich ueber die Parameter ein zu stellen.
   *
   * @param minimumSkala
   * @param maximumSkala
   */
  public SensorSlider(int minimumSkala, int maximumSkala) {
    minimum = minimumSkala;
    maximum = maximumSkala;

    try {
      icon = bildkonf.getImageIcon("Sensor");
    } catch(IOException ex) {
      ex.printStackTrace();
    }

    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setSize(200, 200);

    sensorPanel.setLayout(null);
    sensorPanel.setSize(200, 200);
    sensorPanel.setBackground(Color.GRAY);
    aktuelleTemp.setBounds(100, 115, 25, 20);

    listener = new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent event) {
        // Textfeld aktualisieren, wenn sich Schieberegler aendert
        JSlider source = (JSlider) event.getSource();
        aktuelleTemp.setText(String.valueOf(source.getValue()));

        senorR.setTemperatur(source.getValue());
        senorR.printTemp();

        sensorSlider.setMinimum(minimum);
        sensorSlider.setMaximum(maximum);
      }
    };

    // Teilstriche werden angezeigt
    sensorSlider.setPaintTicks(true);

    // setzt Teilstriche bei Vielfachen der Einheit
    sensorSlider.setMajorTickSpacing(20);
    sensorSlider.setMinorTickSpacing(5);

    sensorSlider.setBounds(0, 101, 100, 50);

    sensorSlider.addChangeListener(listener);

    bildkonf.skalieren(icon);
    sensorLabel.setIcon(icon);
    sensorLabel.setBounds(0, 0, 100, 100);

    sensorPanel.add(aktuelleTemp);
    sensorPanel.add(sensorLabel);
    sensorPanel.add(sensorSlider);

    f.add(sensorPanel);
    f.setResizable(false);
    f.setVisible(true);
  }

  /**
   * Bietet die Moeglichkeit das private Atribut der Klasse SensorRechnung
   * aus zu lesen.
   * @return  die aktuelle Temperatur
   */
  public int getAktuellenWert() {
    return senorR.getTemp();
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (setSliderMinTemp.getText().equals("min"))
      setSliderMinTemp.setText("");

    if (setSliderMaxTemp.getText().equals("max"))
      setSliderMaxTemp.setText("");
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
