package p1;
import java.io.*;
class Ejemplo2 {
    public static void main (String args[]){
	try {
	    FileReader fr = new FileReader("prueba.txt");
	} catch (FileNotFoundException e) {
	    System.out.println("Excepcion: fichero no encontrado");
	    return;
	}
	BufferedReader br = new BufferedReader(fr);
	String s;
	try {
	    while ((s=br.readLine())!=null){
		System.out.println(S);
	    }
	    fr.close();
	} catch (IOException e) {
	}
    }
}
