public class CuentaEj18 {
    public CuentaEj18() {
    }
    
    public static void main(String[] args) {
	Ej18 actual[] = new Ej18[3];
	actual[0]= new Ej18("Pepe",100);
	actual[1]= new Ej18("Luis",-100);

	for(int i=0;i<2;i++) actual[i].show();
    }
}
