package periphgeraete.frequenzabtasten;

/**
 * @author Niklas Knauer
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
    return periodendauer = 1 / frequenz;
  }

  private double berechneOmega() {
  	return 2 * Math.PI * frequenz;
  }

  /**
   * Tastet einen Punkt ab und gibt ihn zurueck.
   *
   * @param zeit
   * @return
   */
  public double calculatePoint(double zeit) {
    return amplitude * Math.sin(Math.toRadians(berechneOmega() * zeit));
  }

  /**
   * Berechnet die Messpunkte anhand der uebergebenen Abtastzeit und gibt diese
   * in einem Array zurueck.
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
