public class ClaseA implements InterfA {

    public void ClaseA() {
	System.out.println("Hola ClaseA");
    }

    public void setPosicion() {
	System.out.println("Escribo desde metodo setPosicion");
    }
    public void miMetodo() {
	System.out.println("Escribo desde metodo miMetodo");
    }

    public void dibujar(int dw) {};

    public static void main(String arg[]) {
	InterfA a;
	a = new ClaseA();
    }
}
