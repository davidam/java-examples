public class ClaseA {

    public int a;

    public ClaseA() {
	System.out.println("Hola ClaseA");
	a=2;
    }

    public void miMetodo() {
	System.out.println("Escribo desde metodo miMetodo");
	{
	    int a = 3;
	    System.out.println("Escribo a:" + a + " y this.a:" + this.a);	    
	}
    }


    public static void main(String arg[]) {

	ClaseA ave = new ClaseA();
	System.out.println("Escribo desde main ave.a:" + ave.a);
	ave.miMetodo();
    }
}

class ClaseB {
    int b;

    public ClaseB() {
	System.out.println("Hola ClaseB");
	b=2;
    }
}
