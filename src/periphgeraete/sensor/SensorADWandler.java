package periphgeraete.sensor;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import periphgeraete.sensor.SensorTeingabe;
import periphgeraete.sensor.Sensor;
import werkzeug.BildImplementierung;

/**
 * @author Niklas Knauer
 */
public class SensorADWandler {

  private static final String degree = "/u00b0";

  private ImageIcon icon;
  private JFrame sensorFrame = new JFrame("AD-Wandler");
  private JPanel sensorPanel = new JPanel();
  private JLabel sensorLabel = new JLabel();
  private JLabel aufloesung_Label = new JLabel("Aufloesung:");
  private JLabel maxTemp_Label = new JLabel("Max. Temp.:");
  private JLabel verstaerkung_Label = new JLabel("Verstaerkung:");
  private JLabel bit_Label = new JLabel("Bit");
  private JLabel grad_Label = new JLabel(degree + "C");//asci code ist leider falsch
  private JLabel spannung_Label = new JLabel("V");
  private JSlider sensorSlider = new JSlider();
  private JSpinner aufloesung_Spinner = new JSpinner(new SpinnerNumberModel(8, 8, 12, 2));
  private JSpinner maxTemp_Spinner = new JSpinner(new SpinnerNumberModel(0,0,100,1));
  private JSpinner berTemp_Spinner = new JSpinner(new SpinnerNumberModel(5,1,10,3));
  private JTextField eingabe_Tfield = new JTextField("TempWert?");
  private JTextField aktuelleTemp_Tfield = new JTextField();
  private JTextField minTemp_Tfield = new JTextField("min");
  private JTextField maxTemp_Tfield = new JTextField("max");
  private ChangeListener listener;
  private ChangeListener spinnerListener;

  BildImplementierung bildkonf = new BildImplementierung();
  Sensor sensor = new Sensor();


  public SensorADWandler(String methode) {
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
	    // Quelle des Bildes:
	    // http://www.pce-instruments.com/deutsch/messtechnik-im-online-handel/messgeraete-fuer-alle-parameter/handtachometer-wachendorff-prozesstechnik-gmbh-laser-handtachometer-pce-155-det_11639.htm
	    icon = bildkonf.getImageIcon("Sensor");
    } catch (IOException e) {
      e.printStackTrace();
    }
  	sensorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  	sensorFrame.setSize(400, 200);
  	sensorPanel.setLayout(null);
  	sensorPanel.setSize(200, 200);
  	sensorPanel.setBackground(Color.orange);
  	eingabe_Tfield.setBounds(40, 100, 70, 20);
  	bildkonf.skalieren(icon);
  	sensorLabel.setBounds(25, 0, 100, 100);
  	sensorLabel.setIcon(icon);
  	aufloesung_Label.setBounds(130, 10, 70, 20);
  	maxTemp_Label.setBounds(130,32,70,20);
  	verstaerkung_Label.setBounds(130, 54, 110, 20);
  	bit_Label.setBounds(305,10,70,20);
  	grad_Label.setBounds(305,32,70,20);
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

  	sensorFrame.add(sensorPanel);
  	sensorFrame.setVisible(true);
  }

  private void rufeSliderMitTextfeldAuf() {
  	try {
	    // Quelle des Bildes:
	    // http://www.pce-instruments.com/deutsch/messtechnik-im-online-handel/messgeraete-fuer-alle-parameter/handtachometer-wachendorff-prozesstechnik-gmbh-laser-handtachometer-pce-155-det_11639.htm
	    icon = bildkonf.getImageIcon("Sensor");
    } catch (IOException e) {
      e.printStackTrace();
    }
  	sensorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  	sensorFrame.setSize(400, 400);

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
