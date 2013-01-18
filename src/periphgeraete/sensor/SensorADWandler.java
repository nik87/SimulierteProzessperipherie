package periphgeraete.sensor;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import periphgeraete.sensor.SensorTeingabe;
import periphgeraete.sensor.Sensor;
import werkzeug.BildImplementierung;

/**
 * @author Niklas Knauer
 */
public class SensorADWandler {

  private static final String degree = "\u00b0";
  private static final String oe     = "\u00f6";
  private static final String ae     = "\u00e4";

  private ImageIcon icon;
  private JFrame f;
  private JPanel sensorPanel = new JPanel();
  private JLabel sensorLabel = new JLabel();
  private JLabel aufloesung_Label = new JLabel("Aufl"+oe+"sung:");
  private JLabel maxTemp_Label = new JLabel("Max. Temp.:");
  private JLabel verstaerkung_Label = new JLabel("Verst"+ae+"rkung:");
  private JLabel bit_Label = new JLabel("Bit");
  private JLabel grad_Label = new JLabel(degree + "C");
  private JLabel spannung_Label = new JLabel("V");
  private JSlider sensorSlider = new JSlider();
  private JSpinner aufloesung_Spinner = new JSpinner(new SpinnerNumberModel(8, 8, 12, 2));
  private JSpinner maxTemp_Spinner = new JSpinner(new SpinnerNumberModel(0,0,100,1));
  private JSpinner berTemp_Spinner = new JSpinner(new SpinnerNumberModel(5,1,10,3));
  private JTextField eingabe_Tfield = new JTextField();
  private JTextField aktuelleTemp_Tfield = new JTextField();
  private JTextField minTemp_Tfield = new JTextField();
  private JTextField maxTemp_Tfield = new JTextField();
  private ChangeListener listener;
  private ChangeListener spinnerListener;

  BildImplementierung bildkonf = new BildImplementierung();
  Sensor sensor = new Sensor();


  public SensorADWandler(String methode) {

    f = new JFrame("AD-Wandler");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  	switch (methode) {
      case "textfield":
        rufeEingabeMitTextfeldAuf();
        break;
      case "slider":
    		rufeSliderMitTextfeldAuf();
    		break;
    }
  }

  private void rufeEingabeMitTextfeldAuf() {
    try {
      icon = bildkonf.getImageIcon("Sensor");
    } catch(IOException ex) {
      ex.printStackTrace();
    }

  	f.setSize(400, 200);

  	sensorPanel.setLayout(null);
  	sensorPanel.setSize(200, 200);
  	sensorPanel.setBackground(Color.ORANGE);

  	bildkonf.skalieren(icon);
  	sensorLabel.setIcon(icon);
  	sensorLabel.setBounds(25, 0, 100, 100);

  	eingabe_Tfield.setBounds(40, 100, 70, 20);
  	aufloesung_Label.setBounds(130, 10, 100, 20);
  	maxTemp_Label.setBounds(130, 32, 100, 20);
  	verstaerkung_Label.setBounds(130, 54, 110, 20);
  	bit_Label.setBounds(305, 10, 70, 20);
  	grad_Label.setBounds(305, 32, 70, 20);
  	spannung_Label.setBounds(305, 54, 70, 20);

  	aufloesung_Spinner.setBounds(250, 10, 50, 20);
  	aufloesung_Spinner.addChangeListener(new ActionListenerForBedienElement());

  	maxTemp_Spinner.setBounds(250, 32, 50, 20);
  	maxTemp_Spinner.addChangeListener(new ActionListenerForBedienElement());

  	berTemp_Spinner.setBounds(250, 54, 50, 20);
  	berTemp_Spinner.addChangeListener(new ActionListenerForBedienElement());

  	sensorPanel.add(aufloesung_Label);
  	sensorPanel.add(maxTemp_Label);
  	sensorPanel.add(verstaerkung_Label);
  	sensorPanel.add(bit_Label);
  	sensorPanel.add(grad_Label);
  	sensorPanel.add(spannung_Label);
  	sensorPanel.add(aufloesung_Spinner);
  	sensorPanel.add(maxTemp_Spinner);
  	sensorPanel.add(berTemp_Spinner);
  	sensorPanel.add(eingabe_Tfield);
  	sensorPanel.add(sensorLabel);

    // Get screen dimensions
    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension screenSize = tk.getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;

  	f.add(sensorPanel);

    Dimension dim = f.getSize();
    f.setLocation(0, screenHeight - dim.height);

    f.setResizable(false);
  	f.setVisible(true);
  }

  private void rufeSliderMitTextfeldAuf() {
    // icon = bildkonf.getImageIcon("Sensor");

  	f.setSize(400, 400);

  	sensorPanel.setLayout(null);
  	sensorPanel.setSize(400, 200);
  	sensorPanel.setBackground(Color.GRAY);
  	aktuelleTemp_Tfield.setBounds(100, 115, 25, 20);
  }

  private void setKeyListener(KeyListener l) {
    eingabe_Tfield.addKeyListener(l);
  }

  private void setMouseListener(MouseListener m) {
    eingabe_Tfield.addMouseListener(m);
  }

  public void print() {
  	setKeyListener(new ActionListenerForBedienElement());
  	setMouseListener(new ActionListenerForBedienElement());
  }

  class ActionListenerForBedienElement implements KeyListener,
                                                  MouseListener,
                                                  ChangeListener {
    @Override
    public void keyPressed(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        try {
          int i = Integer.valueOf(eingabe_Tfield.getText().trim()).intValue();
          sensor.setTemperatur(i);
		    } catch (NumberFormatException nfe) {
          System.out.println("NumberFormatException: " + nfe.getMessage());
        }
		    sensor.printTemp();
		    sensor.wandleAD(
          Double.valueOf(eingabe_Tfield.getText().trim()).intValue(),
                         sensor.getIntSpinnerAufl(),
                         sensor.getDoubleSpinnerMaxTemp(),
                         sensor.getIntSpinnerBereichsSpannung());
		    sensor.printADbinaer();
		    sensor.printADhex();
	    }
    }

  	@Override
  	public void keyReleased(KeyEvent e) {}

  	@Override
  	public void keyTyped(KeyEvent e) {}

  	@Override
  	public void mouseClicked(MouseEvent e) {
      if (eingabe_Tfield.getText().equals("TempWert?"))
        eingabe_Tfield.setText("");
  	}

  	@Override
  	public void mouseEntered(MouseEvent e) {}

  	@Override
  	public void mouseExited(MouseEvent e) {}

  	@Override
  	public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void stateChanged(ChangeEvent e) {
	    int intSpinnerAufl = (Integer)aufloesung_Spinner.getValue();
	    sensor.setIntSpinnerAufl(intSpinnerAufl);
	    int intSpinnerMaxTemp = (Integer)maxTemp_Spinner.getValue();
	    double doubleSpinnerMaxTemp= (double) intSpinnerMaxTemp;
	    sensor.setDoubleSpinnerMaxTemp(doubleSpinnerMaxTemp);
	    int intSpinnerBereichsSpannung = (Integer)berTemp_Spinner.getValue();
	    sensor.setIntSpinnerBereichsSpannung(intSpinnerBereichsSpannung);
    }
  }
}
