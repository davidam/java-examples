public class Cuadrado {
    double longitudLado;
    Cuadrado(double L) { longitudLado=L; }
    public String toString() { return "longitud del lado " + longitudLado; }
}

public class Ejemplo6 {
    public static void main (String[] args) {
	Cuadrado c1 = new Cuadrado(2);
	String s = "Dimension: "+c1;
	System.out.println(s);
    }
}
