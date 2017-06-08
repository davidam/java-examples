//package practica0405;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuPrueba {
    public static void main(String args[]) {
	Menu panel = new Menu();
	JFrame ventana = new JFrame();

	ventana.getContentPane().add( panel,BorderLayout.CENTER );

	ventana.addWindowListener( new WindowAdapter() {
		public void windowClosing( WindowEvent evt ) {
		    System.exit( 0 );
		}
	    } );

	ventana.setSize( 400,300 );
	ventana.setTitle( "Práctica 2004-2005" );
	ventana.setVisible( true );
    }
}
