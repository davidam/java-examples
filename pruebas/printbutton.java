import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;

class MyButton extends Button implements Printable {

  public MyButton() {
    super("Aceptar");
  }

  public void paint(Graphics g) {
    Font  f = new Font("Monospaced",Font.PLAIN,12);
    g.setFont (f);

  //Using "g" render anything you want.
  //Get the button's location, width, and height
    int X = (int)this.getLocation().getX();
    int Y = (int)this.getLocation().getY();
    int W = (int)this.getSize().getWidth();
    int H = (int)this.getSize().getHeight();

  //Draw the button shape
    g.drawRect(X, Y, W, H);

  //Draw the button label
  //For simplicity code to center the label inside the
  //button shape is replaced by integer offset values
    g.drawString(this.getLabel(), X+3, Y+15);

  }

  public int print(Graphics g, PageFormat pf, int pi) throws PrinterException {
    if (pi >= 1) {
      return Printable.NO_SUCH_PAGE;
    }
    Graphics2D g2 = (Graphics2D) g;
    g2.translate(pf.getImageableX(), pf.getImageableY());
    g2.setColor(Color.green);
    paint(g2);
    return Printable.PAGE_EXISTS;
  }
}


public class printbutton extends Panel implements ActionListener {

    public printbutton() {
	setBackground(Color.white);
        MyButton b = new MyButton();
        b.addActionListener(this);
        add(b);
    }

    public void actionPerformed(ActionEvent e) {
        PrinterJob printJob = PrinterJob.getPrinterJob();
        printJob.setPrintable((MyButton) e.getSource());
        try { printJob.print(); } catch (Exception PrintException) { }
    }

    public static void main(String s[]) {
	WindowListener l = new WindowAdapter() {
	    public void windowClosing(WindowEvent e) {System.exit(0);}
	};
	Frame f = new Frame("Imprime Botón");
	f.addWindowListener(l);
	f.add("Center", new printbutton());
	f.pack();
	f.setSize(new Dimension(400,300));
	f.show();
    }
}