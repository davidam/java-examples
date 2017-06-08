import java.io.Serializable;

class Ej12 implements Serializable, Cloneable {
    String nombre;
    String apellidos;
    int edad;

    Ej12(String x, String y, int z) {
	nombre=x;
	apellidos=y;
	edad=z;
    } 

    public Object clone() {
	Ej12 p=null;
	try {
	    p=(Ej12)super.clone();
	    p.nombre=new String(nombre);
	    p.apellidos=new String(apellidos);
	}
	catch(Exception e) {
	}
	return p;
    }
    
}
