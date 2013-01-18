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
import periphgeraete.sensor.SensorRechnen;
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
  private JLabel spinnerLabel1 = new JLabel("Aufl"+oe+"sung:");
  private JLabel spinnerLabel2 = new JLabel("Max. Temp.:");
  private JLabel spinnerLabel3 = new JLabel("Verst"+ae+"rkung:");
  private JLabel spinnerLabel4 = new JLabel("Bit");
  private JLabel spinnerLabel5 = new JLabel(degree + "C");
  private JLabel spinnerLabel6 = new JLabel("V");
  private JSlider sensorSlider = new JSlider();
  private JSpinner spinnerAufl = new JSpinner(new SpinnerNumberModel(8, 8, 12, 2));
  private JSpinner spinnerMaxTemp = new JSpinner(new SpinnerNumberModel(0,0,100,1));
  private JSpinner spinnerBerTemp = new JSpinner(new SpinnerNumberModel(5,1,10,3));
  private JTextField sensorEingabe = new JTextField();
  private JTextField aktuelleTemp = new JTextField();
  private JTextField setSliderMinTemp = new JTextField();
  private JTextField setSliderMaxTemp = new JTextField();
  private ChangeListener listener;
  private ChangeListener spinnerListener;

  BildImplementierung bildkonf = new BildImplementierung();
  SensorRechnen sensorR = new SensorRechnen();


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

  	sensorEingabe.setBounds(40, 100, 70, 20);

  	bildkonf.skalieren(icon);
    sensorLabel.setIcon(icon);
  	sensorLabel.setBounds(0, 0, 100, 100);

  	spinnerLabel1.setBounds(130, 10, 100, 20);
  	spinnerLabel2.setBounds(130, 32, 100, 20);
  	spinnerLabel3.setBounds(130, 54, 110, 20);
  	spinnerLabel4.setBounds(305, 10,  70, 20);
  	spinnerLabel5.setBounds(305, 32,  70, 20);
  	spinnerLabel6.setBounds(305, 54,  70, 20);

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
