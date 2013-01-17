import javax.swing.JFrame;
import periphgeraete.frequenzabtasten.Sinus;
import periphgeraete.motor.Motor;
import periphgeraete.sensor.*;


public class SimulierteProzessperipherie {

	public static void main(String[] args) {
		SensorTeingabe st = new SensorTeingabe();
		// SensorJFrame sj = new SensorJFrame();
		SensorSlider sl = new SensorSlider();
		SensorADWandler sw = new SensorADWandler("textfield");
		Sinus s = new Sinus(24,10,0);
		Motor m = new Motor();

		st.print();
		sw.print();
	}
}
