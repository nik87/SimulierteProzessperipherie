/**
 * Die Klasse SensorRechnen dient dazu die aktuelle Temperatur zu holen.
 * @author Niklas Knauer
 *
 */
package periphgeraete.sensor;


public class SensorRechnen {
    private int startwert;
    private int solltemperatur;
    private int temperatur;
    private double wert = 0;
    private String hex;
    private String binaer;
    
    private int intSpinnerAufl;
    private double doubleSpinnerMaxTemp;
    private int intSpinnerBereichsSpannung;
  
    
    public SensorRechnen() {

    }
    /**
     * Setzt den Startwert 
     * @param startwert
     */
    public void setStartwert(int startwert) {
	this.startwert = startwert;

    }
    /**
     * Setzt  die Solltemperatur
     * @param solltemperatur
     */
    public void setSolltemperatur(int solltemperatur) {

	this.solltemperatur = solltemperatur;
    }
    /**
     * Setzt die Temperatur
     * @param temperatur
     */
    protected void setTemperatur(int temperatur) {
	this.temperatur = temperatur;
    }
    /**
     * 
     * @return startwert(int)
     */
    public int getStartwert() {
	return startwert;
    }
    /**
     * 
     * @return solltemperatur (int)
     */
    public int getSolltemperatur() {
	return solltemperatur;
    }
    /**
     * 
     * @return temperatur (int)
     */
    public int getTemperatur() {
	return temperatur;
    }
    /**
     * Stellt den umgerechneten Hex-Wert zur Verf�gung
     * @return hex
     */
    public String getHexwert(){
	return hex;
    }
    /**
     * Stellt den umgerechneten Binaer-Wert zur Verf�gung
     * @return binaer
     */
    public String getBinaerwert(){
	return binaer;
    }

    public void setIntSpinnerAufl(int intSpinnerAufl) {
	this.intSpinnerAufl = intSpinnerAufl;
    }

    public int getIntSpinnerAufl() {
	return intSpinnerAufl;
    }

    public void setDoubleSpinnerMaxTemp(double doubleSpinnerMaxTemp) {
	this.doubleSpinnerMaxTemp = doubleSpinnerMaxTemp;
    }

    public double getDoubleSpinnerMaxTemp() {
	return doubleSpinnerMaxTemp;
    }

    public void setIntSpinnerBereichsSpannung(int intSpinnerBereichsSpannung) {
	this.intSpinnerBereichsSpannung = intSpinnerBereichsSpannung;
    }

    public int getIntSpinnerBereichsSpannung() {
	return intSpinnerBereichsSpannung;
    }
    /**
     * print- Methode
     */
    public void printTemp() {
	System.out.println("Aktuelle Temperatur: " + getTemperatur() + "�C");

    }
    /**
     * Simuliert eine Temperaturerh�hung als Rampenfunktion (linear)
     */
    public void gibTempAus() {

	if (getSolltemperatur() > 0) {

	    System.out.println("Die Messung beginnt.");
	    System.out.println("Startwert: " + startwert + " C");
	    System.out.println("Solltemperatur: " + solltemperatur + " C");
	    System.out.println("=============================");

	    if (solltemperatur > startwert) {
		for (getStartwert(); startwert <= solltemperatur; startwert++) {
		    try {
			Thread.sleep(1000);
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }

		    System.out.println("Die Temperatur betr�gt: " + startwert
			    + " C");

		    if (startwert == solltemperatur) {
			System.out.println("===================");
			System.out.println("Die Temperatur wurde angeglichen.");
			System.out.println("Der Startwert " + startwert + " C"
				+ " ist gleich der Solltemperatur "
				+ solltemperatur + " C\n");
		    }
		}
		startwert = startwert - 1;
	    } else if (solltemperatur < startwert) {
		for (getStartwert(); startwert >= solltemperatur; startwert--) {
		    try {
			Thread.sleep(1000);
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }

		    System.out.println("Die Temperatur betr�gt: " + startwert
			    + " C");

		    if (startwert == solltemperatur) {
			System.out.println("===================");
			System.out.println("Die Temperatur wurde angeglichen.");
			System.out.println("Der Startwert " + startwert + " C"
				+ " ist gleich der Solltemperatur "
				+ solltemperatur + " C");
		    }
		}
	    }
	}
    }
   
