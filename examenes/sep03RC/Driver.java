import java.io.Serializable;

public class Driver {
    public Driver() {
    }
    
    public static void f(Ej12 p) {
	p.edad=50;
	System.out.println(p.edad);
    }

    public static void main(String[] args) {
	Driver driver1 = new Driver();
	Ej12 x= new Ej12("Juan", "Garcia", 20);
	System.out.println(x.edad);
	f((Ej12)x.clone());
	System.out.println(x.edad);
    }
}
