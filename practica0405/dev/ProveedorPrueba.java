//package practica0405;
import java.util.*;

public class ProveedorPrueba {

	public static void main(String arg[]) {
		System.out.println ("Vamos a probar proveedor");
		Proveedor p = new Proveedor ("46ASBA25", "Michelin", "Gordinflas", "13 Rue del Percebe");
		p.baja();
		p.alta();
		Proveedor pr = p.busqCod("46ASBA25");
		System.out.println ("Vamos a ver los campos del proveedor buscado con busqCod: " + pr.codProv + " " + pr.nombre + " " + pr.apellidos + " " + pr.direccion);
		ListaProveedores lBusqNom = p.busqNom("Michelin");
		Iterator e = lBusqNom.iterator();
		while(e.hasNext())
		    {
			Proveedor pr1 = (Proveedor)e.next();
			System.out.println ("proveedor buscado con busqNom:" + pr1.codProv + " " + pr1.nombre + " " + pr1.apellidos + " " + pr1.direccion);
		    }
		ListaProveedores lBusqDir = p.busqDir("mi kely");
		Iterator e2 = lBusqDir.iterator();
		while(e2.hasNext())
		    {
			Proveedor pr2 = (Proveedor)e2.next();
			System.out.println ("proveedor buscado con busqDir:" + pr2.codProv + " " + pr2.nombre + " " + pr2.apellidos + " " + pr2.direccion);
		    }

		ListaProveedores ltodos = p.todos();
		Iterator e3 = ltodos.iterator();
		while(e3.hasNext())
		    {
			Proveedor pr3 = (Proveedor)e3.next();
			System.out.println ("proveedor buscado con todos:" + pr3.codProv + " " + pr3.nombre + " " + pr3.apellidos + " " + pr3.direccion);
		    }

		System.out.println ("Proveedor probadito");
	}
}

