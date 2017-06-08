

import java.awt.*;
import java.awt.event.*;


/**
 * EjemploAnimacion
 */
public class EjemploAnimacion1 extends java.applet.Applet implements Runnable {
  
    int fps=1000;
    int delay;
    int frame =0;
    Thread animator;

    /**
     * Inicializa el applet y calcula el delay.
     */
	public void init() {
		delay = (1000 / fps);
    }

    /**
     * Este método es llamado cuando el applet es visible en la pantalla
     */
	public void start() {
		animator = new Thread(this);
		animator.start();
    }

   /**
     * Este método es llamado por el el start() del Thread creado.
     *
     */
	public void run() {
	
		while (Thread.currentThread() == animator) {
			    
				// pinta la pantalla
				repaint();		    
			    // espera delay milisegundos
				try {
						Thread.sleep(delay);
			    } catch (InterruptedException e) {
				break;
			    }
				frame++;

		}
	}


    /**
     * Este método es llamado cuando el applet ya no va a está en pantalla
     */
     public void stop() {
		animator = null;
      }

	/**
	* Pinta un frame
	*/
	public void paint(Graphics g) {
	    Dimension d = size();
	    int h = d.height / 2;
	    for (int x = 0 ; x < d.width ; x++) {
	        int y1 = (int)((1.0 + Math.sin((x - frame) * 0.05)) * h);
	        int y2 = (int)((1.0 + Math.sin((x + frame) * 0.07)) * h);
	        g.drawLine(x, y1, x, y2);
	    }
	}	
	
    public static void main(String[] args) {
      Frame f = new Frame("Ejemplo");
      f.addWindowListener(new WindowAdapter() {
          public void windowClosing(WindowEvent e) {
              System.exit(0);
          }
      });
      
      EjemploAnimacion1 ej = new EjemploAnimacion1();	  
      ej.init();
      ej.start();
      
      
      f.add("Center", ej);
      f.setSize(new Dimension(500,100));
      f.show();
    	
    }		
	
}


