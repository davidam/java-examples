
public class Bomba {
    boolean encendida;
    public void cuentaAtras(int n) {
        encendida = true;
        while (n > 0) {
            System.out.println("Estamos en el segundo " + n);
            n--;
        }
        System.out.println("Booooooooooommmmmmmm");
    }
}

