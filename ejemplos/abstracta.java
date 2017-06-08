//Caso de Uso de abstract

abstract class Animal {
    String nombre;
    int patas;
    public Animal (String n, int p) {
	nombre=n;
	patas=p;
    }
    abstract void habla();
    //método abstracto q debe redefinirse
}

class Perro extends Animal {
    String raza;
    public Perro(String n, int p, String r) {
	super(n,p);
	raza=r;
    }
//     public void habla() {
// 	System.out.
// println("Me llamo "+nombre+": GUA, GUAU");
// 	System.out.println("mi raza es "+raza);
//     }
}

class Gallo extends Animal {
    public Gallo (String n, int p) {
	super(n,p);
    }
    public void habla() {
	System.out.println("Soy un Gallo. Me llamo "+nombre);
	System.out.println("kikirikiiii");
    }
}

class Abstracta {
    public static void main(String argumentos[]) {
	Perro toby = new Perro ("Toby",4,"San Bernardo");
	Gallo kiko = new Gallo ("Kiko",2);
	kiko.habla();
	System.out.println();
	toby.habla();
    }
}
