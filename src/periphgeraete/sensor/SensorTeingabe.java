/**

 * Die Klasse SensorTeingabe erstellt einen JFrame mit einem Textfeld,

 * das zum Einstellen der Temperatur genutzt wird.

 *  @author Niklas Knauer

 */

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

public class SensorTeingabe extends JFrame {

    private ImageIcon icon;

    private JFrame sensorFrame = new JFrame("SensorTextfeld");

    private JPanel sensorPanel = new JPanel();

    private JLabel sensorLabel = new JLabel();

    private JTextField sensorEingabe = new JTextField("TempWert?");

    BildImplementierung bildkonf = new BildImplementierung();

    SensorRechnen senorR = new SensorRechnen();

    /**
     * 
     * Standardkonstruktor der Klasse SensorJFrame. Lieﬂt den eingetragenden
     * 
     * Wert aus und gibt ihn in der Konsole aus.
     */

    public SensorTeingabe() {

	try {

	    // quelle des Bildes:

	    // http://www.pce-instruments.com/deutsch/messtechnik-im-online-handel/

	    // messgeraete-fuer-alle-parameter/handtachometer-wachendorff-prozesstechnik-gmbh-laser-handtachometer-pce-155-det_11639.htm

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

    
    public void setKeyListener(KeyListener l){
	sensorEingabe.addKeyListener(l);
    }

    public void setMouseListener(MouseListener m) {
	sensorEingabe.addMouseListener(m);
    }

    public void print() {
	setKeyListener(new ActionListenerForBedienElement());

	setMouseListener(new ActionListenerForBedienElement());
    }

    class ActionListenerForBedienElement implements KeyListener, MouseListener
    {
	public void keyPressed(KeyEvent e){

	    if (e.getKeyCode() == KeyEvent.VK_ENTER) {

		try {
		    int i = Integer.valueOf(sensorEingabe.getText().trim()).intValue();

		    senorR.setTemperatur(i);

		} catch (NumberFormatException nfe) {

		    System.out

		    .println("NumberFormatException: " + nfe.getMessage());

		}

		senorR.printTemp();

	    }

	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	    // TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	    // TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent event) {

	    if (sensorEingabe.getText().equals("TempWert?"))

		sensorEingabe.setText("");

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	    // TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	    // TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	    // TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	    // TODO Auto-generated method stub

	}

    }// innere Klasse

}