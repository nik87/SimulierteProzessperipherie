/**
 * 
 */
package periphgeraete.motor;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * @author KingLui
 *
 */
public class Motor {
    
    private String linksDrehen;
    private String rechtsDrehen;
    private boolean an = true;
    private boolean aus = false;
    
    JFrame frame = new JFrame ("Motor");    		
    MotorPanel mp = new MotorPanel();
    
    public Motor(){
	
	frame.setLocationRelativeTo(null);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      
	//frame.setBackground(Color.gray);
	frame.add(mp);
	frame.setResizable(false);
	frame.pack();
	frame.setVisible(true);
	      
    }
    
    public  void setDrehrichtungLinks(String linksDrehen){
	this.linksDrehen = linksDrehen;
    }
    public void setDrehrichtungRechts(String rechtsDrehen){
	this.rechtsDrehen = rechtsDrehen;
    }
    public void setAn(boolean an){
	this.an = an;
    }
    public void setAus(boolean aus){
	this.aus = aus;
    }
    public String getDrehrichtungLinks(){
	return linksDrehen;
    }
    public String getDrehrichtungRechts(){
	return rechtsDrehen;
    }
    public boolean getAn(){
	return an;
    }
    public boolean getAus(){
	return aus;
    }
    

}
