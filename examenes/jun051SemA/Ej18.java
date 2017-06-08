
class Ej18 {
    String nombre;
    double bal;

    Ej18(String n, double b) {
	nombre=n;
	bal=b;
    }

    void show(){
	if (bal<0)
	    System.out.print("-->");
	System.out.println(nombre + ": &" + bal);
    }
}

