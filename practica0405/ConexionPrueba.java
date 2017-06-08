/*
 * Creado el 18-mar-05
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
package practica0405;
import java.sql.*;
/**
 * @author darroyo
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
public class ConexionPrueba {

	public static void main(String arg[]) {
		Conexion c = new Conexion();
		System.out.println ("Vamos a intentar conectar");
		Statement s;
		try {
			System.out.println("Entramos en el try");
			s = c.conn.createStatement();
			int count = s.executeUpdate ( "insert into Proveedores (codProv, nombre, apellidos, direccion) values ('ASBA25', 'Michelin', 'Gordinflas', '13 Rue del Percebe')");
			ResultSet fila = s.executeQuery("select * from Proveedores;");
			ResultSet rs = s.getResultSet ();
			   count = 0;
			   while (rs.next ())
			   {
				   String vcodProv = rs.getString ("codProv");
				   String vnombre = rs.getString ("nombre");
				   System.out.println (
						   "id = " + vcodProv
						   + ", nombre = " + vnombre);
				   ++count;
			   }
			   rs.close ();
			   s.close ();
			   System.out.println (count + " rows were retrieved");
		    s.close();
			System.out.println(count + "rows inserted");

		} catch (SQLException e) {
			System.err.println ("No puedo conectar a la base de datos debido a: " + e);
			e.printStackTrace();
		}
		c.desconexion();
	}
}
