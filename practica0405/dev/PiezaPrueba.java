
//package practica0405;
import java.util.*;

public class PiezaPrueba {

	public static void main(String arg[]) {
		System.out.println ("Vamos a probar pieza");
		Pieza p = new Pieza ("46ASBA25", "Ruedecilla", 50, "Ruedecilla para ayudar a lo niños a montar en bici");
		p.baja();
		p.alta();
		Pieza pi = p.busqCod("46ASBA25");
		System.out.println ("Vamos a ver los campos de la pieza buscada con busqCod: " + pi.codPieza + " " + pi.nombre + " " + pi.precio + " " + pi.descripcion);
 		ListaPiezas lBusqNom = p.busqNom("nombrecito");
		Iterator e = lBusqNom.iterator();
		while(e.hasNext())
		    {
			Pieza pi1 = (Pieza)e.next();
			System.out.println ("Vamos a ver los campos de la pieza buscada con busqNom: " + pi1.codPieza + " " + pi1.nombre + " " + pi1.precio + " " + pi1.descripcion);
		    }

		System.out.println ("Pieza probadita");

	}
}
