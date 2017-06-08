//package practica0405;
import java.sql.*;
import java.util.*;

public class Proveedor {
    /** Attributes */
    public String codProv;
    public String nombre;
    public String apellidos;
    public String direccion;
    
    public Proveedor (String vcodProv, String vnombre, String vapellidos, String vdireccion) {
  	this.codProv = vcodProv;
  	this.nombre = vnombre;
  	this.apellidos = vapellidos;
  	this.direccion = vdireccion;
    }
  
    public Proveedor (String vcodProv) {
	this.codProv = vcodProv;
	this.nombre = null;
	this.apellidos = null;
	this.direccion = null;
    }
  
    public Boolean alta () {
	//System.out.println ("Vamos a intentar conectar");
	Conexion c = new Conexion();
	Statement s;
	try {
	    //	System.out.println("Entramos en el try");
	    s = c.conn.createStatement(); 
	    s.executeUpdate ( "insert into Proveedores (codProv, nombre, apellidos, direccion) " + "values ('" + this.codProv + "', '" + this.nombre + "', '" +  this.apellidos + "', '" + this.direccion + "') ");
	    s.close();
	    c.desconexion();
	    return true;
	} catch (SQLException e) {
	    System.err.println ("No puedo dar el alta de" + this.codProv + "en la base de datos debido a: " + e);
	    e.printStackTrace();
	    return false;
	}
    }
    
    public Boolean baja (){
	Conexion c = new Conexion();
	//	System.out.println ("Vamos a intentar conectar");
	Statement s;
				
	try {
	    //  System.out.println("Entramos en el try");
	    s = c.conn.createStatement(); 
	    s.executeUpdate ( "delete from Proveedores where codProv='" + this.codProv + "'");
	    s.close();
	    c.desconexion();
	    return true;
	} catch (SQLException e) {
	    System.err.println ("No puedo dar la baja de" + this.codProv + "en la base de datos debido a: " + e);	   
	    e.printStackTrace();
	    return false;
	}
    }

    public Proveedor busqCod (String vcodProv) {
	Conexion c = new Conexion();
	//	System.out.println ("Vamos a intentar conectar");
	Statement s;
	Proveedor prov = new Proveedor(vcodProv);
	try {
	    //	    System.out.println("Entramos en el try");
	    s = c.conn.createStatement();
	    ResultSet fila = s.executeQuery("select * from Proveedores where codProv='" + vcodProv + "'");
	    ResultSet rs = s.getResultSet ();
	    int count = 0;
	    while (rs.next ())
		{
		    prov.nombre = rs.getString ("nombre");
		    prov.apellidos = rs.getString ("apellidos");
		    prov.direccion = rs.getString ("direccion");
		}
	    rs.close ();
	    s.close ();
	    //	    System.out.println (count + " rows were retrieved");
	} catch (SQLException e) {
	    System.err.println ("No puedo buscar por" + vcodProv + "en la base de datos debido a: " + e);	   
	    e.printStackTrace();
	}
	return prov;
    }

    public ListaProveedores busqNom ( String vnombre ){
    
	Conexion c = new Conexion();
	//	System.out.println ("Vamos a intentar conectar");
	Statement s;
	ListaProveedores list = new ListaProveedores();
	try {
	    //	    System.out.println("Entramos en el try");
	    s = c.conn.createStatement();
	    ResultSet fila = s.executeQuery("select * from Proveedores where nombre='" + vnombre + "'");
	    ResultSet rs = s.getResultSet ();
	    int count = 0;
	    while (rs.next ())
		{
		    String vcodProv = rs.getString("codProv");
		    String vnombre2 = rs.getString("nombre");
		    String vapellidos = rs.getString ("apellidos");
		    String vdireccion = rs.getString ("direccion");
		    //		    System.out.println("Se obtienen las variables" + vcodProv + vapellidos + vdireccion);
		    Proveedor prov = new Proveedor(vcodProv, vnombre2, vapellidos, vdireccion);
		    list.add(prov);
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

    public ListaProveedores busqDir ( String vdireccion ){
    
	Conexion c = new Conexion();
	//	System.out.println ("Vamos a intentar conectar");
	Statement s;
	ListaProveedores list = new ListaProveedores();
	try {
	    //	    System.out.println("Entramos en el try");
	    s = c.conn.createStatement();
	    ResultSet fila = s.executeQuery("select * from Proveedores where direccion='" + vdireccion + "'");
	    ResultSet rs = s.getResultSet ();
	    int count = 0;
	    while (rs.next ())
		{
		    String vcodProv = rs.getString("codProv");
		    String vnombre = rs.getString("nombre");
		    String vapellidos = rs.getString ("apellidos");
		    String vdireccion2 = rs.getString ("direccion");
		    //		    System.out.println("Se obtienen las variables" + vcodProv + vapellidos + vdireccion);
		    Proveedor prov = new Proveedor(vcodProv, vnombre, vapellidos, vdireccion2);
		    list.add(prov);
		}
	    rs.close ();
	    s.close ();
	    //	    System.out.println (count + " rows were retrieved");
	} catch (SQLException e) {
	    System.err.println ("No puedo buscar por" + vdireccion + "en la base de datos debido a: " + e);	   
	    e.printStackTrace();
	}
	return list;
    }

    public ListaProveedores todos(){
    
	Conexion c = new Conexion();
	Statement s;
	ListaProveedores list = new ListaProveedores();
	try {
	    s = c.conn.createStatement();
	    ResultSet fila = s.executeQuery("select * from Proveedores");
	    ResultSet rs = s.getResultSet ();
	    while (rs.next ())
		{
		    String vcodProv = rs.getString("codProv");
		    String vnombre = rs.getString("nombre");
		    String vapellidos = rs.getString ("apellidos");
		    String vdireccion = rs.getString ("direccion");
		    Proveedor prov = new Proveedor(vcodProv, vnombre, vapellidos, vdireccion);
		    list.add(prov);
		}
	    rs.close ();
	    s.close ();
	} catch (SQLException e) {
	    System.err.println ("No puedo buscar proveedores en la base de datos debido a: " + e);	   
	    e.printStackTrace();
	}
	return list;
    }

}
