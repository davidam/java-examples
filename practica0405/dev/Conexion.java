
// package practica0405;
import java.sql.*;

class Conexion {
    Connection conn;
    public Conexion () {
	this.conn = null;
	try
	    {
		//		System.out.println ("Vamos a intentar conectar");			  	
		String userName = "darroyo";
		String password = "darroyo";
		String url = "jdbc:mysql://localhost/practica0405";
		Class.forName ("com.mysql.jdbc.Driver").newInstance ();
		this.conn = DriverManager.getConnection (url, userName, password);
		//		System.out.println ("Conexion a la base de datos hecha");  
	    }
	catch (Exception e)
	    {
		System.err.println ("No puedo conectar a la base de datos debido a: " + e);				
	    }			
    }

    public void desconexion() {
	if (this.conn != null)
	    {
		try
		    {
			//			System.out.println ("Vamos a intentar desconectar");
			conn.close ();
			//			System.out.println ("Conexion a la base de datos finalizada");
		    }
		catch (Exception e) { /* ignore close errors */ }
	    }
    }
	
}


