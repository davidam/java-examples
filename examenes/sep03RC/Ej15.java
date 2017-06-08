public class Ej15 {
    static double a=10;
    static {
	int a=100;
	System.out.print(duplica(a)+" ");
    }
    static int duplica(double a) { return (int)(a *=2);}
    public static void main(String[] args) {
	System.out.print(duplica(a)+" ");
    }
}
