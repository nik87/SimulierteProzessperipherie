package periphgeraete.frequenzabtasten;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JPanel;

class SinusPanel extends JPanel implements Runnable, Stroke {

    private Graphics g;
    private Thread animation;

    private int frequenz;
    private float periodendauer;
    private int amplitude;
    private double omega;
    private int phasenverschiebung;
    private double zeit = 0;
    private double schrittweite = 0.2;
    private int[] x = new int[360];
    // arraygröße abhäng gestalten von der periodendauer
    private float[] y = new float[360];
    private int[] dyachse = new int[360];
    private int[] dxachse = new int[360];
    private int[] sinus = new int[360];
    private int[] periodenLaenge = new int[360];
    private final int PAD = 20;

    /**
     * 
     * @param amplitude
     * @param frequenz
     * @param phasenverschiebung
     */
    public SinusPanel(int amplitude, int frequenz, int phasenverschiebung) {
	this.amplitude = amplitude;
	this.frequenz = frequenz;
	this.phasenverschiebung = phasenverschiebung;
	setPreferredSize(new Dimension(400, 200));
	berechneSinusKurve();
	generiereLabelSinus();
	start();
    }

    /**
     * 
     * @return sinus []
     */
    public int[] getVariertenSinus() {
	return sinus;
    }

    /**
     * 
     * @return
     */
    public float[] getYWert() {
	return y;
    }

    private void berechnePeriodendauer() {
	periodendauer = (1 / frequenz);
    }

    /**
     * 
     * @return omega
     */
    private double berechneOmega() {
	omega = (2 * Math.PI * frequenz);
	periodenLaenge = new int[(int) omega];
	return omega;
    }

    /**
     * 
     */
    private void berechneSinusKurve() {
	for (int i = 0; i < y.length; i++) {
	    y[i] = (float) Math.sin(Math.toRadians(i));
	    System.out.println("y:" + y[i]);
	    // x[i] = i;
	}
    }

    /**
     * 
     */
    private void generiereLabelSinus() {
	for (int i = 0; i < y.length; i++) {
	    dyachse[i] = 150 - (int) (Math.sin(Math.toRadians(i)) * 100);
	    dxachse[i] = i + 21;
	}
    }

    /**
     * 
     */
    private void generiereVariertenSinus() {
	for (int winkel = 0; winkel < sinus.length; winkel++) {
	    System.out.println(winkel);
	    sinus[winkel] = (int) (amplitude * Math.sin(Math
		    .toRadians(berechneOmega() * zeit + phasenverschiebung)));
	    dyachse[winkel] = 150 - (int) (amplitude * Math.sin(Math
		    .toRadians(berechneOmega() * zeit + phasenverschiebung)));
	    System.out.println("varirierter Sinus: " + sinus[winkel]);
	    zeit = zeit + schrittweite;
	    dxachse[winkel] = winkel + 21;
	}

    }

    /**
     * 
     */
    public void start() {
	animation = new Thread(this);
	animation.start();
    }

    /**
     * 
     */
    public void run() {
	while (true) {
	    try {
		Thread.sleep(30);
		// berechneSinusKurve();
		generiereVariertenSinus();
	    } catch (InterruptedException e) {
	    }
	    // repaint();
	}
    }

    @Override
    /**
     * 
     */
    protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	Graphics2D g2 = (Graphics2D) g;
	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
	int w = getWidth();
	int h = getHeight();
	// zeichnen der Ordinate
	g2.draw(new Line2D.Double(PAD, 10, PAD, h - PAD));
	// zeichnen der Abzisse
	g2.draw(new Line2D.Double(PAD, 151, w - 10, 151));

	g.drawLine(390, 151, 380, 145);
	g.drawLine(380, 158, 390, 151);
	g.drawLine(20, 10, 15, 15);
	g.drawLine(20, 10, 24, 15);

	g2.setColor(Color.BLUE);
	g2.setStroke(new BasicStroke(2));
	g2.drawPolyline(dxachse, dyachse, x.length);

    }

    @Override
    public Shape createStrokedShape(Shape arg0) {
	// TODO Auto-generated method stub
	return null;
    }
}
