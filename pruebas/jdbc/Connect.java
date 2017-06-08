import java.sql.*;

   public class Connect
   {
       public static void main (String[] args)
       {
           Connection conn = null;
           try
           {
               String userName = "darroyo";
               String password = "darroyo";
               String url = "jdbc:mysql://localhost/practica0405";
               Class.forName ("com.mysql.jdbc.Driver").newInstance ();
			   conn = DriverManager.getConnection (url, userName, password);
               System.out.println ("Database connection established");
               
               Statement s = conn.createStatement();
 			   int count;
 			   count = s.executeUpdate ( "insert into Proveedor (codProv, nombre, apellidos, direccion) values ('ASDFVHASBA25', 'Michelin', 'Gordinflas', '13 Rue del Percebe')");
 			   s.close();
 			   System.out.println(count + "rows inserted");
           }
           catch (Exception e)
           {
               System.err.println ("Cannot connect to database server " + e);
           }
           
           finally
           {
               if (conn != null)
               {
                   try
                   {
                       conn.close ();
                       System.out.println ("Database connection terminated");
                   }
                   catch (Exception e) { /* ignore close errors */ }
               }
           }
           		
       }
   }
