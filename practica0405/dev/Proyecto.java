// package practica0405;
import java.sql.*;
import java.util.*;

public class Proyecto {
    /** Attributes */
    public String codProy;
    public String nombre;
    public String ciudad;
    
    public Proyecto (String vcodProy, String vnombre, String vciudad) {
  	this.codProy = vcodProy;
  	this.nombre = vnombre;
  	this.ciudad = vciudad;
    }
  
    public Proyecto (String vcodProy) {
	this.codProy = vcodProy;
	this.nombre = null;
	this.ciudad = null;
    }

    public void alta () {
	//System.out.println ("Vamos a intentar conectar");
	Conexion c = new Conexion();
	Statement s;
	try {
	    //	System.out.println("Entramos en el try");
	    s = c.conn.createStatement(); 
	    s.executeUpdate ( "insert into Proyectos (codProy, nombre, ciudad) " + "values ('" + this.codProy + "', '" + this.nombre + "', '" +  this.ciudad + "') ");
	    s.close();
	    c.desconexion();
	} catch (SQLException e) {
	    System.err.println ("No puedo dar el alta de" + this.codProy + "en la base de datos debido a: " + e);
	    e.printStackTrace();
	}
    }

    public void baja (){
	Conexion c = new Conexion();
	//	System.out.println ("Vamos a intentar conectar");
	Statement s;
				
	try {
	    //	    System.out.println("Entramos en el try");
	    s = c.conn.createStatement(); 
	    s.executeUpdate ( "delete from Proyectos where codProy='" + this.codProy + "'");
	    s.close();
	    c.desconexion();
	} catch (SQLException e) {
	    System.err.println ("No puedo dar la baja de" + this.codProy + "en la base de datos debido a: " + e);	   
	    e.printStackTrace();
	}
    }

    public Proyecto busqCod (String vcodProy) {
	Conexion c = new Conexion();
	//	System.out.println ("Vamos a intentar conectar");
	Statement s;
	Proyecto proy = new Proyecto(vcodProy);
	try {
	    //	    System.out.println("Entramos en el try");
	    s = c.conn.createStatement();
	    ResultSet fila = s.executeQuery("select * from Proyectos where codProy='" + vcodProy + "'");
	    ResultSet rs = s.getResultSet ();
	    int count = 0;
	    while (rs.next ())
		{
		    proy.nombre = rs.getString ("nombre");
		    proy.ciudad = rs.getString ("ciudad");
		}
	    rs.close ();
	    s.close ();
	    //	    System.out.println (count + " rows were retrieved");
	} catch (SQLException e) {
	    System.err.println ("No puedo buscar por" + vcodProy + "en la base de datos debido a: " + e);	   
	    e.printStackTrace();
	}
	return proy;
    }


}