    /**
     *  Wandelt das Ergebnis aus berechneAequivalenteSpannung(analogWert, maxTemp)
     *  und berechneKleinsteAufloesung( bereichsSpannung, aufloesung ) in die
     *  Binaeredarstellung und Hexadezimaldarstellung um. Gibt dabei die benaere
     *  Darstellung als String zur�ck.
     * @param analogWert
     * @param aufloesung
     * @param maxTemp
     * @param bereichsSpannung
     * @return binaer
     */
    public void wandleAD(double analogWert, int aufloesung, double maxTemp, int bereichsSpannung){
	
	wert = Math.round(berechneAequivalenteSpannung(analogWert,bereichsSpannung ,maxTemp)
		/berechneKleinsteAufloesung( bereichsSpannung, aufloesung ));
	
	long i = (new Double(wert)).longValue();
	binaer = Long.toBinaryString(i);
	hex = Long.toHexString(i);
	
    }
 
    /**
     * Berechnet die aequivalente Spannung zu der uebergebenen Temperatur
     * @param analogWert
     * @param aufloesung
     * @param maxTemp
     * @return aequivalenteSpannung
     */
    public double berechneAequivalenteSpannung(double analogWert,int bereichsSpannung ,double maxTemp){
	double aequivalenteSpannung;
    	aequivalenteSpannung = (double)((analogWert*bereichsSpannung)/maxTemp);
    	System.out.println("Aequivalentespannung: " + aequivalenteSpannung);
    	
	return aequivalenteSpannung;
    }
    /**
     * Berechnet die kleinst moegliche Aufloesung die abhaengig von der 
     * Bereichspannung und der uebergebenen Aufloesung ist.
     * verstaerkung!!
     * @param bereichsSpannung
     * @param aufloesung
     * @return kleinsteAufloesung
     */
    public double berechneKleinsteAufloesung(double bereichsSpannung, int aufloesung ){
	double kleinsteAufloesung = 0.0;
	switch(aufloesung){
	case 8:
	     kleinsteAufloesung = (double)(bereichsSpannung/256);
	     System.out.println("Bereichsspanung: "+ bereichsSpannung);
	     System.out.println("Kleinst moegliche Aufloesung: "+ 
	     kleinsteAufloesung);
	     break;
	case 10:
	    kleinsteAufloesung = (bereichsSpannung/1024);
	    System.out.println("Bereichsspanung: "+ bereichsSpannung);
	    System.out.println("Kleinst moegliche Aufloesung: "+ 
	    kleinsteAufloesung);
	    break;
	case 12:
	    kleinsteAufloesung = (bereichsSpannung/4096);
	    System.out.println("Bereichsspanung: "+ bereichsSpannung);
	    System.out.println("Kleinst moegliche Aufloesung: "+ 
	    kleinsteAufloesung);
	    break;
	}
	return kleinsteAufloesung;
    }
    /**
     * Gibt die Temperatur in binaerer Darstellung aus
     */
    public void printADbinaer(){
	System.out.println("gewandelter Temperaturwert " + wert);
	System.out.println("gewandelter Temperaturwert in Binaerdarstellung: " + binaer);
	System.out.println("*****************************************************");

    }
    /**
     * Gibt die Temperatur in hexadezimaler Darstellung aus
     */
    public void printADhex(){
	System.out.println("gewandelter Temperaturwert " + wert);
	System.out.println("gewandelter Temperaturwert in Hexdarstellung: " + hex);
	System.out.println("*****************************************************");
    }
   
}
