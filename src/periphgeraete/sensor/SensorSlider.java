/**
 * Die Klasse SensorSlider erstellt einen JFrame mit einem JSlider, 
 * der zum Einstellen der Temperatur genutzt wird.
 *  
 * @author Niklas Knauer
 * 
 */
package periphgeraete.sensor;



import java.awt.Color;
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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


import werkzeug.BildImplementierung;

public class SensorSlider extends JFrame implements MouseListener {

    private int minimum;
    private int maximum;

    private ImageIcon icon;

    private JFrame sensorFrame = new JFrame("SensorSlider");
    private JPanel sensorPanel = new JPanel();
    private JLabel sensorLabel = new JLabel();
    private JLabel sensorLabel1 = new JLabel("min");
    private JLabel sensorLabel2 = new JLabel("max");
    private JLabel sensorLabel3 = new JLabel("aktuelle Temperatur");
    private JSlider sensorSlider = new JSlider();
    private JTextField aktuelleTemp = new JTextField();
    private JTextField setSliderMinTemp = new JTextField("min");
    private JTextField setSliderMaxTemp = new JTextField("max");
    private ChangeListener listener;

    BildImplementierung bildkonf = new BildImplementierung();
    SensorRechnen senorR = new SensorRechnen();

    /**
     *Der Standardkonstruktor der Klasse SensorJSlider,
     *bietet die Möglichkeit den Wertebereich des JSliders 
     *in der GUI ein zu stellen. 
     */
    public SensorSlider() {

	try {

	    // quelle des Bildes:
	    // http://www.pce-instruments.com/deutsch/messtechnik-im-online-handel/
	    // messgeraete-fuer-alle-parameter/handtachometer-wachendorff-prozesstechnik-gmbh-laser-handtachometer-pce-155-det_11639.htm

	    icon = bildkonf.getImageIcon("Sensor");
	} catch (IOException e) {

	    e.printStackTrace();
	}
	sensorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	sensorFrame.setSize(265, 200);

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
		// Textfeld aktualisieren, wenn sich Schieberegler ändert

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

	sensorSlider.setPaintTicks(true); // Teilstriche werden angezeigt
	sensorSlider.setMajorTickSpacing(20); // setzt Teilstriche bei
					      // Vielfachen der Einheit
	sensorSlider.setMinorTickSpacing(5); // setzt Teilstriche bei Vielfachen
					     // der Einheit
	sensorSlider.setBounds(0, 101, 100, 50);

	sensorSlider.addChangeListener(listener);

	bildkonf.skalieren(icon);
	sensorLabel.setBounds(0, 0, 100, 100);
	sensorLabel1.setBounds(100, 60, 70, 20);
	sensorLabel2.setBounds(140,60,70,20);
	sensorLabel3.setBounds(130, 115, 120, 20);
	sensorLabel.setIcon(icon);

	sensorPanel.add(setSliderMinTemp);
	sensorPanel.add(setSliderMaxTemp);
	sensorPanel.add(aktuelleTemp);
	sensorPanel.add(sensorLabel);
	sensorPanel.add(sensorLabel1);
	sensorPanel.add(sensorLabel2);
	sensorPanel.add(sensorLabel3);
	sensorPanel.add(sensorSlider);
	sensorFrame.add(sensorPanel);
	sensorFrame.setVisible(true);

    }

    /**
     * Der Konstruktor mit Uebergabeparameter bietet die Möglichkeit an
     * den Wertebereich über die Parameter ein zu stellen.
     * 
     * @param minimumSkala
     * @param maximumSkala
     */
    public SensorSlider(int minimumSkala, int maximumSkala) {
	minimum = minimumSkala;
	maximum = maximumSkala;
	try {

	    // quelle des Bildes:
	    // http://www.pce-instruments.com/deutsch/messtechnik-im-online-handel/
	    // messgeraete-fuer-alle-parameter/handtachometer-wachendorff-prozesstechnik-gmbh-laser-handtachometer-pce-155-det_11639.htm

	    icon = bildkonf.getImageIcon("Sensor");
	} catch (IOException e) {

	    e.printStackTrace();
	}
	sensorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	sensorFrame.setSize(200, 200);

	sensorPanel.setLayout(null);
	sensorPanel.setSize(200, 200);
	sensorPanel.setBackground(Color.GRAY);
	aktuelleTemp.setBounds(100, 115, 25, 20);

	listener = new ChangeListener() {

	    @Override
	    public void stateChanged(ChangeEvent event) {
		// Textfeld aktualisieren, wenn sich Schieberegler ändert
		JSlider source = (JSlider) event.getSource();
		aktuelleTemp.setText(String.valueOf(source.getValue()));

		senorR.setTemperatur(source.getValue());

		senorR.printTemp();

		sensorSlider.setMinimum(minimum);
		sensorSlider.setMaximum(maximum);

	    }

	};

	sensorSlider.setPaintTicks(true); // Teilstriche werden angezeigt
	sensorSlider.setMajorTickSpacing(20); // setzt Teilstriche bei
					      // Vielfachen der Einheit
	sensorSlider.setMinorTickSpacing(5); // setzt Teilstriche bei Vielfachen
					     // der Einheit
	sensorSlider.setBounds(0, 101, 100, 50);

	sensorSlider.addChangeListener(listener);

	bildkonf.skalieren(icon);
	sensorLabel.setBounds(0, 0, 100, 100);
	sensorLabel.setIcon(icon);

	sensorPanel.add(aktuelleTemp);
	sensorPanel.add(sensorLabel);
	sensorPanel.add(sensorSlider);
	sensorFrame.add(sensorPanel);
	sensorFrame.setVisible(true);

    }

    /**
     * Bietet die Möglichkeit das private Atribut der Klasse SensorRechnung 
     * aus zu lesen. 
     * @return  die aktuelle Temperatur
     */
    public int getAktuellenWert() {
	return senorR.getTemperatur();
    }

    /**
     * 
     */
    @Override
    public void mouseClicked(MouseEvent e) {

	if (setSliderMinTemp.getText().equals("min"))
	    setSliderMinTemp.setText("");

	if (setSliderMaxTemp.getText().equals("max"))
	    setSliderMaxTemp.setText("");

    }

    @Override
    public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub

    }

}
