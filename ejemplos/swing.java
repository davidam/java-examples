import javax.swing.*;
import java.awt.*;

class Applet1 extends JApplet {
  public void init() {
    getContentPane().add(new JLabel("Applet!"));
  }
} ///:

class swing {
    public static void main (String argumentos[]) {
	Applet1 x = new Applet1();
	x.init();
    }
}
