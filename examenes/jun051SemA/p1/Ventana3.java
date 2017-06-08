import javax.swing.*;
import java.awt.*;
public class Ventana3 extends JFrame {
    public Ventana3(String texto) {
	super("Ventana con letrero");
	setBounds(100,100,300,300);
	getContentPane().setBackground(Color.white);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	JLabel letrero = new JLabel(texto);
	getContentPane().add(letrero);
	setVisible(true);
    }
    public static void main(String args[]) {
	Ventana3 v = new Ventana3 ("TEXTO");
    }
}
