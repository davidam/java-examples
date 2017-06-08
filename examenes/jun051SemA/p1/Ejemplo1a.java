package p1;

public class Ejemplo1a extends Ejemplo1 {
    Ejemplo1a() {
	System.out.print("n = " + n);
	System.out.print("; n_pri = " + n_pri);
	System.out.print("; n_pro = " + n_pro);
	System.out.print("; n_pub = " + n_pub);
    }
    public static void main(String args[]) {
	Ejemplo1a ej1a = new Ejemplo1a();
    }
}
