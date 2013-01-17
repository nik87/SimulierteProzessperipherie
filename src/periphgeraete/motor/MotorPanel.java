/**
 * 
 */
package periphgeraete.motor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

import javax.swing.JPanel;

import werkzeug.BildImplementierung;

/**
 * @author KingLui
 * 
 */
public class MotorPanel extends JPanel implements Runnable {
    private Thread animation;
    private Image arImg[];
    private int index;
    private MediaTracker mt;
    private Toolkit tk;

    public MotorPanel() {
	setPreferredSize(new Dimension(400, 300));
	setSize(200, 150);
	setVisible(true);
	startAnimation();
    }

    public void startAnimation() {
	animation = new Thread(this);
	animation.start();
    }

    public void run() {
	// Bilder laden
	arImg = new Image[2];
	mt = new MediaTracker(this);
	tk = getToolkit();
	for (int i = 0; i < 2; i++) {
	    arImg[i] = tk.getImage("/bilder/propeller" + i + ".gif");
	    mt.addImage(arImg[i], 1);
	   
	    System.out.println("armIMg" + arImg[i]);
	    try {

		mt.waitForID(1);
	    } catch (InterruptedException e) {
		// nothing
		System.out.println("Fehler beim warten");
	    }
	}
	// Animation beginnen
	Thread me = Thread.currentThread();
	while (animation == me) {
	    try {
		Thread.sleep(50);
	    } catch (InterruptedException e) {
		break;
	    }
	    synchronized (this) {
		index++;
		System.out.println("index: " + index);
		if (index >= arImg.length) {
		    index = 0;
		    System.out.println("index 0: " + index);
		}
	    }
	    repaint();
	}
    }

    public void paint(Graphics g) {
	
	if ((mt.statusAll(false) &MediaTracker.ERRORED) != 0) {
	    g.setColor(Color.RED);
	    g.fillRect(0, 0, getWidth(), getHeight());
	    return;
	}
	if (mt.statusID(1, true) == MediaTracker.COMPLETE) {
	    g.drawImage(arImg[index], 10, 10, this);
	}

    }
}
