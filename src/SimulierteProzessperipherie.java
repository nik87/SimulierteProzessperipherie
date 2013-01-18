import javax.swing.JFrame;

import periphgeraete.frequenzgenerator.Sinus;
import periphgeraete.motor.Motor;
import periphgeraete.sensor.*;


public class SimulierteProzessperipherie {

	public static void main(String[] args) {
		Sensor 					sensor  = new Sensor();
		SensorTeingabe 	sensorT = new SensorTeingabe("Sensor 1");
		SensorSlider 		sensorS = new SensorSlider("Sensor 1 mit Slider");
		SensorADWandler sensorA = new SensorADWandler("textfield");

		Sinus sinus = new Sinus(24,10,0);

		Motor motor = new Motor();

		sensorT.print();
		sensorA.print();
	}
}
