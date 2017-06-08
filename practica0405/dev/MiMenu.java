import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.bruceeckel.swing.*;

public class MiMenu extends JApplet {
    private String[] file = {
	"New", "Save", "Exit"
    };
    private String[] edition = {
	"Undo", "Redo", "Cut", "Copy", "Paste", "Search"
    };

    private String[] help = {
	"Index", "Content", "About of ..."
    };

    private JTextField t = new JTextField("Todavia no hay datos", 30);

    private JMenuBar mb = new JMenuBar();
    private JMenu
	f = new JMenu("File"),
	e = new JMenu("Edit"),
	h = new JMenu("Help");
    // Alternative approach:
    private JMenuItem[] mynew = {
	new JMenuItem("Proveedor"),
	new JCheckBoxMenuItem("Pieza"),
	new JCheckBoxMenuItem("Proyecto"),
	new JCheckBoxMenuItem("Relacion"),
    };

    class ExitL implements  ActionListener {
	public void actionPerformed( ActionEvent evt ) {
	    System.exit(0);
	}
    }


    // Alternatively, you can create a different
    // class for each different MenuItem. Then you
    // Don't have to figure out which one it is:
    class FooL implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    t.setText("Foo selected");
	}
    }

    public void init() {

	FooL myfool = new FooL();
	mynew[0].setActionCommand("Proveedor");
	mynew[0].setMnemonic(KeyEvent.VK_P);
	mynew[0].addActionListener(myfool);
	mynew[1].setActionCommand("Proveedor");
	mynew[1].setMnemonic(KeyEvent.VK_P);
	mynew[1].addActionListener(myfool);
	//	f.add(mynew);
	mb.add(f);
	mb.add(e);
	mb.add(h);

	setJMenuBar(mb);
	t.setEditable(false);
	Container cp = getContentPane();
	cp.add(t, BorderLayout.CENTER);
	// Set up the system for swapping menus:
    }
    public static void main(String[] args) {
	Console.run(new MiMenu(), 300, 100);
    }
} ///:~
