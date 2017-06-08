//package practica0405;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.*;
import javax.swing.border.*;
import javax.swing.*;

class BusquedaProveedor extends JDialog {
  public MyDialog(JFrame parent) {
    super(parent, "Buscar por Nombre", true);
    Box bv = Box.createVerticalBox();
    JLabel jl1 = new JLabel("Nombre del proveedor");
    bv.add(jl1);
    bv.add(Box.createVerticalGlue());
    JTextField jtf1 = new JTextField();
    bv.add(jtf1);
    bv.add(Box.createVerticalGlue());
    Box bh = Box.createHorizontalBox();
    JButton ok = new JButton("Buscar");
    bh.add(ok);
    bh.add(Box.createHorizontalGlue());
    JButton close = new JButton("Cerrar");
    bh.add(close);
    bv.add(bh);
    getContentPane().add(bv);
    ok.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
	  String prueba = jtf1.getSelectedText();
	  JLabel pruebaImp = new JLabel(prueba);
	  bh.add(pruebaImp);
      }
    });
    close.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        dispose(); // Closes the dialog
      }
    });

    setSize(264,93);
  }
}
