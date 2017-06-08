// package practica0405;
import java.util.*;

public class ProyectoPrueba {

	public static void main(String arg[]) {
		System.out.println ("Vamos a probar proyecto");
		Proyecto p = new Proyecto ("37ASZA25", "OurProject", "Maherit");
		p.baja();
		p.alta();
		System.out.println ("Proyecto probadito");
		Proyecto pr = p.busqCod("37ASZA25");
		System.out.println ("Vamos a ver los campos del proyecto buscado con busqCod: " + pr.codProy + " " + pr.nombre + " " + pr.ciudad );

	}
}
