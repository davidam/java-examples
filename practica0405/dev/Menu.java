//package practica0405;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.*;
import javax.swing.border.*;
import javax.swing.*;

class Busqueda extends JDialog {
  public Busqueda(JFrame parent) {
    super(parent, "Buscar", true);
    Box bv = Box.createVerticalBox();
    JLabel jl1 = new JLabel("Nombre");
    bv.add(jl1);
    bv.add(Box.createVerticalGlue());
    final JTextField jtf1 = new JTextField();
    bv.add(jtf1);
    bv.add(Box.createVerticalGlue());
    final Box bh = Box.createHorizontalBox();
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


public class Menu extends JPanel {
    static final Boolean bT = new Boolean( true );
    static final Boolean bF = new Boolean( false );
    static ButtonGroup grupoBotones;

    private MyDialog dlg = new MyDialog(null);

    // Clase que se utiliza para crear los distintos tipos de menús que se
    // va a presentar en la ventana
    static class TipoMenu {
	TipoMenu( int i ) {}
    };

    static final TipoMenu mi = new TipoMenu( 1 ); // Menú con elementos normales

    ActionListener provAlta = new ActionListener() {
	    public void actionPerformed( ActionEvent evt ) {

		String codProv;
		String nombre;
		String apellidos;
		String direccion;
		Proveedor p = new Proveedor ("N002", "Naik", "MegaFashion", "Guarana");
		p.alta();
	    }
	};


    ActionListener salir = new ActionListener() {
	    public void actionPerformed( ActionEvent evt ) {
		System.exit(0);
	    }
	};


//     private JTextField txt = new JTextField(15);

//     ActionListener provAlta = new ActionListener() {   
// 	    String val = JOptionPane.showInputDialog("How many fingers do you see?");
// 	    txt.setText(val);
// 	};

    ActionListener provBaja = new ActionListener() {
	    public void actionPerformed( ActionEvent evt ) {
		Proveedor p = new Proveedor ("N002", "Naik", "MegaFashion", "Guarana");
		p.baja();
	    }
	};


    ActionListener busqNom = new ActionListener() {
	    public void actionPerformed( ActionEvent evt ) {
		dlg.show();		
	    }
	};



    // En estas estrcuturas se almacenas los datos de los menús como si se
    // tratara de los típicos recursos de X
    public Object menuArchivo[][][] = {
	// Nombre del menú y tecla rápida asociada
	{ "Archivo",new Character('A') },
	{
	// Nombre, tipo, tecla rápida, receptor asociado, habilitado o no
	// para cada uno de los elementos del menú
	{ "Nuevo",mi,new Character('N'),provAlta,bF },
	{ "Guardar",mi,new Character('G'),provBaja,bF },
	{ null }, // Separador
	{ "Salir",mi,new Character('S'),salir,bT },
	}
    };
    public Object menuEdicion[][] = {
	// Nombre del menú y tecla rápida asociada
	{ "Edicion",new Character('E') },
	// Nombre, tipo, tecla rápida, receptor asociado, habilitado o no
	{ "Deshacer",mi,new Character('D'),provAlta,bF },
	{ "Rehacer",mi,new Character('R'),provAlta,bF },
	{ null },
	{ "Cortar",mi,new Character('t'),provAlta,bF },
	{ "Copiar",mi,new Character('C'),provAlta,bF },
	{ "Pegar",mi,new Character('P'),provAlta,bF },
	{ null },
	{ "Buscar",mi,new Character('B'),busqNom,bT }
    };
    public Object menuAyuda[][] = {
	// Nombre del menú y tecla rápida asociada
	{ "Ayuda",new Character('y') },
	// Nombre, tipo, tecla rápida, receptor asociado, habilitado o no
	{ "Indice",mi,new Character('I'),provAlta,bF },
	{ "Contenido",mi,new Character('C'),provAlta,bF },
	{ null }, // Separator
	{ "Acerca de...",mi,new Character('A'),provAlta,bF },
    };
    public Object barraMenu[] = {
	menuArchivo,menuEdicion,menuAyuda,
    };

    static public JMenuBar creaMenuBarra( Object barraMenuDato[] ) {
	JMenuBar barraMenu = new JMenuBar();

	for( int i=0; i < barraMenuDato.length; i++ )
	    barraMenu.add( creaMenu((Object[][])barraMenuDato[i]) );
	return( barraMenu );
    }

    static public JMenu creaMenu( Object[][] menuDato ) {
	JMenu menu = new JMenu();

	menu.setText( (String)menuDato[0][0] );
	menu.setMnemonic( ((Character)menuDato[0][1]).charValue() );
	grupoBotones = new ButtonGroup();
	for( int i=1; i < menuDato.length; i++ ) {
	    if( menuDato[i][0] == null )
		menu.add( new JSeparator() );
	    else
		menu.add( creaMenuItem( menuDato[i] ) );
	}
	return( menu );
    }

    static public JMenuItem creaMenuItem( Object[] dato ) {
	JMenuItem m = null;
	TipoMenu tipo = (TipoMenu)dato[1];

	m = new JMenuItem();
	m.setText( (String)dato[0] );
	m.setMnemonic( ((Character)dato[2]).charValue() );
	m.addActionListener( (ActionListener)dato[3] );
	m.setEnabled( ((Boolean)dato[4]).booleanValue() );
	// Y ahora el caso opcional de los iconos
	if( dato.length == 6 )
	    m.setIcon( (Icon)dato[5] );
	return( m );
    }

    Menu() {
	setLayout( new BorderLayout() );
	add( creaMenuBarra( barraMenu ),BorderLayout.NORTH );
	JPanel p = new JPanel();
	p.setLayout( new BorderLayout() );
	add( p,BorderLayout.CENTER );
    }
}
