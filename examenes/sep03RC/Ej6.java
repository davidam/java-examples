class Prueba1 {
    int x;
    char y;
}

class Prueba2 extends Prueba1 {
    int z;
    int x=3;
}

public class Ej6 extends Prueba2 {
    public Ej6() {
	int x=5;
    }

    public static void main(String[] args) {
	Prueba1 AAA = new Ej6();
	AAA.x=1;
	System.out.println(AAA.x);
    }
}
