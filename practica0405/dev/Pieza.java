//package practica0405;
import java.sql.*;

public class Pieza {
    /** Attributes */
    public String codPieza;
    public String nombre;
    public int precio;
    public String descripcion;

    public Pieza (String vcodPieza, String vnombre, int vprecio, String vdescripcion) {
  	this.codPieza = vcodPieza;
  	this.nombre = vnombre;
  	this.precio = vprecio;
  	this.descripcion = vdescripcion;
    }

    public Pieza (String vcodPieza, int vprecio) {
  	this.codPieza = vcodPieza;
  	this.nombre = null;
  	this.precio = vprecio;
  	this.descripcion = null;
    }

    public Pieza (String vcodPieza) {
  	this.codPieza = vcodPieza;
  	this.nombre = null;
  	this.precio = 0;
  	this.descripcion = null;
    }


    public Boolean alta () {
	Conexion c = new Conexion();
	Statement s;
	try {
	    //	System.out.println("Entramos en el try");
	    s = c.conn.createStatement(); 
	    s.executeUpdate ( "insert into Piezas (codPieza, nombre, precio, descripcion) " + "values ('" + this.codPieza + "', '" + this.nombre + "', '" +  this.precio + "', '" + this.descripcion + "') ");
	    s.close();
	    c.desconexion();
	    return true;
	} catch (SQLException e) {
	    System.err.println ("No puedo dar el alta de" + this.codPieza + "en la base de datos debido a: " + e);
	    e.printStackTrace();
	    return false;
	}
    }
 
    public Boolean baja (){
	Conexion c = new Conexion();
	Statement s;
				
	try {
	    //	    System.out.println("Entramos en el try");
	    s = c.conn.createStatement(); 
	    s.executeUpdate ( "delete from Piezas where codPieza='" + this.codPieza + "'");
	    s.close();
	    c.desconexion();
	    return true;
	} catch (SQLException e) {
	    System.err.println ("No puedo dar la baja de" + this.codPieza + "en la base de datos debido a: " + e);	   
	    e.printStackTrace();
	    return false;
	}
    }

    public Pieza busqCod (String vcodPieza) {
	Conexion c = new Conexion();
	//	System.out.println ("Vamos a intentar conectar");
	Statement s;
	Pieza pieza = new Pieza(vcodPieza);
	try {
	    //	    System.out.println("Entramos en el try");
	    s = c.conn.createStatement();
	    ResultSet fila = s.executeQuery("select * from Piezas where codPieza='" + vcodPieza + "'");
	    ResultSet rs = s.getResultSet ();
	    int count = 0;
	    while (rs.next ())
		{
		    pieza.nombre = rs.getString ("nombre");
		    pieza.precio = rs.getInt("precio");
		    pieza.descripcion = rs.getString ("descripcion");
		}
	    rs.close ();
	    s.close ();
	    //	    System.out.println (count + " rows were retrieved");
	} catch (SQLException e) {
	    System.err.println ("No puedo buscar por" + vcodPieza + "en la base de datos debido a: " + e);	   
	    e.printStackTrace();
	}
	return pieza;
    }

    public ListaPiezas busqNom ( String vnombre ){
    
	Conexion c = new Conexion();
	System.out.println ("Vamos a intentar conectar");
	Statement s;
	ListaPiezas list = new ListaPiezas();
	try {
	    //	    System.out.println("Entramos en el try");
	    s = c.conn.createStatement();
	    ResultSet fila = s.executeQuery("select * from Piezas where nombre='" + vnombre + "'");
	    ResultSet rs = s.getResultSet ();
	    int count = 0;
	    while (rs.next ())
		{
		    String vcodPieza = rs.getString("codPieza");
		    String vnombre2 = rs.getString("nombre");
		    int vprecio = rs.getInt("precio");
		    String vdescripcion = rs.getString("descripcion");
		    //		    System.out.println("Se obtienen las variables" + vcodPieza + vapellidos + vdireccion);
		    Pieza pi = new Pieza(vcodPieza, vnombre2, vprecio, vdescripcion);
		    list.add(pi);
		}
	    rs.close ();
	    s.close ();
	    //	    System.out.println (count + " rows were retrieved");
	} catch (SQLException e) {
	    System.err.println ("No puedo buscar por" + vnombre + "en la base de datos debido a: " + e);	   
	    e.printStackTrace();
	}
	return list;
    }

}


