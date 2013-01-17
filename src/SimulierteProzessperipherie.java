import javax.swing.JFrame;

import periphgeraete.frequenzgenerator.Sinus;
import periphgeraete.motor.Motor;
import periphgeraete.sensor.*;


public class SimulierteProzessperipherie {

	public static void main(String[] args) {
		SensorTeingabe st = new SensorTeingabe("Sensor 1");
		// SensorJFrame sj = new SensorJFrame();
		SensorSlider sl = new SensorSlider("Sensor 1 mit Slider");
		SensorADWandler sw = new SensorADWandler("textfield");
		Sinus s = new Sinus(24,10,0);
		Motor m = new Motor();

		st.print();
		sw.print();
	}
}
