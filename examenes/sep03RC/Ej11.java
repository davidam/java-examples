import java.io.*;

public class Ej11 {
    public Ej11() {
    }

    public static void main(String[] args) throws IOException {
	String str;
	String NombreArchivo="";
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	System.out.print("Nombre del archivo: ");
	try {
	    NombreArchivo = br.readLine();
	} catch (IOException ex) {
	}
	int i;
	FileInputStream fin;
	
	try {
	    fin=new FileInputStream(NombreArchivo);

	    do {
		i=fin.read();
		if(i != -1) {
		    System.out.print((char) i);
		}
	    }
	    while (i!=-1);
	}
	catch (FileNotFoundException e) {
	    System.out.println("Archivo no encontrado");
	}
    }
}
