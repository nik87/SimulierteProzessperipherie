/**
 * 
 */
package periphgeraete.frequenzabtasten;

/**
 * @author Niklas Knauer
 * 
 */
public class FrequenzAbtaster {
    
    private int frequenz;
    private int periodendauer;
    private double amplitude;
    private double omega;
    
    public FrequenzAbtaster(int frequenz, double amplitude) {
	this.frequenz = frequenz;
	this.amplitude = amplitude;
    }

   

    private int berechnePeiodendauer() {
	periodendauer = 1 / frequenz;
	return periodendauer;
    }

    private double berechneOmega() {
	omega = (2 * Math.PI * frequenz);
	return omega;
    }

    /**
     * tastet einen punkt ab und gibt ihn zurück
     * 
     * @param zeit
     * @return
     */
    public double calculatePoint(double zeit) {
	double point = amplitude
		* Math.sin(Math.toRadians(berechneOmega() * zeit));
	return point;
    }

    /**
     * berechnet die Messpunkte anhand der übergebenen Abtaszeit und gibt diese
     * in einem Array zurück.
     * 
     * @param abtastzeit
     * @return
     */
    public double[] calculatePoints(double abtastzeit) {

	double zeit = 0;
	int anzMesspkt = (int) (berechnePeiodendauer() / abtastzeit);
	double[] y = new double[anzMesspkt + 1];
	for (int i = 0; i <= anzMesspkt; i++) {
	    y[i] = amplitude * Math.sin(Math.toRadians(berechneOmega() * zeit));
	    zeit = zeit + abtastzeit;

	}
	return y;

    }
}
