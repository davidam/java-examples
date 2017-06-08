













import java.awt.*;
import java.awt.event.*;


/**
 * EjemploAnimacion
 */
public class EjemploAnimacion2 extends java.applet.Applet implements Runnable {
  
    int fps=10;
    int delay;
    int frame=0;
    Thread animator;

    Dimension offDimension;
    Image offImage;
    Graphics offGraphics;


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
	
	long tm = System.currentTimeMillis();
	while (Thread.currentThread() == animator) {
			    
		// pinta la pantalla
		repaint();		    
		// espera delay reales milisegundos
		try {
			tm += delay;
			Thread.sleep(Math.max(0, tm - System.currentTimeMillis()));
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
     * Update a frame of animation.
     */
     public void update(Graphics g) {
        Dimension d = size();

        // Create the offscreen graphics context
        if ((offGraphics == null)
         || (d.width != offDimension.width)
         || (d.height != offDimension.height)) {
            offDimension = d;
            offImage = createImage(d.width, d.height);
            offGraphics = offImage.getGraphics();
        }

        // Erase the previous image
        offGraphics.setColor(getBackground());
        offGraphics.fillRect(0, 0, d.width, d.height);
        offGraphics.setColor(Color.black);

        // Paint the frame into the image
        paintFrame(offGraphics);

        // Paint the image onto the screen
        g.drawImage(offImage, 0, 0, null);
    }

    /**
     * Paint the previous frame (if any).
     */
    public void paint(Graphics g) {
        if (offImage != null) {
            g.drawImage(offImage, 0, 0, null);
        }
    }

    /**
     * Paint a frame of animation.
     */
    public void paintFrame(Graphics g) {
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
      
      EjemploAnimacion2 ej = new EjemploAnimacion2();	  
      ej.init();
      ej.start();
      
      
      f.add("Center", ej);
      f.setSize(new Dimension(500,100));
      f.show();
    	
    }		
	
}


