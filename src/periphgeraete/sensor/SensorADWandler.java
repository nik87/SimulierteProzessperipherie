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
import periphgeraete.sensor.SensorRechnen;
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
  private JLabel spinnerLabel1 = new JLabel("Aufloesung:");
  private JLabel spinnerLabel2 = new JLabel("Max. Temp.:");
  private JLabel spinnerLabel3 = new JLabel("Verstaerkung:");
  private JLabel spinnerLabel4 = new JLabel("Bit");
  private JLabel spinnerLabel5 = new JLabel(degree + "C");
  private JLabel spinnerLabel6 = new JLabel("V");
  private JSlider sensorSlider = new JSlider();
  private JSpinner spinnerAufl = new JSpinner(new SpinnerNumberModel(8, 8, 12, 2));
  private JSpinner spinnerMaxTemp = new JSpinner(new SpinnerNumberModel(0,0,100,1));
  private JSpinner spinnerBerTemp = new JSpinner(new SpinnerNumberModel(5,1,10,3));
  private JTextField sensorEingabe = new JTextField("TempWert?");
  private JTextField aktuelleTemp = new JTextField();
  private JTextField setSliderMinTemp = new JTextField("min");
  private JTextField setSliderMaxTemp = new JTextField("max");
  private ChangeListener listener;
  private ChangeListener spinnerListener;

  BildImplementierung bildkonf = new BildImplementierung();
  SensorRechnen sensorR = new SensorRechnen();


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
  	sensorEingabe.setBounds(40, 100, 70, 20);
  	bildkonf.skalieren(icon);
  	sensorLabel.setBounds(25, 0, 100, 100);
  	sensorLabel.setIcon(icon);
  	spinnerLabel1.setBounds(130, 10, 70, 20);
  	spinnerLabel2.setBounds(130,32,70,20);
  	spinnerLabel3.setBounds(130, 54, 110, 20);
  	spinnerLabel4.setBounds(305,10,70,20);
  	spinnerLabel5.setBounds(305,32,70,20);
  	spinnerLabel6.setBounds(305, 54, 70, 20);

  	spinnerAufl.setBounds(250, 10, 50, 20);
  	spinnerAufl.addChangeListener(new ActionListenerForBedienElement());

  	spinnerMaxTemp.setBounds(250, 32, 50, 20);
  	spinnerMaxTemp.addChangeListener(new ActionListenerForBedienElement());

  	spinnerBerTemp.setBounds(250, 54, 50, 20);
  	spinnerBerTemp.addChangeListener(new ActionListenerForBedienElement());

  	sensorPanel.add(spinnerLabel1);
  	sensorPanel.add(spinnerLabel2);
  	sensorPanel.add(spinnerLabel3);
  	sensorPanel.add(spinnerLabel4);
  	sensorPanel.add(spinnerLabel5);
  	sensorPanel.add(spinnerLabel6);
  	sensorPanel.add(spinnerAufl);
  	sensorPanel.add(spinnerMaxTemp);
  	sensorPanel.add(spinnerBerTemp);
  	sensorPanel.add(sensorEingabe);
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
  	aktuelleTemp.setBounds(100, 115, 25, 20);
  }

  private void setKeyListener(KeyListener l) {
    sensorEingabe.addKeyListener(l);
  }

  private void setMouseListener(MouseListener m) {
    sensorEingabe.addMouseListener(m);
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
          int i = Integer.valueOf(sensorEingabe.getText().trim()).intValue();
          sensorR.setTemperatur(i);
		    } catch (NumberFormatException nfe) {
          System.out.println("NumberFormatException: " + nfe.getMessage());
        }
		    sensorR.printTemp();
		    sensorR.wandleAD(
          Double.valueOf(sensorEingabe.getText().trim()).intValue(),
                         sensorR.getIntSpinnerAufl(),
                         sensorR.getDoubleSpinnerMaxTemp(),
                         sensorR.getIntSpinnerBereichsSpannung());
		    sensorR.printADbinaer();
		    sensorR.printADhex();
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

    @Override
    public void stateChanged(ChangeEvent e) {
	    int intSpinnerAufl = (Integer)spinnerAufl.getValue();
	    sensorR.setIntSpinnerAufl(intSpinnerAufl);
	    int intSpinnerMaxTemp = (Integer)spinnerMaxTemp.getValue();
	    double doubleSpinnerMaxTemp= (double) intSpinnerMaxTemp;
	    sensorR.setDoubleSpinnerMaxTemp(doubleSpinnerMaxTemp);
	    int intSpinnerBereichsSpannung = (Integer)spinnerBerTemp.getValue();
	    sensorR.setIntSpinnerBereichsSpannung(intSpinnerBereichsSpannung);
    }
  }
}
